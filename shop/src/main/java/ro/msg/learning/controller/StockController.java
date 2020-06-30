package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.dto.StockExportDto;
import ro.msg.learning.model.Stock;
import ro.msg.learning.service.ExportStockService;
import ro.msg.learning.service.StockService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private final ExportStockService exportStockService;

    @Autowired
    private final StockService stockService;

    @GetMapping("/{id}/{quant}")
    public ResponseEntity<List<Stock>> get(@PathVariable("id") Integer id, @PathVariable("quant") Integer quantity){
        return ResponseEntity.accepted().body(stockService.getAvailableLocations(id, quantity));
    }

    @GetMapping(value = "/{locationId}", produces = {"text/csv"})
    public List<StockExportDto> exportStock(@PathVariable("locationId") Integer locationId, HttpServletResponse response){
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stocks.csv");

        return exportStockService.exportStock(locationId);
    }
}
