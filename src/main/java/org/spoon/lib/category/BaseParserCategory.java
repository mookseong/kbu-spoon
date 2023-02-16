package org.spoon.lib.category;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookCategoryType;

import java.util.ArrayList;

public abstract class BaseParserCategory {
    protected final String homeURL = "https://lib.bible.ac.kr";
    protected final String bookQuery = "ul > li > a";
    protected BookCategoryType documentType;
    protected Document document;

    protected ArrayList<BookCategory> toArrayList(Elements elements) {
        ArrayList<BookCategory> arrayList = new ArrayList<>();
        for (Element element : elements) {
            arrayList.add(toBookLocation(element));
        }
        return arrayList;
    }

    /**
     * 책 위치별 BookCategory 형식으로 만들어줍니다.
     *
     * @param element 책 정보를 입력 받습니다.
     * @return Location 따라 BookCategory 형식으로 만들어 반환합니다.
     */
    private BookCategory toBookLocation(Element element) {
        String titleByLocation = null;
        if (documentType == BookCategoryType.RECENT_RENT_BOOK) {
            titleByLocation = element.attr("title"); // 책 제목
        } else {
            titleByLocation = element.getElementsByTag("span").text();
        }
        return new BookCategory(
                element.attr("href"), //상세 정보를 이동하는 주소 등록
                element.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                titleByLocation
        );
    }

}
