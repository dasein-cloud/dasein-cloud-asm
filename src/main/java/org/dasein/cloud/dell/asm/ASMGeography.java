/*
 * Copyright (C) 2013-2014 Dell, Inc
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

import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.OperationNotSupportedException;
import org.dasein.cloud.dell.asm.capabilities.ASMDataCenterCapabilities;
import org.dasein.cloud.dc.*;
import org.dasein.cloud.util.APITrace;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;

/**
 * Implements Datacenter concepts for ASM
 * @author Drew Lyall (drew.lyall@imaginary.com)
 * @since 2015.09.1
 * @version 2015.09.1
 */
public class ASMGeography extends AbstractDataCenterServices<DellASM> {
    private DellASM provider;

    ASMGeography(DellASM provider){
        super(provider);
        this.provider = provider;
    }

    private transient volatile ASMDataCenterCapabilities capabilities;
    @Nonnull
    @Override
    public ASMDataCenterCapabilities getCapabilities() throws InternalException, CloudException {
        if( capabilities == null ) {
            capabilities = new ASMDataCenterCapabilities(getProvider());
        }
        return capabilities;
    }

    @Nullable
    @Override
    public DataCenter getDataCenter(@Nonnull String providerDataCenterId) throws InternalException, CloudException {
        APITrace.begin(provider, "DC.getDataCenter");
        try{
            for(Region r : listRegions()){
                for(DataCenter dc : listDataCenters(r.getProviderRegionId())){
                    if(providerDataCenterId.equals(dc.getProviderDataCenterId()))return dc;
                }
            }
            throw new InternalException("The DataCenter could not be found");
        }
        finally {
            APITrace.end();
        }
    }

    @Nullable
    @Override
    public Region getRegion(@Nonnull String providerRegionId) throws InternalException, CloudException {
        APITrace.begin(provider, "DC.getRegion");
        try{
            for(Region r : listRegions()){
                if(providerRegionId.equals(r.getProviderRegionId()))return r;
            }
            throw new InternalException("The Region could not be found");
        }
        finally {
            APITrace.end();
        }
    }

    @Nonnull
    @Override
    public Iterable<DataCenter> listDataCenters(@Nonnull String providerRegionId) throws InternalException, CloudException {
        APITrace.begin(provider, "DC.listDataCenters");
        try {
            Region region = getRegion(providerRegionId);

            if( region == null ) {
                throw new CloudException("No such region: " + providerRegionId);
            }
            DataCenter dc = new DataCenter(region.getProviderRegionId() + "-a", region.getProviderRegionId() + "-a", providerRegionId, true, true);
            return Collections.singletonList(dc);
        }
        finally {
            APITrace.end();
        }
    }

    @Nonnull
    @Override
    public Iterable<Region> listRegions() throws InternalException, CloudException {
        APITrace.begin(provider, "DC.listRegions");
        try{
            Region region = new Region("ASM", "ASM", true, true);
            return Collections.singletonList(region);
        }
        finally {
            APITrace.end();
        }
    }

    @Nonnull
    @Override
    public Iterable<ResourcePool> listResourcePools(@Nonnull String providerDataCenterId) throws InternalException, CloudException {
        throw new OperationNotSupportedException("Resource pools are not supported in " + provider.getCloudName());
    }

    @Nullable
    @Override
    public ResourcePool getResourcePool(@Nonnull String providerResourcePoolId) throws InternalException, CloudException {
        throw new OperationNotSupportedException("Resource pools are not supported in " + provider.getCloudName());
    }

    @Nonnull
    @Override
    public Iterable<StoragePool> listStoragePools() throws InternalException, CloudException {
        throw new OperationNotSupportedException("Storage pools are not supported in " + provider.getCloudName());
    }

    @Nullable
    @Override
    public StoragePool getStoragePool(@Nonnull String providerStoragePoolId) throws InternalException, CloudException {
        throw new OperationNotSupportedException("Stprage pools are not supported in " + provider.getCloudName());
    }

    @Nonnull
    @Override
    public Iterable<Folder> listVMFolders() throws InternalException, CloudException {
        throw new OperationNotSupportedException("Folders are not supported in " + provider.getCloudName());
    }

    @Nullable
    @Override
    public Folder getVMFolder(@Nonnull String providerVMFolderId) throws InternalException, CloudException {
        throw new OperationNotSupportedException("Folders are not supported in " + provider.getCloudName());
    }
}
