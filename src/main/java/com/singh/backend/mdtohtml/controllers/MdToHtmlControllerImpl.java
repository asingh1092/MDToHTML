package com.singh.backend.mdtohtml.controllers;

import com.singh.backend.mdtohtml.services.MdToHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class MdToHtmlControllerImpl implements MdToHtmlController {

    private final MdToHtmlService mdToHtmlService;

    @Autowired
    public MdToHtmlControllerImpl(MdToHtmlService mdToHtmlService) {
        this.mdToHtmlService = mdToHtmlService;
    }

    @GetMapping(value = "/")
    public CompletableFuture<String> hello() {
        return CompletableFuture.completedFuture("Hello!");
    }

    @Override
    @PostMapping(value = "/convert", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public CompletableFuture<String> convertToHTML(@RequestBody String content) {
        return mdToHtmlService.convert(content);
    }
}
