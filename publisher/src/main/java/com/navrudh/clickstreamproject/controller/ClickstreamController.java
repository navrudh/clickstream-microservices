package com.navrudh.clickstreamproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import com.navrudh.clickstreamproject.service.ClickstreamAggregatorService;
import com.navrudh.clickstreamproject.util.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("v1/click")
public class ClickstreamController {

  private final ClickstreamAggregatorService clickstreamAggregatorService;

  @Autowired
  public ClickstreamController(final ClickstreamAggregatorService clickstreamAggregatorService) {
    this.clickstreamAggregatorService = clickstreamAggregatorService;
  }

  /*
   * POST /aggregate
   *
   * performs some validation, then passes value onto a Kafka stream
   *
   * */

  @PostMapping("/aggregate")
  @ResponseStatus(HttpStatus.OK)
  public void processClickstreamData(@Valid @RequestBody ClickstreamDTO clickstreamDTO)
      throws JsonProcessingException {

    clickstreamAggregatorService.process(clickstreamDTO);
  }

  /*
   * POST /click
   *
   * reason for POST is that a URL of HTTP Request has a maximum length (varying by implementation),
   * so we pass the url as a POST parameter to get around this to avoid data loss
   *
   * */

  @PostMapping("/count")
  @ResponseStatus(HttpStatus.OK)
  public Long getClickstreamCountByUrl(@RequestParam String url)
      throws MalformedURLException, URISyntaxException {

    UrlValidator.validate(url);

    return clickstreamAggregatorService.getUrlCount(url);
  }
}
