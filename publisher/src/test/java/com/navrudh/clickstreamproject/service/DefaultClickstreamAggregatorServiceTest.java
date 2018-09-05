package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.dataaccessobject.ClickstreamRepository;
import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;
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

  @Spy private ClickstreamRepository clickstreamRepository;

  @Test
  public void process() throws Exception {

    ClickstreamDO clickstreamDO = new ClickstreamDO(null, null, null, null);

    defaultClickstreamAggregatorService.process(clickstreamDO);

    Mockito.verify(streamSender, Mockito.times(1)).send("click", clickstreamDO);
  }

  @Test
  public void getUrlCount() {

    defaultClickstreamAggregatorService.getUrlCount("url");

    Mockito.verify(clickstreamRepository, Mockito.times(1)).countByUrl("url");
  }
}
