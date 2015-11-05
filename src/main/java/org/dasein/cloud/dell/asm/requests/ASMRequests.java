package org.dasein.cloud.dell.asm.requests;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.dell.asm.model.authentication.ASMAuthenticationRequestModel;
import org.dasein.cloud.dell.asm.model.virtualmachine.ASMDeploymentModel;
import org.dasein.cloud.dell.asm.utils.LoggerUtils;
import org.dasein.cloud.util.requester.entities.DaseinObjectToXmlEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * A class to handle all ASM API requests
 * @author Drew Lyall (drew.lyall@imaginary.com)
 * @since 2015.09.1
 * @version 2015.09.1
 */
public class ASMRequests {
    private DellASM provider;
    private Logger wire = LoggerUtils.getWireLogger(ASMRequests.class);

    String uri = "/Api/%s/%s";
    //String uri = "/Api";
    private String userAgent = "Dasein";

    public ASMRequests(DellASM provider){
        this.provider = provider;
    }

    public RequestBuilder getRequestBuilder(@Nonnull DellASM.HTTP_METHOD httpMethod, @Nonnull ASMRequestType.RequestType requestType, @Nullable String resourceId, boolean addAuthentication) throws InternalException, CloudException{
        RequestBuilder requestBuilder = null;
        switch (httpMethod) {
            case POST:
                requestBuilder = RequestBuilder.post();
                break;
            case GET:
                requestBuilder = RequestBuilder.get();
                break;
            default:
                requestBuilder = RequestBuilder.get();
                break;
        }

        addCommonHeaders(requestBuilder);
        String uriPath = String.format(uri, provider.getApiVersion(), ASMRequestType.RequestType.getUrlEndpoint(requestType)) + (resourceId == null ? "" : "/" + resourceId);
        requestBuilder.setUri(provider.getContext().getCloud().getEndpoint() + uriPath);//TODO: I need to add filtering parameters
        //requestBuilder.setUri(provider.getContext().getCloud().getEndpoint() + uri + "/" + provider.getApiVersion() + "/" + ASMRequestType.RequestType.getUrlEndpoint(requestType) + (!addAuthentication ? "" : "?filter=eq,templateName,Linux%20VM"));
        if(addAuthentication)addAuthenticationHeaders(requestBuilder, uriPath);
        if(wire.isDebugEnabled()){
            wire.debug(requestBuilder.getUri());
        }
        return requestBuilder;
    }

    public RequestBuilder getAPIKeys(@Nonnull ASMAuthenticationRequestModel requestModel) throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.POST, ASMRequestType.RequestType.AUTHENTICATE, null, false);
        requestBuilder.setEntity(new DaseinObjectToXmlEntity<>(requestModel));
        return requestBuilder;
    }

    public RequestBuilder getServiceTemplate(@Nonnull String providerMachineImageId) throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.GET, ASMRequestType.RequestType.SERVICE_TEMPLATE, providerMachineImageId, true);
        return requestBuilder;
    }

    public RequestBuilder listServiceTemplates() throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.GET, ASMRequestType.RequestType.SERVICE_TEMPLATE, null, true);
        return requestBuilder;
    }

    public RequestBuilder getVirtualMachine(@Nonnull String providerVirtualMachineId) throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.GET, ASMRequestType.RequestType.DEPLOYMENT, providerVirtualMachineId, true);
        return requestBuilder;
    }

    public RequestBuilder listVirtualMachines() throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.GET, ASMRequestType.RequestType.DEPLOYMENT, null, true);
        return requestBuilder;
    }

    public RequestBuilder launchVirtualMachine(@Nonnull ASMDeploymentModel deploymentModel) throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.POST, ASMRequestType.RequestType.DEPLOYMENT, null, true);
        requestBuilder.setEntity(new DaseinObjectToXmlEntity<>(deploymentModel));

        if(wire.isDebugEnabled()){
            try{wire.debug(EntityUtils.toString(requestBuilder.getEntity()));}catch(Exception ex){}
        }
        return requestBuilder;
    }

    public RequestBuilder terminateVirtualMachine(@Nonnull ASMDeploymentModel deploymentModel, @Nonnull String providerVirtualMachineId) throws InternalException, CloudException{
        RequestBuilder requestBuilder = getRequestBuilder(DellASM.HTTP_METHOD.PUT, ASMRequestType.RequestType.DEPLOYMENT, providerVirtualMachineId, true);
        requestBuilder.setEntity(new DaseinObjectToXmlEntity<>(deploymentModel));
        if(wire.isDebugEnabled()){
            try{wire.debug(EntityUtils.toString(requestBuilder.getEntity()));}catch(Exception ex){}
        }
        return requestBuilder;
    }

    private void addCommonHeaders(RequestBuilder requestBuilder){
        requestBuilder.addHeader("Content-Type", "application/xml");
        requestBuilder.addHeader("Accept", "application/xml");
        requestBuilder.addHeader("User-Agent", userAgent);
    }

    private void addAuthenticationHeaders(RequestBuilder requestBuilder, String uriPath) throws InternalException, CloudException{
        String[] apiKeys = provider.getAPIKeys();
        long timestamp = System.currentTimeMillis()/1000L;
        String signature = generateSignatureDigest(apiKeys, requestBuilder, uriPath, timestamp);

        requestBuilder.addHeader("x-dell-auth-key", apiKeys[0]);
        requestBuilder.addHeader("x-dell-auth-signature", signature);
        requestBuilder.addHeader("x-dell-auth-timestamp", timestamp + "");
    }

    private String generateSignatureDigest(String[] apiKeys, RequestBuilder requestBuilder, String uriPath, long timestamp) throws InternalException{
        String httpMethod = requestBuilder.getMethod();
        //String uriPath = uri + "/" + provider.getApiVersion() + "/" + ASMRequestType.RequestType.getUrlEndpoint(requestType);
        String requestString = apiKeys[0] + ":" + httpMethod + ":" + uriPath + ":" + userAgent + ":" + timestamp;

        try{
            SecretKey signingKey = new SecretKeySpec(apiKeys[1].getBytes(), "HMACSHA256");
            Mac mac = Mac.getInstance("HMACSHA256");
            mac.init(signingKey);
            return Base64.encodeBase64String(mac.doFinal(requestString.getBytes("UTF-8")));
        }
        catch(Exception ex){
            throw new InternalException(ex);
        }
    }
}
