package ro.msg.learning.shop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.dto.ProductDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.*;
import ro.msg.learning.service.exception.OutOfStockException;
import ro.msg.learning.shop.ShopApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = ShopApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CreateOrderTest {

    @Autowired
    private MockMvc mvc;

    private static Boolean isInitialized = Boolean.FALSE;

    @Before
    public void setUp() throws Exception {

        if (!isInitialized) {
            Customer customer = new Customer(1, "Cristina", "Calbaza", "calbazac", "1234", "cristina.calbaza@email.com");
            Supplier supplier = new Supplier(1, "Test");
            ProductCategory productCategory = new ProductCategory(1, "Test", "Test");

            Product product1 = new Product(1, "product1", "product1", new BigDecimal(100), 1, productCategory, supplier, "image");
            Product product2 = new Product(2, "product2", "product2", new BigDecimal(200), 1, productCategory, supplier, "image");

            Address address1 = new Address("address1", "address1", "address1", "address1");
            Address address2 = new Address("address2", "address2", "address2", "address2");
            Address address3 = new Address("address3", "address3", "address3", "address3");
            Location location1 = new Location(1, "location1", address1);
            Location location2 = new Location(2, "location2", address2);
            Location location3 = new Location(3, "location3", address3);

            Stock stock1 = new Stock(new StockId(1, 1), product1, location1, 15);
            Stock stock2 = new Stock(new StockId(1, 2), product1, location2, 12);
            Stock stock3 = new Stock(new StockId(1, 3), product1, location3, 5);

            Stock stock4 = new Stock(new StockId(2, 1), product2, location1, 3);
            Stock stock5 = new Stock(new StockId(2, 2), product2, location2, 6);
            Stock stock6 = new Stock(new StockId(2, 3), product2, location3, 13);

            // populate de db
            MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .post("/addCustomer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(customer)))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addSupplier")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(supplier)))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addProductCategory")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(productCategory)))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addLocation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(location1)))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addLocation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(location2)))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addLocation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(location3)))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .get("/productCategories"))
                    .andReturn();

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addProduct")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new ProductDto(product1))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addProduct")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new ProductDto(product2))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addStock")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new StockDto(stock1))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addStock")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new StockDto(stock2))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addStock")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new StockDto(stock3))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addStock")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new StockDto(stock4))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addStock")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new StockDto(stock5))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .post("/addStock")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new StockDto(stock6))))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            isInitialized = Boolean.TRUE;
        }

    }

    @After
    public void tearDown() throws Exception {

        if (!isInitialized) {
            MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteOrders"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteAllStocks"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteAllProducts"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());


            result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteCustomers"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteSuppliers"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteProductCategories"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            result = mvc.perform(MockMvcRequestBuilders
                    .delete("/deleteLocations"))
                    .andReturn();
            Assert.assertEquals(200, result.getResponse().getStatus());

            isInitialized = Boolean.TRUE;
        }

    }

    @Test
    public void createOrderSuccessTest() throws Exception {

        List<OrderDetailDto> products = Arrays.asList(new OrderDetailDto(1,10), new OrderDetailDto(2,10));

        OrderDto orderDto = new OrderDto(null, new Address("Romania", "Cluj-Napoca", "Cluj", "Str. Dorobantilor, nr. 186"), products);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void createOrderMissingStock() throws Exception {

        List<OrderDetailDto> products = Arrays.asList(new OrderDetailDto(1,20), new OrderDetailDto(2,10));

        OrderDto orderDto = new OrderDto(null, new Address("Romania", "Cluj-Napoca", "Cluj", "Str. Dorobantilor, nr. 186"), products);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof OutOfStockException))
                .andReturn();
        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
    }
}
