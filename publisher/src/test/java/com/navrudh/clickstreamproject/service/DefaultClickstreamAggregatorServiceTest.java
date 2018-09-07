package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DefaultClickstreamAggregatorServiceTest {

  @InjectMocks private DefaultClickstreamAggregatorService defaultClickstreamAggregatorService;

  @Spy private StreamSender streamSender;

  @Spy private ClickstreamService clickstreamService;

  @Test
  public void process() throws Exception {

    ClickstreamDTO clickstreamDTO = new ClickstreamDTO(null, null, "get", null);

    defaultClickstreamAggregatorService.process(clickstreamDTO);

    Mockito.verify(streamSender, Mockito.times(1)).send("click", clickstreamDTO);
  }

  @Test
  public void getUrlCount() {

    defaultClickstreamAggregatorService.getUrlCount("url");

    Mockito.verify(clickstreamService, Mockito.times(1)).countByUrl("url");
  }
}
