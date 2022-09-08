package com.weatherapp.metrics;

import com.weatherapp.metrics.controller.MetricsResource;
import com.weatherapp.metrics.dto.MetricsDto;
import com.weatherapp.metrics.service.MetricsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.time.LocalDate;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(MetricsResource.class)
class MetricsApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private MetricsService service;

	@Test
	public void addMetricsTest(){
		Mono<MetricsDto> metricsDtoMono= Mono.just(new MetricsDto(2,50.0,"temperature"));
		when(service.addMetric(metricsDtoMono)).thenReturn(metricsDtoMono);

		webTestClient.post().uri("/sensor/metrics/addReading")
				.body(Mono.just(metricsDtoMono),MetricsDto.class)
				.exchange()
				.expectStatus().isOk();//200

	}

	@Test
	public void getMetricsBySensorIdTest(){
		Flux<MetricsDto> metricsDtoFlux= Flux.just(new MetricsDto(3,101.0,"temperature"),
				new MetricsDto(3,11.0,"temperature"));
		Mono<Double> result = Mono.just(56.0);
		when(service.getMetricBySensorId(3, "temperature", LocalDate.parse("2017-06-28"), LocalDate.parse("2017-06-30"))).thenReturn(result);

		Flux<Double> floatFlux = webTestClient.get().uri(uriBuilder ->
						uriBuilder
								.path("/sensor/metrics/getDataBySensorId/3")
								.queryParam("metricType", "temperature")
								.queryParam("start", "2017-06-28")
								.queryParam("end", "2017-06-30")
								.build())
				.exchange()
				.expectStatus().isOk()
				.returnResult(Double.class)
				.getResponseBody();

		StepVerifier.create(floatFlux)
				.expectSubscription()
				.expectNext(56.0)
				.verifyComplete();

	}

}
