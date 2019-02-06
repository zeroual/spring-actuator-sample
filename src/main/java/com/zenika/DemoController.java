package com.zenika;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class DemoController {

    private final Timer timer;
    private final DistributionSummary summary;

    public DemoController(MeterRegistry meterRegistry) {
        timer = meterRegistry.timer("demo.timer");
        summary = meterRegistry.summary("demo.summary");
    }

    @GetMapping("/demo/{value}")
//    @Timed()
    public List<String> demo(@PathVariable(required = false, name = "value") Long value) {
        summary.record( value);
        return timer.record(() -> asList("hello", "salut", "mahaba", "azul"));

    }
}
