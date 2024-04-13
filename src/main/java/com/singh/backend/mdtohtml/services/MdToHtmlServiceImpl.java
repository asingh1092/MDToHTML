package com.singh.backend.mdtohtml.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MdToHtmlServiceImpl implements MdToHtmlService {

    private static final Logger logger = LoggerFactory.getLogger(MdToHtmlServiceImpl.class);

    @Override
    public CompletableFuture<String> help() {
        String help = "Hello! This is a service to convert markdown to html. This service will use the following rules:\n" +
                "  1. headers (h1, h2, h3, etc) with link text potentially\n" +
                "  2. paragraphs with link text pontetially\n" +
                "  3. Handle new lines by either ending a paragraph after a blank new line\n" +
                "     or continuing the paragraph if text continues on next line\n\n" +
                " usage: \n" +
                "GET <domain>/markdown/\n" +
                "POST <domain>/markdown/convert";
        return CompletableFuture.completedFuture(help);

    }

    @Override
    public CompletableFuture<String> convert(String content) {
        return CompletableFuture.completedFuture(mdToHtml(content));
    }

    /**
     * Converts md content to html looking at a few rules.
     *      1. headers (h1, h2, ..., h6) with link text potentially
     *      2. paragraphs with link text potentially
     *      3. Handle new lines by either ending a paragraph after a blank new line
     *         or continuing the paragraph if text continues on next line
     *
     * @param content content in plain text
     * @return String of formatted html
     */
    private String mdToHtml(String content) {
        logger.debug(content);
        StringBuilder sb = new StringBuilder();
        String[] lines = content.split("\n");
        boolean inParagraph = false;

        for (String line : lines) {
            logger.debug(line);
            if (line.trim().isEmpty()) {
                // handle if empty line or if end of paragraph
                if (inParagraph) {
                    sb.append("</p>");
                    inParagraph = false;
                }
            } else if (line.startsWith("#")) {
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
                    // handle if link text in header
                    sb.append("<h").append(headerLevel).append(">")
                            .append(handleWithLinkText(line.substring(headerLevel + 1)))
                            .append("</h").append(headerLevel).append(">");
                }

            } else {
                // handle text for paragraphs
                if (!inParagraph) {
                    inParagraph = true;
                    sb.append("<p>");
                } else {
                    // TODO Talk about case where we need a break to add to next line
                    sb.append("<br>");
                }
                // handle if link text in paragraph
                sb.append(handleWithLinkText(line));
            }
        }
        if (inParagraph) {
            sb.append("</p>");
        }
        logger.debug(String.valueOf(sb));
        return sb.toString();
    }

    /**
     * Handle text that has any link text.

     * This method will match link url, link text, and remaining text, and subsequently build
     * the String out using StringBuilder.
     *
     * @param line the line to handle
     * @return conversion of line as a String
     */
    private String handleWithLinkText(String line) {
        // Replace links and regular text within the same line
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]\\(([^\\)]+)\\)|([^\\[]+)");
        Matcher matcher = pattern.matcher(line);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String linkText = matcher.group(1);
            String linkUrl = matcher.group(2);
            String text = matcher.group(3);

            logger.debug("Linktext= " + linkText + "| LinkUrl= " + linkUrl + "| Text= " + text);
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
