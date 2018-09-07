package com.navrudh.clickstreamproject;

import com.navrudh.clickstreamproject.service.ClickstreamService;
import com.navrudh.clickstreamproject.service.DefaultClickstreamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class LibraryApplicationTests {

  @Autowired private ClickstreamService clickstreamService;

  @Autowired private DefaultClickstreamService defaultClickstreamService;

  @Test
  public void contextLoads() {
    assertThat(clickstreamService).isNotNull();
    assertThat(defaultClickstreamService).isNotNull();
  }
}
