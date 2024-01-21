package at.fhtw.swen3.paperless.controller;

import at.fhtw.swen3.paperless.services.dto.CreateDocumentType200Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentTypesApiControllerTest {

    @Test
    void testNoArgsConstructor() {
        CreateDocumentType200Response response = new CreateDocumentType200Response();
        assertNotNull(response);
        assertNull(response.getId());
        assertNull(response.getSlug());
        assertNull(response.getName());
        assertNull(response.getMatch());
        assertNull(response.getMatchingAlgorithm());
        assertNull(response.getIsInsensitive());
        assertNull(response.getOwner());
        assertNull(response.getUserCanChange());
    }

    @Test
    void testParameterizedConstructor() {
        CreateDocumentType200Response response = new CreateDocumentType200Response(1, "test-slug", "Test Name", "Test Match", 2, true, 3, true);
        assertEquals(1, response.getId());
        assertEquals("test-slug", response.getSlug());
        assertEquals("Test Name", response.getName());
        assertEquals("Test Match", response.getMatch());
        assertEquals(2, response.getMatchingAlgorithm());
        Assertions.assertTrue(response.getIsInsensitive());
        assertEquals(3, response.getOwner());
        Assertions.assertTrue(response.getUserCanChange());
    }

    @Test
    void testEqualsAndHashCode() {
        CreateDocumentType200Response response1 = new CreateDocumentType200Response(1, "test-slug", "Test Name", "Test Match", 2, true, 3, true);
        CreateDocumentType200Response response2 = new CreateDocumentType200Response(1, "test-slug", "Test Name", "Test Match", 2, true, 3, true);
        CreateDocumentType200Response response3 = new CreateDocumentType200Response(2, "test-slug2", "Test Name2", "Test Match2", 3, false, 4, false);

        // Test equality of response1 and response2
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());

        // Test inequality with response3
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        CreateDocumentType200Response response = new CreateDocumentType200Response(1, "test-slug", "Test Name", "Test Match", 2, true, 3, true);
        String expectedToString = "class CreateDocumentType200Response {\n" +
                "    id: 1\n" +
                "    slug: test-slug\n" +
                "    name: Test Name\n" +
                "    match: Test Match\n" +
                "    matchingAlgorithm: 2\n" +
                "    isInsensitive: true\n" +
                "    owner: 3\n" +
                "    userCanChange: true\n" +
                "}";
        assertEquals(expectedToString, response.toString());
    }

    /*
    @Test
    void testToIndentedString() {
        CreateDocumentType200Response response = new CreateDocumentType200Response(1, "test-slug", "Test Name", "Test Match", 2, true, 3, true);

        String indentedString = response.toIndentedString(response);

        String expectedIndentedString = "class CreateDocumentType200Response {\n" +
                "    id: 1\n" +
                "    slug: test-slug\n" +
                "    name: Test Name\n" +
                "    match: Test Match\n" +
                "    matchingAlgorithm: 2\n" +
                "    isInsensitive: true\n" +
                "    owner: 3\n" +
                "    userCanChange: true\n" +
                "}";

        assertEquals(expectedIndentedString, indentedString);
    }
    */
}
