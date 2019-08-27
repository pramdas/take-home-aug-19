package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.config.WebdataConfig;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStoreConfig implements WebdataConfig {
  private final static Map<String, String> configMap = new HashMap<>();
  static {
    configMap.put("data.store.partition.num", "10");
    configMap.put("data.store.partition.capacity", "100000");
    configMap.put("data.store.poll.time.ms", "10");

  }
  @Override
  public String getConfig(String configName) {
    return configMap.getOrDefault(configName, "");
  }
}
