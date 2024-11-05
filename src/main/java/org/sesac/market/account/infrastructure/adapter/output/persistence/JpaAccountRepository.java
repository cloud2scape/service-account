package org.sesac.market.account.infrastructure.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAccountRepository extends JpaRepository<JpaAccount, UUID> {

    boolean existsByEmail(String email);
}
