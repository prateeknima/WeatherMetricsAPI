package com.weatherapp.metrics.utils;

import com.weatherapp.metrics.dto.MetricsDto;
import com.weatherapp.metrics.model.Metrics;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static MetricsDto entityToDto(Metrics metrics) {
        MetricsDto metricsDto = new MetricsDto();
        BeanUtils.copyProperties(metrics, metricsDto);
        return metricsDto;
    }

    public static Metrics dtoToEntity(MetricsDto metricsDto) {
        Metrics metrics = new Metrics();
        BeanUtils.copyProperties(metricsDto, metrics);
        return metrics;
    }
}
