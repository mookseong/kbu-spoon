package org.mookseong.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mookseong.data.lib.BookCategory;
import org.mookseong.data.lib.BookCategoryType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ParserBookCategory {
    String URL = "https://lib.bible.ac.kr";
    Document document;

    /**
     * 메소드가 처음 초기화시 default 값으로 KBU 도서관 홈페이지에서 정보를 가져와 {@link Document}를 초기화를 진행한다.
     */
    public ParserBookCategory() {
        try {
            Connection conn = Jsoup.connect(URL);
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    /**
     * 도서관 홈페이지에서 인기도서, 신작도서, 전자도서, 추천도서를 추출하여 반환한다.
     *
     * @param model 추출하고자 하는 책 정보 요청한다. <p>추출 가능한 타입은 {@link BookCategoryType} 타입만 가능하다.</p>
     * @return 반환시 {@link ArrayList<BookCategory>}형식으로 반환된다.
     */
    public ArrayList<BookCategory> getBookList(BookCategoryType model) {
        ArrayList<BookCategory> bookCategories = new ArrayList<>();
        Element libHome = getCategoryByDocument();
        Elements bookListByDocument = getBookListByDocument(libHome, model);

        for (Element List : bookListByDocument) {
            bookCategories.add(
                    new BookCategory(
                            List.attr("href"), //상세 정보를 이동하는 주소 등록
                            List.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                            List.getElementsByTag("span").text() // 책 제목
                    )
            );
        }
        return bookCategories;
    }

    /**
     * 불러올 Book 정보를 String 형식으로 변환해준다.
     *
     * @param model enum 형식으로 지정된  {@link BookCategoryType} 형식으로 받는다
     * @return {@link BookCategoryType} 형식으로 된 값을 {@link String}형식인 값으로 변환해준다.
     */
    public String BookType(BookCategoryType model) {
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
     * @return 책 정보 위치만 추출후 return 한다.
     */
    public Element getCategoryByDocument() {
        return document.getElementsByClass("col-md-9 sponge-main-book book").get(0);
    }

    /**
     * 책 위치 정보를 가지고 HTML 태그를 분석하고 정보를 가져온다.
     *
     * @param element {@code getDocumentByLibHome}에처 추출된 값을 받는다 형식은 {@link Element} 이다.
     * @param model   사용자가 요처를 원하는 {@link BookCategoryType}를 받는다.
     * @return 추출된 값을 추출후 {@link Elements}형식으로 return 한다 <p>단. 모든 정보를 가공하지 않고 태그 a 형식만 가지고 return 한다</p>
     */
    public Elements getBookListByDocument(Element element, BookCategoryType model) {
        return Objects.requireNonNull(element.getElementById(BookType(model))).select("ul").select("li").select("a");
    }

}
