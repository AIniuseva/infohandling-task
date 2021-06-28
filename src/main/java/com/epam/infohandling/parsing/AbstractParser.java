package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Composite;
import com.epam.infohandling.model.Leaf;

public abstract class AbstractParser implements Parser {

    private Parser successor;

    protected Parser getSuccessor() {
        return successor;
    }

    public void setSuccessor(Parser successor) {
        this.successor = successor;
    }

    @Override
    public Composite parse(String text) {
        if (text == null || "".equals(text)) {
            return null;
        }
        Composite composite = new Composite();
        String[] parts = text.split(getSplitter());

        for (String part : parts) {
            if (getSuccessor() != null) {
                Composite inner = getSuccessor().parse(part);
                composite.add(inner);
            } else {
                composite.add(new Leaf(part));
            }

        }
        return composite;
    }

    protected abstract String getSplitter();
}
