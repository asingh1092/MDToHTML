package com.singh.backend.mdtohtml.services;

import java.util.concurrent.CompletableFuture;

public interface MdToHtmlService {

    CompletableFuture<String> convert(String content);
}
