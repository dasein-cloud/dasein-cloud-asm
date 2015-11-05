package org.dasein.cloud.dell.asm.compute;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.log4j.Logger;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.OperationNotSupportedException;
import org.dasein.cloud.compute.*;
import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.dell.asm.capabilities.ASMImageCapabilities;
import org.dasein.cloud.dell.asm.model.image.ASMServiceTemplateModel;
import org.dasein.cloud.dell.asm.model.image.ASMServiceTemplatesModel;
import org.dasein.cloud.dell.asm.requests.ASMRequester;
import org.dasein.cloud.dell.asm.requests.ASMRequests;
import org.dasein.cloud.dell.asm.utils.LoggerUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of specific ASM Service Templates as Dasein images.
 * @author Drew Lyall (drew.lyall@imaginary.com)
 * @since 2015.09.1
 * @version 2015.09.1
 */
public class ASMImageSupport extends AbstractImageSupport<DellASM> {
    private static final Logger logger = LoggerUtils.getLogger(ASMImageSupport.class);
    private DellASM provider;

    private volatile transient ASMImageCapabilities capabilities;

    public ASMImageSupport(DellASM provider){
        super(provider);
        this.provider = provider;
    }

    @Override
    public ImageCapabilities getCapabilities() throws CloudException, InternalException {
        if(capabilities == null){
            capabilities = new ASMImageCapabilities(provider);
        }
        return capabilities;
    }

    @Override
    public @Nullable MachineImage getImage(@Nonnull String providerImageId) throws CloudException, InternalException {
        HttpUriRequest getImageRequest = new ASMRequests(provider).getServiceTemplate(providerImageId).build();
        ASMServiceTemplateModel templateModel = new ASMRequester(provider, getImageRequest).withXmlProcessor(ASMServiceTemplateModel.class).execute();
        return toMachineImage(templateModel);
    }

    @Override
    public boolean isSubscribed() throws CloudException, InternalException {
        return true;
    }

    @Override
    public @Nonnull Iterable<MachineImage> listImages(@Nullable final ImageFilterOptions options) throws CloudException, InternalException {
        if (options != null && !ImageClass.MACHINE.equals(options.getImageClass())) {
            return Collections.emptyList();
        }

        return getImages(new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                if(options != null){
                    return options.matches((MachineImage)object);
                }
                else{
                    return true;
                }
            }
        });
    }

    private List<MachineImage> getImages(Predicate predicate) throws CloudException, InternalException {
        HttpUriRequest listImagesRequest = new ASMRequests(provider).listServiceTemplates().build();
        ASMServiceTemplatesModel imagesModel = new ASMRequester(provider, listImagesRequest).withXmlProcessor(ASMServiceTemplatesModel.class).execute();

        final String regionId = provider.getContext().getRegionId();//TODO: Region filtering doesn't really make sense in ASM
        final List<MachineImage> images = new ArrayList<>();
        CollectionUtils.forAllDo(imagesModel.getImages(), new Closure() {
            @Override
            public void execute(Object input) {
                ASMServiceTemplateModel templateModel = (ASMServiceTemplateModel)input;
                if(templateModel.getCategory().equals("DaseinVM") && !templateModel.isDraft()){//TODO: Category should be done in API query filter
                    images.add(toMachineImage(templateModel));
                }
            }
        });

        CollectionUtils.filter(images, predicate);
        return images;
    }

    @Override
    public void remove(@Nonnull String providerImageId, boolean checkState) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Removal of images is not currently supported in " + provider.getCloudName());
    }

    private MachineImage toMachineImage(ASMServiceTemplateModel template){
        MachineImage image = MachineImage.getInstance(template.getCreatedBy(),
                provider.getContext().getRegionId(),
                template.getId(),
                ImageClass.MACHINE,
                MachineImageState.ACTIVE,
                template.getTemplateName(),
                template.getTemplateDescription(),
                Architecture.I64,
                template.getTemplateName().toLowerCase().contains("windows") ? Platform.WINDOWS : Platform.UNIX);
        return image;
    }
}
