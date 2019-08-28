package com.kzhu.netflix.data.analytics.common.impl.schema;

import com.kzhu.data.analytics.common.schema.Webdata;

import java.io.Serializable;
import java.util.Map;

public class NetflixWebdata implements Webdata, Serializable {
    private static final String SESSION_START_VALUE = "success";
    private String _device;
    private String _sev;
    private String _country;
    private String _title;

    public NetflixWebdata(Map<String, String> inputMap) {
        this._device = inputMap.getOrDefault("device", "unknown");
        this._sev = inputMap.getOrDefault("sev", "");
        this._country = inputMap.getOrDefault("country", "ZZ");
        this._title = inputMap.getOrDefault("title", "unknown");
    }

    public boolean isSessionStart() {
        return _sev.equalsIgnoreCase(SESSION_START_VALUE);
    }

    public String getDevice() {
        return _device;
    }

    public String getCountry() {
        return _country;
    }

    public String getTitle() {
        return _title;
    }

    @Override public String toString() {
        return _device +
          "," +
          _sev +
          "," +
          _country +
          "," +
          _title;
    }

    @Override
    public String hashKey() {
        return _device +
          "," +
          _country +
          "," +
          _title;
    }
}
