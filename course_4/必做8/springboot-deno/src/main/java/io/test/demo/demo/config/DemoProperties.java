package io.test.demo.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DemoProperties.PREFIX)
public class DemoProperties {

    public static final String PREFIX = "spring.demo";

}
