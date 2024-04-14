package com.singh.backend.mdtohtml.model;

public class Header implements Node {

    private final MarkdownValues.Type type = MarkdownValues.Type.HEADER;
    private final String content;

    public Header(String content) {
        this.content = content;
    }

    public MarkdownValues.Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
