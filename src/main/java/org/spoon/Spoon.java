package org.spoon;


import org.jsoup.nodes.Element;
import org.spoon.lib.category.ParserBookList;
import org.spoon.lib.information.NaverBookSearchAPI;
import org.spoon.lib.information.ParserBookKbuDetail;
import org.spoon.lib.information.ParserBookDetail;
import org.spoon.lib.information.ParserBookNaverDetail;
import org.spoon.lib.model.*;
import org.spoon.lib.search.ParserBookSearch;
import org.spoon.lib.search.ParserBookSearchModel;

import java.util.ArrayList;
import java.util.List;

public class Spoon {

    /**
     * 도서관 홈페이지에서 검색된 도서 정보를 불러옵니다.<p>책제목, 책이미지주소, 책정보, 책기타정보, ISBN 정보를 반홚바니다.</p>
     *
     * @param word  검색할 도서 정보를 입력합니다.
     * @param index 검색된 페이지 주소를 입력합니다.
     * @return 반환형식은 {@link ArrayList<BookSearch>} 형식으로 반환됩니다.
     */
    public List<BookSearch> getBookSearchListBySearch(ParserBookSearch parserBookSearch, String word, int index) {
        parserBookSearch.setParsingURL(word, index);
        return parserBookSearch.getBookSearchListBySearch();
    }

    public List<BookCategory> getBookCategoryByParser(ParserBookList parserBookCategory ) {
        Element element = parserBookCategory.extractBookDocument();
        return  parserBookCategory.getBookList(element);
    }

    /**
     * 도서관 홈페이지에서 책 정보를 가져옵니다.
     * @param url 도서관 책 url  받습니다.
     * @return 데이터를 {@link BookInfo} 형식으로 반환합니다. 만약 데이터가 존재하지 않는다면 null 반환합니다.
     */
    public BookInfo getInformationByParser(ParserBookDetail parserBookDetail, String url) {
        parserBookDetail.setParsingURL(url);
        return parserBookDetail.getBookInformation();
    }
}