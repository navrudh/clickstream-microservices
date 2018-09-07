package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;

public interface ClickstreamAggregatorService {
  void process(ClickstreamDTO clickstreamDTO) throws JsonProcessingException;

  Long getUrlCount(String url);
}
