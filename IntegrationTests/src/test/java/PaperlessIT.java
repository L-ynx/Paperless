import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.PaperlessServicesApplication;
import at.fhtw.swen3.paperless.config.ElasticSearchConfig;
import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import jakarta.servlet.ServletContext;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OpenApiGeneratorApplication.class, PaperlessServicesApplication.class, ElasticSearchConfig.class})
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaperlessIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DatabaseMapper mapper;

    private static MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void givenWac_whenServletContext_thenItProvidesHomeController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertInstanceOf(MockServletContext.class, servletContext);
        assertNotNull(webApplicationContext.getBean("homeController"));
    }

    @Test
    @Order(2)
    public void getUiSettings() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/ui_settings/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        JSONObject resultBody = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("PaperlessProjectUser", resultBody.get("display_name"));
        assertEquals("username", resultBody.getJSONObject("user").getString("username"));
    }

    @Test
    @Order(3)
    public void postAndGetDocumentsIntegrationTest() throws Exception {
        MockMultipartFile file = getDocument();

        this.mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/api/documents/post_document/")
                                .file(file)
                                .param("title", "Test Document")
                                .param("created", "2024-01-22T12:34:56Z")
                                .param("document_type", "1")
                                .param("tags", "1,2,3")
                                .param("correspondent", "1")
                )
                .andExpect(status().is2xxSuccessful());

        assertEquals(1, documentRepository.findAll().get(0).getId());
    }

    @Test
    @Order(4)
    public void getAndUpdateDocumentIntegrationTest() throws Exception {
        MvcResult document = this.mockMvc.perform(get("/api/documents/1/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        JSONObject documentBody = new JSONObject(document.getResponse().getContentAsString());
        documentBody.put("title", "Updated Title");
        documentBody.put("content", "Test-Content");

        this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/documents/1/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(documentBody.toString())
                )
                .andExpect(status().is2xxSuccessful());

        Optional<Document> updatedDocumentOpt = documentRepository.findById(1L);
        assertTrue(updatedDocumentOpt.isPresent());
        assertEquals("Updated Title", updatedDocumentOpt.get().getTitle());

    }

    @Test
    @Order(5)
    public void deleteDocument() throws Exception {
        // Delete the document
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/documents/1/")
                )
                .andExpect(status().is2xxSuccessful());

        // Verify that the document has been deleted
        Optional<Document> deletedDocumentOpt = documentRepository.findById(1L);
        assertFalse(deletedDocumentOpt.isPresent());
    }

    //@Test
    @Order(6)
    public void searchDocumentsIntegrationTest() throws Exception {
        String searchQuery = "TestPDF.pdf";

        MvcResult result = this.mockMvc.perform(get("/api/documents/")
                        .param("query", searchQuery))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        JSONObject resultBody = new JSONObject(result.getResponse().getContentAsString());

        assertFalse(resultBody.getJSONArray("results").isEmpty());
    }

    @NotNull
    private static MockMultipartFile getDocument() throws IOException {
        FileInputStream input = null;

        try {
            input = new FileInputStream("src/test/resources/TestPDF.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new MockMultipartFile(
                "document",
                "TestPDF.pdf",
                "application/pdf",
                input);
    }
}