package org.spoon.lib.model;

/**
 * kbu 도서관 검색 정보입니다.
 */
public class BookSearch {
    private final String title;
    private final String etc;
    private final String image;
    private final String info;
    private final String isbn;

    /**
     * kbu 도서관 검색 정보입니다.
     *
     * @param title 도서 제목
     * @param etc   도서 기타 정보
     * @param image 도서 이미지 주소
     * @param info  도서 책정보 페이지
     * @param isbn  도서 isbn 정 보
     */
    public BookSearch(String title, String etc, String image, String info, String isbn) {
        this.title = title;
        this.etc = etc;
        this.image = image;
        this.info = info;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getEtc() {
        return etc;
    }

    public String getImage() {
        return image;
    }

    public String getInfo() {
        return info;
    }

    public String getIsbn() {
        return isbn;
    }
}
