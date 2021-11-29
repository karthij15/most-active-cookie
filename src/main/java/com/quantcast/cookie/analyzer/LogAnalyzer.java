package com.quantcast.cookie.analyzer;

public interface LogAnalyzer<I, O> {

    O analyze(I i);
}
