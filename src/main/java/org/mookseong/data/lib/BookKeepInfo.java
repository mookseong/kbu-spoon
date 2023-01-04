package org.mookseong.data.lib;



public record BookKeepInfo(
        String bookRegister,
        String bookNumber,
        String holdingInstitution,
        String loanStatus,
        String returnDate
        ) {

}
