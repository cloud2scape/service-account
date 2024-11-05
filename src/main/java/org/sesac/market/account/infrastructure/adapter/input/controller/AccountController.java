package org.sesac.market.account.infrastructure.adapter.input.controller;

import lombok.RequiredArgsConstructor;
import org.sesac.market.account.application.dto.request.*;
import org.sesac.market.account.application.dto.response.ReadAccountResponse;
import org.sesac.market.account.application.dto.response.ReadAccountsResponse;
import org.sesac.market.account.application.port.input.AccountCommand;
import org.sesac.market.account.application.port.input.AccountQuery;
import org.sesac.market.account.infrastructure.adapter.input.converter.AccountDomainConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@CrossOrigin
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController implements AccountApiDocs {
    private final AccountCommand command;
    private final AccountQuery query;
    private final AccountDomainConverter mapper;

    @Override
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest request) {
        var account = command.create(request);

        UUID id = account.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable UUID id, @RequestBody UpdateAccountRequest request) {
        command.update(request.toBuilder()
                .id(id)
                .build());

        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        var request = DeleteAccountRequest.builder()
                .id(id)
                .build();

        command.delete(request);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ReadAccountsResponse>> getAccounts(
            @PageableDefault(sort = "createdDate", direction = DESC) Pageable pageable
    ) {
        ReadAccountsRequest request = ReadAccountsRequest.builder()
                .pageable(pageable)
                .build();

        var accounts = query.read(request);

        var dtos = accounts.map(mapper::toDTO);

        var response = dtos.map(dto -> ReadAccountsResponse.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .modifiedDate(dto.getModifiedDate())
                .build());

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ReadAccountResponse> getAccountById(@PathVariable UUID id) {
        var request = ReadAccountRequest.builder()
                .id(id)
                .build();

        var account = query.read(request);

        var dto = mapper.toDTO(account);

        var response = ReadAccountResponse.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .modifiedDate(dto.getModifiedDate())
                .build();

        return ResponseEntity.ok(response);
    }
}
