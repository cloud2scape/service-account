package org.sesac.market.account.domain.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    private UUID id;
    private String email;
    private String name;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;

    public static Account create(String name, String email, String picture) {
        return Account.builder()
                .name(name)
                .email(email)
                .build();
    }
}