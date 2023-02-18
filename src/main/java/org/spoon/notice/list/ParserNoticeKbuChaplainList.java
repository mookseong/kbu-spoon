package org.spoon.notice.list;

import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeType;

public class ParserNoticeKbuChaplainList extends BaseParserNoticeList implements ParseNoticeList {

    public ParserNoticeKbuChaplainList(int pageIndex) {
        super(PostNoticeType.KBU_CHAPLAIN_NOTICE, pageIndex);
        this.postQuery = "ul > li.tbody";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("black");
    }

}
