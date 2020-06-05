package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Stock;
import ro.msg.learning.service.StockService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @GetMapping("/{id}/{quant}")
    public ResponseEntity<List<Location>> get(@PathVariable("id") Integer id, @PathVariable("quant") Integer quantity){
        return ResponseEntity.accepted().body(stockService.getAvailableLocations(id, quantity));
    }
}
