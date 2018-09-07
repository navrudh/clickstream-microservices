package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaListenerService implements StreamListenerService {

  @Autowired private ClickstreamAggregatorConsumerService clickstreamAggregatorConsumerService;

  @Autowired private ObjectMapper mapper;

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  @Override
  @KafkaListener(id = "clickstream", topics = "click")
  public void listen(ConsumerRecord<?, ?> cr) throws IOException {
    String ser = cr.value().toString();
    ClickstreamDTO clickstreamDTO = mapper.readValue(ser, ClickstreamDTO.class);
    clickstreamAggregatorConsumerService.consume(clickstreamDTO);
  }
}
