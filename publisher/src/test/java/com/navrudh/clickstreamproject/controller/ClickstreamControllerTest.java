package com.navrudh.clickstreamproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import com.navrudh.clickstreamproject.service.ClickstreamAggregatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.MalformedURLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClickstreamController.class)
public class ClickstreamControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper mapper;

  @MockBean private ClickstreamAggregatorService clickstreamAggregatorService;

  @Test
  public void aggregate() throws Exception {

    ClickstreamDTO clickstreamDTO =
        ClickstreamDTO.newBuilder()
            .setTimestamp(9324234L)
            .setUserid("b20524ae-befe-11e3-b335-425861b86ab6:b20524ae-befe-11e3-b335-425861b86ab6")
            .setType("get")
            .setUrl("http://example.com")
            .createClickstreamDTO();

    Mockito.doNothing()
        .when(clickstreamAggregatorService)
        .process(ArgumentMatchers.any(ClickstreamDTO.class));

    this.mockMvc
        .perform(
            post("/v1/click/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clickstreamDTO)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void aggregate_invalidUrl() throws Exception {

    ClickstreamDTO clickstreamDTO =
        ClickstreamDTO.newBuilder()
            .setTimestamp(9324234L)
            .setUserid("b20524ae-befe-11e3-b335-425861b86ab6:b20524ae-befe-11e3-b335-425861b86ab6")
            .setType("get")
            .setUrl("invalid-url")
            .createClickstreamDTO();

    Mockito.doNothing()
        .when(clickstreamAggregatorService)
        .process(ArgumentMatchers.any(ClickstreamDTO.class));

    this.mockMvc
        .perform(
            post("/v1/click/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clickstreamDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test(expected = IllegalArgumentException.class)
  public void aggregate_invalidType() throws Exception {

    ClickstreamDTO clickstreamDTO =
        ClickstreamDTO.newBuilder()
            .setTimestamp(9324234L)
            .setUserid("b20524ae-befe-11e3-b335-425861b86ab6:b20524ae-befe-11e3-b335-425861b86ab6")
            .setType("invalid")
            .setUrl("http://example.com")
            .createClickstreamDTO();

    Mockito.doNothing()
        .when(clickstreamAggregatorService)
        .process(ArgumentMatchers.any(ClickstreamDTO.class));

    this.mockMvc
        .perform(
            post("/v1/click/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clickstreamDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void aggregate_negativeTimestamp() throws Exception {

    ClickstreamDTO clickstreamDTO =
        ClickstreamDTO.newBuilder()
            .setTimestamp(-9324234L)
            .setUserid("b20524ae-befe-11e3-b335-425861b86ab6:b20524ae-befe-11e3-b335-425861b86ab6")
            .setType("get")
            .setUrl("http://example.com")
            .createClickstreamDTO();

    Mockito.doNothing()
        .when(clickstreamAggregatorService)
        .process(ArgumentMatchers.any(ClickstreamDTO.class));

    this.mockMvc
        .perform(
            post("/v1/click/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clickstreamDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void aggregate_useridNotUuid() throws Exception {

    ClickstreamDTO clickstreamDTO =
        ClickstreamDTO.newBuilder()
            .setTimestamp(9324234L)
            .setUserid("invalid-userid")
            .setType("get")
            .setUrl("http://example.com")
            .createClickstreamDTO();

    Mockito.doNothing()
        .when(clickstreamAggregatorService)
        .process(ArgumentMatchers.any(ClickstreamDTO.class));

    this.mockMvc
        .perform(
            post("/v1/click/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(clickstreamDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void countUrl() throws Exception {

    Mockito.when(clickstreamAggregatorService.getUrlCount(ArgumentMatchers.any(String.class)))
        .thenReturn(121L);

    this.mockMvc
        .perform(
            post("/v1/click/count")
                .contentType(MediaType.APPLICATION_JSON)
                .param("url", "http://example.com"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("121"));
  }

  @Test(expected = MalformedURLException.class)
  public void countUrl_invalidUrl() throws Exception {

    Mockito.when(clickstreamAggregatorService.getUrlCount(ArgumentMatchers.any(String.class)))
        .thenReturn(121L);

    this.mockMvc
        .perform(
            post("/v1/click/count")
                .contentType(MediaType.APPLICATION_JSON)
                .param("url", "invalid-url"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
