package com.zougrar.user_service.controller;


import com.zougrar.user_service.dto.TransactionRequestDto;
import com.zougrar.user_service.dto.TransactionResponseDto;
import com.zougrar.user_service.entity.UserTransaction;
import com.zougrar.user_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono){
        return requestDtoMono.flatMap(this.transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId){
        return this.transactionService.getByUserId(userId);
    }

}