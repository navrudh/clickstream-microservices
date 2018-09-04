package com.navrudh.clickstreamproject.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

public interface StreamListener {
  void listen(ConsumerRecord<?, ?> cr) throws IOException;
}
