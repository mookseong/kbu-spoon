package org.spoon.lib.data;

/**
 * kbu 도서관에 소장된 책의 자세한 정보입니다.
 * @param title 책 제목
 * @param image 책표지 이미지 주소
 * @param isbn 책 isbn
 * @param ddc 책 ddc
 * @param author 저자
 * @param discount 책가격
 * @param publisher 출판사
 * @param pubdate 책 출간일
 * @param description 책 설명
 */

public record BookInformation(
        String title,
        String image,
        String isbn,
        String ddc,
        String author,
        String discount,
        String publisher,
        String pubdate,
        String description

) {
}
