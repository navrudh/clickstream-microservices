package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.LibraryApplication;
import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;
import com.navrudh.clickstreamproject.domainvalue.HttpMethod;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LibraryApplication.class)
public class DefaultClickstreamServiceTest {

  @Autowired private ClickstreamService clickstreamService;

  @Autowired private MongoTemplate mongoTemplate;

  @After
  public void tearDown() {
    mongoTemplate.dropCollection(ClickstreamDO.class);
  }

  @Test
  public void save() {
    ClickstreamDO clickstreamDO = new ClickstreamDO("UID", "URL", HttpMethod.GET, 0L);

    clickstreamService.save(clickstreamDO);

    Query query = new Query();
    query.addCriteria(Criteria.where("userid").is("UID"));

    List<ClickstreamDO> clickstreamDOList = mongoTemplate.find(query, ClickstreamDO.class);

    assertThat(clickstreamDOList).isNotEmpty();
    assertThat(clickstreamDOList.get(0)).isEqualToComparingFieldByField(clickstreamDO);
  }

  @Test
  public void regenerateClickUrlAggregationResults_and_countByUrl() {
    clickstreamService.save(new ClickstreamDO("UID", "URL", HttpMethod.GET, 0L));
    clickstreamService.save(new ClickstreamDO("UID", "URL", HttpMethod.GET, 0L));

    clickstreamService.regenerateClickUrlAggregationResults();

    assertThat(clickstreamService.countByUrl("URL")).isEqualTo(2);
  }

  @Test
  public void countByUrl_expectZero() {
    assertThat(clickstreamService.countByUrl("URL")).isEqualTo(0);
  }
}
