package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.dto.RevenueDto;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.Revenue;
import ro.msg.learning.repository.OrderRepository;
import ro.msg.learning.repository.RevenueRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class RevenueService {

    private final RevenueRepository revenueRepository;
    private final OrderRepository orderRepository;

    public List<RevenueDto> getByDate(LocalDate date){
        List<RevenueDto> result = new ArrayList<>();
        List<Revenue> revenues = revenueRepository.findByDate(date);
        revenues.forEach(revenue -> result.add(new RevenueDto(revenue)));
        return result;
    }

    public void aggregateRevenueForCurrentDay(){
        LocalDate now = java.time.LocalDate.now();
        List<RevenueDto> revenuesFromToday = getByDate(now);

        if (revenuesFromToday.isEmpty()) {

            List<Order> ordersFromToday = orderRepository.findAllByCreatedAtBetween(now.atStartOfDay(), now.plusDays(1).atStartOfDay());

            if (!ordersFromToday.isEmpty()){
                RevenueDto revenueForToday = new RevenueDto();
                ordersFromToday.forEach(order -> {
                    revenueForToday.setDate(now);
                    revenueForToday.setLocation(order.getShippedFrom());
                    AtomicReference<BigDecimal> sumForToday = new AtomicReference<>(new BigDecimal(0));
                    order.getOrderDetailList().forEach(orderDetail ->
                        sumForToday.updateAndGet(v -> v.add(orderDetail.getProduct().getPrice().multiply(new BigDecimal(orderDetail.getQuantity()))))
                    );
                    revenueForToday.setSum(sumForToday.get());

                    revenueRepository.save(revenueForToday.toEntity());
                });
            }
        }

    }
}
