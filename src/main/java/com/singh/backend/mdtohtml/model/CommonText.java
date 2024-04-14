package com.singh.backend.mdtohtml.model;

public class CommonText implements Node {

    private final MarkdownValues.Type type = MarkdownValues.Type.COMMON;
    private final String content;

    public CommonText(String content) {
        this.content = content;
    }

    public MarkdownValues.Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
