package org.sesac.market.account.infrastructure.adapter.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@Comment("회원")
public class JpaAccount extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Comment("ID")
    private UUID id;

    @Comment("이름")
    private String name;

    @Column(unique = true)
    @Comment("이메일")
    private String email;

    public void update(JpaAccount entity) {
        this.toBuilder()
                .name(entity.name)
                .build();
    }
}
