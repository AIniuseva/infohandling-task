package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Composite;

public class SentenceParser extends AbstractParser {

    private static final String SPLITTER = "(?!\\[.*)\\s(?![^\\[]*?\\])";

    @Override
    public Composite parse(String text) {
        return super.parse(text);
    }

    @Override
    protected String getSplitter() {
        return SPLITTER;
    }
}
