package com.zougrar.order_serivce.service;

import com.zougrar.order_serivce.dto.PurchaseOrderResponseDto;
import com.zougrar.order_serivce.entity.PurchaseOrder;
import com.zougrar.order_serivce.repository.PurchaseOrderRepository;
import com.zougrar.order_serivce.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;


@Service
public class OrderQueryService {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId){
        return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream()) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

}