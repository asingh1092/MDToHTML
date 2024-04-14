package com.singh.backend.mdtohtml.model;

public class MarkdownConverter {

    public MarkdownConverter(String content) {
        String[] lines = content.split("\n");

        for (String line : lines) {
            
            if (line.trim().isEmpty()) {
                handleEmptyLine(line);
            } else if (line.startsWith("#")) {
                handleHeader(line);
            } else {
                handleParagraph(line);
            }
        }
        handleDanglingParagraph();
    }

}
