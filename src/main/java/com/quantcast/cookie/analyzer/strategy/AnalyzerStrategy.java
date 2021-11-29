package com.quantcast.cookie.analyzer.strategy;

public interface AnalyzerStrategy<I, O> {

    O predict(I i);
}
