package org.spoon.lib.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @param lastBuildDate 검색 결과를 생성한 시간
 * @param total 총 검색 결과 개수
 * @param start 검색 시작 위치
 * @param display 	한 번에 표시할 검색 결과 개수
 * @param items 개별 검색 결과. JSON 형식의 결괏값에서는 items 속성은 {@link NaverBookItems} 배열로 개별 검색 결과를 반환합니다.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverBookApi(
        String lastBuildDate,
        int total,
        int start,
        int display,
        List<NaverBookItems> items
) {
}