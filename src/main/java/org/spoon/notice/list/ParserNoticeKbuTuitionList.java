package org.spoon.notice.list;

import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeType;

public class ParserNoticeKbuTuitionList extends BaseParserNoticeList implements ParseNoticeList {

    public ParserNoticeKbuTuitionList(int pageIndex) {
        super(PostNoticeType.KBU_TUITION_NOTICE, pageIndex);
        this.postQuery = "ul > li.tbody";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("black");
    }

}
