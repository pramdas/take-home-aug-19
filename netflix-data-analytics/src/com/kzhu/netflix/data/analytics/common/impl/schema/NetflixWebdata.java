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

    public String get_device() {
        return _device;
    }

    public String get_country() {
        return _country;
    }

    public String get_title() {
        return _title;
    }

    @Override public String toString() {
        return new StringBuilder()
          .append(_device)
          .append(",")
          .append(_sev)
          .append(",")
          .append(_country)
          .append(",")
          .append(_title)
          .toString();
    }

    @Override
    public String hashKey() {
        return new StringBuilder()
          .append(_device)
          .append(",")
          .append(_country)
          .append(",")
          .append(_title)
          .toString();
    }
}
