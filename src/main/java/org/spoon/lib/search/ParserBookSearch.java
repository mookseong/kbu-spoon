package org.spoon.lib.search;

import org.spoon.lib.model.BookKeepInfo;
import org.spoon.lib.model.BookSearch;

import java.util.List;

public interface ParserBookSearch {

    /**
     * 검색 대상의 페이지를 불러옵니다.
     * @param searchWord 검색 단어
     * @param index 검색 페이지
     */
    void setParsingURL(String searchWord, int index);

    /**
     * 책 검색 정보를 불러옵니다.
     * @return 검색정보를 List 형식으로 반환합니다.
     */
    List<BookSearch> getBookSearchListBySearch();

    List<String> getBookImg();

    String getBookImg(int index);

    List<String> getBookInformation();

    String getBookInformation(int index);

    List<String> getBookTitle();

    String getBookTitle(int index);

    List<String> getBookIsbn();

    String getBookIsbn(int index);

    List<String> getBookEtc();

    String getBookEtc(int index);

    List<BookKeepInfo> getKeepBookList();

    List<BookKeepInfo> getKeepBookList(int index);
}
