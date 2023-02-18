package org.spoon.notice.list;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeList;
import org.spoon.notice.model.PostNoticeType;

import java.util.List;

public class ParserNoticeKbuChaplainList extends BaseParserNoticeList implements ParseNoticeList {

    public ParserNoticeKbuChaplainList(String pageIndex) {
        super(PostNoticeType.KBU_CHAPLAIN_NOTICE, pageIndex);
        this.postQuery = "ul > li.tbody";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("black");
    }

}
