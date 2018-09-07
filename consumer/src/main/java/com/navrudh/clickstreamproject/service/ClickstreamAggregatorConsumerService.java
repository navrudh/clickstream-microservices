package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;

public interface ClickstreamAggregatorConsumerService {
  void consume(ClickstreamDTO clickstreamDTO);
}
