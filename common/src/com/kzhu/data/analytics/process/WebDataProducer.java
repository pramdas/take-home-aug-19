package com.kzhu.data.analytics.process;

import com.kzhu.data.analytics.common.storage.WebDataStore;

import java.io.IOException;

public class WebDataProducer implements Runnable {
  private WebDataSource _source;
  private WebDataStore _store;

  public WebDataProducer(WebDataSource source, WebDataStore store) {
    this._source = source;
    this._store = store;
  }

  public void open() throws IOException {
    _source.open();
    _store.open();
  }

  public void close() throws IOException {
    _store.close();
    _source.close();
  }

  public void run() {
    try {
      _source.read(_store);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
