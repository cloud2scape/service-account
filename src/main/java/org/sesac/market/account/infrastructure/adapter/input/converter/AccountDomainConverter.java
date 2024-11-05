package org.sesac.market.account.infrastructure.adapter.input.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sesac.market.account.application.dto.AccountDTO;
import org.sesac.market.account.domain.model.Account;
import org.sesac.market.account.infrastructure.adapter.output.persistence.JpaAccount;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDomainConverter implements DomainConverter<Account, JpaAccount, AccountDTO> {

    private final ModelMapper mapper;

    @Override
    public JpaAccount toEntity(Account domain) {
        return mapper.map(domain, JpaAccount.class);
    }

    @Override
    public Account toDomain(JpaAccount entity) {
        return mapper.map(entity, Account.class);
    }

    @Override
    public AccountDTO toDTO(Account domain) {
        return mapper.map(domain, AccountDTO.class);
    }
}
