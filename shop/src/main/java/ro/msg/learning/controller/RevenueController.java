package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.dto.RevenueDto;
import ro.msg.learning.service.RevenueService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/revenue")
public class RevenueController {

    private final RevenueService revenueService;

    @GetMapping(value = "/{date}", produces = "application/json")
    public List<RevenueDto> getRevenueForDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return revenueService.getByDate(date);
    }

}
