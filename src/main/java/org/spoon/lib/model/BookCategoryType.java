package org.spoon.lib.model;

public enum BookCategoryType {
    BEST_BOOK("book-best", "col-md-9 sponge-main-book book"),
    NEW_BOOK("book-new", "col-md-9 sponge-main-book book"),
    EBOOK_BOOK("book-ebook", "col-md-9 sponge-main-book book"),
    RECOMMEND_BOOK("book-recommend", "col-md-9 sponge-main-book book"),
    RECENT_RENT_BOOK("user-welcome-page-thumb", "col-md-6 bostbooklist");


    private final String bookType;
    private final String classType;

    BookCategoryType(String bookType, String classType) {
        this.bookType = bookType;
        this.classType = classType;
    }

    public String getBookType() {
        return bookType;
    }

    public String getClassType() {
        return classType;
    }
}
