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
import java.util.List;
import java.util.Objects;

public abstract class BaseParserCategory {
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
    abstract Element extractBookDocument();

    public List<BookCategory> getBookList() {
        Elements element = extractBookDocument().getElementsByClass(this.documentType.getClassType());
        Elements elements = Objects.requireNonNull(element).select(this.documentType.getBookType());
        return toArrayList(elements);
    }

    public List<BookCategory> getBookList(Element element) {
        Elements tmpElement = element.getElementsByClass(this.documentType.getClassType());
        Elements elements = Objects.requireNonNull(tmpElement).select(this.documentType.getBookType());
        return toArrayList(elements);
    }

    protected ArrayList<BookCategory> toArrayList(Elements elements) {
        ArrayList<BookCategory> arrayList = new ArrayList<>();
        for (Element element : elements) {
            arrayList.add(toBookLocation(element));
        }
        return arrayList;
    }

    protected BookCategory toBookLocation(Element element) {
        return new BookCategory(
                element.attr("href"), //상세 정보를 이동하는 주소 등록
                element.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                element.getElementsByTag("span").text()
        );
    }
}