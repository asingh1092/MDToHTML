package com.singh.backend.mdtohtml.model;

import java.util.HashMap;
import java.util.Map;

public class MarkdownValues {

    public enum Type {
        NODE,
        HEADER,
        LINK_TEXT,
        COMMON
    }

    public static final Map<Character, Type> markdownMap = new HashMap<>();

    static {
        markdownMap.put('#', Type.HEADER);
    }
}
