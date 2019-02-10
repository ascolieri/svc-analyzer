package com.scolieri.ml.analizer.repositories;

import com.scolieri.ml.analizer.models.database.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface SequenceRepository extends CrudRepository<Sequence,Long> {
    @Modifying
    @Query(value = "INSERT INTO dna(sequence,is_mutant,cdt,mdt) VALUES(:sequence,:isMutant,now(),now()) ON CONFLICT (sequence) DO UPDATE SET mdt = excluded.mdt",
            nativeQuery = true)
    void insertSequence(@Param("sequence") String sequence,@Param("isMutant") Boolean isMutant);

    @Query("select d from dna as d where d.sequence = :sequence")
    Optional<Sequence> findBySequence(@Param("sequence") String sequence);
}
