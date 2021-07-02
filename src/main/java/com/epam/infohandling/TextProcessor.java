package com.epam.infohandling;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.parsing.ChainBuilder;
import com.epam.infohandling.parsing.Parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextProcessor {

    private static final Logger LOGGER = LogManager.getLogger(TextProcessor.class);

    public Composite parseText(String text) {

        if (text == null || "".equals(text)) {
            return null;
        }

        Parser parser = new ChainBuilder().build();
        LOGGER.info("Text parsed.");
        return parser.parse(text);
    }
}
