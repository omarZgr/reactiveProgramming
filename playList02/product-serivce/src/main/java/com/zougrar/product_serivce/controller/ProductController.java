package com.zougrar.product_serivce.controller;


import com.zougrar.product_serivce.dto.ProductDto;
import com.zougrar.product_serivce.servcie.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService ;



    @GetMapping(value = "/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getAll()
    {
        return this.productService.getAll() ;
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable  String id)
    {
        return this.productService.get(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono)
    {
       return this.productService.insert(productDtoMono) ;
    }


    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono)
    {
        return this.productService.update(id,productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id)
    {
        return this.productService.delete(id) ;
    }


    @GetMapping("/price-range")
    public Flux<ProductDto> getProductRangePrice(@RequestParam  int min,@RequestParam  int max)
    {
        return this.productService.getProductRangePrice(min,max)  ;

    }
}
