package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.dto.StockExportDto;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportStockService {

    private final StockRepository stockRepository;

    public List<StockExportDto> exportStock(int locationId){
        List<StockExportDto> result = new ArrayList<>();
        List<Stock> stocks = stockRepository.findByLocationId(locationId);
        stocks.forEach(stock -> result.add(new StockExportDto(stock)));
        return result;
    }
}
