package org.spoon.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.spoon.lib.data.BookCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ParserBookInformation {
    Document document;

    /**
     * 메소드 생성시 검색 대상의 정보를 미리 생성합니다.
     * @param SearchURL 책 정보를 담고 있는 url
     */
    public ParserBookInformation(String SearchURL) {
        try {
            Connection conn = Jsoup.connect(SearchURL);
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    /**
     * 도서관 홈페이지 정보를 새로 불러오거나 변경할때 사용합니다.
     *
     * @param SearchURL 변경하자 하는 도서 정보를 입력 받습니다.
     */
    public void setParsingURL(String SearchURL) {
        try {
            Connection conn = Jsoup.connect(SearchURL);
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    /**
     * 책 자세한 정보 페이지에서 책 제목을 추출합니다.
     *
     * @return 정보를 추출하여 {@link String} 형식으로 반환합니다.
     */
    public String getBookTitleByElements() {
        return document
                .getElementsByClass("col-md-10 detail-table-right")
                .get(0)
                .getElementsByClass("sponge-book-title")
                .text();
    }

    /**
     * 책 자세한 정보를 추출하여 반환합니다. <p>단, "자료유형", "청구기호", "ISBN", "DDC", "서명/저자", "가격", "발행사항"중에서 존재하는 데이터만 삽입되며 반환됩니다.</p>
     *
     * @return 정보를 추출하여 {@link Map} 형식으로 반환합니다.
     */
    public Map<String, String> getBookInformationByDocument() {
        Elements elements = document.getElementsByClass("col-md-10 detail-table-right");
        Map<String, String> toBookInfoList = new HashMap<>();
        List<String> bookInfoTitle = List.of("자료유형", "청구기호", "ISBN", "DDC", "서명/저자", "가격", "발행사항");

        elements.select("dl")
                .stream()
                .filter(element ->
                        bookInfoTitle
                                .stream()
                                .anyMatch(Predicate.isEqual(element.select("dt").text()))
                )
                .forEach(element -> {
                    if (element.select("dt").text().trim().equals("ISBN") &&toBookInfoList.containsKey("ISBN")){
                        return;
                    }
                    toBookInfoList.put(element.select("dt").text().trim(), element.select("dd").text().trim());
                });
        toBookInfoList.put("저자", toBookInfoList.get("서명/저자").split("/")[1]);
        toBookInfoList.put("ISBN", toBookInfoList.get("ISBN").split(" ")[0]);
        toBookInfoList.remove("서명/저자");
        return toBookInfoList;
    }

    /**
     * 책 관련 도서를 불러옵니다. <p>하지만, 관련 인기도서 정보를 불러오지 않습니다.</p>
     *
     * @return 관련도서를 추출후 {@link ArrayList}에 {@link BookCategory} 형식으로 반횐합니다.
     */
    public ArrayList<BookCategory> getBookCategoryByDocument() {
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

    /**
     * 책 관련 도서를 이미지 정보를 불러옵니다.
     * @return 이미지 정보를 추출하여 반환합니다.
     */
    public String getBookImageByDocument(){
        return document.getElementsByClass("page-detail-title-image").select("a > img").attr("src");
    }

}
