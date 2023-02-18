package org.spoon.notice.list;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeList;

import java.util.List;
import java.util.Objects;

public interface ParseNoticeList {

    /**
     * kbu 페이지에서 불러올 url 통해 불러와 저장합니다.
     * @param pageIndex 페이지 인덱스
     */
    void setParsingURL(String pageIndex);

    /**
     * 도서관 입력된 정보를 토대로 도서관 정보를 추출합니다.
     * @return {@code Element} 형식으로 변경하여 추출합니다.
     */
    Elements extractPostDocument();

    /**
     * {@code Element}형식으로 된 정보에서 책 정보만 추출합니다.
     * @return 정보를 추출하여 반환합니다.
     */
    List<PostNoticeList> getPostList();
}
