package org.sesac.market.account.application.port.output;

import org.sesac.market.account.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AccountPort {
    Account save(Account account);

    void delete(Account account);

    Optional<Account> get(Account account);

    Page<Account> getMultiple(Pageable pageable);

    boolean exists(String email);

    boolean exists(UUID id);
}