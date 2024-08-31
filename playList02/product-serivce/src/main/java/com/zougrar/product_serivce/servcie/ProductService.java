package com.zougrar.product_serivce.servcie;


import com.zougrar.product_serivce.dto.ProductDto;
import com.zougrar.product_serivce.repository.ProductRepository;
import com.zougrar.product_serivce.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository ;



    public Flux<ProductDto> getAll()
    {
        return this.productRepository.findAll()
                .map(EntityDtoUtil::toDto)
                .delayElements(Duration.ofSeconds(1))

                ;
    }

    public Mono<ProductDto> get(String id)
    {
        return this.productRepository.findById(id)
                .map(EntityDtoUtil::toDto)
                ;
    }

    public Mono<ProductDto> insert(Mono<ProductDto> productDto)
    {
        return productDto.map(EntityDtoUtil::toEntity)
                .flatMap(this.productRepository::insert)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> update(String id,Mono<ProductDto> productDtoMono)
    {
        return this.productRepository.findById(id)
                .flatMap(p-> productDtoMono.map(EntityDtoUtil::toEntity)
                        .doOnNext(e-> e.setId(id)))
                .flatMap(this.productRepository::save)
                .map(EntityDtoUtil::toDto) ;


    }

    public Mono<Void> delete(String id)
    {
        return this.productRepository.deleteById(id) ;
    }

    public Flux<ProductDto> getProductRangePrice(int min,int max)
    {
        return this.productRepository.findByPriceBetween(Range.closed(min,max))
                .map(EntityDtoUtil::toDto) ;
    }

}
