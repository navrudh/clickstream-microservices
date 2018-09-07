package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender implements StreamSender {

  private static org.slf4j.Logger LOG = LoggerFactory.getLogger(KafkaSender.class);

  private final ObjectMapper mapper;

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  public KafkaSender(
      final KafkaTemplate<String, String> kafkaTemplate, final ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.mapper = objectMapper;
  }

  @Override
  public <T> void send(final String topic, final T message) throws JsonProcessingException {

    LOG.info("send()");

    String serMessage = mapper.writeValueAsString(message);

    LOG.info("send(): " + serMessage);

    kafkaTemplate.send(topic, serMessage);
  }
}
