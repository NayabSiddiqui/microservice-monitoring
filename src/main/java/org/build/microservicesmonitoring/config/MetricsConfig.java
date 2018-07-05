package org.build.microservicesmonitoring.config;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    private String hostId = "Blaze";

    @Value("${spring.application.name}")
    private String serviceId;

    @Value("${server.context-path}")
    private String serverContextPath;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() { // (2)
        return registry -> registry.config()
                .commonTags("host", hostId, "service", serviceId) // (3)
                .meterFilter(MeterFilter.deny(id -> { // (4)
                    String uri = id.getTag("uri");
                    return nonMonitoredEndpoints(uri);
                }))
                .meterFilter(MeterFilter.replaceTagValues("uri", s -> serverContextPath.concat(s)))
                .meterFilter(new MeterFilter() {
                    @Override
                    public DistributionStatisticConfig configure(Meter.Id id,
                                                                 DistributionStatisticConfig config) {
                        return config.merge(DistributionStatisticConfig.builder()
                                .percentilesHistogram(true)
                                .build());
                    }
                });
    }

    private boolean nonMonitoredEndpoints(String uri) {
        return uri != null &&
                (uri.contains("/swagger") ||
                        uri.contains("/health") ||
                        uri.contains("/metrics") ||
                        uri.contains("/trace"));
    }

}
