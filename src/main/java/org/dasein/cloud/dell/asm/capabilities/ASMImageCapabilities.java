package org.dasein.cloud.dell.asm.capabilities;

import org.dasein.cloud.*;
import org.dasein.cloud.compute.*;
import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.util.NamingConstraints;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Locale;

public class ASMImageCapabilities extends AbstractCapabilities<DellASM> implements ImageCapabilities {

    public ASMImageCapabilities(DellASM provider) {
        super(provider);
    }

    @Override
    public boolean canBundle(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean canImage(@Nonnull VmState fromState) throws CloudException, InternalException {
        return false;
    }

    @Override
    public @Nonnull String getProviderTermForImage(@Nonnull Locale locale, @Nonnull ImageClass cls) {
        return "Service Template";
    }

    @Override
    public @Nonnull String getProviderTermForCustomImage(@Nonnull Locale locale, @Nonnull ImageClass cls) {
        return "Service Template";
    }

    @Override
    public @Nonnull VisibleScope getImageVisibleScope() {
        return VisibleScope.ACCOUNT_REGION;
    }

    @Override
    public @Nonnull Requirement identifyLocalBundlingRequirement() throws CloudException, InternalException {
        return Requirement.NONE;
    }

    @Override
    public @Nonnull Iterable<MachineImageFormat> listSupportedFormats() throws CloudException, InternalException {
        return Collections.singletonList(MachineImageFormat.RAW);
    }

    @Override
    public @Nonnull Iterable<MachineImageFormat> listSupportedFormatsForBundling() throws CloudException, InternalException {
        return Collections.emptyList();
    }

    @Override
    public @Nonnull Iterable<ImageClass> listSupportedImageClasses() throws CloudException, InternalException {
        return Collections.singletonList(ImageClass.MACHINE);
    }

    @Override
    public @Nonnull Iterable<MachineImageType> listSupportedImageTypes() throws CloudException, InternalException {
        return Collections.singletonList(MachineImageType.VOLUME);
    }

    @Override
    public boolean supportsDirectImageUpload() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsImageCapture(@Nonnull MachineImageType type) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsImageCopy() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsImageRemoval() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsImageSharing() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsImageSharingWithPublic() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsListingAllRegions() throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean supportsPublicLibrary(@Nonnull ImageClass cls) throws CloudException, InternalException {
        return false;
    }

    @Override
    public boolean imageCaptureDestroysVM() throws CloudException, InternalException {
        return false;
    }

    @Override
    public @Nonnull NamingConstraints getImageNamingConstraints() throws CloudException, InternalException {
        return NamingConstraints.getAlphaNumeric(1, 200);
    }
}
