package org.dasein.cloud.dell.asm.compute;

import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.compute.AbstractComputeServices;

import javax.annotation.Nonnull;

public class ASMComputeServices extends AbstractComputeServices<DellASM> {
    private DellASM provider;

    public ASMComputeServices(@Nonnull DellASM provider) {
        super(provider);
        this.provider = provider;
    }

    @Override
    public ASMImageSupport getImageSupport() {
        return new ASMImageSupport(provider);
    }

    @Override
    public ASMVirtualMachineSupport getVirtualMachineSupport() {
        return new ASMVirtualMachineSupport(provider);
    }
}
