package org.mookseong.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mookseong.data.lib.BookListType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BookList {
    String URL = "https://lib.bible.ac.kr";
    static Document document;

    /**
     * 메소드가 처음 초기화시 KBU 도서관 홈체이지에서 정보를 가져와 초기화를 진행한다.
     * {@link Document}를 초기화하는데 이중으로 초기화하지 않는다.
     */
    public BookList() {
        if (document == null) {
            try {
                Connection conn = Jsoup.connect(URL);
                document = conn.get();
            } catch (IOException e) {
                throw new RuntimeException("사이트 문서화 실패", e);
            }
        }

    }

    /**
     * 도서관 홈페이지에서 인기도서, 신작도서, 전자도서, 추천도서를 추출하여 반환한다.
     *
     * @param model 추출하고자 하는 책 정보 요청한다. <p>추출 가능한 타입은 {@link BookListType} 타입만 가능하다.</p>
     * @return 반환시 {@link ArrayList<BookList>}형식으로 반환된다.
     */
    public ArrayList<org.mookseong.data.lib.BookList> getBookList(BookListType model) {
        ArrayList<org.mookseong.data.lib.BookList> bookLists = new ArrayList<>();
        Element libHome = getDocumentByLibHome(document);
        Elements bookListByDocument = getBookListByDocument(libHome, model);

        for (Element List : bookListByDocument) {
            bookLists.add(new org.mookseong.data.lib.BookList(
                    URL + List.attr("href"), //상세 정보를 이동하는 주소 등록
                    isContainsHTTP(List.getElementsByTag("img").attr("src")), //책 표지 정보 URL 형식으로 반환
                    List.getElementsByTag("span").text()) // 책 제목
            );
        }
        return bookLists;
    }

    /**
     * 책 표지가 없다면, 학교 임시 표지 형식으로 변환한다.
     *
     * @param imgURL URL 정보를 받는다.
     * @return {@param imgURL} 형식에 표지가 존재한다면 똑같은 값을 return, 없다면 도서관 URL 형식을 변환한다.
     */
    private String isContainsHTTP(String imgURL) {
        if (imgURL.contains("https")) {
            return imgURL;
        }
        return URL + imgURL;
    }

    /**
     * 불러올 Book 정보를 String 형식으로 변환해준다.
     *
     * @param model enum 형식으로 지정된  {@link BookListType} 형식으로 받는다
     * @return {@link BookListType} 형식으로 된 값을 {@link String}형식인 값으로 변환해준다.
     */
    private String BookType(BookListType model) {
        return switch (model) {
            case NEW_BOOK -> "book-new";
            case BEST_BOOK -> "book-best";
            case EBOOK_BOOK -> "book-ebook";
            case RECOMMEND_BOOK -> "book-recommend";
            default -> throw new RuntimeException("문자열 변환 오류 발생");
        };
    }

    /**
     * 도서관 홈페이지에서 필요한 부분을 추출한다.
     *
     * @param document 도서관 홈페이지를 Document 형식으로 받는다.
     * @return 책 정보 위치만 추출후 return 한다.
     */
    private Element getDocumentByLibHome(Document document) {
        return document.getElementsByClass("col-md-9 sponge-main-book book").get(0);
    }

    /**
     * 책 위치 정보를 가지고 HTML 태그를 분석하고 정보를 가져온다.
     *
     * @param element {@code getDocumentByLibHome}에처 추출된 값을 받는다 형식은 {@link Element} 이다.
     * @param model   사용자가 요처를 원하는 {@link BookListType}를 받는다.
     * @return 추출된 값을 추출후 {@link Elements}형식으로 return 한다 <p>단. 모든 정보를 가공하지 않고 태그 a 형식만 가지고 return 한다</p>
     */
    private Elements getBookListByDocument(Element element, BookListType model) {
        return Objects.requireNonNull(element.getElementById(BookType(model))).select("ul").select("li").select("a");
    }


}
