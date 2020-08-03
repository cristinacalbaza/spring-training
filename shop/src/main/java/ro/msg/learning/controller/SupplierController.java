package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.model.Supplier;
import ro.msg.learning.service.SupplierService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Supplier>> getAll(){
        return ResponseEntity.accepted().body(supplierService.getAll());
    }
}
