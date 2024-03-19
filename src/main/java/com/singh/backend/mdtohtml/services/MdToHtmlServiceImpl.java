package com.singh.backend.mdtohtml.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MdToHtmlServiceImpl implements MdToHtmlService {

    @Override
    @Async
    public CompletableFuture<String> convert(String content) {
        return CompletableFuture.completedFuture(mdToHtml(content));
    }

    private String mdToHtml(String markdownContent) {
        StringBuilder sb = new StringBuilder();
        String[] lines = markdownContent.split("\n");
        boolean inParagraph = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                if (inParagraph) {
                    sb.append("</p>");
                    inParagraph = false;
                }
            }
            else if (line.startsWith("#")) {
                int headerLevel = 0;
                while (line.charAt(headerLevel) == '#') {
                    headerLevel++;
                }
                sb.append("<h").append(headerLevel).append(">")
                        .append(convertLineToHtml(line.substring(headerLevel + 1)))
                        .append("</h").append(headerLevel).append(">");
            }
            else {
                // handle text
                if (!inParagraph) {
                    inParagraph = true;
                    sb.append("<p>");
                }
                // handle if link text in paragraph as well
                sb.append(convertLineToHtml(line));
            }
        }
        if (inParagraph) {
            sb.append("</p>");
        }
        return sb.toString();
    }

    private String convertLineToHtml(String line) {
        // Replace links and regular text within the same line
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]\\(([^\\)]+)\\)|([^\\[]+)");
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
}
