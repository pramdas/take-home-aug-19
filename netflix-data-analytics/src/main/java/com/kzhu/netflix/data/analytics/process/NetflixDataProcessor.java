package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.data.analytics.process.WebDataProcessor;

import java.io.IOException;

public class NetflixDataProcessor implements WebDataProcessor {
  private final InMemoryDataAggregator _manager = new InMemoryDataAggregator();
  long _delay = 1L;
  long _period = 5000L;

  public NetflixDataProcessor() {
    _manager.registerTimer(_delay, _period);
  }

  @Override
  public void process(Webdata data) throws IOException {
    if (data != null) {
      _manager.addData(data);
    }
  }
}
