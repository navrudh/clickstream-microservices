package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.dataaccessobject.ClickstreamRepository;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import com.navrudh.clickstreamproject.util.mapper.ClickstreamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultClickstreamAggregatorConsumerService
    implements ClickstreamAggregatorConsumerService {

  private final ClickstreamRepository clickstreamRepository;

  @Autowired
  public DefaultClickstreamAggregatorConsumerService(
      final ClickstreamRepository clickstreamRepository) {
    this.clickstreamRepository = clickstreamRepository;
  }

  @Override
  public void consume(ClickstreamDTO clickstreamDTO) {
    clickstreamRepository.save(ClickstreamMapper.makeClickstreamDO(clickstreamDTO));
  }
}
