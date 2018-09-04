package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;

public interface ClickstreamAggregatorService {
  void process(ClickstreamDO makeClickstreamDO) throws JsonProcessingException;

  Long getUrlCount(String url);
}
