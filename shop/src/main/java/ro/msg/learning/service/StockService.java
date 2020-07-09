package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockId;
import ro.msg.learning.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> getAvailableLocations(int productId, int quantity){
        return stockRepository.findAvailableLocations(productId, quantity);
    }

    public StockDto update(StockDto stockDto) {
        Optional<Stock> stockOptional = stockRepository.findById(new StockId(stockDto.getProduct().getId(), stockDto.getLocation().getId()));
        if (stockOptional.isEmpty())
            return null;
        return new StockDto(stockRepository.save(stockDto.toEntity()));
    }

    public StockDto save(StockDto stockDto){
        return new StockDto(stockRepository.save(stockDto.toEntity()));
    }

    public void deleteAll(){
        stockRepository.deleteAll();
    }
}
