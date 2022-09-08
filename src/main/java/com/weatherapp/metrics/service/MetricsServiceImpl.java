package com.weatherapp.metrics.service;

import com.weatherapp.metrics.dto.MetricsDto;
import com.weatherapp.metrics.model.Metrics;
import com.weatherapp.metrics.repository.MetricRepository;
import com.weatherapp.metrics.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricsServiceImpl implements MetricsService{

    @Autowired
    private MetricRepository metricRepository;

    @Override
    public Mono<MetricsDto> addMetric(Mono<MetricsDto> metricsDtoMono) {
        return metricsDtoMono.map(AppUtils::dtoToEntity).flatMap(metricRepository::insert).map(AppUtils::entityToDto);
    }
    @Override
    public Mono<Double> getMetricBySensorId(int id, String metricType, LocalDate startDate, LocalDate endDate) {
        Flux<Metrics> data = metricRepository.findAllSensorId(id, metricType, startDate, endDate);
        return data.map(AppUtils::entityToDto).map(MetricsDto::getMetricReading).collect(Collectors.averagingDouble(Double::doubleValue));
    }
    @Override
    public Mono<Double> getAllSensorMetric(String metricType, LocalDate startDate, LocalDate endDate) {
        Flux<Metrics> data = metricRepository.getAllMetricData(metricType, startDate, endDate);
        return data.map(AppUtils::entityToDto).map(MetricsDto::getMetricReading).collect(Collectors.averagingDouble(Double::doubleValue));
    }

    public Mono<Map<String, Double>> getAllData(LocalDate startDate, LocalDate endDate) {
        Flux<Metrics> data = metricRepository.getAllData(startDate, endDate);
        return (Mono<Map<String, Double>>) data.map(AppUtils::entityToDto).collect(Collectors.groupingBy(MetricsDto::getMetricType,Collectors.averagingDouble(MetricsDto::getMetricReading)));

    }

}

