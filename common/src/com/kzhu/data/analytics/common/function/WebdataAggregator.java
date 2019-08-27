package com.kzhu.data.analytics.common.function;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.data.analytics.common.schema.WebdataMetrics;

import java.util.Collection;

public interface WebdataAggregator {
  WebdataMetrics agg(Collection<Webdata> webdataCollection);
}
