package org.spoon;

import org.junit.jupiter.api.Test;
import org.spoon.notice.list.*;
import org.spoon.notice.model.PostNoticeList;

import java.util.List;

class SpoonNoticeTest {

    @Test
    void getKbuNoticeList(){
        Spoon spoon = new Spoon();
        List<PostNoticeList> postNoticeLists =spoon.getKbuNoticeList(new ParserNoticeAiNaviList(1));
        System.out.println(" ");
        System.out.println("bookRentBook");
        for (PostNoticeList noticeList : postNoticeLists) {
            inputNoticeList(noticeList);
        }
    }

    private void inputNoticeList(PostNoticeList noticeList){
        System.out.println("글 번호 : " + noticeList.getPageNum());
        System.out.println("글 제목 : " + noticeList.getTitle());
        System.out.println("글 작성자 : " + noticeList.getAuthor());
        System.out.println("글 작성일 : " + noticeList.getDate());
        System.out.println("글 url : " + noticeList.getUrl());
        System.out.println("-----------------------------------");
    }

}