package org.spoon.lib.data;

/**
 * 네이버 요청된 오류 메세지를 반환합니다. 오류 정보는 네이버 개발자 문서 확인해주세요
 * @param errorMessage 에러 메세지
 * @param errorCode 에러 코드
 */
public record NaverBookError(
        String errorMessage,
        String errorCode
) {
}
