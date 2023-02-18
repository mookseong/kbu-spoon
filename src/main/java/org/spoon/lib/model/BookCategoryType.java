package org.spoon.lib.model;

public enum BookCategoryType {

    BEST_BOOK("#book-best > div:nth-child(2) > div > ul > li > a", "col-md-9 sponge-main-book book"),
    NEW_BOOK("#book-new > div:nth-child(2) > div > ul > li > a", "col-md-9 sponge-main-book book"),
    EBOOK_BOOK("#book-ebook > div:nth-child(2) > div > ul > li > a", "col-md-9 sponge-main-book book"),
    RECOMMEND_BOOK("#book-recommend > div:nth-child(2) > div > ul > li > a", "col-md-9 sponge-main-book book"),
    RECENT_RENT_BOOK("ul > li > a", "col-md-6 bostbooklist");


    private final String bookQuery;
    private final String classType;

    BookCategoryType(String bookType, String classType) {
        this.bookQuery = bookType;
        this.classType = classType;
    }

    public String getBookType() {
        return bookQuery;
    }

    public String getClassType() {
        return classType;
    }
}
