package org.spoon.lib;

import org.jsoup.nodes.Document;

public abstract class BaseParser {
    protected String homeURL = "https://lib.bible.ac.kr";
    protected String searchURL = "https://lib.bible.ac.kr/Search";
    protected Document document;
}
