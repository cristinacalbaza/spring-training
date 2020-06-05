package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.dto.ProductDto;
import ro.msg.learning.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.accepted().body(productService.getAll());
    }

    @PostMapping()
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto product){
        return ResponseEntity.accepted().body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto product, @PathVariable("id") Integer id){
        return ResponseEntity.accepted().body(productService.update(product, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> delete(@PathVariable("id") Integer id){
        return ResponseEntity.accepted().body(productService.delete(id));
    }
}
