package com.kzhu.netflix.data.analytics.common.impl.extractor;

import com.kzhu.data.analytics.common.extractor.WebdataExtractor;
import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.netflix.data.analytics.common.impl.codec.NetflixDataCodec;

public class NetflixDataExtractor implements WebdataExtractor<String> {
  private final NetflixDataCodec _codec = new NetflixDataCodec();

  @Override
  public Webdata extract(String value) {
    return _codec.decode(value);
  }
}
