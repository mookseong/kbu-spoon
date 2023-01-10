package org.spoon.lib.data;

import java.util.List;


public class NaverBookInformation {
    public String lastBuildDate; //검색 결과를 생성한 시간
    public int total; //총 검색 결과
    public int start; //검색 시작 위치
    public int display; //한 번에 표시할 검색 결과 개수
    public List<NaverBookItems> items; //  개별 검색 결과. JSON 형식의 결괏값에서는 items 속성은 {@link NaverBookItems} 배열로 개별 검색 결과를 반환합니다.

}