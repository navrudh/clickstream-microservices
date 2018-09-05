package com.navrudh.clickstreamproject;

import com.navrudh.clickstreamproject.controller.ClickstreamController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClickstreamPublisherApplication.class)
public class ClickstreamPublisherApplicationTests {

  @Autowired private ClickstreamController clickstreamController;

  @Test
  public void contextLoads() {
    assertThat(clickstreamController).isNotNull();
  }
}
