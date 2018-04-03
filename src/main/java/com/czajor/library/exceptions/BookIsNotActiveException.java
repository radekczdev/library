package com.czajor.library.exceptions;

public class BookIsNotActiveException extends Exception {
    public BookIsNotActiveException(){
        super("This book cannot be borrowed!");
    }
}
