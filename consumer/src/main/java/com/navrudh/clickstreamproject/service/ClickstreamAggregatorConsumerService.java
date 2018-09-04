package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;

public interface ClickstreamAggregatorConsumerService {
  void consume(ClickstreamDO makeClickstreamDO);
}
