package ro.msg.learning.shop.unitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.dto.StockExportDto;
import ro.msg.learning.service.ExportStockService;
import ro.msg.learning.service.StockService;
import ro.msg.learning.service.util.converter.CSVMessageConverter;
import ro.msg.learning.shop.ShopApplication;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = ShopApplication.class)
@AutoConfigureMockMvc
public class CsvTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExportStockService exportStockService;

    @MockBean
    private StockService stockService;

    @Test
    public void givenLocation_whenGetStocks_thenReturnCsvFile() throws Exception {
        CSVMessageConverter<StockExportDto> csvMessageConverter = new CSVMessageConverter<>();
        List<StockExportDto> stocks = new ArrayList<>();
        StockExportDto stockExportDto = new StockExportDto(1,1, 10);
        stocks.add(stockExportDto);

        Mockito.when(exportStockService.exportStock(1)).thenReturn(stocks);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/stock/{locationId}", 1)
                .accept("text/csv"))
                .andReturn();

        InputStream inputStream = new ByteArrayInputStream(result.getResponse().getContentAsString().getBytes());
        List<StockExportDto> stockExportDtos = csvMessageConverter.fromCsv(StockExportDto.class, inputStream);

        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals(1, stockExportDtos.size());
        Assert.assertEquals(stockExportDto, stockExportDtos.get(0));
    }
}
