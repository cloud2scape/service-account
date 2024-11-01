package org.sesac.market.account.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AccountController {
    @GetMapping
    public String home() {
        return "<h2>Account Server</h2>";
    }
}
