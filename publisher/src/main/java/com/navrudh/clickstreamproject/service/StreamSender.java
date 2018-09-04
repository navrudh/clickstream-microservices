package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface StreamSender {

  <T> void send(String topic, T message) throws JsonProcessingException;
}
