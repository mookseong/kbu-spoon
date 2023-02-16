package org.spoon.lib.category;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookCategoryType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParserRentBookCategory extends BaseParserCategory implements ParserBookList {
    public ParserRentBookCategory() {
        try {
            String searchURL = "https://lib.bible.ac.kr/Search";
            Connection conn = Jsoup.connect(searchURL);
            this.document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
        this.documentType = BookCategoryType.RECENT_RENT_BOOK;
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
