package org.sesac.market.account.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record UpdateAccountRequest(
        @NotNull UUID id,
        @NotNull String name
) {
}
