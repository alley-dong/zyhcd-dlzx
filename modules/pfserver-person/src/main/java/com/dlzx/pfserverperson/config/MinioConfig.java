package com.dlzx.pfserverperson.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
@ComponentScan(basePackages = {"com.dlzx.common.minio.*"})
public class MinioConfig {
    @ApiModelProperty("endPoint是一个URL，域名，IPv4或者IPv6地址")
    @Value("${minio.endpoint}")
    private String endpoint;

    @ApiModelProperty("TCP/IP端口号")
    private int port;

    @ApiModelProperty("accessKey类似于用户ID，用于唯一标识你的账户")
    @Value("${minio.accessKey}")
    private String accessKey;

    @ApiModelProperty("secretKey是你账户的密码")
    @Value("${minio.secretKey}")
    private String secretKey;

    @ApiModelProperty("如果是true，则用的是https而不是http,默认值是true")
    private Boolean secure;


    @Bean
    public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
        MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey,false);
        return minioClient;
    }
}
