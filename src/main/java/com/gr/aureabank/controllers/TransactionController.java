package com.gr.aureabank.controllers;

import com.gr.aureabank.dtos.TransactionDto;
import com.gr.aureabank.dtos.TransferRequest;
import com.gr.aureabank.security.UserMain;
import com.gr.aureabank.services.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionDto> findAllByUser(@AuthenticationPrincipal UserMain userMain) {
        return service.findAllByUser(userMain.getUser().getId());
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable Long id, @AuthenticationPrincipal UserMain userMain) {
        return service.findByIdAndUser(id, userMain.getUser().getId());
    }

    @PostMapping("/transfer")
    public TransactionDto transfer(@RequestBody TransferRequest request,
                                   @AuthenticationPrincipal UserMain userMain) {
        return service.transfer(request, userMain.getUser().getId());
    }
}
