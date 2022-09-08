package com.weatherapp.metrics.controller;

import com.weatherapp.metrics.dto.MetricsDto;
import com.weatherapp.metrics.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;

/*
 * Rest controller to add and retrieve weather sensor metrics data
 * */
@RestController
@RequestMapping(MetricsResource.SENSOR_RESOURCE)
@CrossOrigin
public class MetricsResource {

    public static final String SENSOR_RESOURCE = "/sensor/metrics/";

    @Autowired
    MetricsService metricsService;

    /*
     *
     * Add current reading of the  for the
     *
     * @Param Mono<MetricsDto> - Input request
     *
     * @return Mono<MetricsDto>
     *
     * @author Prateek Nima
     * */
    @PostMapping(path = "/addReading")
    public Mono<MetricsDto> addMetricData(@RequestBody Mono<MetricsDto> metric) {
        return metricsService.addMetric(metric);
    }

    /*
     *
     * Get average metric value associated with a sensor Id within the provided date range
     *
     * @Param id - Sensor Id
     * @Param metricType - metric type for e.g. temperature
     * @Param start - Start date for the range
     * @Param end - End date for the range
     *
     * @return Mono<Double>
     *
     * @author Prateek Nima
     * */
    @GetMapping(path = "getDataBySensorId/{id}")
    public Mono<Double> getDataBySensor(@PathVariable int id, @RequestParam("metricType") String metricType, @RequestParam("start") String start, @RequestParam("end") String end) {
        if(metricType == null) {
            return Mono.empty();
        }
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return metricsService.getMetricBySensorId(id, metricType, startDate, endDate);
        }
        catch (Exception e){
            System.out.println("Exception Occurred. Invalid date format");
            return Mono.empty();
        }

    }

    /*
     *
     * Get average value of a metric across all the sensors within the provided date range
     *
     * @Param metricType - metric type for e.g. temperature
     * @Param start - Start date for the range
     * @Param end - End date for the range
     *
     * @return Mono<Double>
     *
     * @author Prateek Nima
     * */
    @GetMapping(path = "getDataByMetricType/{metricType}")
    public Mono<Double> getDataBySensorMetric( @PathVariable("metricType") String metricType,@RequestParam("start") String start, @RequestParam("end") String end) {
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return metricsService.getAllSensorMetric(metricType, startDate, endDate);
        }
        catch (Exception e){
            System.out.println("Exception Occurred. Invalid date format");
            return Mono.empty();
        }
    }

    /*
     *
     * Get the average value for all the metrics across all the sensors within the provided time range
     *
     * @Param start - Start date for the range
     * @Param end - End date for the range
     *
     * @return Mono<Map<String, Double>>
     * @author Prateek Nima
     * */
    @GetMapping(path = "/getAllData")
    public Mono<Map<String, Double>> getAllMetricsData(@RequestParam("start") String start, @RequestParam("end") String end){
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return metricsService.getAllData(startDate, endDate);
        }
        catch (Exception e){
            System.out.println("Exception Occurred. Invalid date format");
            return Mono.empty();
        }
    }
}
