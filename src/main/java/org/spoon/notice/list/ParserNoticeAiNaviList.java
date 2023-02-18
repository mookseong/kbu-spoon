package org.spoon.notice.list;

import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeType;

public class ParserNoticeAiNaviList extends BaseParserNoticeList implements ParseNoticeList {
    public ParserNoticeAiNaviList(int pageIndex) {
        super(PostNoticeType.KBU_AINAVI_NOTICE, pageIndex);
        this.postQuery = "ul > li.tbody";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("black");
    }
}
