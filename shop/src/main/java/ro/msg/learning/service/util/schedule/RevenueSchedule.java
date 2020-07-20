package ro.msg.learning.service.util.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.msg.learning.service.RevenueService;

@Component
@RequiredArgsConstructor
public class RevenueSchedule {

    private final RevenueService revenueService;

    // second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "0 59 23 * * ?")
    public void aggregateRevenue(){
        revenueService.aggregateRevenueForCurrentDay();
    }
}
