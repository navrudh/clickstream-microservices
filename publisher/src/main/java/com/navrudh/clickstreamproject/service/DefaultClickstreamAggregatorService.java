package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultClickstreamAggregatorService implements ClickstreamAggregatorService {

  private final StreamSender streamSender;

  private final ClickstreamService clickstreamService;

  @Autowired
  public DefaultClickstreamAggregatorService(
      final StreamSender streamSender, final ClickstreamService clickstreamService) {
    this.streamSender = streamSender;
    this.clickstreamService = clickstreamService;
  }

  @Override
  public void process(ClickstreamDTO clickstreamDTO) throws JsonProcessingException {
    streamSender.send("click", clickstreamDTO);
  }

  @Override
  public Long getUrlCount(String url) {
    return clickstreamService.countByUrl(url);
  }
}
