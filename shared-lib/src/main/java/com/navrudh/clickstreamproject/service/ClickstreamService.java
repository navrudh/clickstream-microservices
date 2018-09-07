package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;

public interface ClickstreamService {

  void save(ClickstreamDO clickstreamDO);

  void regenerateClickUrlAggregationResults();

  Long countByUrl(String url);
}
