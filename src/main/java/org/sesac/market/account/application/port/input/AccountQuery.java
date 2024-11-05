package org.sesac.market.account.application.port.input;

import org.sesac.market.account.application.dto.request.ReadAccountRequest;
import org.sesac.market.account.application.dto.request.ReadAccountsRequest;
import org.sesac.market.account.domain.model.Account;
import org.springframework.data.domain.Page;

public interface AccountQuery {
    Account read(ReadAccountRequest query);

    Page<Account> read(ReadAccountsRequest query);
}
