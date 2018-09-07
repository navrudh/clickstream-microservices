package com.navrudh.clickstreamproject.domainobject;

import com.navrudh.clickstreamproject.domainvalue.HttpMethod;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clickstream")
public class ClickstreamDO {

  private String userid;

  private String url;

  private HttpMethod type;

  private Long timestamp;

  @Version private Long version;

  private ClickstreamDO() {}

  public ClickstreamDO(String userid, String url, HttpMethod type, Long timestamp) {
    this.userid = userid;
    this.url = url;
    this.type = type;
    this.timestamp = timestamp;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public HttpMethod getType() {
    return type;
  }

  public void setType(HttpMethod type) {
    this.type = type;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public Long getVersion() {
    return version;
  }
}
