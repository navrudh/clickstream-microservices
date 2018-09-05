package com.navrudh.clickstreamproject.datatransferobject;

import com.navrudh.clickstreamproject.domainvalue.HttpMethod;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class ClickstreamDTO {

  @Pattern(
      regexp =
          "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
  private String userid;

  @URL(message = "url is invalid")
  private String url;

  @NotNull(message = "http method type is invalid")
  private HttpMethod type;

  @Positive private Long timestamp;

  private ClickstreamDTO() {}

  public ClickstreamDTO(String userid, String url, String type, Long timestamp) {
    this.userid = userid;
    this.url = url;
    this.type = HttpMethod.create(type);
    this.timestamp = timestamp;
  }

  public static ClickstreamDTOBuilder newBuilder() {
    return new ClickstreamDTOBuilder();
  }

  public String getUserid() {
    return userid;
  }

  public String getUrl() {
    return url;
  }

  public HttpMethod getType() {
    return type;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public static class ClickstreamDTOBuilder {
    private String userid;
    private String url;
    private String type;
    private Long timestamp;

    public ClickstreamDTOBuilder setUserid(String userid) {
      this.userid = userid;
      return this;
    }

    public ClickstreamDTOBuilder setUrl(String url) {
      this.url = url;
      return this;
    }

    public ClickstreamDTOBuilder setType(String type) {
      this.type = type;
      return this;
    }

    public ClickstreamDTOBuilder setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public ClickstreamDTO createClickstreamDTO() {
      return new ClickstreamDTO(userid, url, type, timestamp);
    }
  }
}
