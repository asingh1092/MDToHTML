package com.singh.backend.mdtohtml.model;

public class LinkText implements Node {

    private final MarkdownValues.Type type = MarkdownValues.Type.LINK_TEXT;
    private final String content;

    public LinkText(String content) {
        this.content = content;
    }

    public MarkdownValues.Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
