package org.spoon.lib.category;

import org.jsoup.nodes.Element;
import org.spoon.lib.model.BookCategory;

import java.util.List;

public interface ParserBookList {

    /**
     * 도서관 입력된 정보를 토대로 도서관 정보를 추출합니다.
     * @return {@code Element} 형식으로 변경하여 추출합니다.
     */
    Element extractBookDocument();

    /**
     * {@code Element}형식으로 된 정보에서 책 정보만 추출합니다.
     * @return 정보를 추출하여 반환합니다.
     */
    List<BookCategory> getBookList();

    /**
     *  {@code Element}형식으로 된 정보에서 책 정보만 추출합니다.
     * @param element 추출대상의 {@code Element}입력받습니다.
     * @return 정보를 추출하여 반환합니다.
     */
    List<BookCategory> getBookList(Element element);

}
