package org.spoon.lib.data;

/**
 * kbu 도서관에 소장된 책의 자세한 정보입니다.
 */

public class BookInformation {
    private final String title;
    private final String image;
    private final String isbn;
    private final String number;
    private final String ddc;
    private final String author;
    private final String discount;
    private final String publisher;
    private final String pubdate;
    private final String description;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNumber() {
        return number;
    }

    public String getDdc() {
        return ddc;
    }

    public String getAuthor() {
        return author;
    }

    public String getDiscount() {
        return discount;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getDescription() {
        return description;
    }

    /**
     * kbu 도서관에 소장된 책의 자세한 정보입니다.
     *
     * @param title       책 제목
     * @param image       책표지 이미지 주소
     * @param isbn        책 isbn
     * @param number      도서 청구번호
     * @param ddc         책 ddc
     * @param author      저자
     * @param discount    책가격
     * @param publisher   출판사
     * @param pubdate     책 출간일
     * @param description 책 설명
     */
    public BookInformation(String title, String image, String isbn, String number, String ddc, String author, String discount, String publisher, String pubdate, String description) {
        this.title = title;
        this.image = image;
        this.isbn = isbn;
        this.number = number;
        this.ddc = ddc;
        this.author = author;
        this.discount = discount;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.description = description;
    }
}
