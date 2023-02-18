package org.spoon.lib.category;

import org.jsoup.nodes.Element;
import org.spoon.lib.model.BookCategoryType;


public class ParserEBookCategory extends BaseParserCategory implements ParserBookList {


    public ParserEBookCategory() {
        super("https://lib.bible.ac.kr", BookCategoryType.EBOOK_BOOK);
    }

    @Override
    public Element extractBookDocument() {
        return this.document.getElementsByClass(this.documentType.getClassType()).get(0);
    }

}