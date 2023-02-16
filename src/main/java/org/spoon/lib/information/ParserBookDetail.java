package org.spoon.lib.information;

import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookInfo;

import java.util.List;
import java.util.Map;

public interface ParserBookDetail {

    /**
     * kbu 페이지에서 불러올 url 통해 불러와 저장합니다.
     * @param SearchURL 책정보를 불러올 주소
     */
    void setParsingURL(String SearchURL);

    /**
     * 책 제목을 가져옵니다.
     * @return {@code String} 형식으로 책제목을 반환합니다.
     */
    String getBookTitle();

    /**
     * 책 정보를 불러옵니다.
     * @return 책 정보를 담고 있는 map 형식으로 반환합니다.
     */
    BookInfo getBookInformation();

    /**
     * 책 정보페이지에 있는 추천도서를 불러옵니다.
     * @return List 형식으로 반환하지만, 데이터가 존재하지 않다면 빈값으로 반환합니다.
     */
    List<BookCategory> getRelatedBook();

    /**
     * 책표지 이미지 주소를 불러옵니다.
     * @return 이미지 주소를 반환합니다.
     */
    String getBookImage();
}
