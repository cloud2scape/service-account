package org.sesac.market.account.infrastructure.adapter.output.persistence;

import lombok.RequiredArgsConstructor;
import org.sesac.market.account.application.port.output.AccountPort;
import org.sesac.market.account.domain.exception.BizException;
import org.sesac.market.account.domain.model.Account;
import org.sesac.market.account.infrastructure.adapter.input.converter.AccountDomainConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPort {
    private final JpaAccountRepository repository;
    private final AccountDomainConverter mapper;

    @Override
    @Transactional
    public Account save(Account account) {
        return Optional.of(repository.save(mapper.toEntity(account)))
                .map(mapper::toDomain)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    @Transactional
    public Account update(Account account) {
        var jpaAccount = repository.findById(account.getId())
                .orElseThrow(BizException.NoneExists::new);

        jpaAccount.update(mapper.toEntity(account));

        return mapper.toDomain(jpaAccount);
    }

    @Override
    @Transactional
    public void delete(Account account) {
        repository.delete(mapper.toEntity(account));
    }

    @Override
    public Account get(Account account) {
        return repository.findById(account.getId())
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Page<Account> getMultiple(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean exists(UUID id) {
        return repository.existsById(id);
    }
}
