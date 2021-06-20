package com.epam.infohandling.parsing;

import com.epam.infohandling.model.Composite;

public interface Parser {
    Composite parse(String text);
}
