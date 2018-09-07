package com.navrudh.clickstreamproject.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;

public interface StreamListenerService {
  void listen(ConsumerRecord<?, ?> cr) throws IOException;
}
