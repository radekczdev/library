package com.czajor.library.model;

public enum Statuses {
    ACTIVE("Book is available for borrow"),
    BORROWED("Book is borrowed"),
    WORN_OUT("Book is worn out"),
    LOST("Book has been lost");

    private String description;

    Statuses(String description) {
        this.description = description;
    }
}
