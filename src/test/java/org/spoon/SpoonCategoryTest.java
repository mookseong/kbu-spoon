package org.spoon;

import org.junit.jupiter.api.Test;
import org.spoon.lib.category.ParserBestBookCategory;
import org.spoon.lib.category.ParserEBookCategory;
import org.spoon.lib.category.ParserNewBookCategory;
import org.spoon.lib.category.ParserRentBookCategory;
import org.spoon.lib.model.BookCategory;

import java.util.List;

public class SpoonCategoryTest {
    @Test
    void getNewBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategory(new ParserNewBookCategory());
        System.out.println(" ");
        System.out.println("bookNewBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }

    @Test
    void getBestBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategory(new ParserBestBookCategory());
        System.out.println(" ");
        System.out.println("bookBestBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }


    @Test
    void getEBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategory(new ParserEBookCategory());
        System.out.println(" ");
        System.out.println("bookEBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }

    @Test
    void getRentBookParser() {
        Spoon spoon = new Spoon();
        List<BookCategory> parser = spoon.getBookCategory(new ParserRentBookCategory());
        System.out.println(" ");
        System.out.println("bookRentBook");
        for (BookCategory bookList : parser) {
            inputCategoryBook(bookList);
        }
    }

    private void inputCategoryBook(BookCategory bookList){
        System.out.println("책 제목 : " + bookList.getTitle());
        System.out.println("책 사진 : " + bookList.getImage());
        System.out.println("책 url : " + bookList.getUrl());
        System.out.println("-----------------------------------");
    }
}
