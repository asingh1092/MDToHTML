package com.singh.backend.mdtohtml;

import com.singh.backend.mdtohtml.services.MdToHtmlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MdtohtmlApplicationTests {

	@Autowired
	private MdToHtmlService mdToHtmlService;
	@Mock
	private CompletableFuture<String> completableFutureMock;

	@Test
	public void testConvertMarkdownToHtml_EmptyContent() throws ExecutionException, InterruptedException {
		String content = "";
		String expected = "";
		completableFutureMock =  mdToHtmlService.convert(content);
		String actual = completableFutureMock.get();
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertMarkdownToHtml_test1() throws ExecutionException, InterruptedException {
		String content = "# Sample Document\n" +
				"\n" +
				"Hello!\n" +
				"\n" +
				"This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.";
		String expected = "<h1>Sample Document" +
				"</h1>" +
				"<p>Hello!" +
				"</p>" +
				"<p>This is sample markdown for the <a href=\"https://www.mailchimp.com\">Mailchimp</a> homework assignment.</p>";
		completableFutureMock =  mdToHtmlService.convert(content);
		String actual = completableFutureMock.get();
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertMarkdownToHtml_test2() throws ExecutionException, InterruptedException {
		String content = "# Header one\n" +
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
		String expected = "<h1>Header one" +
				"</h1><p>Hello there" +
				"</p><p>How are you?" +
				"What's going on?" +
				"</p><h2>Another Header" +
				"</h2><p>This is a paragraph <a href=\"http://google.com\">with an inline link</a>. Neat, eh?" +
				"</p><h2>This is a header <a href=\"http://yahoo.com\">with a link</a></h2>";
		completableFutureMock =  mdToHtmlService.convert(content);
		String actual = completableFutureMock.get();
		assertEquals(expected, actual);
	}
}
