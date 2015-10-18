package org.dasein.cloud.dell.asm.capabilities;

import org.dasein.cloud.*;
import org.dasein.cloud.compute.*;
import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.util.NamingConstraints;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class ASMVirtualMachineCapabilities extends AbstractCapabilities<DellASM> implements VirtualMachineCapabilities {

    public ASMVirtualMachineCapabilities(DellASM provider){
        super(provider);
    }

    @Override
    public boolean canAlter(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canClone(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canPause(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canReboot(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canResume(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canStart(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canStop(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canSuspend(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canTerminate(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canUnpause(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public int getMaximumVirtualMachineCount() throws CloudException, InternalException {
        return Capabilities.LIMIT_UNLIMITED;
    }

    @Override
    public int getCostFactor(@Nonnull VmState state) throws CloudException, InternalException {
        return 0;
    }

    @Nonnull
    @Override
    public String getProviderTermForVirtualMachine(@Nonnull Locale locale) throws CloudException, InternalException {
        return "Deployment";
    }

    @Nullable
    @Override
    public VMScalingCapabilities getVerticalScalingCapabilities() throws CloudException, InternalException {
        return null;
    }

    @Nonnull
    @Override
    public NamingConstraints getVirtualMachineNamingConstraints() throws CloudException, InternalException {
        return NamingConstraints.getAlphaNumeric(1, 255);
    }

    @Nullable
    @Override
    public VisibleScope getVirtualMachineVisibleScope() {
        return VisibleScope.ACCOUNT_REGION;
    }

    @Nullable
    @Override
    public VisibleScope getVirtualMachineProductVisibleScope() {
        return VisibleScope.ACCOUNT_REGION;
    }

    @Nullable
    @Override
    public String[] getVirtualMachineReservedUserNames() {
        return new String[0];
    }

    @Nonnull
    @Override
    public Requirement identifyDataCenterLaunchRequirement() throws CloudException, InternalException {
        return Requirement.REQUIRED;
    }

    @Nonnull
    @Override
    public Requirement identifyImageRequirement(@Nonnull ImageClass cls) throws CloudException, InternalException {
        return Requirement.REQUIRED;
    }

    @Nonnull
    @Override
    public Requirement identifyUsernameRequirement() throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Nonnull
    @Override
    public Requirement identifyPasswordRequirement(Platform platform) throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Nonnull
    @Override
    public Requirement identifyRootVolumeRequirement() throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Nonnull
    @Override
    public Requirement identifyShellKeyRequirement(Platform platform) throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Nonnull
    @Override
    public Requirement identifyStaticIPRequirement() throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Nonnull
    @Override
    public Requirement identifySubnetRequirement() throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Nonnull
    @Override
    public Requirement identifyVlanRequirement() throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Override
    public boolean isAPITerminationPreventable() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean isBasicAnalyticsSupported() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean isExtendedAnalyticsSupported() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean isUserDataSupported() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean isUserDefinedPrivateIPSupported() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean isRootPasswordSSHKeyEncrypted() throws CloudException, InternalException {
        return false;
    }

    private static volatile Collection<Architecture> architectures;
    @Nonnull
    @Override
    public Iterable<Architecture> listSupportedArchitectures() throws InternalException, CloudException {
        if (architectures == null) {
            architectures = Arrays.asList(Architecture.I32, Architecture.I64);
        }
        return architectures;
    }

    @Override
    public boolean supportsSpotVirtualMachines() throws InternalException, CloudException {
        return false;
    }

    @Override
    public boolean supportsClientRequestToken() throws InternalException, CloudException {
        return false;
    }

    @Override
    public boolean supportsCloudStoredShellKey() throws InternalException, CloudException {
        return false;
    }

    @Override
    public boolean isVMProductDCConstrained() throws InternalException, CloudException {
        return true;
    }

    @Override
    public boolean supportsAlterVM() {
        return false;
    }

    @Override
    public boolean supportsClone() {
        return false;
    }

    @Override
    public boolean supportsPause() {
        return false;
    }

    @Override
    public boolean supportsReboot() {
        return false;
    }

    @Override
    public boolean supportsResume() {
        return false;
    }

    @Override
    public boolean supportsStart() {
        return false;
    }

    @Override
    public boolean supportsStop() {
        return false;
    }

    @Override
    public boolean supportsSuspend() {
        return false;
    }

    @Override
    public boolean supportsTerminate() {
        return false;
    }

    @Override
    public boolean supportsUnPause() {
        return false;
    }
}
