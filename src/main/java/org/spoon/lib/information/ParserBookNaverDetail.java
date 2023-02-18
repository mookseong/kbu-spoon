package org.spoon.lib.information;

import org.spoon.lib.model.BookInfo;
import org.spoon.lib.model.NaverBookInformation;
import org.spoon.lib.model.NaverBookItems;

import java.util.*;

public class ParserBookNaverDetail extends BaseParserBookDetail implements ParserBookDetail {

    private final String searchURL = "https://lib.bible.ac.kr/Search";
    private NaverBookItems naverBookApi;
    private final NaverBookSearchAPI naverAPI;
    private Map<String, String> parserInfo;

    public ParserBookNaverDetail(String clientId, String clientSecret) {
        this.naverAPI = new NaverBookSearchAPI(clientId, clientSecret);
    }

    @Override
    public void setParsingURL(String parsingURL) {
        super.setParsingURL(parsingURL);
        this.parserInfo = getKbuBookInformation();
        NaverBookInformation naverBookInformation = this.naverAPI.getNaverApi(parserInfo.get("ISBN"));
        this.naverBookApi = naverBookInformation.items.get(0);
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
    public String getBookImage() {
        return naverBookApi.image;
    }

}
