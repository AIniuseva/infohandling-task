package com.epam.infohandling.parsing;

public abstract class AbstractParser implements Parser {

    private Parser successor;

    protected Parser getSuccessor() {
        return successor;
    }
}
