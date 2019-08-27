package com.kzhu.data.analytics.common.function;

import com.kzhu.data.analytics.common.schema.Webdata;

public interface WebdataFilter {
    boolean filter(Webdata webdata);
}
