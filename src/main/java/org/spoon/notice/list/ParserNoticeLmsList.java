package org.spoon.notice.list;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeList;
import org.spoon.notice.model.PostNoticeType;

import java.util.List;

public class ParserNoticeLmsList extends BaseParserNoticeList implements ParseNoticeList {

    public ParserNoticeLmsList(String pageIndex) {
        super(PostNoticeType.KBU_LMS_NOTICE, pageIndex);
        this.postQuery = "table > tbody > tr";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("ubboard_container");
    }

}
