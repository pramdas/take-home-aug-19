package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.data.analytics.common.schema.WebdataMetrics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

class InMemoryDataAggregator {
  private static final Log logger = LogFactory.getLog(InMemoryDataAggregator.class);
  private Map<String, InMemoryMetricsStore> _metricsMap = new HashMap<>(10000);

  InMemoryDataAggregator() {
  }

  synchronized void addData(Webdata data) {
   InMemoryMetricsStore metricsStore = _metricsMap.getOrDefault(data.hashKey(),
     new InMemoryMetricsStore(data.hashKey()));
   metricsStore.addData(data);
   _metricsMap.put(data.hashKey(), metricsStore);
  }

  void registerTimer(long delay, long period) {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        for (Map.Entry<String, InMemoryMetricsStore> entry : _metricsMap.entrySet()) {
          WebdataMetrics metrics = entry.getValue().aggregate();
          for (Map.Entry<String, Long> metricsEntry : metrics.getMetrics().entrySet()) {
            if (metricsEntry.getValue() > 0) {
              logger.info(String.format("# Of Session Start for [%s] = %d", metricsEntry.getKey(),
                metricsEntry.getValue()));
            }
          }
        }
      }
    };
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(task, delay, period);
  }
}
