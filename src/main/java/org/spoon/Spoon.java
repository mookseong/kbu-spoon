package org.spoon;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.*;
import org.spoon.lib.category.ParserBookList;
import org.spoon.lib.information.NaverBookSearchAPI;
import org.spoon.lib.information.ParserBookKbuDetail;
import org.spoon.lib.information.ParserBookDetail;
import org.spoon.lib.information.ParserBookNaverDetail;
import org.spoon.lib.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Spoon {

    /**
     * 도서관 홈페이지에서 검색된 도서 정보를 불러옵니다.<p>책제목, 책이미지주소, 책정보, 책기타정보, ISBN 정보를 반홚바니다.</p>
     *
     * @param word  검색할 도서 정보를 입력합니다.
     * @param index 검색된 페이지 주소를 입력합니다.
     * @return 반환형식은 {@link ArrayList<BookSearch>} 형식으로 반환됩니다.
     */
    public ArrayList<BookSearch> getBookSearchListBySearch(String word, int index) {
        ParserBookSearchModel parserBookSearch = new ParserBookSearchModel();
        parserBookSearch.setParsingURL(word, index);

        ArrayList<BookSearch> bookCategories = new ArrayList<>();
        Elements getSearchElement = parserBookSearch.getSearchListByDocument();

        for (int i = 0; i < getSearchElement.size(); i++) {
            bookCategories.add(
                    new BookSearch(
                            parserBookSearch.getBookTitle(getSearchElement, i),
                            parserBookSearch.getBookEtc(getSearchElement, i),
                            parserBookSearch.getBookImg(getSearchElement, i),
                            parserBookSearch.getBookInformation(getSearchElement, i),
                            parserBookSearch.getBookISBN(getSearchElement, i)
                    )
            );
        }

        return bookCategories;
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
    public BookInfo getInformationByParser(String url) {
        ParserBookDetail parserBookDetail = new ParserBookKbuDetail();
        parserBookDetail.setParsingURL(url);
        return parserBookDetail.getBookInformation();
    }

    /**
     * 네이버 api 통해 정보를 중요한 데이터를 불러오고, 일부 정보를 도서관 홈페이지에서 정보를 가져옵니다. <p>isbn 정보는 도서관 홈페이지에서 가져옵니다.</p></p>
     * @param clientId 애플리케이션 클라이언트 아이디
     * @param clientSecret 애플리케이션 클라이언트 시크릿
     * @param url 도서관 책 url  받습니다.
     * @return 데이터를 {@link BookInfo} 형식으로 반환합니다. 만약 데이터가 존재하지 않는다면 null 반환합니다.
     */
    public BookInfo getInformationByNaver(String clientId, String clientSecret, String url) {
        ParserBookDetail parserBookDetail = new ParserBookNaverDetail(new NaverBookSearchAPI(clientId, clientSecret));
        parserBookDetail.setParsingURL(url);
        return parserBookDetail.getBookInformation();
    }
}