package com.kzhu.data.analytics.process;

import com.kzhu.data.analytics.common.schema.Webdata;

import java.io.IOException;

public interface WebDataProcessor {
    void process(Webdata data) throws IOException;
}
