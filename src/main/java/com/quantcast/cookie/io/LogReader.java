package com.quantcast.cookie.io;

import java.util.List;

public interface LogReader<I, T> {

    List<T> read(I request);

}
