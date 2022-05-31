package com.artexplorer.proiectpad.exception;

import java.util.NoSuchElementException;

public class MuseumNotFoundException extends NoSuchElementException {
    public MuseumNotFoundException(String message)
    {
        super(message);
    }
}
