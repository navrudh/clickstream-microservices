package com.navrudh.clickstreamproject.domainobject;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "url-count")
public class ClickstreamUrlAggregationDO {
  @Id private String url;
  private Long count;

  public Long getCount() {
    return count;
  }
}
