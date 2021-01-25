package com.microsoft.azure.data;

import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {

    @GetMapping(
            value = "/count",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String count() {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .endpoint("https://sa2weidxu.blob.core.windows.net")
                .containerName("container1")
                .credential(new ManagedIdentityCredentialBuilder().build())
                .buildClient();

        long count = containerClient.listBlobs().stream().count();
        return "Number of blobs: " + count;
    }
}
