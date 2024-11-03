package org.sesac.market.account.application.port.input;

import org.sesac.market.account.application.dto.request.CreateAccountRequest;
import org.sesac.market.account.application.dto.request.DeleteAccountRequest;
import org.sesac.market.account.application.dto.request.UpdateAccountRequest;
import org.sesac.market.account.domain.model.Account;

public interface AccountCommand {
    Account create(CreateAccountRequest request);

    Account update(UpdateAccountRequest request);

    boolean delete(DeleteAccountRequest request);
}
