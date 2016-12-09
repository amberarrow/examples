package com.example.myapexapp;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.apex.malhar.kafka.KafkaMetrics;
import org.apache.apex.malhar.kafka.KafkaMetrics.KafkaConsumerStats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.api.AutoMetric;
import com.datatorrent.api.Stats;
import com.datatorrent.api.Stats.OperatorStats;
import com.datatorrent.api.StatsListener;
import com.datatorrent.api.StatsListener.BatchedOperatorStats;

public class KafkaStatsListener implements StatsListener, Serializable {

  private static final Logger logger = LoggerFactory.getLogger(KafkaStatsListener.class);

  @Override
  public Response processStats(BatchedOperatorStats stats)
  {
      Response response = new Response();

      int operatorId = stats.getOperatorId();
      long windowId = stats.getCurrentWindowId();
      logger.info("processStats: operator: {}, window: {}", operatorId, windowId);

      List<OperatorStats> opStatList = stats.getLastWindowedStats();
      logger.info("processStats: opStatList.size = {}", opStatList.size());

      int i = 0;
      for (OperatorStats os : opStatList) {
        Map<String, Object> metrics = os.metrics;
        logger.info("{}: processStats: metrics.size = {}", i, metrics.size());

        for (Map.Entry<String, Object> entry : metrics.entrySet()) {
          String key = entry.getKey();
          Object value = entry.getValue();
          String className = value.getClass().getName();
          logger.info("  metric: key = {}, value.class = {}", key, className);
          if (! "org.apache.apex.malhar.kafka.KafkaMetrics".equals(className))
            continue;    // not interesting

          // print some accumulated consumer metrics
          KafkaMetrics kMetrics = (KafkaMetrics)value;
          KafkaConsumerStats[] statsList = kMetrics.getStats();
          for (KafkaConsumerStats kcs : statsList) {
            logger.info("bytes-consumed-rate = {}, records-consumed-rate = {}",
                        kcs.bytesPerSec, kcs.msgsPerSec);
          }
        }
        ++i;
      }
      
      return response;
  }

}
