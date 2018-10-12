package org.paasta.sample.app.postgresql;

import org.paasta.sample.app.entity.postgresql.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends CrudRepository<Sample, Integer> {
}
