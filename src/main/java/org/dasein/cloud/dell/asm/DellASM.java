/**
 * Copyright (C) 2009-2015 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.dell.asm;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.dasein.cloud.AbstractCloud;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.ContextRequirements;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.dell.asm.compute.ASMComputeServices;
import org.dasein.cloud.dell.asm.model.authentication.ASMAuthenticationRequestModel;
import org.dasein.cloud.dell.asm.model.authentication.ASMAuthenticationResponseModel;
import org.dasein.cloud.dell.asm.requests.ASMRequester;
import org.dasein.cloud.dell.asm.requests.ASMRequests;
import org.dasein.cloud.dell.asm.utils.LoggerUtils;
import org.dasein.cloud.compute.ComputeServices;
import org.dasein.cloud.dc.DataCenterServices;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Core cloud provider implementation for Dell Active System Manager.
 * @author Drew Lyall (drew.lyall@imaginary.com)
 * @since 2015.09.1
 * @version 2015.09.1
 */
public class DellASM extends AbstractCloud {
    private static final Logger logger = LoggerUtils.getLogger(DellASM.class);

    public enum HTTP_METHOD{
        PUT, POST, GET
    }

    private String ASM_API_VERSION = "V1";
    private String DSN_ACCESS_KEY = "apiKey";

    @Override
    public @Nonnull String getCloudName() {
        return "ASM";
    }

    @Override
    public @Nonnull String getProviderName() {
        return "Dell";
    }

    @Override
    public @Nonnull ContextRequirements getContextRequirements() {
        return new ContextRequirements(
                new ContextRequirements.Field(DSN_ACCESS_KEY, "The API Keypair", ContextRequirements.FieldType.KEYPAIR, ContextRequirements.Field.ACCESS_KEYS, true),
                new ContextRequirements.Field("proxyHost", "Proxy host", ContextRequirements.FieldType.TEXT, null, false),
                new ContextRequirements.Field("proxyPort", "Proxy port", ContextRequirements.FieldType.TEXT, null, false)
        );
    }

    @Override
    public @Nullable String testContext() {
        if(getContext() == null) {
            logger.error("Context object was null");
            return null;
        }

        try {
            String[] keys = getAPIKeys();
            if(keys.length == 2 && keys[0].length() > 0 && keys[1].length() > 0){
                return getContext().getAccountNumber();//TODO: Might be something better I can get and return
            }
        } catch (Exception ex) {
            logger.error("Could not text context: " + ex.getMessage());
        }
        return null;
    }

    public String[] getAPIKeys() throws InternalException{
        if(getContext() != null && getContext().getConfigurationValue(DSN_ACCESS_KEY) != null){
            byte[][] configValues = (byte[][])getContext().getConfigurationValue(DSN_ACCESS_KEY);

            ASMAuthenticationRequestModel authRequest = new ASMAuthenticationRequestModel();
            authRequest.setUserName(new String(configValues[0]));
            authRequest.setPassword(new String(configValues[1]));
            authRequest.setDomain(getContext().getAccountNumber());

            try{
                HttpUriRequest request = new ASMRequests(this).getAPIKeys(authRequest).build();
                ASMAuthenticationResponseModel response = new ASMRequester(this, request).withXmlProcessor(ASMAuthenticationResponseModel.class).execute();
                return new String[]{response.getApiKey(), response.getApiSecret()};
            }
            catch(CloudException ex){
                logger.error(ex.getMessage());
                return null;
            }
        }
        else throw new InternalException("Credentials not properly provided");
    }

    public HttpClientBuilder getASMClientBuilder(){
        try{
            //TODO: Add an if(insecure) flag here - useful for testing etc.
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom().setSSLSocketFactory(sslsf);
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    public String getApiVersion(){
        return ASM_API_VERSION;
    }

    @Override
    public @Nonnull DataCenterServices getDataCenterServices() {
        return new ASMGeography(this);
    }

    @Override
    public @Nonnull ComputeServices getComputeServices() { return new ASMComputeServices(this); }
}
