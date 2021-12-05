package com.quantcast.cookie.io;

import java.util.List;

public interface LogReader<I, T> {

    T read(I request);

    T readWithFrequency(I request);

}
