package com.singh.backend.mdtohtml.controllers;

import java.util.concurrent.CompletableFuture;

public interface MdToHtmlController {

    CompletableFuture<String> convertToHTML(String content);
}
