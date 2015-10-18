package org.dasein.cloud.dell.asm.capabilities;

import org.dasein.cloud.AbstractCapabilities;
import org.dasein.cloud.dell.asm.DellASM;

import java.util.Locale;

/**
 * Provides DataCenter Capabilities for ASM
 * @author Drew Lyall (drew.lyall@imaginary.com)
 * @since 2015.09.1
 * @version 2015.09.1
 */
public class ASMDataCenterCapabilities extends AbstractCapabilities<DellASM> implements org.dasein.cloud.dc.DataCenterCapabilities {
    public ASMDataCenterCapabilities(DellASM provider){
        super(provider);
    }

    @Override
    public String getProviderTermForDataCenter(Locale locale) {
        return "DataCenter";
    }

    @Override
    public String getProviderTermForRegion(Locale locale) {
        return "Region";
    }

    @Override
    public boolean supportsAffinityGroups() {
        return false;
    }

    @Override
    public boolean supportsResourcePools() {
        return false;
    }

    @Override
    public boolean supportsStoragePools() {
        return false;
    }

    @Override
    public boolean supportsFolders() {
        return false;
    }
}
