package org.spoon.lib.category;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookCategoryType;

import java.io.IOException;
import java.util.ArrayList;

public abstract class BaseParserCategory {
    protected final String bookQuery = "ul > li > a";
    protected BookCategoryType documentType;
    protected Document document;

    public BaseParserCategory(String url, BookCategoryType model) {
        try {
            Connection conn = Jsoup.connect(url);
            this.document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
        this.documentType = model;
    }

    protected ArrayList<BookCategory> toArrayList(Elements elements) {
        ArrayList<BookCategory> arrayList = new ArrayList<>();
        for (Element element : elements) {
            arrayList.add(toBookLocation(element));
        }
        return arrayList;
    }

    private BookCategory toBookLocation(Element element) {
        String titleByLocation = null;
        if (documentType == BookCategoryType.RECENT_RENT_BOOK) {
            titleByLocation = element.attr("title"); // 책 제목
        }
        if (documentType != BookCategoryType.RECENT_RENT_BOOK){
            titleByLocation = element.getElementsByTag("span").text();
        }
        return new BookCategory(
                element.attr("href"), //상세 정보를 이동하는 주소 등록
                element.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                titleByLocation
        );
    }
}