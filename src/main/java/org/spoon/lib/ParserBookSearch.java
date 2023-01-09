package org.spoon.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.data.BookKeepInfo;

import java.io.IOException;
import java.util.ArrayList;

public class ParserBookSearch {
    String searchURL = "https://lib.bible.ac.kr/Search";
    String homeURL = "https://lib.bible.ac.kr";
    Document document;

    public void setParsingURL(String searchWord, int listIndex) {
        try {
            Connection conn = Jsoup.connect(searchURL + "/?q=" + searchWord + "&p=" + listIndex);
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }


    /**
     * 도서관 홈페이지에서 필요한 부분을 추출한다.
     * Param 값을 주지 않으면 내장 변수를 통해 호출한다.
     *
     * @return 책 정보 위치만 추출후 return 한다.
     */
    public Elements getSearchListByDocument() {
        return document.getElementsByClass("search-list-result");
    }

    /**
     * 도서관 홈페이지에서 필요한 부분을 추출한다.
     *
     * @param document 도서관 홈페이지를 Document 형식으로 받는다.
     * @return 책 정보 위치만 추출후 return 한다.
     */
    public Elements getSearchListByDocument(Document document) {
        return document.getElementsByClass("search-list-result");
    }

    /**
     * 도서검색된 책표지 이미지 주소 정보를 모두 가져옵니다.
     *
     * @param elementsIndex 추출할 elements 받습니다.
     * @return 도서 검색된 표지정보를 반환합니다. {@link ArrayList<String>}형식으로 반환됩니다.
     */
    public ArrayList<String> getBookImg(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            String img = element.getElementsByTag("a").get(0).getElementsByTag("img").attr("src");
            if (!img.contains("https")) {
                arrayList.add("https://lib.bible.ac.kr" + img);
            } else {
                arrayList.add(img);
            }
        }
        return arrayList;
    }

    /**
     * 검색된 데이터중 특정 위치 책표지 이미지 주소 정보를 가져옵니다.
     *
     * @param elements 추출할 elements 받습니다.
     * @param index    요청한 위치에서 정보를 가져옵니다.
     * @return 도서 검색된 표지정보를 반환합니다.
     */
    public String getBookImg(Elements elements, int index) {
        String img = elements.get(index).getElementsByTag("a").get(0).getElementsByTag("img").attr("src");
        if (!img.contains("https")) {
            return "https://lib.bible.ac.kr" + img;
        }
        return img;
    }

