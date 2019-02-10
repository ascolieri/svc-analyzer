package com.scolieri.ml.analyzer.repositories;

import com.scolieri.ml.analyzer.models.database.Sequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SequenceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SequenceRepository sequenceRepository;


    @Test
    public void testFindBySequence(){
        Sequence sequence = new Sequence("SEQUENCE",true);
        entityManager.persist(sequence);
        Optional<Sequence> sequenceOptional = sequenceRepository.findBySequence("SEQUENCE");
        Assert.assertTrue(sequenceOptional.isPresent());
        Assert.assertTrue(sequenceOptional.get().isMutant());
        Assert.assertEquals("SEQUENCE",sequenceOptional.get().getSequence());
    }
}
