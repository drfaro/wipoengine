package com.wipoengine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_PROCESS",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"externalIdProcess"})
)
public class ProcessModel implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProcess;
    private String title;
    private String externalIdProcess;
    private String internationalOrder;
    private Date publicationDate;
    @Column(columnDefinition="Text")
    private String claimant;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    public UUID getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(UUID idProcess) {
        this.idProcess = idProcess;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExternalIdProcess() {
        return externalIdProcess;
    }

    public void setExternalIdProcess(String pub) {
        this.externalIdProcess = pub;
    }

    public String getInternationalOrder() {
        return internationalOrder;
    }

    public void setInternationalOrder(String internationalOrder) {
        this.internationalOrder = internationalOrder;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
