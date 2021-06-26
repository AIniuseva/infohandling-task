package com.epam.infohandling;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.parsing.ChainBuilder;
import com.epam.infohandling.parsing.Parser;

public class TextProcessor {

    public Composite parseText(String text) {
        Parser parser = new ChainBuilder().build();
        return parser.parse(text);
    }
}
