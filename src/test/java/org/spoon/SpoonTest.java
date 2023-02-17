package org.spoon;

import org.junit.jupiter.api.Test;
import org.spoon.lib.category.ParserBestBookCategory;
import org.spoon.lib.category.ParserEBookCategory;
import org.spoon.lib.category.ParserNewBookCategory;
import org.spoon.lib.category.ParserRentBookCategory;
import org.spoon.lib.information.ParserBookKbuDetail;
import org.spoon.lib.information.ParserBookNaverDetail;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookInfo;
import org.spoon.lib.model.BookSearch;
import org.spoon.lib.search.ParserBookSearchModel;

import java.util.List;

class SpoonTest {

    @Test
    void getBookSearchListBySearch() {
        Spoon spoon = new Spoon();
        List<BookSearch> bookSearches = spoon.getBookSearchListBySearch(new ParserBookSearchModel(), "언어", 1);
        for (BookSearch bookList : bookSearches) {
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 etc : " + bookList.getEtc());
            System.out.println("책 info : " + bookList.getInfo());
            System.out.println("책 isbn : " + bookList.getIsbn());
            System.out.println("-----------------------------------");
        }
    }

    @Test
    void getNewBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserNewBookCategory());
        System.out.println(" ");
        System.out.println("bookNewBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }

    @Test
    void getBestBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserBestBookCategory());
        System.out.println(" ");
        System.out.println("bookBestBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }


    @Test
    void getEBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserEBookCategory());
        System.out.println(" ");
        System.out.println("bookEBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }

    @Test
    void getRentBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategoryByParser(new ParserRentBookCategory());
        System.out.println(" ");
        System.out.println("bookRentBook");
        for (BookCategory bookList : parser) {
           inputCategoryBook(bookList);
        }
    }

    @Test
    void getInformationByParser() {
        Spoon spoon = new Spoon();
        BookInfo bookInfo = spoon.getInformationByParser(new ParserBookKbuDetail(),"https://lib.bible.ac.kr/Search/Detail/165517");
        inputBookInfo(bookInfo);
    }

    @Test
    void getInformationByNaver() {
        Spoon spoon = new Spoon();
        BookInfo bookInfo = spoon.getInformationByParser(new ParserBookNaverDetail("", ""), "https://lib.bible.ac.kr/Search/Detail/165517");
        inputBookInfo(bookInfo);
    }

    private void inputCategoryBook(BookCategory bookList){
        System.out.println("책 제목 : " + bookList.getTitle());
        System.out.println("책 사진 : " + bookList.getImage());
        System.out.println("책 url : " + bookList.getUrl());
        System.out.println("-----------------------------------");
    }

    private void inputBookInfo(BookInfo bookInfo) {
        System.out.println(" ");
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
        System.out.println("-----------------------------------");
    }

}