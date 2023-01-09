package org.spoon.lib.data;

/**
 * 네이버 api 요청 시 items 항목입니다.
 *
 * @param title       책 제목
 * @param link        네이버 도서 정보 URL
 * @param image       섬네일 이미지의 URL
 * @param author      저자 이름
 * @param discount    판매 가격. 절판 등의 이유로 가격이 없으면 값을 반환하지 않습니다.
 * @param publisher   출판사
 * @param pubdate     출간일
 * @param isbn        ISBN
 * @param description 네이버 도서의 책 소개
 */
public record NaverBookItems(
        String title,
        String link,
        String image,
        String author,
        String discount,
        String publisher,
        String pubdate,
        String isbn,
        String description
) {
    @Override
    public String title() {
        return title;
    }

    @Override
    public String link() {
        return link;
    }

    @Override
    public String image() {
        return image;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public String discount() {
        return discount;
    }

    @Override
    public String publisher() {
        return publisher;
    }

    @Override
    public String pubdate() {
        return pubdate;
    }

    @Override
    public String isbn() {
        return isbn;
    }

    @Override
    public String description() {
        return description;
    }
}
