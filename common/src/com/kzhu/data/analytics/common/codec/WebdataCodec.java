package com.kzhu.data.analytics.common.codec;

import com.kzhu.data.analytics.common.schema.Webdata;

public interface WebdataCodec<T> {
    T encode(Webdata data);
    Webdata decode(T value);
}
