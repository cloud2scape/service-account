package org.sesac.market.account.application.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDTO {

    private UUID id;

    private String email;

    private String name;

    private OffsetDateTime createdDate;

    private OffsetDateTime modifiedDate;
}
