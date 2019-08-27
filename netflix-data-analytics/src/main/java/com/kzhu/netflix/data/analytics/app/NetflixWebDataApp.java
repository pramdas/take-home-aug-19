package com.kzhu.netflix.data.analytics.app;


import com.kzhu.netflix.data.analytics.process.NetflixWebDataStreamProcessor;

import java.io.IOException;

public class NetflixWebDataApp {
    private final String _url;

    public static void main(String[] args) {
        // TODO(kzhu) Input parameter or read from configuration file.
        String url = "https://tweet-service.herokuapp.com/sps";
        new NetflixWebDataApp(url).run();
    }

    public NetflixWebDataApp(String url) {
        this._url = url;
    }

    public void run() {
        NetflixWebDataStreamProcessor processor = new NetflixWebDataStreamProcessor(_url);
        try {
          processor.process();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
