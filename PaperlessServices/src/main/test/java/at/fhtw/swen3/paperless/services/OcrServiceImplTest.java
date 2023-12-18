package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.services.OcrServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OcrServiceImplTest {


    @Test
    void testExecuteOCROnValidImage(@TempDir Path tempDir) throws Exception {
        String tessData = getResourcePath("Tesseract/eng.traineddata");
        System.setProperty("TESSDATA_PREFIX", tessData);

        OcrServiceImpl ocrService = new OcrServiceImpl(tessData);
        File testImage = createTestImageWithText(tempDir, "Expected Text");

        String result = ocrService.executeOCR(testImage);

        assertEquals("Expected Text\n", result);
    }

    @NotNull
    private File createTestImageWithText(Path tempDir, String text) throws IOException {
        // Create a buffered image with a white background
        BufferedImage image = new BufferedImage(200, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        // Add the text in black
        graphics.setPaint(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = (image.getWidth() - fontMetrics.stringWidth(text)) / 2;
        int y = ((image.getHeight() - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
        graphics.drawString(text, x, y);
        graphics.dispose();

        // Save the image to a file in the temporary directory
        File imageFile = new File(tempDir.toFile(), "testImage.png");
        ImageIO.write(image, "png", imageFile);

        return imageFile;
    }

    private String getResourcePath(String resourceName) {
        try {
            Path resourcePath = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
            return resourcePath.getParent().toAbsolutePath().toString();
        } catch (Exception e) {
            throw new RuntimeException("Error getting resource path: " + resourceName, e);
        }
    }

    @Test
    void testExecuteOCROnEmptyImage(@TempDir Path tempDir) throws Exception {
        String tessData = getResourcePath("Tesseract/eng.traineddata");
        System.setProperty("TESSDATA_PREFIX", tessData);

        OcrServiceImpl ocrService = new OcrServiceImpl(tessData);
        File emptyImage = createEmptyImage(tempDir);

        String result = ocrService.executeOCR(emptyImage);

        assertEquals("", result); // oder prüfen Sie auf eine spezifische Rückmeldung für leere Bilder
    }

    private File createEmptyImage(Path tempDir) throws IOException {
        BufferedImage image = new BufferedImage(200, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.dispose();

        File imageFile = new File(tempDir.toFile(), "emptyImage.png");
        ImageIO.write(image, "png", imageFile);

        return imageFile;
    }

    /*
    @Test
    void testExecuteOCROnValidPdf(@TempDir Path tempDir) throws Exception {
        String tessData = getResourcePath("Tesseract/eng.traineddata");
        System.setProperty("TESSDATA_PREFIX", tessData);

        OcrServiceImpl ocrService = new OcrServiceImpl(tessData);
        File testPdf = createTestPdfWithText(tempDir, "Expected PDF Text");

        String result = ocrService.executeOCR(testPdf);

        assertEquals("Expected PDF Text", result);
    }

    private File createTestPdfWithText(Path tempDir, String text) throws IOException {
        File pdfFile = new File(tempDir.toFile(), "testPdf.pdf");
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12); // Achten Sie auf die korrekte Schreibweise
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();

            doc.save(pdfFile);
        }
        return pdfFile;
    }

    @Test
    void testExecuteOCROnPdfConvertedToImage(@TempDir Path tempDir) throws Exception {
        // Set up the OCR Service
        String tessData = getResourcePath("Tesseract/eng.traineddata");
        System.setProperty("TESSDATA_PREFIX", tessData);
        OcrServiceImpl ocrService = new OcrServiceImpl(tessData);

        // Create a PDF with text
        File testPdf = createTestPdfWithText(tempDir, "Expected PDF Text");

        // Convert PDF to image using Ghostscript
        File outputImage = new File(tempDir.toFile(), "output.png");
        executeGhostscriptCommand(testPdf.getAbsolutePath(), outputImage.getAbsolutePath());

        // Perform OCR on the generated image
        String result = ocrService.executeOCR(outputImage);

        // Assert the result
        assertEquals("Expected PDF Text", result.trim());
    }

    private void executeGhostscriptCommand(String inputPdfPath, String outputImagePath) throws IOException, InterruptedException {
        String command = String.format(
                "gs -dNOPAUSE -dBATCH -sDEVICE=pngalpha -sOutputFile=%s %s",
                outputImagePath,
                inputPdfPath
        );

        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();

        // Optionally, capture the output of the command for logging or debugging
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }*/
}