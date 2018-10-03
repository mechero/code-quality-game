package com.thepracticaldeveloper.devgame.modules.code;

public class InvalidCodeException extends Exception {
    public InvalidCodeException(String msg, Exception e) {
        super(msg, e);
    }
}
