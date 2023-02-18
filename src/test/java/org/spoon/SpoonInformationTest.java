package org.spoon;

import org.junit.jupiter.api.Test;
import org.spoon.lib.information.ParserBookKbuDetail;
import org.spoon.lib.information.ParserBookNaverDetail;
import org.spoon.lib.model.BookInfo;

public class SpoonInformationTest {
    @Test
    void getInformationByParser() {
        Spoon spoon = new Spoon();
        BookInfo bookInfo = spoon.getInformation(new ParserBookKbuDetail(),"https://lib.bible.ac.kr/Search/Detail/165517");
        inputBookInfo(bookInfo);
    }

    @Test
    void getInformationByNaver() {
        Spoon spoon = new Spoon();
        BookInfo bookInfo = spoon.getInformation(new ParserBookNaverDetail("", ""), "https://lib.bible.ac.kr/Search/Detail/166123");
        inputBookInfo(bookInfo);
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
