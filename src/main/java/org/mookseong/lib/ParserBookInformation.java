package org.mookseong.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ParserBookInformation {
    Document document;

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
     * 책 정보 페이지에서 책 정보를 가져올 수 있는 페이지를 추출합니다.
     *
     * @return 데이터를 추출하여 가공되지 않은 {@link Elements}  형식으로 반환합니다.
     */
    public Elements getBookInfoByDocument() {
        return document.getElementsByClass("col-md-10 detail-table-right");
    }

    /**
     * 책 자세한 정보 페이지에서 책 제목을 추출합니다.
     *
     * @param elements 추출하기 위한 데이터를 입력받습니다.
     * @return 정보를 추출하여 {@link String} 형식으로 반환합니다.
     */
    public String getBookTitleByElements(Elements elements) {
        return elements.get(0).getElementsByClass("sponge-book-title").text();
    }

    /**
     * 책 자세한 정보를 추출하여 반환합니다. <p>단, "자료유형", "청구기호", "ISBN", "DDC", "서명/저자", "가격", "발행사항"중에서 존재하는 데이터만 삽입되며 반환됩니다.</p>
     *
     * @param elements 추출하기 위한 데이터를 입력받습니다.
     * @return 정보를 추출하여 {@link Map<String,String>} 형식으로 반환합니다.
     */
    public Map<String, String> getBookInformationByElements(Elements elements) {
        Map<String, String> toBookInfoList = new HashMap<>();
        List<String> bookInfoTitle = List.of("자료유형", "청구기호", "ISBN", "DDC", "서명/저자", "가격", "발행사항");
        elements.select("dl")
                .stream()
                .filter(element -> bookInfoTitle.stream().anyMatch(Predicate.isEqual(element.select("dt").text())))
                .forEach(i -> toBookInfoList.put(i.select("dt").text().trim(), i.select("dd").text().trim()));
        toBookInfoList.put("저자", toBookInfoList.get("서명/저자").split("/")[1]);
        toBookInfoList.remove("서명/저자");
        return toBookInfoList;
    }

}
