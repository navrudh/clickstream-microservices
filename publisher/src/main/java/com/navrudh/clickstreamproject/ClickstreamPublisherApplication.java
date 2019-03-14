package com.navrudh.clickstreamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Application that publishes POSTed click data into a stream
 * */
@SpringBootApplication
public class ClickstreamPublisherApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClickstreamPublisherApplication.class, args);
  }
}
