package org.spoon.lib.model;

/**
 * 도서관 홈페이지에 있는 책 정보를 리스트 형식으로 반환합니다.
 */
public class BookCategory {
    private final String url;
    private final String image;
    private final String title;

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
    /**
     * 도서관 홈페이지에 있는 책 정보를 리스트 형식으로 반환합니다.
     *
     * @param url   책 상세정보 페이지를 담고있습니다.<p>주소를 무조건 lib.bible.ac.kr와 결합합나다.</p>
     * @param image 책 커버 주소를 담고 있습니다.<p>이미지 주소가 없다면 일반 주소로 변경합니다.</p>
     * @param title 책 제목을 담고 있습니다.
     */
    public BookCategory(String url, String image, String title) {
        this.url = "https://lib.bible.ac.kr" + url;
        this.title = title;
        if (!image.contains("https")) {
            this.image = "https://lib.bible.ac.kr" + image;
        }else {
            this.image = image;
        }
    }
}

