# JavaAwsCrtNoProxy

Example of unexpected behavior reading env proxy variables.

### Development

```
docker build .
docker run {SHA}
```

Expected output:
```
Hello world!
Exception in thread "main" java.util.concurrent.ExecutionException: software.amazon.awssdk.services.s3.model.S3Exception: The AWS Access Key Id you provided does not exist in our records. (Service: S3, Status Code: 403, Request ID: 59C..., Extended Request ID: JjKPL...)
	at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:396)
	at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:2073)
	at org.example.Main.main(Main.java:35)
Caused by: software.amazon.awssdk.services.s3.model.S3Exception: The AWS Access Key Id you provided does not exist in our records. (Service: S3, Status Code: 403, Request ID: 59C..., Extended Request ID: JjKPL...)
```

Observed output:
```
Hello world!
Exception in thread "main" java.util.concurrent.ExecutionException: software.amazon.awssdk.core.exception.SdkClientException: Failed to send the request: socket connection refused.
        at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:396)
        at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:2073)
        at org.example.Main.main(Main.java:35)
Caused by: software.amazon.awssdk.core.exception.SdkClientException: Failed to send the request: socket connection refused.
```
