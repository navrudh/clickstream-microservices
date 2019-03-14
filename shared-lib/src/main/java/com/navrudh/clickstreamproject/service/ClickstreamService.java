package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;

/*
 * Interface that all services that read from a stream should provide
 * */
public interface ClickstreamService {

  void save(ClickstreamDO clickstreamDO);

  void regenerateClickUrlAggregationResults();

  Long countByUrl(String url);
}
