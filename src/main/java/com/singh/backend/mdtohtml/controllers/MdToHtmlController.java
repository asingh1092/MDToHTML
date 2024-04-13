package com.singh.backend.mdtohtml.controllers;

import com.singh.backend.mdtohtml.services.MdToHtmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@CrossOrigin
@RestController
@RequestMapping("/markdown")
public class MdToHtmlController {

    private static final Logger logger = LoggerFactory.getLogger(MdToHtmlController.class);
    private final MdToHtmlService mdToHtmlService;

    @Autowired
    public MdToHtmlController(MdToHtmlService mdToHtmlService) {
        this.mdToHtmlService = mdToHtmlService;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> help() {
        logger.debug("Calling: /markdown/");
        try {
            Future<String> future = mdToHtmlService.help();
            return ResponseEntity.ok(future.get());
        } catch (InterruptedException | ExecutionException e) {
            logger.error(String.format("Error: %s", e.getLocalizedMessage()));
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Post mapping to convert a request with plain text body of formatted markdown to html.
     *
     * @param content text in markdown format
     * @return CompletableFuture String of text in converting html
     */
    @PostMapping(value = "/convert", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convert(@RequestBody String content) {
        logger.debug("Calling: /markdown/convert");
        try {
            Future<String> future = mdToHtmlService.convert(content);
            return ResponseEntity.ok(future.get());
        }  catch (InterruptedException | ExecutionException e) {
            logger.error(String.format("Error: %s", e.getLocalizedMessage()));
            return ResponseEntity.internalServerError().build();
        }
    }

}
