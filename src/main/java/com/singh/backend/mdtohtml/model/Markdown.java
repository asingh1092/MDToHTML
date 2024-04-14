package com.singh.backend.mdtohtml.model;

import java.util.*;
public class Markdown {

    /*
       go line by line, character by character 6
       TREE NOT A LIST
       have a hashmap of beginning chars?
       start from beginning char and create Node child object (Header, paragraph, etc)
       go through each char and see if there is an embedded Node child object (like link text or bold, etc)
       // TODO - how to handle stuff not implemented i.e bold or <script>
       // TODO - how to handle a large body request
       //

     */

    private final List<Node> nodes;
    private final String content;

    public Markdown(String content) {
        this.content = content;
        this.nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public String getContent() {
        return content;
    }
}
