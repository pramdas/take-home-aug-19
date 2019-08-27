package com.kzhu.data.analytics.process;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.data.analytics.common.storage.WebDataStore;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class WebDataConsumer implements Runnable {
  private final WebDataStore _webDataStore;
  private final WebDataProcessor _processor;
  private final AtomicBoolean _running = new AtomicBoolean(true);

  public WebDataConsumer(WebDataStore store, WebDataProcessor processor) {
    this._webDataStore = store;
    this._processor = processor;
  }

  public void open() throws IOException {
    _webDataStore.open();
  }

  public void close() throws IOException {
    _webDataStore.close();
  }

  @Override
  public void run() {
    while(_running.get()) {
      try {
        _processor.process(_webDataStore.getData());
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }
}
