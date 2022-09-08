package com.weatherapp.metrics.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metrics {
    @Id
    private String Id;
    private int sensorId;
    private double metricReading;
    private String metricType;
    private LocalDateTime date;

    public void setDate(String date) throws ParseException {
        System.out.println("Metrics 1 ");
        System.out.println(date);
        this.date = LocalDateTime.parse(date);
    }
}

