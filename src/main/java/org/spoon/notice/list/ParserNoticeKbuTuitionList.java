package org.spoon.notice.list;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeList;
import org.spoon.notice.model.PostNoticeType;

import java.util.List;

public class ParserNoticeKbuTuitionList extends BaseParserNoticeList implements ParseNoticeList {
    private final String postQuery;

    public ParserNoticeKbuTuitionList(String pageIndex) {
        super(PostNoticeType.KBU_TUITION_NOTICE, pageIndex);
        this.postQuery = "ul > li.tbody";
    }

    @Override
    public Element extractPostDocument() {
        return this.document.getElementById("ModuleBoardListForm");
    }

    @Override
    public List<PostNoticeList> getPostList() {
        Elements elements = extractPostDocument().select(this.postQuery);
        return toArrayList(elements);
    }
}
