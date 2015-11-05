package org.dasein.cloud.dell.asm.requests;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.util.requester.fluent.DaseinRequest;

public class ASMRequester extends DaseinRequest {
    public ASMRequester(DellASM provider, HttpUriRequest httpUriRequest) throws CloudException {
        this(provider, provider.getASMClientBuilder(), httpUriRequest);
    }
    public ASMRequester(CloudProvider provider, HttpClientBuilder httpClientBuilder, HttpUriRequest httpUriRequestBuilder) {
        super(provider, httpClientBuilder, httpUriRequestBuilder);
    }
}
