package com.navrudh.clickstreamproject.service;

import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;
import com.navrudh.clickstreamproject.domainobject.ClickstreamUrlAggregationDO;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * Default clickstream service implementation that writes to MongoDB
 * */
@Service
public class DefaultClickstreamService implements ClickstreamService {

  private final MongoOperations mongoOperations;

  public DefaultClickstreamService(final MongoOperations mongoOperations) {
    this.mongoOperations = mongoOperations;
  }

  @Override
  public void save(ClickstreamDO clickstreamDO) {
    this.mongoOperations.save(clickstreamDO);
  }

  @Override
  public void regenerateClickUrlAggregationResults() {
    TypedAggregation<ClickstreamDO> aggregation =
        Aggregation.newAggregation(
            ClickstreamDO.class,
            Aggregation.project("url"),
            Aggregation.group("url").count().as("count"),
            Aggregation.out("url-count"));

    this.mongoOperations.aggregate(aggregation, ClickstreamUrlAggregationDO.class);
  }

  @Override
  public Long countByUrl(String url) {
    return Optional.ofNullable(
            this.mongoOperations.findById(url, ClickstreamUrlAggregationDO.class))
        .map(ClickstreamUrlAggregationDO::getCount)
        .orElse(0L);
  }
}
