package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import com.navrudh.clickstreamproject.util.mapper.ClickstreamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultClickstreamAggregatorConsumerService
    implements ClickstreamAggregatorConsumerService {

  private final ClickstreamService clickstreamService;

  @Autowired
  public DefaultClickstreamAggregatorConsumerService(final ClickstreamService clickstreamService) {
    this.clickstreamService = clickstreamService;
  }

  @Override
  public void consume(ClickstreamDTO clickstreamDTO) {
    clickstreamService.save(ClickstreamMapper.makeClickstreamDO(clickstreamDTO));
    clickstreamService.regenerateClickUrlAggregationResults();
  }
}
