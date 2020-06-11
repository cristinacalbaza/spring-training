package ro.msg.learning.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;

@RequiredArgsConstructor
@Configuration
public class FindLocationStrategyFactory {

    @Value("${find-location-strategy}")
    private StrategyTypes chosenStrategy;

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Bean
    public FindLocationStrategy getStrategy(){

        switch (chosenStrategy){
            case MOST_ABUNDANT:
                return new MostAbundantStrategy(stockRepository, productRepository);
            case SINGLE_LOCATION:
                return new SingleLocationStrategy(stockRepository, productRepository);
            default:
                return null;
        }
    }
}
