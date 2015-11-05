package org.dasein.cloud.dell.asm.requests;

import javax.annotation.Nullable;

public class ASMRequestType {
    public enum RequestType{
        AUTHENTICATE("Authenticate"),
        SERVICE_TEMPLATE("ServiceTemplate"),
        DEPLOYMENT("Deployment");

        private String urlEndpoint;

        RequestType(String urlEndpoint){
            this.urlEndpoint = urlEndpoint;
        }

        public static @Nullable String getUrlEndpoint(RequestType requestType){
            for(RequestType type : RequestType.values()){
                if(type == requestType){
                    return type.urlEndpoint;
                }
            }
            return null;
        }
    }
}
