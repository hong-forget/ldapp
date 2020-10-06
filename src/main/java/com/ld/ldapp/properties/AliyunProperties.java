package com.ld.ldapp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aliyun")
@Component
@Data
public class AliyunProperties {

    private String xxxx;
    private String yyyy;
    private String zzzz;
    private String aaaa;
    private String bbbb;
}
