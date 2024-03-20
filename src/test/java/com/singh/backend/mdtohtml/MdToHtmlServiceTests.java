package com.singh.backend.mdtohtml;

import com.singh.backend.mdtohtml.controllers.MdToHtmlController;
import com.singh.backend.mdtohtml.services.MdToHtmlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class MdToHtmlServiceTests {
    @Autowired
    private MdToHtmlService mdToHtmlService;

    @Test
    public void testConvertMarkdownToHtml_EmptyContent() throws ExecutionException, InterruptedException {
        // Given
        String markdown = "";
        String expectedHtml = "";

        // When
        String actualHtml = mdToHtmlService.convert(markdown).get();

        // Then
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testConvertMarkdownToHtml_special() throws ExecutionException, InterruptedException {
        // Given
        String markdown = "############### what";
        String expectedHtml = "<p>############### what</p>";

        // When
        String actualHtml = mdToHtmlService.convert(markdown).get();

        // Then
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testConvertMarkdownToHtml() throws ExecutionException, InterruptedException {
        // Given
        String markdown = "# Hello\nThis is Markdown";
        String expectedHtml = "<h1>Hello</h1><p>This is Markdown</p>";

        // When
        String actualHtml = mdToHtmlService.convert(markdown).get();

        // Then
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testConvertMarkdownToHtml_test1() throws ExecutionException, InterruptedException {
        // Given
        String markdown = "# Sample Document\n" +
                "\n" +
                "Hello!\n" +
                "\n" +
                "This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.";
        String expectedHtml = "<h1>Sample Document" +
                "</h1>" +
                "<p>Hello!" +
                "</p>" +
                "<p>This is sample markdown for the <a href=\"https://www.mailchimp.com\">Mailchimp</a> homework assignment.</p>";

        // When
        String actualHtml = mdToHtmlService.convert(markdown).get();

        // Then
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testConvertMarkdownToHtml_test2() throws ExecutionException, InterruptedException {
        // Given
        String markdown = "# Header one\n" +
                "\n" +
                "Hello there\n" +
                "\n" +
                "How are you?\n" +
                "What's going on?\n" +
                "\n" +
                "## Another Header\n" +
                "\n" +
                "This is a paragraph [with an inline link](http://google.com). Neat, eh?\n" +
                "\n" +
                "## This is a header [with a link](http://yahoo.com)";
        String expectedHtml = "<h1>Header one" +
                "</h1><p>Hello there" +
                "</p><p>How are you?" +
                "What's going on?" +
                "</p><h2>Another Header" +
                "</h2><p>This is a paragraph <a href=\"http://google.com\">with an inline link</a>. Neat, eh?" +
                "</p><h2>This is a header <a href=\"http://yahoo.com\">with a link</a></h2>";

        // When
        String actualHtml = mdToHtmlService.convert(markdown).get();

        // Then
        assertEquals(expectedHtml, actualHtml);
    }

}
