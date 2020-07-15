package ro.msg.learning.service.util.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.exception.OutOfStockException;
import ro.msg.learning.service.exception.ProductNotFoundException;

import java.util.*;

@RequiredArgsConstructor
public class MostAbundantStrategy implements FindLocationStrategy {

    @Autowired
    private final StockRepository stockRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<StockDto> findLocations(List<OrderDetailDto> orderDetails) {
        List<StockDto> result = new ArrayList<>();
        Map<Integer, List<Stock>> availableLocations = new LinkedHashMap<>();

        orderDetails.forEach(orderDetailDto -> { Optional<Product> product = productRepository.findById(orderDetailDto.getProductId());
                                                 if (product.isEmpty())
                                                    throw new ProductNotFoundException(orderDetailDto.getProductId());
        });

        orderDetails.forEach(orderDetailDto -> {
            availableLocations.put(orderDetailDto.getProductId(), stockRepository.findAvailableLocations(orderDetailDto.getProductId(), orderDetailDto.getQuantity()));
            result.add(new StockDto(new Location(), productRepository.getOne(orderDetailDto.getProductId()), orderDetailDto.getQuantity()));
        } );

        result.forEach(stockDto -> stockDto.setLocation(getMostAbundand(availableLocations.get(stockDto.getProduct().getId())).getLocation()));
        return result;
    }

    private Stock getMostAbundand(List<Stock> stocks){
        Optional<Stock> stock = stocks.stream().max(Comparator.comparingInt(Stock::getQuantity));
        if (stock.isEmpty())
            throw new OutOfStockException();
        return stock.get();
    }
}
