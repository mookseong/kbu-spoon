package org.spoon;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.spoon.lib.ParserBookSearch;
import org.spoon.lib.data.BookCategory;
import org.spoon.lib.data.BookCategoryType;
import org.spoon.lib.data.BookInformation;
import org.spoon.lib.data.BookSearch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void getBookCategoryByParser() {
        Spoon spoon = new Spoon();

        List<BookCategory> bookNewBook = spoon.getBookCategoryByParser(BookCategoryType.NEW_BOOK);
        List<BookCategory> bookBestBook = spoon.getBookCategoryByParser(BookCategoryType.BEST_BOOK);
        List<BookCategory> bookEBook = spoon.getBookCategoryByParser(BookCategoryType.EBOOK_BOOK);
        System.out.println("bookNewBook");
        for (BookCategory bookList :bookNewBook){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }
        System.out.println("bookBestBook");
        for (BookCategory bookList :bookBestBook){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }
        System.out.println("bookEBook");
        for (BookCategory bookList :bookEBook){
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 url : " + bookList.getUrl());
        }


    }

    @Test
    void getInformationByParser() {
        Spoon spoon = new Spoon();
        BookInformation bookInformation = spoon.getInformationByParser("https://lib.bible.ac.kr/Search/Detail/165517");
        System.out.println("title : " + bookInformation.getTitle());
        System.out.println("image : " + bookInformation.getImage());
        System.out.println("isbn : " + bookInformation.getIsbn());
        System.out.println("ddc : " + bookInformation.getDdc());
        System.out.println("저자 : " + bookInformation.getAuthor());
        System.out.println("책가격 : " + bookInformation.getDiscount().trim());
        System.out.println("청구기호 : " + bookInformation.getNumber());
        System.out.println("출판사 : " + bookInformation.getPublisher());
        System.out.println("출판날자 : " + bookInformation.getPubdate());
        System.out.println("설명 : " + bookInformation.getDescription());
    }

    @Test
    void getInformationByNaver() {
        Spoon spoon = new Spoon();
        BookInformation bookInformation = spoon.getInformationByNaver("", "","https://lib.bible.ac.kr/Search/Detail/165517");
        System.out.println("title : " + bookInformation.getTitle());
        System.out.println("image : " + bookInformation.getImage());
        System.out.println("isbn : " + bookInformation.getIsbn());
        System.out.println("ddc : " + bookInformation.getDdc());
        System.out.println("저자 : " + bookInformation.getAuthor());
        System.out.println("책가격 : " + bookInformation.getDiscount().trim());
        System.out.println("청구기호 : " + bookInformation.getNumber());
        System.out.println("출판사 : " + bookInformation.getPublisher());
        System.out.println("출판날자 : " + bookInformation.getPubdate());
        System.out.println("설명 : " + bookInformation.getDescription());
    }

    @Test
    void testGetInformationByNaver() {
        Spoon spoon = new Spoon();
        BookInformation bookInformation = spoon.getInformationByNaver("", "","9788956991634","https://lib.bible.ac.kr/Search/Detail/165517");
        System.out.println("title : " + bookInformation.getTitle());
        System.out.println("image : " + bookInformation.getImage());
        System.out.println("isbn : " + bookInformation.getIsbn());
        System.out.println("ddc : " + bookInformation.getDdc());
        System.out.println("저자 : " + bookInformation.getAuthor());
        System.out.println("책가격 : " + bookInformation.getDiscount().trim());
        System.out.println("청구기호 : " + bookInformation.getNumber());
        System.out.println("출판사 : " + bookInformation.getPublisher());
        System.out.println("출판날자 : " + bookInformation.getPubdate());
        System.out.println("설명 : " + bookInformation.getDescription());
    }
}