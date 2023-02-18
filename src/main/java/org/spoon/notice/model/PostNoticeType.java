package org.spoon.notice.model;

public enum PostNoticeType {
    KBU_MAIN_NOTICE(
            "https://www.bible.ac.kr/ko/life/notice/list/",
            "span.loopnum",
            "span.title > a",
            "span.name > span",
            "span.reg_date > time",
            "https://www.bible.ac.kr"
    ),
    KBU_TUITION_NOTICE(
            "https://www.bible.ac.kr/ko/life/tuition_notice/list/",
            "span.loopnum",
            "span.title > a",
            "span.name > span",
            "span.reg_date > time",
            "https://www.bible.ac.kr"
    ),
    KBU_CHAPLAIN_NOTICE(
            "https://www.bible.ac.kr/ko/illip/notice/list/",
            "span.loopnum",
            "span.title > a",
            "span.name > span",
            "span.reg_date > time",
            "https://www.bible.ac.kr"
    ),
    KBU_LMS_NOTICE(
            "https://lms.bible.ac.kr/mod/ubboard/view.php?id=1&page=",
            "tr > td:nth-child(1)",
            "tr > td:nth-child(2) > a",
            "tr > td:nth-child(3)",
            "tr > td:nth-child(4)",
            ""),

    KBU_LIB_NOTICE("https://lib.bible.ac.kr/Board?n=notice&p=",
            "dt",
            "dd > a",
            "dd",
            "dd",
            "https://lib.bible.ac.kr"
    ),
    KBU_AINAVI_NOTICE("https://ainavi.bible.ac.kr/ko/guide/notice/list/",
            "span.loopnum",
            "span.title > a",
            "span.name > span",
            "span.reg_date > time",
            "https://ainavi.bible.ac.kr"
    );
    private final String parsingUrl;
    private final String selectNum;
    private final String selectTitle;
    private final String selectAuthor;
    private final String selectDate;
    private final String rootUrl;

    PostNoticeType(String parsingUrl, String selectNum, String selectTitle, String selectAuthor, String selectDate, String rootUrl) {
        this.parsingUrl = parsingUrl;
        this.selectNum = selectNum;
        this.selectTitle = selectTitle;
        this.selectAuthor = selectAuthor;
        this.selectDate = selectDate;
        this.rootUrl = rootUrl;
    }

    public String getSelectNum() {
        return selectNum;
    }

    public String getSelectTitle() {
        return selectTitle;
    }

    public String getSelectAuthor() {
        return selectAuthor;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public String getParsingUrl() {
        return parsingUrl;
    }

    public String getRootUrl() {
        return rootUrl;
    }
}
