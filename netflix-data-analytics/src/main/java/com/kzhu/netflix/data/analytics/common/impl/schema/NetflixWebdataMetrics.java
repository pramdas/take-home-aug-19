package com.kzhu.netflix.data.analytics.common.impl.schema;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.data.analytics.common.schema.WebdataMetrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetflixWebdataMetrics implements WebdataMetrics {
  private final String _key;
  private final long _numOfSessionStart;
  public NetflixWebdataMetrics(String key, long numOfSessionStart) {
    this._key = key;
    this._numOfSessionStart = numOfSessionStart;
  }

  @Override
  public Map<String, Long> getMetrics() {
    Map<String, Long> metrics = new HashMap();
    metrics.put(_key, _numOfSessionStart);
    return metrics;
  }
}
