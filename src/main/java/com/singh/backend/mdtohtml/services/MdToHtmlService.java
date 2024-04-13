package com.singh.backend.mdtohtml.services;

import java.util.concurrent.CompletableFuture;

public interface MdToHtmlService {

    CompletableFuture<String> help();
    CompletableFuture<String> convert(String content);
}
