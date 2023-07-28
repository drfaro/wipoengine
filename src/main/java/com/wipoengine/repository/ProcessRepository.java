package com.wipoengine.repository;

import com.wipoengine.models.ProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProcessRepository extends JpaRepository<ProcessModel, UUID> {
    Optional<List<ProcessModel>> findByExternalIdProcessContainingOrClaimantContaining(String externalIdProcess, String claimant);
    Optional<List<ProcessModel>> findFirst50ByOrderByCreatedAtDesc();

}
