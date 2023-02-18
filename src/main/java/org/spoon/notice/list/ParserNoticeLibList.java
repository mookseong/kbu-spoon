package org.spoon.notice.list;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spoon.notice.model.PostNoticeList;
import org.spoon.notice.model.PostNoticeType;

public class ParserNoticeLibList extends BaseParserNoticeList implements ParseNoticeList {
    public ParserNoticeLibList(int pageIndex) {
        super(PostNoticeType.KBU_LIB_NOTICE, pageIndex);
        this.postQuery = "dl";
    }

    @Override
    public Elements extractPostDocument() {
        return this.document.getElementsByClass("onroad-board");
    }

    @Override
    public PostNoticeList toPostNoticeList(Element element) {
        String[] tmpText = textSplit(element.select(this.postNoticeType.getSelectAuthor()).text());
        return new PostNoticeList(
                element.select(this.postNoticeType.getSelectNum()).text(),
                element.select(this.postNoticeType.getSelectTitle()).text(),
                isAuthor(tmpText),
                isDate(tmpText),
                this.postNoticeType.getRootUrl() + element.select(this.postNoticeType.getSelectTitle()).attr("href")
        );
    }

    private String isDate(String[] tmpString) {
        for (int i = 0; i < tmpString.length; i++) {
            if (tmpString[i].equals("게시일")) {
                return tmpString[i + 1];
            }
        }
        return "정보 없음";
    }

    private String isAuthor(String[] tmpString) {
        for (int i = 0; i < tmpString.length; i++) {
            if (tmpString[i].equals("글쓴이")) {
                return tmpString[i + 1];
            }
        }
        return "정보 없음";
    }

    private String[] textSplit(String strings) {
        return strings.split(" ");
    }
}
