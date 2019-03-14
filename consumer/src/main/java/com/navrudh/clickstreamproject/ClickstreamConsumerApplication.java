package com.navrudh.clickstreamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/*
 * Application that consumes clickstream data
 * */
@SpringBootApplication
@EnableKafka
public class ClickstreamConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClickstreamConsumerApplication.class, args);
  }
}
