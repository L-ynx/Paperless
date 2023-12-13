package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Correspondent;
import at.fhtw.swen3.persistence.entity.DocTag;
import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.entity.DocumentType;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.CorrespondentRepository;
import at.fhtw.swen3.persistence.repository.DocTagRepository;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import at.fhtw.swen3.persistence.repository.DocumentTypeRepository;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private CorrespondentRepository correspondentRepository;

    @Mock
    private DocTagRepository docTagRepository;

    @Mock
    private DatabaseMapper mapper;

    @Mock
    private DocumentTypeService documentTypeService;

    @Mock
    private CorrespondentService correspondentService;

    @Mock
    private DocTagService docTagService;

    @InjectMocks
    private DocumentServiceImpl documentService;

    public DocumentServiceTest() {

    }

    @BeforeEach // JUnit 5, for JUnit 4 use @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //@Test
    public void testCreateDocument() {
        // Arrange
        String title = "Test Document";
        OffsetDateTime created = OffsetDateTime.now();
        Integer documentType = 1;
        List<Integer> tags = new ArrayList<>();
        Integer correspondent = 2;
        List<MultipartFile> documents = new ArrayList<>();

        // Creating a mock DocumentType
        DocumentType mockDocumentType = mock(DocumentType.class);
        when(mockDocumentType.getId()).thenReturn(1L);
        when(mockDocumentType.getName()).thenReturn("Some Document Type");
        when(mockDocumentType.getMatchingAlgorithm()).thenReturn(12345L);
        when(mockDocumentType.isInsensitive()).thenReturn(true);

        // Creating a mock Correspondent
        Correspondent mockCorrespondent = mock(Correspondent.class);
        when(mockCorrespondent.getId()).thenReturn(2L);
        when(mockCorrespondent.getMatchingAlgorithm()).thenReturn(54321L);
        when(mockCorrespondent.isInsensitive()).thenReturn(false);
        when(mockCorrespondent.getDocumentCount()).thenReturn(10L);
        when(mockCorrespondent.getLastCorrespondence()).thenReturn(OffsetDateTime.now());

        // Creating a mock DocTag
        DocTag mockDocTag = mock(DocTag.class);
        when(mockDocTag.getId()).thenReturn(1L);
        when(mockDocTag.getSlug()).thenReturn("some-slug");
        when(mockDocTag.getName()).thenReturn("Some Tag");
        when(mockDocTag.getColor()).thenReturn("#FFFFFF");
        when(mockDocTag.getMatch()).thenReturn("some-match");
        when(mockDocTag.getMatchingAlgorithm()).thenReturn(12345L);
        when(mockDocTag.isInsensitive()).thenReturn(true);
        when(mockDocTag.isInboxTag()).thenReturn(true);
        when(mockDocTag.getDocumentCount()).thenReturn(5L);

        // Creating a mock DocumentDTO
        DocumentDTO mockDocumentDTO = mock(DocumentDTO.class);
        when(mockDocumentDTO.getId()).thenReturn(1L);
        when(mockDocumentDTO.getTitle()).thenReturn("Test Document Title");
        when(mockDocumentDTO.getContent()).thenReturn("Sample content");
        when(mockDocumentDTO.getCreatedAt()).thenReturn(LocalDateTime.now());
        when(mockDocumentDTO.getCorrespondent()).thenReturn(mockCorrespondent); // Assuming mockCorrespondent is already defined
        when(mockDocumentDTO.getDocumentType()).thenReturn(mockDocumentType); // Assuming mockDocumentType is already defined
        when(mockDocumentDTO.getDocTags()).thenReturn(Arrays.asList(mockDocTag)); // Assuming mockDocTag is already defined


        // Mock the repository and mapper behavior
        when(documentTypeRepository.findById(any())).thenReturn(Optional.of(mockDocumentType));
        when(correspondentRepository.findById(any())).thenReturn(Optional.of(mockCorrespondent));
        when(docTagRepository.findAllById(any())).thenReturn(Arrays.asList(mockDocTag));
        when(mapper.toDTOs(anyList())).thenReturn(Arrays.asList(mockDocumentDTO));

        // Act
        //documentService.create(title, created, documentType, tags, correspondent, documents);

        // Verify interactions with mock objects
        // verify(documentTypeRepository).findById(Long.valueOf(documentType)); // Verify findById is called with specific documentType
        // verify(correspondentRepository).findById(Long.valueOf(correspondent)); // Verify findById is called with specific correspondent
        // verify(docTagRepository).findAllById(Long.valueOf(tags)); // Verify findAllById is called with specific tags
        verify(documentRepository).save(any(Document.class)); // Verify save is called on documentRepository


        // Verify that repository.save was called
        verify(documentRepository, times(1)).save(any(Document.class));
    }
}