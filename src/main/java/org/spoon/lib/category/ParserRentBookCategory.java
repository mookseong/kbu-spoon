package org.spoon.lib.category;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookCategoryType;

import java.util.List;
import java.util.Objects;

public class ParserRentBookCategory extends BaseParserCategory implements ParserBookList {
    public ParserRentBookCategory() {
        super("https://lib.bible.ac.kr/Search", BookCategoryType.RECENT_RENT_BOOK);
    }

    @Override
    public Element extractBookDocument() {
        return this.document;
    }

    @Override
    public List<BookCategory> getBookList() {
        Elements element = extractBookDocument().getElementsByClass(this.documentType.getClassType());
        Elements elements = Objects.requireNonNull(element).select(this.bookQuery);
        return toArrayList(elements);
    }

    @Override
    public List<BookCategory> getBookList(Element element) {
        Elements tmpElement = element.getElementsByClass(this.documentType.getClassType());
        Elements elements = Objects.requireNonNull(tmpElement).select(this.bookQuery);
        return toArrayList(elements);
    }
}
