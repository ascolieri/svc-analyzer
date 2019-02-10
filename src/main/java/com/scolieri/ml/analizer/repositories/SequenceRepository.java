package com.scolieri.ml.analizer.repositories;

import com.scolieri.ml.analizer.models.database.Sequence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SequenceRepository extends CrudRepository<Sequence,Long> {

    @Query("select d from dna as d where d.sequence = :sequence")
    Optional<Sequence> findBySequence(@Param("sequence") String sequence);
}
