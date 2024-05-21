package org.gelecekbilimde.scienceplatform.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.gelecekbilimde.scienceplatform.notification.client")
class FeignClientConfiguration {
}
