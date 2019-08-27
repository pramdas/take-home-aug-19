package com.kzhu.data.analytics.common.extractor;

import com.kzhu.data.analytics.common.codec.WebdataCodec;
import com.kzhu.data.analytics.common.schema.Webdata;

public interface WebdataExtractor<T> {
    Webdata extract(T value);
}
