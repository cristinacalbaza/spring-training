package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.service.ProductCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productCategory")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductCategory>> getAll(){
        return ResponseEntity.accepted().body(productCategoryService.getAll());
    }
}
