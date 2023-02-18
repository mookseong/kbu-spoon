package org.spoon.lib.category;

import org.jsoup.nodes.Element;
import org.spoon.lib.model.BookCategoryType;


public class ParserNewBookCategory extends BaseParserCategory implements ParserBookList {

    public ParserNewBookCategory() {
        super("https://lib.bible.ac.kr", BookCategoryType.NEW_BOOK);
    }

    @Override
    public Element extractBookDocument() {
        return this.document.getElementsByClass(this.documentType.getClassType()).get(0);
    }
}