package org.spoon;

import org.junit.jupiter.api.Test;
import org.spoon.lib.model.BookSearchList;
import org.spoon.lib.search.ParserBookSearchModel;

import java.util.List;

public class SpoonSearchTest {
    @Test
    void getBookSearchListBySearch() {
        Spoon spoon = new Spoon();
        List<BookSearchList> bookSearchLists = spoon.getBookSearchList(new ParserBookSearchModel(), "언어", 1);
        for (BookSearchList bookList : bookSearchLists) {
            System.out.println("책 제목 : " + bookList.getTitle());
            System.out.println("책 사진 : " + bookList.getImage());
            System.out.println("책 etc : " + bookList.getEtc());
            System.out.println("책 info : " + bookList.getInfo());
            System.out.println("책 isbn : " + bookList.getIsbn());
            System.out.println("-----------------------------------");
        }
    }

}
