package org.spoon.lib.information;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class ParserBookKbuDetail implements ParserBookInformation {
    private Document document;
    private Elements bookDetail;
    private final String searchURL = "https://lib.bible.ac.kr/Search";

    @Override
    public void setParsingURL(String parsingURL) {
//        //https://lib.bible.ac.kr/Search/Detail/166113
//        String detailUrl = null;
//        if (parsingURL.isEmpty()) {
//            throw new RuntimeException("잘못된 값이 포함되어있거나 비어있는 값이 존재합니다.");
//        }
//        if (parsingURL.contains("https") || parsingURL.contains("Detail")) {
//            String[] tmp = parsingURL.split("/");
//            detailUrl = tmp[5];
//        }

        try {
//            Connection conn = Jsoup.connect(this.searchURL + detailUrl);
            Connection conn = Jsoup.connect(parsingURL);
            this.document = conn.get();
            this.bookDetail = document.getElementsByClass("col-md-10 detail-table-right");
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }
    @Override
    public String getBookTitle() {
        return bookDetail.get(0).getElementsByClass("sponge-book-title").text();
    }

    @Override
    public Map<String, String> getInformation() {
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

    @Override
    public List<BookCategory> getRelatedBook() {
        ArrayList<BookCategory> bookCategories = new ArrayList<>();
        Elements elementsByClass = document.getElementsByClass("sponge-detail-book").get(0).select("div:nth-child(1) > div > ul > li > a");
        elementsByClass.forEach(
                element -> bookCategories.add(
                        new BookCategory(
                                element.attr("href"),
                                element.getElementsByTag("img").attr("src"),
                                element.attr("title")
                        )
                )
        );
        return bookCategories;
    }

    @Override
    public String getBookImage() {
        return document.getElementsByClass("page-detail-title-image").select("a > img").attr("src");
    }

}
