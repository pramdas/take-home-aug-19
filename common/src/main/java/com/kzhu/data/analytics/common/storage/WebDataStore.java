package com.kzhu.data.analytics.common.storage;

import com.kzhu.data.analytics.common.schema.Webdata;

// This is the interface that contains all the Webdata, it could hold all in memory or be serialized to a
// particular file.
public interface WebDataStore {
    void open();
    void close();
    void addData(Webdata data);
    Webdata getData();
}