package at.fhtw.swen3.persistence.entity;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DocumentValidationTest {
    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenAllFieldsAreValid_thenNoConstraintViolations() {
        Document document = new Document();
        document.setId(1L);
        document.setFilesize(1024L);
        document.setContent("Sample content");
        document.setTitle("Sample title");
        document.setOriginalName("original_name.pdf");
        document.setChecksum("12345");
        document.setMimeType("application/pdf");
        document.setStoragePath("/path/to/storage");
        document.setOwner("user123");
        document.setCreatedAt(LocalDateTime.now());

        assertTrue(validator.validate(document).isEmpty());
    }

    @Test
    public void whenTitleIsNull_thenOneConstraintViolation() {
        Document document = new Document();
        document.setTitle(null);
        // Set other fields to valid values

        assertFalse(validator.validate(document).isEmpty());
    }
}
