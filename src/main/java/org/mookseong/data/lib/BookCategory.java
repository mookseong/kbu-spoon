package org.mookseong.data.lib;

/**
 * 도서관 홈페이지에 있는 책 정보를 리스트 형식으로 반환합니다.
 * @param bookURL   책 상세정보 페이지를 담고있습니다.<p>주소를 무조건 lib.bible.ac.kr와 결합합나다.</p>
 * @param bookImg   책 커버 주소를 담고 있습니다.<p>이미지 주소가 없다면 일반 주소로 변경합니다.</p>
 * @param bookTitle 책 제목을 담고 있습니다.
 */
public record BookCategory(String bookURL, String bookImg, String bookTitle) {
    public BookCategory {
        bookURL = "https://lib.bible.ac.kr" + bookURL;
        if (!bookImg.contains("https")) {
            bookImg = "https://lib.bible.ac.kr" + bookImg;
        }
    }
}

