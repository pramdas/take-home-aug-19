package com.kzhu.netflix.data.analytics.common.impl;

import com.kzhu.data.analytics.common.schema.Webdata;
import com.kzhu.netflix.data.analytics.common.impl.codec.NetflixDataCodec;
import com.kzhu.netflix.data.analytics.common.impl.schema.NetflixWebdata;
import org.junit.Assert;
import org.junit.Test;

public class NetflixDataCodecTest {
  @Test
  public void testDecode() {
    NetflixDataCodec codec = new NetflixDataCodec();
    String input = "data: {\"device\":\"xbox_one\",\"sev\":\"error\",\"title\":\"orange is the new black\",\"country\":\"IND\",\"time\":1515445354624}";
    NetflixWebdata data = (NetflixWebdata) codec.decode(input);
    Assert.assertEquals("xbox_one", data.getDevice());


  }
}
