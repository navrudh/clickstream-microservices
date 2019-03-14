package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;

/*
 * Interface for services that POST click data and GET aggregated stats
 * */
public interface ClickstreamAggregatorService {
  void process(ClickstreamDTO clickstreamDTO) throws JsonProcessingException;

  Long getUrlCount(String url);
}
