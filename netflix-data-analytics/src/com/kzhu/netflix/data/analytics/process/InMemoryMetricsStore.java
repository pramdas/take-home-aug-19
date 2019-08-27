package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.data.analytics.common.schema.WebdataMetrics;
import com.kzhu.netflix.data.analytics.common.impl.schema.NetflixWebdataMetrics;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMetricsStore {
  private final String _hashKey;

  private List<Webdata> _dataList = new ArrayList<>(10000);

  InMemoryMetricsStore(String hashKey) {
    this._hashKey = hashKey;
  }

  synchronized void addData(Webdata data) {
    _dataList.add(data);
  }

  public synchronized WebdataMetrics aggregate() {
    // Aggregate and create a WebdataMetrics
    WebdataMetrics metrics = new NetflixWebdataMetrics(_hashKey, _dataList.size());
    // cleanup all data and create an empty dataList.
    _dataList = new ArrayList<>(10000);
    return metrics;
  }
}
