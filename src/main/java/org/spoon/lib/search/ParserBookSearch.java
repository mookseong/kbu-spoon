package org.spoon.lib.search;

import org.spoon.lib.model.BookKeepInfo;
import org.spoon.lib.model.BookSearchList;

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
    List<BookSearchList> getBookSearchListBySearch();

    /**
     * 검색된 책 이미지 정보만 가져옵니다.
     * @return 책 이미지 정보를 담고 있는 List 반환합니다.
     */
    List<String> getBookImg();

    /**
     * 특정위치 책위치 정보를 불러옵니다.
     * @param index 검색 위치 정보
     * @return 책 이미지 정보를 담고 있는 정보를 반환합니다.
     */
    String getBookImg(int index);

    /**
     * 검색된 일부 정보를 담고 있는 List 반환합니다.
     * @return 책 정보를 담고 있는 List 반환합니다.
     */
    List<String> getBookInformation();

    /**
     * 특정위치에 있는 정보를 불러옵니다.
     * @param index 검색 위치 정보
     * @return 책 특정 위치 정보를 반환합니다.
     */
    String getBookInformation(int index);

    /**
     * 책 제목만 불러옵니다.
     * @return 책 정보를 담고 있는 List 반환합니다.
     */
    List<String> getBookTitle();

    /**
     * 특정위치에 있는 정보를 불러옵니다.
     * @param index 검색 위치 정보
     * @return 책 특정 위치 정보를 반환합니다.
     */
    String getBookTitle(int index);

    /**
     * 책 isbn 정보를 불러옵니다.
     * @return 책 isbn 정보를 담고 있는 List 반환합니다.
     */
    List<String> getBookIsbn();

    /**
     * 특정위치에 있는 정보를 불러옵니다.
     * @param index 검색 위치 정보
     * @return 책 특정 위치 정보를 반환합니다.
     */
    String getBookIsbn(int index);

    /**
     * 책 기타 정보를 불러옵니다.
     * @return 책 기타 정보를 담고 있는 List 반환합니다.
     */
    List<String> getBookEtc();

    /**
     * 특정위치에 있는 정보를 불러옵니다.
     * @param index 검색 위치 정보
     * @return 책 특정 위치 정보를 반환합니다.
     */
    String getBookEtc(int index);

    /**
     * 책 대여정보를 정보를 불러옵니다.
     * @return 책 기타 정보를 담고 있는 List 반환합니다.
     */
    List<BookKeepInfo> getKeepBookList();

    /**
     * 특정위치에 있는 책 대여정보 정보를 불러옵니다.
     * @param index 검색 위치 정보
     * @return 책 특정 위치 정보를 반환합니다.
     */
    List<BookKeepInfo> getKeepBookList(int index);
}
