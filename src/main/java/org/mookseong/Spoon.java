package org.mookseong;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mookseong.data.lib.BookCategory;
import org.mookseong.data.lib.BookCategoryType;
import org.mookseong.data.lib.BookSearch;
import org.mookseong.lib.ParserBookCategory;
import org.mookseong.lib.ParserBookSearch;


import java.util.ArrayList;

public class Spoon {
    public static void main(String[] args) {


    }

    /**
     * 도서관 홈페이지에서 검색된 도서 정보를 불러옵니다.<p>책제목, 책이미지주소, 책정보, 책기타정보, ISBN 정보를 반홚바니다.</p>
     * @param word 검색할 도서 정보를 입력합니다.
     * @param index 검색된 페이지 주소를 입력합니다.
     * @return 반환형식은 {@link ArrayList<BookSearch>} 형식으로 반환됩니다.
     */
    public ArrayList<BookSearch> getBookSearchList(String word, int index) {
        ParserBookSearch parserBookSearch = new ParserBookSearch();
        parserBookSearch.setParsingURL(word, 1);

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

    /**
     * 도서관 홈페이지에서 인기도서, 신작도서, 전자도서, 추천도서를 추출하여 반환한다.
     *
     * @param model 추출하고자 하는 책 정보 요청한다. <p>추출 가능한 타입은 {@link BookCategoryType} 타입만 가능하다.</p>
     * @return 반환시 {@link ArrayList< BookCategory >}형식으로 반환된다.
     */
    public ArrayList<BookCategory> getBookCategoryList(BookCategoryType model) {
        ParserBookCategory parserBookCategory = new ParserBookCategory();
        ArrayList<BookCategory> bookCategories = new ArrayList<>();
        Element libHome = parserBookCategory.getCategoryByDocument();
        Elements bookListByDocument = parserBookCategory.getBookListByDocument(libHome, model);

        for (Element List : bookListByDocument) {
            bookCategories.add(
                    new BookCategory(
                            List.attr("href"), //상세 정보를 이동하는 주소 등록
                            List.getElementsByTag("img").attr("src"), //책 표지 정보 URL 형식으로 반환
                            List.getElementsByTag("span").text() // 책 제목
                    )
            );
        }
        return bookCategories;
    }

}