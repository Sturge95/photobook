package com.sturgeon.photobook.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }
}
