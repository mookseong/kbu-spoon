package org.spoon;

import org.junit.jupiter.api.Test;
import org.spoon.lib.category.ParserBestBookCategory;
import org.spoon.lib.category.ParserEBookCategory;
import org.spoon.lib.category.ParserNewBookCategory;
import org.spoon.lib.category.ParserRentBookCategory;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookInfo;
import org.spoon.lib.model.BookSearch;

import java.util.ArrayList;
import java.util.List;

class SpoonTest {

    @Test
    void getBookSearchListBySearch() {
        Spoon spoon = new Spoon();
        ArrayList<BookSearch> bookSearches = spoon.getBookSearchListBySearch("언어", 1);
        for (BookSearch bookList :bookSearches){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 etc : " + bookList.getEtc());
            System.out.println("책 info : " + bookList.getInfo());
            System.out.println("책 isbn : " + bookList.getIsbn());
        }
    }

    @Test
    void getNewBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserNewBookCategory());

        System.out.println("bookNewBook");
        for (BookCategory bookList :parser){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }
    }
    @Test
    void getBestBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserBestBookCategory());

        System.out.println("bookBestBook");
        for (BookCategory bookList :parser){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }

    }

    @Test
    void getEBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserEBookCategory());

        System.out.println("bookEBook");
        for (BookCategory bookList :parser){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }

    }

    @Test
    void getRentBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserRentBookCategory());

        System.out.println("bookRentBook");
        for (BookCategory bookList :parser){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }

    }

    @Test
    void getInformationByParser() {
        Spoon spoon = new Spoon();
        BookInfo bookInfo = spoon.getInformationByParser("https://lib.bible.ac.kr/Search/Detail/165517");
        System.out.println("title : " + bookInfo.getTitle());
        System.out.println("image : " + bookInfo.getImage());
        System.out.println("isbn : " + bookInfo.getIsbn());
        System.out.println("ddc : " + bookInfo.getDdc());
        System.out.println("저자 : " + bookInfo.getAuthor());
        System.out.println("책가격 : " + bookInfo.getDiscount().trim());
        System.out.println("청구기호 : " + bookInfo.getNumber());
        System.out.println("출판사 : " + bookInfo.getPublisher());
        System.out.println("출판날자 : " + bookInfo.getPubdate());
        System.out.println("설명 : " + bookInfo.getDescription());
    }

    @Test
    void getInformationByNaver() {
        Spoon spoon = new Spoon();
        BookInfo bookInfo = spoon.getInformationByNaver("clientId", "clientSecret","https://lib.bible.ac.kr/Search/Detail/165517");
        System.out.println("title : " + bookInfo.getTitle());
        System.out.println("image : " + bookInfo.getImage());
        System.out.println("isbn : " + bookInfo.getIsbn());
        System.out.println("ddc : " + bookInfo.getDdc());
        System.out.println("저자 : " + bookInfo.getAuthor());
        System.out.println("책가격 : " + bookInfo.getDiscount().trim());
        System.out.println("청구기호 : " + bookInfo.getNumber());
        System.out.println("출판사 : " + bookInfo.getPublisher());
        System.out.println("출판날자 : " + bookInfo.getPubdate());
        System.out.println("설명 : " + bookInfo.getDescription());
    }

}