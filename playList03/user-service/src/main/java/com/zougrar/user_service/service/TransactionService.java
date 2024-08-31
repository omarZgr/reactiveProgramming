package com.zougrar.user_service.service;

import com.zougrar.user_service.dto.TransactionResponseDto;
import com.zougrar.user_service.dto.TransactionStatus;
import com.zougrar.user_service.dto.TransactionRequestDto;
import com.zougrar.user_service.entity.UserTransaction;
import com.zougrar.user_service.reposiory.UserRepository;
import com.zougrar.user_service.reposiory.UserTransactionRepository;
import com.zougrar.user_service.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class TransactionService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransactionRepository transactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){
        return this.userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(this.transactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getByUserId(int userId){
        return this.transactionRepository.findByUserId(userId);
    }

}