    /**
     * 도서검색된 책 정보 페이지 주소를 모두 가져옵니다.
     *
     * @param elementsIndex 추출할 elements 받습니다.
     * @return 데이터를 추출하고 완전한 주소를 return 합니다.
     */
    public ArrayList<String> getBookInformation(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            arrayList.add(homeURL + element.getElementsByTag("a").get(1).attr("href"));
        }
        return arrayList;
    }

    /**
     * 도서검색된 책 정보 페이지 주소 정보를 특정 위치에서 가져옵니다.
     *
     * @param elements 추출할 elements 받습니다.
     * @param index    요청한 위치에서 정보를 가져옵니다.
     * @return 데이터를 추출하고 완전한 주소를 return 합니다.
     */
    public String getBookInformation(Elements elements, int index) {
        return homeURL + elements.get(index).getElementsByTag("a").get(1).attr("href");
    }

    /**
     * 도서검색된 모든 책 제목을 불러옵니다.
     *
     * @param elementsIndex 추출할 elements 받습니다.
     * @return 도서 검색된 모든 책 제목를 반환합니다. {@link ArrayList<String>}형식으로 반환됩니다.
     */
    public ArrayList<String> getBookTitle(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            arrayList.add(element.getElementsByTag("a").get(1).text());
        }
        return arrayList;
    }

    /**
     * 도서검색된 데이터중 특정 위치에 있는 책 제목을 불러옵니다.
     *
     * @param elements 추출할 elements 받습니다.
     * @param index    요청한 위치에서 정보를 가져옵니다.
     * @return 데이터를 추출하고 책 제목을 return 합니다.
     */
    public String getBookTitle(Elements elements, int index) {
        return elements.get(index).getElementsByTag("a").get(1).text();
    }

    /**
     * 도서검색된 ISBN 정보를 모두 가져옵니다..
     *
     * @param elementsIndex 추출할 elements 받습니다.
     * @return 도서 검색된 ISBN 정보를 반환합니다. {@link ArrayList<String>}형식으로 반환됩니다.
     */
    public ArrayList<String> getBookISBN(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            String ISBN = element.getElementsByTag("a").get(2).attr("href").replace("/Naver/NaverDetail?isbn=", "");
            if (ISBN.contains("/Print")) {
                arrayList.add(null);
            } else {
                arrayList.add(ISBN);
            }
        }
        return arrayList;
    }

    /**
     * 검색된 도서중 해당 도서의 ISBN 정보를 추출합니다.
     *
     * @param elements 추출할 elements 받습니다.
     * @param index    요청한 위치에서 정보를 가져옵니다.
     * @return 데이터를 추출하고, ISBN return 합니다.<p>단 ISBN 값이 존재하지 않는다면 {@code null}을 반환합니다. </p>
     */
    public String getBookISBN(Elements elements, int index) {
        String ISBN = elements.get(index).getElementsByTag("a").get(2).attr("href").replace("/Naver/NaverDetail?isbn=", "");
        if (ISBN.contains("/Print")) {
            return null;
        }
        return ISBN;
    }

    /**
     * 검색된 도서중 책에서 기타 정보를 모두 불러옵니다.<p>기타 정보로 저자, 출판년도, 출판사등 기타 정보를 가져옵니다.</p>
     *
     * @param elementsIndex 추출할 elements 받습니다.
     * @return 추출된 기타 정보를 추출후 return 합니다. {@link ArrayList<String>}형식으로 반환됩니다.
     */
    public ArrayList<String> getBookEtc(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            arrayList.add(element.getElementsByClass("search-list-etc").text());
        }
        return arrayList;
    }

    /**
     * 검색된 도서중 책에서 기타 정보를 특정 위치에서 불러옵니다.<p>기타 정보로 저자, 출판년도, 출판사등 기타 정보를 가져옵니다.</p>
     *
     * @param elements 추출할 elements 받습니다.
     * @param index    요청한 위치에서 정보를 가져옵니다.
     * @return 추출된 기타 정보를 추출후 return 합니다.
     */
    public String getBookEtc(Elements elements, int index) {
        return elements.get(index).getElementsByClass("search-list-etc").text();
    }

    /**
     * 도서 검색된 책 정보중 소장 정보를 불러옵니다. <P>소장정보는 등록번호, 청구기호, 소장처, 대출가능여부, 대출정보를 불러옵니다.</P><p>단 정보중 {@link BookKeepInfo#returnDate()}는 값이 없으면 빈 값으로 반환됩니다.</p>
     * 입력 받은 {@link Elements} 값을 가지고 모든 정보를 분석후 반환합니다.
     *
     * @param elementsIndex 추출할 {@link Elements} 받습니다.
     * @return 소장정보를 반환합니다.
     */
    public ArrayList<BookKeepInfo> getKeepBookList(Elements elementsIndex) {
        ArrayList<BookKeepInfo> infoArrayList = new ArrayList<>();
        elementsIndex.forEach(element ->
                element.getElementsByClass("table-striped sponge-table-default")
                        .select("tbody > tr")
                        .forEach(i -> infoArrayList.add(addBookKeInfo(i)))
        );
        return infoArrayList;
    }

    /**
     * 도서 검색된 책 정보중 소장 정보를 불러옵니다. <P>소장정보는 등록번호, 청구기호, 소장처, 대출가능여부, 대출정보를 불러옵니다.</P><p>단 정보중 {@link BookKeepInfo#returnDate()}는 값이 없으면 빈 값으로 반환됩니다.</p>
     * 해당 지정된 인덱스 값을 가져와 반환합니다.
     *
     * @param elements 추출할 {@link Elements} 받습니다.
     * @param index    요청한 위치에서 정보를 가져옵니다.
     * @return 소장 정보를 가공하고 반환합니다.
     */
    public ArrayList<BookKeepInfo> getKeepBookList(Elements elements, int index) {
        ArrayList<BookKeepInfo> infoArrayList = new ArrayList<>();
        Elements bookKeep = elements.get(index).getElementsByClass("table-striped sponge-table-default").select("tbody").select("tr");
        bookKeep.forEach(i -> infoArrayList.add(addBookKeInfo(i)));
        return infoArrayList;
    }

    private BookKeepInfo addBookKeInfo(Element element) {
        return new BookKeepInfo(
                element.select("td").get(0).text(),
                element.select("td").get(1).text(),
                element.select("td").get(2).text(),
                element.select("td").get(3).text(),
                element.select("td").get(4).text());
    }
}
