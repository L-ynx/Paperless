package at.fhtw.swen3.paperless.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ApiUtilTest {

    @Mock
    private NativeWebRequest nativeWebRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetExampleResponse() throws IOException {
        // Arrange
        String contentType = "application/json";
        String example = "{\"key\": \"value\"}";
        when(nativeWebRequest.getNativeResponse(HttpServletResponse.class)).thenReturn(httpServletResponse);
        PrintWriter writer = mock(PrintWriter.class);
        when(httpServletResponse.getWriter()).thenReturn(writer);

        // Act
        ApiUtil.setExampleResponse(nativeWebRequest, contentType, example);

        // Assert
        verify(httpServletResponse).setCharacterEncoding("UTF-8");
        verify(httpServletResponse).addHeader("Content-Type", contentType);
        verify(writer).print(example);
    }

    @Test
    void testSetExampleResponseIOException() throws IOException {
        // Arrange
        String contentType = "application/json";
        String example = "{\"key\": \"value\"}";
        when(nativeWebRequest.getNativeResponse(HttpServletResponse.class)).thenReturn(httpServletResponse);
        when(httpServletResponse.getWriter()).thenThrow(new IOException("IO error"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> ApiUtil.setExampleResponse(nativeWebRequest, contentType, example));
    }
}
