//package at.fhtw.swen3.persistence.service;
//
//import at.fhtw.swen3.persistence.entity.Document;
//import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
//import at.fhtw.swen3.persistence.repository.CorrespondentRepository;
//import at.fhtw.swen3.persistence.repository.DocTagRepository;
//import at.fhtw.swen3.persistence.repository.DocumentRepository;
//import at.fhtw.swen3.persistence.repository.DocumentTypeRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//public class DocumentServiceImplTest {
//
//    @Mock
//    private DocumentRepository documentRepository;
//
//    @Mock
//    private DocumentTypeRepository documentTypeRepository;
//
//    @Mock
//    private CorrespondentRepository correspondentRepository;
//
//    @Mock
//    private DocTagRepository docTagRepository;
//
//    @Mock
//    private DatabaseMapper mapper;
//
//    @InjectMocks
//    private DocumentServiceImpl documentService;
//
//    public DocumentServiceImplTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateDocument() {
//        // Arrange
//        String title = "Test Document";
//        OffsetDateTime created = OffsetDateTime.now();
//        Integer documentType = 1;
//        List<Integer> tags = new ArrayList<>();
//        Integer correspondent = 2;
//        List<MultipartFile> documents = new ArrayList<>();
//
//        // Mock the repository and mapper behavior
//        when(documentTypeRepository.findById(any())).thenReturn(Optional.of(/* mock DocumentType */));
//        when(correspondentRepository.findById(any())).thenReturn(Optional.of(/* mock Correspondent */));
//        when(docTagRepository.findAllById(any())).thenReturn(Arrays.asList(/* mock DocTag */));
//        when(mapper.toDTOs(anyList())).thenReturn(Arrays.asList(/* mock DocumentDTO */));
//
//        // Act
//        documentService.create(title, created, documentType, tags, correspondent, documents);
//
//        // Assert
//        // You can add assertions or additional verifications based on your service logic
//
//        // Verify that repository.save was called
//        verify(documentRepository, times(1)).save(any(Document.class));
//    }
//}
