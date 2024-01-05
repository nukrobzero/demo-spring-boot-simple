package com.example.backend.api;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.ProductBusiness;
import com.example.backend.exception.BaseException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/product")
public class ProductsApi {

    private final ProductBusiness business;

    public ProductsApi(ProductBusiness business) {
        this.business = business;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws BaseException {
        String response = business.getProductById(id);

        return ResponseEntity.ok(response);
    }

}
