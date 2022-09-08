package com.weatherapp.metrics.repository;
import com.weatherapp.metrics.model.Metrics;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

/*
* Persisting data using Reactive Mongo Repository
*/
public interface MetricRepository extends ReactiveMongoRepository<Metrics, String> {
    @Query("{'sensorId' : ?0, 'metricType' : ?1,'date' :  {$gte: ?2}, 'date' :  {$lte: ?3}}")
    Flux<Metrics> findAllSensorId(int id, String metricType, LocalDate startDate, LocalDate endDate);

    @Query("{'metricType' : ?0,'date' :  {$gte: ?1}, 'date' :  {$lte: ?2}}")
    Flux<Metrics> getAllMetricData(String metricType, LocalDate startDate, LocalDate endDate);

    @Query("'date' :  {$gte: ?0}, 'date' :  {$lte: ?2}}")
    Flux<Metrics> getAllData(LocalDate startDate, LocalDate endDate);
}
