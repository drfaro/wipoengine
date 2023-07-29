package com.wipoengine.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProcessRecordDto(@NotBlank String title,
                               @NotBlank String externalIdProcess,
                               @NotBlank String internationalOrder,
                               @NotNull Date publicationDate,
                               @NotBlank String claimant) {
}
