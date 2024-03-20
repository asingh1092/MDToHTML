package com.singh.backend.mdtohtml;

import com.singh.backend.mdtohtml.controllers.MdToHtmlController;
import com.singh.backend.mdtohtml.controllers.MdToHtmlControllerImpl;
import com.singh.backend.mdtohtml.services.MdToHtmlService;
import com.singh.backend.mdtohtml.services.MdToHtmlServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MdToHtmlControllerTests {
    @Mock
    private MdToHtmlService mdToHtmlService = new MdToHtmlServiceImpl();

    @InjectMocks
    private MdToHtmlController mdToHtmlController = new MdToHtmlControllerImpl(mdToHtmlService);

    @Test
    public void testConvertMarkdownToHtml() throws ExecutionException, InterruptedException {
        // Given
        String markdown = "# Hello";
        String expectedHtml = "<h1>Hello</h1>";
        // when(mdToHtmlService.convert(markdown)).thenReturn(CompletableFuture.completedFuture(expectedHtml));

        // When
        String actualHtml = mdToHtmlController.convertToHTML(markdown).get();

        // Then
        assertEquals(expectedHtml, actualHtml);
    }

}
