package org.spoon.lib.category;

import org.jsoup.nodes.Element;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookCategoryType;


public class ParserRentBookCategory extends BaseParserCategory implements ParserBookList {
    public ParserRentBookCategory() {
        super("https://lib.bible.ac.kr/Search", BookCategoryType.RECENT_RENT_BOOK);
    }

    @Override
    public Element extractBookDocument() {
        return this.document;
    }

    @Override
    protected BookCategory toBookLocation(Element element) {
        return new BookCategory(
                element.attr("href"), //상세 정보를 이동하는 주소 등록
                element.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                element.attr("title")
        );
    }
}
