package ro.msg.learning.service.util.strategy;

import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Address;

import java.util.List;

public interface FindLocationStrategy {

    List<StockDto> findLocations(List<OrderDetailDto> orderDetails, Address orderAddress);
}
