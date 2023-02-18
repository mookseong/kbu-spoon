package org.spoon.notice.list;

import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeType;

public class ParserNoticeKbuMainList extends BaseParserNoticeList implements ParseNoticeList {

    public ParserNoticeKbuMainList(int pageIndex) {
        super(PostNoticeType.KBU_MAIN_NOTICE, pageIndex);
        this.postQuery = "ul > li.tbody";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("black");
    }

}
