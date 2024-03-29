package com.singh.backend.mdtohtml.controllers;

import com.singh.backend.mdtohtml.services.MdToHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

// TODO Could have added prefix to service to be more restful
@RestController
@RequestMapping("/markdown")
public class MdToHtmlControllerImpl implements MdToHtmlController {

    private final MdToHtmlService mdToHtmlService;

    @Autowired
    public MdToHtmlControllerImpl(MdToHtmlService mdToHtmlService) {
        this.mdToHtmlService = mdToHtmlService;
    }

    // TODO Talk about using base url as a "help or usage"
    @GetMapping(value = "/")
    public String help() {
        return "Hello! This is a service to convert markdown to html. This service will use the following rules:\n" +
                "  1. headers (h1, h2, h3, etc) with link text potentially\n" +
                "  2. paragraphs with link text pontetially\n" +
                "  3. Handle new lines by either ending a paragraph after a blank new line\n" +
                "     or continuing the paragraph if text continues on next line\n\n" +
                " usage: \n" +
                "GET <domain>/markdown/\n" +
                "POST <domain>/markdown/convert";
    }

    /**
     * Post mapping to convert a request with plain text body of formatted markdown to html.
     *
     * @param content text in markdown format
     * @return CompletableFuture String of text in converting html
     */
    @Override
    // TODO Talk about CORS
    @CrossOrigin
    @PostMapping(value = "/convert", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public CompletableFuture<String> convertToHTML(@RequestBody String content) {
        return mdToHtmlService.convert(content);
    }
}
