package com.navrudh.clickstreamproject.dataaccessobject;

import com.navrudh.clickstreamproject.domainobject.ClickstreamDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickstreamRepository extends MongoRepository<ClickstreamDO, Long> {
  Long countByUrl(String url);

  Long countByUserid(String userid);

  Long countByTimestamp(Long timestamp);
}
