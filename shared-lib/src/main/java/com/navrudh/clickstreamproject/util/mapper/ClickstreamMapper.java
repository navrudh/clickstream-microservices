package com.navrudh.clickstreamproject.util.mapper;

import com.navrudh.clickstreamproject.datatransferobject.ClickstreamDTO;
import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;

public class ClickstreamMapper {

  public static ClickstreamDO makeClickstreamDO(ClickstreamDTO clickstreamDTO) {
    return new ClickstreamDO(
        clickstreamDTO.getUserid(),
        clickstreamDTO.getUrl(),
        clickstreamDTO.getType(),
        clickstreamDTO.getTimestamp());
  }

  public static ClickstreamDTO makeClickstreamDTO(ClickstreamDO clickstreamDO) {
    ClickstreamDTO.ClickstreamDTOBuilder clickstreamDTOBuilder =
        ClickstreamDTO.newBuilder()
            .setUserid(clickstreamDO.getUserid())
            .setUrl(clickstreamDO.getUrl())
            .setType(clickstreamDO.getType().name().toLowerCase())
            .setTimestamp(clickstreamDO.getTimestamp());

    return clickstreamDTOBuilder.createClickstreamDTO();
  }
}
