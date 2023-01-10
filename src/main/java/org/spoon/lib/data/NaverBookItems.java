package org.spoon.lib.data;

/**
 * 네이버 api 요청 시 items 항목입니다.
 */
public class NaverBookItems {
    public String title; //책 제목
    public String link; // 네이버 도서 정보 URL
    public String image; //섬네일 이미지의 URL
    public String author; //저자 이름
    public String discount; //판매 가격. 절판 등의 이유로 가격이 없으면 값을 반환하지 않습니다.
    public String publisher; //출판사
    public String pubdate; //출간일
    public String isbn; //ISBN
    public String description; //네이버 도서의 책 소개

}
