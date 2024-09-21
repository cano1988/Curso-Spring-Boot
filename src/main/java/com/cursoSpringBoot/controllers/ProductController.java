package com.cursoSpringBoot.controllers;

import com.cursoSpringBoot.domain.Product;
import com.cursoSpringBoot.service.ProductService;
import com.cursoSpringBoot.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    //Instancia de clase
   // ProductService productsService = new ProductsServiceImpl();
    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products = productsService.getProducts();
        return ResponseEntity.ok(products);
    }

}
