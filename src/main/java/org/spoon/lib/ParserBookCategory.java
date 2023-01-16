package org.spoon.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.data.BookCategoryType;

import java.io.IOException;
import java.util.Objects;

public class ParserBookCategory extends BaseParser{
    private BookCategoryType documentType;

    /**
     * 메소드가 처음 초기화시 default 값으로 {@link BookCategoryType#RECENT_LOAN_BOOK}제외한 KBU 도서관 홈페이지에서 정보를 가져와 {@link Document}를 초기화를 진행한다.
     */
    public ParserBookCategory() {
        try {
            documentType = BookCategoryType.BEST_BOOK;
            Connection conn = Jsoup.connect(homeURL);
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    /**
     * 도서관 홈페이지 정보를 새로 불러오거나 변경할때 사용합니다.
     *
     * @param model 변경하자 하는 모델 정보를 입력 받습니다.
     */
    public void setParsingURL(BookCategoryType model) {
        try {
            Connection conn;
            documentType = model;
            if (model == BookCategoryType.RECENT_LOAN_BOOK) {
                conn = Jsoup.connect(searchURL);
            } else {
                conn = Jsoup.connect(homeURL);
            }
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    /**
     * 불러올 Book 정보를 String 형식으로 변환해준다.
     *
     * @param model enum 형식으로 지정된  {@link BookCategoryType} 형식으로 받는다
     * @return {@link BookCategoryType} 형식으로 된 값을 {@link String}형식인 값으로 변환해준다.
     */
    private String BookType(BookCategoryType model) {
        switch (model) {
            case NEW_BOOK:
                return "book-new";
            case BEST_BOOK:
                return "book-best";
            case EBOOK_BOOK:
                return "book-ebook";
            default:
                throw new RuntimeException("현재 지원하지 않는 기능입니다.");
        }
    }

    /**
     * 도서관 홈페이지에서 필요한 부분을 추출한다.<p>default 값으로는 {@link BookCategoryType#RECENT_LOAN_BOOK}제외하고는 동일한 값으로 처리됩니다.</p>
     *
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
        return Objects.requireNonNull(element.getElementById(BookType(model))).select("ul > li > a");

    }

}
