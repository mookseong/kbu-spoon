package org.mookseong.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mookseong.data.lib.BookList;
import org.mookseong.data.lib.BookListType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainBookList {
    String URL = "https://lib.bible.ac.kr/";
    static Document document;

    public MainBookList() {
        try {
            Connection conn = Jsoup.connect(URL);
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    public ArrayList<BookList> getBookList(BookListType model){
        ArrayList<BookList> bookLists = new ArrayList<>();
        Element libHome = getDocumentByLibHome(document);
        Elements bookListByDocument = getBookListByDocument(libHome, model);

        for (Element List : bookListByDocument) {
            bookLists.add(new BookList(List.attr("href"), List.getElementsByTag("img").attr("src"), List.getElementsByTag("span").text()));
        }
        return bookLists;
    }

    /**
     * 불러올 Book 정보를 String 형식으로 변환해준다.
     * @param model enum 형식으로 지정된  {@link BookListType} 형식으로 받는다
     * @return {@link BookListType} 형식으로 된 값을 {@link String}형식인 값으로 변환해준다.
     */
    private String BookType(BookListType model){
        return switch (model) {
            case NEW_BOOK -> "book-new";
            case BEST_BOOK -> "book-best";
            case EBOOK_BOOK -> "book-ebook";
            case RECOMMEND_BOOK -> "book-recommend";
            default -> throw new RuntimeException("문자열 변환 오류 발생");
        };
    }

    private Element getDocumentByLibHome(Document document){
        return document.getElementsByClass("col-md-9 sponge-main-book book").get(0);
    }

    private Elements getBookListByDocument(Element element, BookListType model){
        return Objects.requireNonNull(element.getElementById(BookType(model))).select("ul").select("li").select("a");
    }


}
