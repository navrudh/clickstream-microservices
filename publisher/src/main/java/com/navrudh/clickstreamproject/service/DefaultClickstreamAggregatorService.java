package com.navrudh.clickstreamproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navrudh.clickstreamproject.dataaccessobject.ClickstreamRepository;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultClickstreamAggregatorService implements ClickstreamAggregatorService {

  @Autowired private StreamSender streamSender;

  @Autowired private ClickstreamRepository clickstreamRepository;

  @Override
  public void process(ClickstreamDTO clickstreamDTO) throws JsonProcessingException {
    streamSender.send("click", clickstreamDTO);
  }

  @Override
  public Long getUrlCount(String url) {
    return clickstreamRepository.countByUrl(url);
  }
}
