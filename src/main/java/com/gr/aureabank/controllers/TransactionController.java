package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.TransactionDto;
import com.gr.aureabank.dtos.TransferRequest;
import com.gr.aureabank.security.UserMain;
import com.gr.aureabank.services.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public TransactionDto transfer(@RequestBody TransferRequest request,
                                   @AuthenticationPrincipal UserMain userMain) {
        return service.transfer(request, userMain.getUser().getId());
    }
}
