package com.navrudh.clickstreamproject.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlValidator {

  public static void validate(String url) throws MalformedURLException, URISyntaxException {
    new URL(url).toURI();
  }
}
