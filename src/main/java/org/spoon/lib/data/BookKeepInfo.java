package org.spoon.lib.data;


/**
 * kbu 도서관에 소장된 책 정보입니다.
 *
 */
public class BookKeepInfo {
    private final String register;
    private final String number;
    private final String holdingInstitution;
    private final String loanStatus;
    private final String returnDate;

    public String getRegister() {
        return register;
    }

    public String getNumber() {
        return number;
    }

    public String getHoldingInstitution() {
        return holdingInstitution;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public String getReturnDate() {
        return returnDate;
    }

    /**
     * kbu 도서관에 소장된 책 정보입니다.
     *
     * @param register           도서 등록번호
     * @param number             도서 청구번호
     * @param holdingInstitution 도서 소장처
     * @param loanStatus         도서 대출 가능여부
     * @param returnDate         도서 반납일정<p>반납 정보가 없다면 {@code null} 반환합니다.</p>
     */
    public BookKeepInfo(String register, String number, String holdingInstitution, String loanStatus, String returnDate) {
        this.register = register;
        this.number = number;
        this.holdingInstitution = holdingInstitution;
        this.loanStatus = loanStatus;
        this.returnDate = returnDate;
    }
}
