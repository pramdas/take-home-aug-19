package com.kzhu.netflix.data.analytics.process;

import com.kzhu.data.analytics.common.config.WebdataConfig;
import com.kzhu.data.analytics.common.storage.WebDataStore;
import com.kzhu.data.analytics.common.schema.Webdata;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class InMemoryDataStore implements WebDataStore {
  private static final Log logger = LogFactory.getLog(InMemoryDataStore.class);

  private WebdataConfig _config;
  private List<ArrayBlockingQueue<Webdata>> _dataQueueList = null;
  private int _readIndex = 0;
  private int _writeIndex = 0;
  private int _parallel = -1;

  public InMemoryDataStore(WebdataConfig config) {
    super();
    this._config = config;
  }

  @Override
  public void open() {
    synchronized (this) {
      if (_dataQueueList == null) {
        // initialize list
        int parallelism =
          Integer.valueOf(_config.getConfig("data.store.partition.num"));
        int capacity =
          Integer.valueOf(_config.getConfig("data.store.partition.capacity"));
        List<ArrayBlockingQueue<Webdata>> queueList = new ArrayList<>(parallelism);

        for (int i = 0; i < parallelism; i++) {
          ArrayBlockingQueue<Webdata> dataQueueList = new ArrayBlockingQueue<>(capacity);
          queueList.add(dataQueueList);
        }
        _dataQueueList = Collections.synchronizedList(queueList);
        _parallel = parallelism;
      }
    }
  }

  @Override
  public void close() {
    // Do nothing here as there is no connection need to close for in-memory DataStore.
  }

  @Override
  public void addData(Webdata data) {
    // Todo(kzhu): we may want to keep a size limit for the queue.
    int index = getCurrentWriteIndex();
    logger.debug(String.format("Adding data %s at index: %d", data.toString(), index));
    _dataQueueList.get(index).add(data);
  }

  @Override
  public Webdata getData() {
    int index = getCurrentReadIndex();
    int timeout = Integer.valueOf(_config.getConfig("data.store.poll.time.ms"));
    try {
      Webdata data = _dataQueueList.get(index).poll(timeout, TimeUnit.MILLISECONDS);
      if (data != null) {
        logger.debug(String.format("Getting data %s at index: %d", data.toString(), index));
      }
      return data;
    } catch (InterruptedException ie) {
      ie.printStackTrace();
      return null;
    }
  }

  // Make get current read/write index separated in order to implement read/write scheduling
  // separately although current implementation is the same.
  private synchronized int getCurrentReadIndex() {
    if (_readIndex == _parallel - 1) {
      _readIndex = 0;
    }
    return _readIndex++;
  }

  private synchronized int getCurrentWriteIndex() {
    if (_writeIndex == _parallel - 1) {
      _writeIndex = 0;
    }
    return _writeIndex++;
  }
}
