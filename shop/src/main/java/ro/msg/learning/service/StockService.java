package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Location> getAvailableLocations(int productId, int quantity){
        return stockRepository.findAvailableLocations(productId, quantity);
    }

}
