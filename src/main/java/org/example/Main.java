package org.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3CrtAsyncClientBuilder;
import software.amazon.awssdk.services.s3.crt.S3CrtHttpConfiguration;
import software.amazon.awssdk.services.s3.crt.S3CrtProxyConfiguration;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello world!");

        String accessKey = Objects.requireNonNullElse(System.getenv("accessKey"), "baz");
        String secretKey = Objects.requireNonNullElse(System.getenv("secretKey"), "ban");

        S3CrtAsyncClientBuilder s3AsyncClient =
                S3AsyncClient.crtBuilder()
                        .httpConfiguration(
                                S3CrtHttpConfiguration.builder()
                                        .proxyConfiguration(
                                                S3CrtProxyConfiguration.builder()
                                                        .scheme("http")
                                                        .host("squid-proxy")
                                                        .port(3128)
                                                        .build())
                                        .build())
                        .targetThroughputInGbps(1D)
                        .region(Region.of("us-east-1"))
                        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)));
        S3AsyncClient client = s3AsyncClient.build();

        System.out.println(client.listBuckets().get().toString());
    }
}
