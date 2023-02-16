package org.spoon.lib.information;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.lib.model.BookCategory;
import org.spoon.lib.model.BookInfo;

import java.io.IOException;
import java.util.*;

public class ParserBookKbuDetail extends BaseParserBookDetail implements ParserBookDetail {

    private final String searchURL = "https://lib.bible.ac.kr/Search";

    @Override
    public void setParsingURL(String parsingURL) {
        try {
            Connection conn = Jsoup.connect(parsingURL);
            this.document = conn.get();
            this.bookDetail = this.document.getElementsByClass(this.baseElementsClass);
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    @Override
    public String getBookTitle() {
        return this.bookDetail.get(0).getElementsByClass("sponge-book-title").text();
    }

    @Override
    public BookInfo getBookInformation() {
        Map<String, String> parserInfo = getKbuBookInformation();
        return new BookInfo(
                getBookTitle(),
                getBookImage(),
                parserInfo.get("ISBN"),
                parserInfo.get("청구기호"),
                parserInfo.get("DDC"),
                parserInfo.get("저자"),
                parserInfo.get("가격").replaceAll("[^0-9]", ""),
                parserInfo.get("발행사항"),
                null,
                null
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
        return this.document.getElementsByClass("page-detail-title-image").select("a > img").attr("src");
    }

}
