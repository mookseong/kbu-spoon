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
        Elements elements = Objects.requireNonNull(element).select("ul > li > a");
        return toArrayList(elements);
    }

    @Override
    public List<BookCategory> getBookList(Element element) {
        Element tmpElement = element.getElementById(this.documentType.getBookType());
        Elements elements = Objects.requireNonNull(tmpElement).select("ul > li > a");
        return toArrayList(elements);
    }

    private ArrayList<BookCategory> toArrayList(Elements elements){
        ArrayList<BookCategory> arrayList = new ArrayList<>();
        elements.forEach(i ->
                arrayList.add(
                        new BookCategory(
                                i.attr("href"), //상세 정보를 이동하는 주소 등록
                                i.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                                i.getElementsByTag("span").text() // 책 제목
                        )
                )
        );
        return arrayList;
    }

}
