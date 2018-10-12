package org.paasta.sample.app.mysql;

import org.paasta.sample.app.entity.mysql.MysqlSample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlSampleRepository extends CrudRepository<MysqlSample, Integer> {
}
