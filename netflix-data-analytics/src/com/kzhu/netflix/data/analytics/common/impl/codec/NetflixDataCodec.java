package com.kzhu.netflix.data.analytics.common.impl.codec;

import com.google.gson.Gson;
import com.kzhu.data.analytics.common.codec.WebdataCodec;
import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.netflix.data.analytics.common.impl.schema.NetflixWebdata;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class NetflixDataCodec implements WebdataCodec<String> {
  private final Gson _gson = new Gson();
  private static final Log logger = LogFactory.getLog(NetflixDataCodec.class);

  @Override
  public String encode(Webdata data) {
    return null;
  }

  @Override
  public Webdata decode(String value) {
    if (!value.trim().isEmpty()) {
      String json = value.substring(5).trim();
      if (!json.isEmpty()) {
        Map<String, String> mapData =
          (Map<String, String>) _gson.fromJson(json, Map.class);
        for (Map.Entry<String, String> entry : mapData.entrySet()) {
          logger.debug(String.format("%s = %s", entry.getKey(), entry.getValue()));
        }
        return new NetflixWebdata(mapData);
      }
    }
    return null;
  }
}
