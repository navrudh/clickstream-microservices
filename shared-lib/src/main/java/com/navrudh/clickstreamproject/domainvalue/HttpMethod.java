package com.navrudh.clickstreamproject.domainvalue;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HttpMethod {
  OPTIONS,
  GET,
  HEAD,
  POST,
  PUT,
  PATCH,
  DELETE,
  TRACE;

  @JsonCreator
  public static HttpMethod create(String name) {
    return HttpMethod.valueOf(name.toUpperCase());
  }
}
