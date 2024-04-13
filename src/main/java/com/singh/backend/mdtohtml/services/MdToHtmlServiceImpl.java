package com.singh.backend.mdtohtml.services;

import com.singh.backend.mdtohtml.model.MarkdownConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MdToHtmlServiceImpl implements MdToHtmlService {

    private static final Logger logger = LoggerFactory.getLogger(MdToHtmlServiceImpl.class);

    @Override
    public CompletableFuture<String> help() {
        String help = "Hello! This is a service to convert markdown to html. This service will use the following rules:\n" +
                "  1. headers (h1, h2, h3, etc) with link text potentially\n" +
                "  2. paragraphs with link text pontetially\n" +
                "  3. Handle new lines by either ending a paragraph after a blank new line\n" +
                "     or continuing the paragraph if text continues on next line\n\n" +
                " usage: \n" +
                "GET <domain>/markdown/\n" +
                "POST <domain>/markdown/convert";
        return CompletableFuture.completedFuture(help);

    }

    @Override
    public CompletableFuture<String> convert(String content) {
        MarkdownConverter markdownConverter = new MarkdownConverter(content);
        return CompletableFuture.completedFuture(markdownConverter.toString());
    }
}
