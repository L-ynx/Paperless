import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.PaperlessServicesApplication;
import at.fhtw.swen3.paperless.config.ElasticSearchConfig;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import jakarta.servlet.ServletContext;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={OpenApiGeneratorApplication.class, PaperlessServicesApplication.class, ElasticSearchConfig.class})
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class PaperlessIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DocumentRepository documentRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesHomeController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertInstanceOf(MockServletContext.class, servletContext);
        assertNotNull(webApplicationContext.getBean("homeController"));
    }

    @Test
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
    public void postAndGetDocumentsIntegrationTest() throws Exception {
        this.mockMvc.perform(post("/api/documents/post_document/")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                //TODO: maybe add file to request
                .andExpect(status().is2xxSuccessful());

        assertEquals(1, documentRepository.findAll().get(0).getId());
    }

}