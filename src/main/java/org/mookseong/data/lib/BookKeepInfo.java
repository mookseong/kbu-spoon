package org.mookseong.data.lib;


/**
 * kbu 도서관에 소장된 책 정보입니다.
 * @param register 도서 등록번호
 * @param number 도서 청구번호
 * @param holdingInstitution 도서 소장처
 * @param loanStatus 도서 대출 가능여부
 * @param returnDate 도서 반납일정<p>반납 정보가 없다면 {@code null} 반환합니다.</p>
 */
public record BookKeepInfo(
        String register,
        String number,
        String holdingInstitution,
        String loanStatus,
        String returnDate
        ) {

}
