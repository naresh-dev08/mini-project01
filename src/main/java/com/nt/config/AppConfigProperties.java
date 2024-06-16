package com.nt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "plan.module")
@EnableConfigurationProperties
public class AppConfigProperties {

   private Map<String, String> messages;
}
