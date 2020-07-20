package ro.msg.learning.service.util.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.repository.LocationRepository;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;

@RequiredArgsConstructor
@Configuration
public class FindLocationStrategyFactory {

    @Value("${find-location-strategy}")
    private StrategyTypes chosenStrategy;

    @Value("${api-url}")
    private String apiUrl;

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    protected final ObjectMapper objectMapper;

    @Bean
    public FindLocationStrategy getStrategy(){

        switch (chosenStrategy){
            case MOST_ABUNDANT:
                return new MostAbundantStrategy(stockRepository, productRepository);
            case SINGLE_LOCATION:
                return new SingleLocationStrategy(stockRepository, productRepository);
            case PROXIMITY:
                return new ProximityStrategy(stockRepository, productRepository, locationRepository, objectMapper, apiUrl);
            default:
                return null;
        }
    }
}
