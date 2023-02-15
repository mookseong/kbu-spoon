package org.spoon.lib.category;

import org.jsoup.nodes.Document;
import org.spoon.lib.model.BookCategoryType;

public abstract class BaseParserCategory {
    protected String homeURL = "https://lib.bible.ac.kr";
    protected String searchURL = "https://lib.bible.ac.kr/Search";
    protected BookCategoryType documentType;
    protected Document document;

}
