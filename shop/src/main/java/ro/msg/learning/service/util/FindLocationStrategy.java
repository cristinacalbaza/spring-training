package ro.msg.learning.service.util;

import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.repository.StockRepository;

import java.util.List;

public interface FindLocationStrategy {

    List<StockDto> findLocations(List<OrderDetailDto> orderDetails);
}
