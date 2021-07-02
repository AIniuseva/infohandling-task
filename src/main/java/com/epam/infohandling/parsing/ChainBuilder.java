package com.epam.infohandling.parsing;

public class ChainBuilder {

    public Parser build() {
        AbstractParser textParser = new TextParser();
        AbstractParser paragraphParser = new ParagraphParser();
        AbstractParser sentenceParser = new SentenceParser();

        textParser.setSuccessor(paragraphParser);
        paragraphParser.setSuccessor(sentenceParser);
        sentenceParser.setSuccessor(null);

        return textParser;
    }
}
