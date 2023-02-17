package org.spoon.lib.information;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookInfo;
import org.spoon.lib.model.NaverBookInformation;
import org.spoon.lib.model.NaverBookItems;

import java.io.*;
import java.util.*;

public class ParserBookNaverDetail extends BaseParserBookDetail implements ParserBookDetail {

    private final String searchURL = "https://lib.bible.ac.kr/Search";
    private NaverBookItems naverBookApi;
    private final NaverBookSearchAPI naverAPI;
    private Map<String, String> parserInfo;

    public ParserBookNaverDetail(String clientId, String clientSecret ) {
        this.naverAPI = new NaverBookSearchAPI(clientId, clientSecret);
    }

    @Override
    public void setParsingURL(String parsingURL) {
        try {
            Connection conn = Jsoup.connect(parsingURL);
            this.document = conn.get();
            this.bookDetail = this.document.getElementsByClass(this.baseElementsClass);
            this.parserInfo = getKbuBookInformation();
            NaverBookInformation naverBookInformation = this.naverAPI.getNaverApi(parserInfo.get("ISBN"));
            this.naverBookApi = naverBookInformation.items.get(0);
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    @Override
    public String getBookTitle() {
        return this.naverBookApi.title;
    }

    @Override
    public BookInfo getBookInformation() {
        return new BookInfo(
                getBookTitle(),
                getBookImage(),
                this.naverBookApi.isbn,
                this.parserInfo.get("청구기호"),
                this.parserInfo.get("DDC"),
                this.naverBookApi.author,
                this.naverBookApi.discount,
                this.naverBookApi.publisher,
                this.naverBookApi.pubdate,
                this.naverBookApi.description
        );
    }

    @Override
    public List<BookCategory> getRelatedBook() {
        ArrayList<BookCategory> bookCategories = new ArrayList<>();
        for (Element element : getRelatedList()) {
            bookCategories.add(toBookCategory(element));
        }
        return bookCategories;
    }

    @Override
    public String getBookImage() {
        return naverBookApi.image;
    }

}
