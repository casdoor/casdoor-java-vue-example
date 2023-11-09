package org.casbin.casdoor.demo.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.casbin.casdoor.config.CasdoorConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(prefix = "casdoor")
public class CasdoorConfiguration extends CasdoorConfig {
}
