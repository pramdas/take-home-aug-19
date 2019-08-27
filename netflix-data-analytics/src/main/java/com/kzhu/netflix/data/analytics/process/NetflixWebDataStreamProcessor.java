package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.storage.WebDataStore;
import com.kzhu.data.analytics.process.WebDataConsumer;
import com.kzhu.data.analytics.process.WebDataProcessor;
import com.kzhu.data.analytics.process.WebDataProducer;
import com.kzhu.data.analytics.process.WebDataSource;
import com.kzhu.netflix.data.analytics.common.impl.extractor.NetflixDataExtractor;
import com.kzhu.netflix.data.analytics.common.impl.function.NetflixDataFilter;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NetflixWebDataStreamProcessor {
  private final String _url;

  public NetflixWebDataStreamProcessor(String url) {
    this._url = url;
  }

  public void process() throws IOException {
    WebDataStore store = new InMemoryDataStore(new InMemoryDataStoreConfig());
    WebDataSource source = new SimpleWebDataSource(_url, new NetflixDataExtractor(),
      new NetflixDataFilter());
    WebDataProducer producer = new WebDataProducer(source, store);
    producer.open();

    WebDataProcessor processor = new NetflixDataProcessor();
    WebDataConsumer consumer = new WebDataConsumer(store, processor);
    consumer.open();

    ThreadPoolExecutor producerExecutor =
      (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    producerExecutor.execute(producer);

    ThreadPoolExecutor consumerExecutor =
      (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    consumerExecutor.execute(consumer);

    try {
      Thread.sleep(1000000);
    } catch (InterruptedException e) {
      System.out.println("Done !!!");
    }
  }
}
