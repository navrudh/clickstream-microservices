package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaListener implements StreamListener {

  @Autowired private ClickstreamAggregatorConsumerService clickstreamAggregatorConsumerService;

  @Autowired private ObjectMapper mapper;

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  @Override
  @org.springframework.kafka.annotation.KafkaListener(id = "clickstream", topics = "click")
  public void listen(ConsumerRecord<?, ?> cr) throws IOException {
    String ser = cr.value().toString();
    ClickstreamDO clickstreamDO = mapper.readValue(ser, ClickstreamDO.class);
    clickstreamAggregatorConsumerService.consume(clickstreamDO);
  }
}
