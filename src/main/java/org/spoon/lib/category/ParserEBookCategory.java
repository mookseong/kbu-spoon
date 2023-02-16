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

public class ParserEBookCategory extends BaseParserCategory implements ParserBookList {

    public ParserEBookCategory() {
        try {
            Connection conn = Jsoup.connect(homeURL);
            this.document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
        this.documentType = BookCategoryType.EBOOK_BOOK;
    }

    @Override
    public Element extractBookDocument() {
        return this.document.getElementsByClass(this.documentType.getClassType()).get(0);
    }

    @Override
    public List<BookCategory> getBookList() {
        Element element = extractBookDocument().getElementById(this.documentType.getBookType());
        Elements elements = Objects.requireNonNull(element).select(this.bookQuery);
        return toArrayList(elements);
    }

    @Override
    public List<BookCategory> getBookList(Element element) {
        Element tmpElement = element.getElementById(this.documentType.getBookType());
        Elements elements = Objects.requireNonNull(tmpElement).select(this.bookQuery);
        return toArrayList(elements);
    }

}
