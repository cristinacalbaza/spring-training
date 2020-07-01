package ro.msg.learning.shop.unitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.*;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.util.strategy.MostAbundantStrategy;
import ro.msg.learning.service.util.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocationStrategyTest {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private StockRepository stockRepository;

    @MockBean
    private ProductRepository productRepository;

    private List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();

    private List<StockDto> expectedMostAbundant;
    private List<StockDto> expectedSingleLocation;

    @Before
    public void setUp(){
        // order details
        ProductCategory productCategory = new ProductCategory(1, "Test", "Test");
        Supplier supplier = new Supplier(1, "Test");
        Product product1 = new Product(1, "product1", "product1", new BigDecimal(100), 1, productCategory, supplier, "image");
        Product product2 = new Product(2, "product2", "product2", new BigDecimal(200), 1, productCategory, supplier, "image");
        OrderDetailDto d1 = new OrderDetailDto(1, 10);
        OrderDetailDto d2 = new OrderDetailDto(2, 5);
        orderDetailDtoList.add(d1);
        orderDetailDtoList.add(d2);

        // stock
        Address address1 = new Address("address1", "address1", "address1", "address1");
        Address address2 = new Address("address2", "address2", "address2", "address2");
        Address address3 = new Address("address3", "address3", "address3", "address3");
        Location location1 = new Location(1, "location1", address1);
        Location location2 = new Location(2, "location2", address2);
        Location location3 = new Location(3, "location3", address3);

        Stock stock1 = new Stock(new StockId(1,1 ), product1, location1, 15);
        Stock stock2 = new Stock(new StockId(1,2 ), product1, location2, 12);
        Stock stock3 = new Stock(new StockId(1,3 ), product1, location3, 5);

        List<Stock> availableLocations1 = Arrays.asList(stock1, stock2);

        Stock stock4 = new Stock(new StockId(2,1 ), product2, location1, 3);
        Stock stock5 = new Stock(new StockId(2,2 ), product2, location2, 6);
        Stock stock6 = new Stock(new StockId(2,3 ), product2, location3, 13);

        List<Stock> availableLocations2 = Arrays.asList(stock5, stock6);

        Mockito.when(stockRepository.findAvailableLocations(1,10)).thenReturn(availableLocations1);
        Mockito.when(stockRepository.findAvailableLocations(2, 5)).thenReturn(availableLocations2);
        Mockito.when(productRepository.getOne(1)).thenReturn(product1);
        Mockito.when(productRepository.getOne(2)).thenReturn(product2);

        expectedMostAbundant = createExpectedResult(stock1, stock6);
        expectedSingleLocation = createExpectedResult(stock2, stock5);
    }

    @Test
    public void mostAbundantTest(){
        MostAbundantStrategy mostAbundantStrategy = new MostAbundantStrategy(stockRepository, productRepository);
        List<StockDto> actualMostAbundant = mostAbundantStrategy.findLocations(orderDetailDtoList);

        Assert.assertEquals(2, actualMostAbundant.size());
        Assert.assertEquals(actualMostAbundant.get(0), expectedMostAbundant.get(0));
        Assert.assertEquals(actualMostAbundant.get(1), expectedMostAbundant.get(1));
    }

    @Test
    public void singleLocationTest(){
        SingleLocationStrategy singleLocationStrategy = new SingleLocationStrategy(stockRepository, productRepository);
        List<StockDto> actualSingleLocation = singleLocationStrategy.findLocations(orderDetailDtoList);

        Assert.assertEquals(2, actualSingleLocation.size());
        Assert.assertEquals(actualSingleLocation.get(0), expectedSingleLocation.get(0));
        Assert.assertEquals(actualSingleLocation.get(1), expectedSingleLocation.get(1));
    }

    private List<StockDto> createExpectedResult(Stock stock1, Stock stock2){
        return Arrays.asList(new StockDto(stock1.getLocation(), stock1.getProduct(), 10),
                             new StockDto(stock2.getLocation(), stock2.getProduct(), 5));
    }

}
