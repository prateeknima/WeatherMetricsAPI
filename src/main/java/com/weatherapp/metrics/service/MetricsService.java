package com.weatherapp.metrics.service;

import com.weatherapp.metrics.dto.MetricsDto;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;

public interface MetricsService {

    /*
     *
     * Add the new metric data received from the sensor
     *
     * @Param Mono<MetricsDto> - MetricsDto Mono object
     *
     * @return Mono<MetricsDto>
     *
     * @author Prateek Nima
     * */
    public Mono<MetricsDto> addMetric(Mono<MetricsDto> metricsDtoMono);

    /*
     *
     * Get average value for a metric type for the provided sensor Id
     *
     * @Param Mono<MetricsDto> - MetricsDto Mono object
     *
     * @Param id - Sensor Id
     * @Param metricType - metric type for e.g. temperature
     * @Param startDate - Start date for the range
     * @Param endDate - End date for the range
     *
     * @return Mono<Double>
     *
     * @author Prateek Nima
     * */
    public Mono<Double> getMetricBySensorId(int id, String metricType, LocalDate startDate, LocalDate endDate);

    /*
     *
     * Get average value for a metric type across all the sensors
     *
     * @Param Mono<MetricsDto> - MetricsDto Mono object
     *
     * @Param metricType - metric type for e.g. temperature
     * @Param startDate - Start date for the range
     * @Param endDate - End date for the range
     *
     * @return Mono<Double>
     *
     * @author Prateek Nima
     * */
    public Mono<Double> getAllSensorMetric(String metricType,LocalDate startDate, LocalDate endDate);

    /*
     *
     * Get average value for each metric type across all the sensors
     *
     * @Param startDate - Start date for the range
     * @Param endDate - End date for the range
     *
     * @return Mono<Map<String, Double>>
     *
     * @author Prateek Nima
     * */
    public Mono<Map<String, Double>> getAllData(LocalDate startDate, LocalDate endDate);
}
