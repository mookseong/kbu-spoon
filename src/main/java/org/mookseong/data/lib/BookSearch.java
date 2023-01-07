package org.mookseong.data.lib;

/**
 * kbu 도서관 검색 정보입니다.
 * @param title 도서 제목
 * @param etc 도서 기타 정보
 * @param image 도서 이미지 주소
 * @param info 도서 책정보 페이지
 * @param isbn 도서 isbn 정 보
 */
public record BookSearch(
        String title,
        String etc,
        String image,
        String info,
        String isbn
) {

}
