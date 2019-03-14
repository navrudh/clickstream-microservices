package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/*
* Interface for services that write to streams
* */
public interface StreamSender {

  <T> void send(String topic, T message) throws JsonProcessingException;
}
