package org.spoon.lib.category;

import org.jsoup.nodes.Element;
import org.spoon.lib.model.BookCategoryType;


public class ParserBestBookCategory extends BaseParserCategory implements ParserBookList {

    public ParserBestBookCategory() {
        super("https://lib.bible.ac.kr", BookCategoryType.BEST_BOOK);
    }

    @Override
    public Element extractBookDocument() {
        return this.document.getElementsByClass(documentType.getClassType()).get(0);
    }
}