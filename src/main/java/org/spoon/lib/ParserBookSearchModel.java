package org.spoon.lib;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.category.BaseParserCategory;
import org.spoon.lib.model.BookKeepInfo;

import java.io.IOException;
import java.util.ArrayList;

public class ParserBookSearchModel {
    private Document document;
    private final String homeURL = "https://lib.bible.ac.kr";

    public void setParsingURL(String searchWord, int listIndex) {
        try {
            String searchURL = "https://lib.bible.ac.kr/Search";
            Connection conn = Jsoup.connect(searchURL + "/?q=" + searchWord + "&p=" + listIndex);
            this.document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    public Elements getSearchListByDocument() {
        return this.document.getElementsByClass("search-list-result");
    }

    public Elements getSearchListByDocument(Document document) {
        return document.getElementsByClass("search-list-result");
    }

    public ArrayList<String> getBookImg(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            String img = element.getElementsByTag("a").get(0).getElementsByTag("img").attr("src");
            if (!img.contains("https")) {
                arrayList.add(this.homeURL + img);
            } else {
                arrayList.add(img);
            }
        }
        return arrayList;
    }

    public String getBookImg(Elements elements, int index) {
        String img = elements.get(index).getElementsByTag("a").get(0).getElementsByTag("img").attr("src");
        if (!img.contains("https")) {
            return this.homeURL + img;
        }
        return img;
    }

    public ArrayList<String> getBookInformation(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            arrayList.add(this.homeURL + element.getElementsByTag("a").get(1).attr("href"));
        }
        return arrayList;
    }

    public String getBookInformation(Elements elements, int index) {
        return this.homeURL + elements.get(index).getElementsByTag("a").get(1).attr("href");
    }

    public ArrayList<String> getBookTitle(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            arrayList.add(element.getElementsByTag("a").get(1).text());
        }
        return arrayList;
    }

    public String getBookTitle(Elements elements, int index) {
        return elements.get(index).getElementsByTag("a").get(1).text();
    }

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

    public String getBookISBN(Elements elements, int index) {
        String ISBN = elements.get(index).getElementsByTag("a").get(2).attr("href").replace("/Naver/NaverDetail?isbn=", "");
        if (ISBN.contains("/Print")) {
            return null;
        }
        return ISBN;
    }

    public ArrayList<String> getBookEtc(Elements elementsIndex) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            arrayList.add(element.getElementsByClass("search-list-etc").text());
        }
        return arrayList;
    }


    public String getBookEtc(Elements elements, int index) {
        return elements.get(index).getElementsByClass("search-list-etc").text();
    }


    public ArrayList<BookKeepInfo> getKeepBookList(Elements elementsIndex) {
        ArrayList<BookKeepInfo> infoArrayList = new ArrayList<>();
        for (Element element : elementsIndex) {
            element.getElementsByClass("table-striped sponge-table-default").select("tbody > tr")
                    .forEach(i -> infoArrayList.add(addBookKeInfo(i)));
        }

        return infoArrayList;
    }

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
