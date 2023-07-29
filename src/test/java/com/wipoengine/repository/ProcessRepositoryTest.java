package com.wipoengine.repository;

import com.wipoengine.models.ProcessModel;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProcessRepositoryTest {

    @Autowired
    ProcessRepository processRepository;

    @Test
    public void testCreateReadDelete() {

        ProcessModel testProcessModel = new ProcessModel();
        testProcessModel.setTitle("Title");
        testProcessModel.setClaimant("Claimant");
        testProcessModel.setInternationalOrder("InternationalOrder");
        testProcessModel.setPublicationDate(new Date());
        testProcessModel.setExternalIdProcess("ExternalIdProcess");

        processRepository.save(testProcessModel);

        Optional<List<ProcessModel>> optionalListProcessModels = processRepository.findByExternalIdProcessContainingOrClaimantContaining(testProcessModel.getExternalIdProcess(),testProcessModel.getClaimant());
        var listProcessModels = optionalListProcessModels.get();
        if (!listProcessModels.isEmpty()) {

            var processModels = listProcessModels.get(0);
            assertEquals(processModels.getTitle(), testProcessModel.getTitle());
        }

        processRepository.deleteAll();
        Assertions.assertThat(processRepository.findAll()).isEmpty();
    }
}