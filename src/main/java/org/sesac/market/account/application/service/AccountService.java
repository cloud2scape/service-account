package org.sesac.market.account.application.service;

import lombok.RequiredArgsConstructor;
import org.sesac.market.account.application.dto.request.*;
import org.sesac.market.account.application.port.input.AccountCommand;
import org.sesac.market.account.application.port.input.AccountQuery;
import org.sesac.market.account.application.port.output.AccountPort;
import org.sesac.market.account.domain.exception.BizException;
import org.sesac.market.account.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountCommand, AccountQuery {
    private final AccountPort port;

    @Override
    public Account create(CreateAccountRequest request) {
        Account account = Account.builder()
                .name(request.name())
                .email(request.email())
                .build();

        if (port.exists(account.getEmail())) {
            throw new BizException.AlreadyExists();
        }

        return port.save(account);
    }

    @Override
    public Account update(UpdateAccountRequest request) {
        Account account = Account.builder()
                .id(request.id())
                .name(request.name())
                .build();

        if (port.exists(account.getId())) {
            throw new BizException.NoneExists();
        }

        return port.update(account);
    }

    @Override
    public boolean delete(DeleteAccountRequest request) {
        Account account = Account.builder()
                .id(request.id())
                .build();

        if (!port.exists(account.getId())) {
            throw new BizException.NoneExists();
        }

        port.delete(account);
        return true;
    }

    @Override
    public Account read(ReadAccountRequest query) {
        Account account = Account.builder()
                .id(query.id())
                .build();

        return Optional.ofNullable(port.get(account))
                .orElseThrow(BizException.NoneExists::new);
    }

    @Override
    public Page<Account> read(ReadAccountsRequest query) {
        return port.getMultiple(query.pageable());
    }
}