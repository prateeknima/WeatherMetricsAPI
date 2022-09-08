package com.weatherapp.metrics.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.ParseException;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricsDto {

    private int sensorId;
    private double metricReading;
    private String metricType;
    private String date = LocalDateTime.now().toString();

    public void setDate(LocalDateTime date) throws ParseException {
        this.date = date.toString();
    }


    public MetricsDto(int sensorId, double metricReading, String metricType) {
        this.sensorId = sensorId;
        this.metricReading = metricReading;
        this.metricType = metricType;
        this.date = LocalDateTime.now().toString();
    }
}
