package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.extractor.WebdataExtractor;
import com.kzhu.data.analytics.common.function.WebdataFilter;
import com.kzhu.data.analytics.common.storage.WebDataStore;
import com.kzhu.data.analytics.process.WebDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// This is the connector to web stream. It services as data producer and write into DataStore.
public class SimpleWebDataSource implements WebDataSource {
    private final String _url;
    private final WebdataFilter _filter;
    private final WebdataExtractor<String> _extractor;

    public SimpleWebDataSource(String url, WebdataExtractor<String> extractor, WebdataFilter filter) {
        this._url = url;
        this._filter = filter;
        this._extractor = extractor;
    }

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    // This is the function to add the data into DataStore, the read is in parallel, however, the
    // write to DataStore is not yet, depending on the implementation of DataStore, it is
    // possible to make it parallel write.
    @Override
    public void read(WebDataStore dataStore) throws IOException {
        URL websiteUrl = new URL(_url);
        BufferedReader in = new BufferedReader(new InputStreamReader(websiteUrl.openStream()));
        // Process web stream in parallel
        // - Extract
        // - Filter
        // - Save into Datastore. TODO(kzhu): create a separated DataStore for each thread.
        in.lines().parallel()
          .map(str -> _extractor.extract(str))
          .filter(data -> _filter.filter(data))
          .forEach(data -> { if (data != null) dataStore.addData(data); });
    }
}
