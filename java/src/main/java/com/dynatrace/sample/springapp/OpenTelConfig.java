package com.dynatrace.sample.springapp;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.autoconfigure.OpenTelemetrySdkAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelConfig {
	@Bean
	static OpenTelemetry otelSdk() {
		return OpenTelemetrySdkAutoConfiguration.initialize();
	}
}
