package com.kzhu.netflix.data.analytics.common.impl.function;

import com.kzhu.data.analytics.common.function.WebdataFilter;
import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.netflix.data.analytics.common.impl.schema.NetflixWebdata;

public class NetflixDataFilter implements WebdataFilter {
  @Override
  public boolean filter(Webdata webdata) {
    if (webdata instanceof NetflixWebdata) {
      NetflixWebdata netflixWebdata = (NetflixWebdata) webdata;
      return netflixWebdata.isSessionStart();
    } else {
      return false;
    }
  }
}
