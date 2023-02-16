package org.spoon.lib.search;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookKeepInfo;
import org.spoon.lib.model.BookSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserBookSearchModel implements ParserBookSearch {

    private Elements searchDocument;
    private final String homeURL = "https://lib.bible.ac.kr";

    public void setParsingURL(String searchWord, int index) {
        try {
            String searchURL = "https://lib.bible.ac.kr/Search";
            Connection conn = Jsoup.connect(searchURL + "/?q=" + searchWord + "&p=" + index);
            Document document = conn.get();
            this.searchDocument = document.getElementsByClass("search-list-result");
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    @Override
    public List<BookSearch> getBookSearchListBySearch() {
        ArrayList<BookSearch> bookCategories = new ArrayList<>();

        for (int i = 0; i < searchDocument.size(); i++) {
            bookCategories.add(ToBookSearch(i));
        }
        return bookCategories;
    }

    private BookSearch ToBookSearch(int index) {
        return new BookSearch(
                getBookTitle(index),
                getBookEtc(index),
                getBookImg(index),
                getBookInformation(index),
                getBookIsbn(index)
        );
    }

    @Override
    public List<String> getBookImg() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : this.searchDocument) {
            String img = bookImgByElement(element);
            if (!img.contains("https")) {
                arrayList.add(this.homeURL + img);
            } else {
                arrayList.add(img);
            }
        }
        return arrayList;
    }

    @Override
    public String getBookImg(int index) {
        String img = bookImgByElement(this.searchDocument.get(index));
        if (!img.contains("https")) {
            return this.homeURL + img;
        }
        return img;
    }

    @Override
    public List<String> getBookInformation() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : this.searchDocument) {
            arrayList.add(this.homeURL + bookInfoByElement(element));
        }
        return arrayList;
    }

    @Override
    public String getBookInformation(int index) {
        return this.homeURL + bookInfoByElement(this.searchDocument.get(index));
    }

    @Override
    public List<String> getBookTitle() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : this.searchDocument) {
            arrayList.add(titleByElement(element));
        }
        return arrayList;
    }

    @Override
    public String getBookTitle(int index) {
        return titleByElement(this.searchDocument.get(index));
    }

    @Override
    public List<String> getBookIsbn() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : this.searchDocument) {
            String ISBN = bookIsbnByElement(element);
            if (ISBN.contains("/Print")) {
                arrayList.add(null);
            } else {
                arrayList.add(ISBN);
            }
        }
        return arrayList;
    }

    @Override
    public String getBookIsbn(int index) {
        String ISBN = bookIsbnByElement(this.searchDocument.get(index));
        if (ISBN.contains("/Print")) {
            return null;
        }
        return ISBN;
    }

    @Override
    public List<String> getBookEtc() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : this.searchDocument) {
            arrayList.add(bookEtcByElement(element));
        }
        return arrayList;
    }

    @Override
    public String getBookEtc(int index) {
        return bookEtcByElement(this.searchDocument.get(index));
    }

    @Override
    public List<BookKeepInfo> getKeepBookList() {
        ArrayList<BookKeepInfo> infoArrayList = new ArrayList<>();
        for (Element element : this.searchDocument) {
            getKeepBookByElement(element).forEach(i -> infoArrayList.add(addBookKeInfo(i)));
        }

        return infoArrayList;
    }

    @Override
    public List<BookKeepInfo> getKeepBookList(int index) {
        ArrayList<BookKeepInfo> infoArrayList = new ArrayList<>();
        getKeepBookByElement(this.searchDocument.get(index)).forEach(i -> infoArrayList.add(addBookKeInfo(i)));
        return infoArrayList;
    }


    private String titleByElement(Element element) {
        return element.getElementsByTag("a").get(1).text();
    }

    private String bookImgByElement(Element element) {
        return element.getElementsByTag("a").get(0).getElementsByTag("img").attr("src");
    }

    private String bookInfoByElement(Element element) {
        return element.getElementsByTag("a").get(1).attr("href");
    }

    private String bookIsbnByElement(Element element) {
        return element.getElementsByTag("a").get(2).attr("href").replace("/Naver/NaverDetail?isbn=", "");
    }

    private String bookEtcByElement(Element element) {
        return element.getElementsByClass("search-list-etc").text();
    }

    private Elements getKeepBookByElement(Element element) {
        return element.getElementsByClass("table-striped sponge-table-default").select("tbody > tr");
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
