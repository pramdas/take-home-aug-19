package com.kzhu.data.analytics.process;

import com.kzhu.data.analytics.common.storage.WebDataStore;

import java.io.IOException;

public interface WebDataSource {
  void open()throws IOException;
  void close() throws IOException;
  void read(WebDataStore dataStore) throws IOException;
}
