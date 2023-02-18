package org.spoon.notice.list;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeList;
import org.spoon.notice.model.PostNoticeType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseParserNoticeList {
    protected final String parsingUrl;
    protected final PostNoticeType postNoticeType;
    protected String postQuery;
    protected Document document;


    public BaseParserNoticeList(PostNoticeType postNoticeType, int pageIndex) {
        this.postNoticeType = postNoticeType;
        this.parsingUrl = postNoticeType.getParsingUrl();
        setParsingURL(pageIndex);
    }
    abstract Elements extractPostDocument();

    public void setParsingURL(int pageIndex) {
        try {
            Connection conn = Jsoup.connect(this.parsingUrl + pageIndex);
            this.document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException("사이트 문서화 실패", e);
        }
    }

    public List<PostNoticeList> getPostList() {
        Elements elements = extractPostDocument().select(this.postQuery);
        return toArrayList(elements);
    }

    protected List<PostNoticeList> toArrayList(Elements elements) {
        ArrayList<PostNoticeList> arrayList = new ArrayList<>();
        for (Element element : elements) {
            arrayList.add(toPostNoticeList(element));
        }
        return arrayList;
    }

    protected PostNoticeList toPostNoticeList(Element element) {
        return new PostNoticeList(
                element.select(this.postNoticeType.getSelectNum()).text(),
                element.select(this.postNoticeType.getSelectTitle()).text(),
                element.select(this.postNoticeType.getSelectAuthor()).text(),
                element.select(this.postNoticeType.getSelectDate()).text(),
                this.postNoticeType.getRootUrl() + element.select(this.postNoticeType.getSelectTitle()).attr("href")
        );
    }
}
