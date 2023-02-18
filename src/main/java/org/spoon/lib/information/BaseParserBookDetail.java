package org.spoon.lib.information;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public abstract class BaseParserBookDetail {
    protected Document document;
    protected Elements bookDetail;
    protected final String baseElementsClass = "col-md-10 detail-table-right";

    public void setParsingURL(String parsingURL) {
        try {
            Connection conn = Jsoup.connect(parsingURL);
            this.document = conn.get();
            this.bookDetail = this.document.getElementsByClass(this.baseElementsClass);
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    protected Map<String, String> getKbuBookInformation() {
        Map<String, String> toBookInfoList = new HashMap<>();
        List<String> bookInfoTitle = new ArrayList<>(Arrays.asList("자료유형", "청구기호", "ISBN", "DDC", "서명/저자", "가격", "발행사항"));

        bookDetail.select("dl")
                .stream()
                .filter(element -> bookInfoTitle.stream().anyMatch(Predicate.isEqual(element.select("dt").text())))
                .forEach(element -> {
                    if (element.select("dt").text().trim().equals("ISBN") && toBookInfoList.containsKey("ISBN")) {
                        return;
                    }
                    toBookInfoList.put(element.select("dt").text().trim(), element.select("dd").text().trim());
                });
        toBookInfoList.put("저자", toBookInfoList.get("서명/저자").split("/")[1]);
        toBookInfoList.put("ISBN", toBookInfoList.get("ISBN").split(" ")[0]);
        toBookInfoList.remove("서명/저자");
        return toBookInfoList;
    }

    protected Elements getRelatedList() {
        return document.getElementsByClass("sponge-detail-book").get(0).select("div:nth-child(1) > div > ul > li > a");
    }

    protected BookCategory toBookCategory(Element element) {
        return new BookCategory(
                element.attr("href"),
                element.getElementsByTag("img").attr("src"),
                element.attr("title")
        );
    }

    public List<BookCategory> getRelatedBook() {
        ArrayList<BookCategory> bookCategories = new ArrayList<>();
        for (Element element : getRelatedList()) {
            bookCategories.add(toBookCategory(element));
        }
        return bookCategories;
    }
}
