package com.singh.backend.mdtohtml.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownConverter {

    private StringBuilder sb;

    // getter setter
    private boolean inParagraph;
    private MarkdownConverter() {
        // private no arg constructor
    }

    public MarkdownConverter(String content) {
        sb = new StringBuilder();
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

    public void handleEmptyLine(String line) {
        // handle if empty line or if end of paragraph
        if (inParagraph) {
            sb.append("</p>");
            inParagraph = false;
        }
    }

    public void handleHeader(String line) {
        int headerLevel = 0;
        while (line.charAt(headerLevel) == '#') {
            if (headerLevel > 6) {
                headerLevel = -1;
                break;
            }
            headerLevel++;
        }
        if (headerLevel == -1) {
            // special case where # of # is > 6
            if (!inParagraph) {
                inParagraph = true;
                sb.append("<p>");
            }
            // handle if link text in paragraph
            sb.append(handleWithLinkText(line));
        } else {
            // another edge case
            if (inParagraph) {
                sb.append("</p>");
                inParagraph = false;
            }
            // handle if link text in header
            sb.append("<h").append(headerLevel).append(">")
                    .append(handleWithLinkText(line.substring(headerLevel + 1)))
                    .append("</h").append(headerLevel).append(">");
        }
    }

    public void handleParagraph(String line) {
        // handle text for paragraphs
        if (!inParagraph) {
            inParagraph = true;
            sb.append("<p>");
        } else {
            sb.append("<br>");
        }
        // handle if link text in paragraph
        sb.append(handleWithLinkText(line));
    }

    private String handleWithLinkText(String line) {
        // Replace links and regular text within the same line
        Pattern pattern = Pattern.compile("\\[([^]]+)]\\(([^)]+)\\)|([^\\[]+)");
        Matcher matcher = pattern.matcher(line);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String linkText = matcher.group(1);
            String linkUrl = matcher.group(2);
            String text = matcher.group(3);

            if (linkText != null) {
                matcher.appendReplacement(sb, "<a href=\"" + linkUrl + "\">" + linkText + "</a>");
            } else {
                matcher.appendReplacement(sb, text);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public void handleDanglingParagraph() {
        if (inParagraph) {
            sb.append("</p>");
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
