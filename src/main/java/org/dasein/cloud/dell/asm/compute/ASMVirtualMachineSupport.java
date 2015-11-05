package org.dasein.cloud.dell.asm.compute;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.log4j.Logger;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.compute.*;
import org.dasein.cloud.dell.asm.DellASM;
import org.dasein.cloud.dell.asm.capabilities.ASMVirtualMachineCapabilities;
import org.dasein.cloud.dell.asm.model.image.*;
import org.dasein.cloud.dell.asm.model.virtualmachine.ASMDeploymentModel;
import org.dasein.cloud.dell.asm.model.virtualmachine.ASMDeploymentsModel;
import org.dasein.cloud.dell.asm.requests.ASMRequester;
import org.dasein.cloud.dell.asm.requests.ASMRequests;
import org.dasein.cloud.dell.asm.utils.LoggerUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of specific ASM Deployments as Dasein VMs.
 * @author Drew Lyall (drew.lyall@imaginary.com)
 * @since 2015.09.1
 * @version 2015.09.1
 */
public class ASMVirtualMachineSupport extends AbstractVMSupport<DellASM> {
    private static final Logger logger = LoggerUtils.getLogger(ASMVirtualMachineSupport.class);
    private DellASM provider;
    private volatile transient ASMVirtualMachineCapabilities capabilities;

    public ASMVirtualMachineSupport(DellASM provider){
        super(provider);
        this.provider = provider;
    }

    @Override
    public @Nonnull ASMVirtualMachineCapabilities getCapabilities() throws InternalException, CloudException {
        if(capabilities == null){
            capabilities = new ASMVirtualMachineCapabilities(provider);
        }
        return capabilities;
    }

    @Override
    public boolean isSubscribed() throws CloudException, InternalException {
        return true;
    }

    @Override
    public @Nonnull VirtualMachine getVirtualMachine(@Nonnull String providerVirtualMachineId) throws CloudException, InternalException{
        HttpUriRequest getVMRequest = new ASMRequests(provider).getVirtualMachine(providerVirtualMachineId).build();
        ASMDeploymentModel deployment = new ASMRequester(provider, getVMRequest).withXmlProcessor(ASMDeploymentModel.class).execute();
        return toVirtualMachine(deployment);
    }

    @Override
    public @Nonnull Iterable<VirtualMachine> listVirtualMachines() throws CloudException, InternalException{
        return listVirtualMachines(null);
    }

    @Override
    public @Nonnull Iterable<VirtualMachine> listVirtualMachines(@Nullable final VMFilterOptions options) throws CloudException, InternalException{
        return getVirtualMachines(new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                if(options != null){
                    return options.matches((VirtualMachine)object);
                }
                else{
                    return true;
                }
            }
        });
    }

    private List<VirtualMachine> getVirtualMachines(Predicate predicate) throws CloudException, InternalException {
        HttpUriRequest listVMsRequest = new ASMRequests(provider).listVirtualMachines().build();
        ASMDeploymentsModel vmsModel = new ASMRequester(provider, listVMsRequest).withXmlProcessor(ASMDeploymentsModel.class).execute();

        final List<VirtualMachine> vms = new ArrayList<>();
        CollectionUtils.forAllDo(vmsModel.getVirtualMachines(), new Closure() {
            @Override
            public void execute(Object input) {
                ASMDeploymentModel vmModel = (ASMDeploymentModel)input;
                if(vmModel.getImage().getCategory().equals("DaseinVM")){
                    vms.add(toVirtualMachine(vmModel));
                }
            }
        });

        CollectionUtils.filter(vms, predicate);
        return vms;
    }

    @Override
    public @Nonnull VirtualMachine launch(@Nonnull VMLaunchOptions withLaunchOptions) throws CloudException, InternalException {
        HttpUriRequest getServiceTemplate = new ASMRequests(provider).getServiceTemplate(withLaunchOptions.getMachineImageId()).build();
        ASMServiceTemplateModel serviceTemplate = new ASMRequester(provider, getServiceTemplate).withXmlProcessor(ASMServiceTemplateModel.class).execute();

        for(ASMServiceTemplateComponent component : serviceTemplate.getComponents()){
            for(ASMServiceTemplateComponentResource resource : component.getResources()){
                if(resource.getId().equals("asm::cluster")){
                    for(ASMServiceTemplateComponentResourceParameter params : resource.getParameters()){
                        if(params.getId().equals("$new$datacenter")){
                            params.setDependencyTarget("datacenter");
                            params.setDependencyValue("$new$");
                        }
                        else if(params.getId().equals("$new$cluster")){
                            params.setDependencyTarget("cluster");
                            params.setDependencyValue("$new$");
                        }
                    }
                }
            }
        }

        try{
            ASMDeploymentModel deploymentRequest = new ASMDeploymentModel();
            deploymentRequest.setName(withLaunchOptions.getFriendlyName());
            deploymentRequest.setDescription(withLaunchOptions.getDescription());
            deploymentRequest.setNumberOfDeployments(1);
            deploymentRequest.setImage(serviceTemplate);
            HttpUriRequest request = new ASMRequests(provider).launchVirtualMachine(deploymentRequest).build();
            ASMDeploymentModel deployment = new ASMRequester(provider, request).withXmlProcessor(ASMDeploymentModel.class).execute();
            return toVirtualMachine(deployment);
        }
        catch(CloudException ex){
            logger.error(ex.getMessage());
            throw new CloudException(ex);
        }
    }

    @Override
    public void terminate(@Nonnull String vmId, @Nullable String explanation) throws InternalException, CloudException {
        HttpUriRequest getVMRequest = new ASMRequests(provider).getVirtualMachine(vmId).build();
        ASMDeploymentModel deployment = new ASMRequester(provider, getVMRequest).withXmlProcessor(ASMDeploymentModel.class).execute();
        for(ASMServiceTemplateComponent component : deployment.getImage().getComponents()){
            component.setTeardown(true);
        }
        deployment.setTeardown(true);

        HttpUriRequest request = new ASMRequests(provider).terminateVirtualMachine(deployment, vmId).build();
        System.out.println(new ASMRequester(provider, request).execute());
    }

    @Override
    public @Nonnull Iterable<VirtualMachineProduct> listProducts(@Nonnull String machineImageId, @Nonnull VirtualMachineProductFilterOptions options) throws InternalException, CloudException {
        VirtualMachineProduct product = new VirtualMachineProduct();
        product.setProviderProductId("default");
        product.setName("Default");
        product.setDescription("The default product");
        return Collections.singletonList(product);
    }

    @Override
    public @Nonnull Iterable<VirtualMachineProduct> listAllProducts() throws InternalException, CloudException{
        return listProducts(null, VirtualMachineProductFilterOptions.getInstance());
    }

    private VirtualMachine toVirtualMachine(ASMDeploymentModel deployment){
        VirtualMachine vm = new VirtualMachine();
        vm.setProviderVirtualMachineId(deployment.getProviderVirtualMachineId());
        vm.setName(deployment.getName());
        //TODO: Set created date/time
        if(deployment.getStatus().equals("complete")) vm.setCurrentState(VmState.RUNNING);
        else if(deployment.getStatus().equals("in_progress")) vm.setCurrentState(VmState.PENDING);
        else vm.setCurrentState(VmState.ERROR);
        vm.setDescription(deployment.getDescription());
        vm.setImagable(false);//TODO: This might change
        vm.setPersistent(true);
        Platform platform = Platform.guess(deployment.getImage().getTemplateName().toLowerCase());
        vm.setPlatform(platform);
        vm.setArchitecture(Architecture.I64);//TODO: Not sure if I can always get this
        vm.setProductId("default");
        vm.setProviderMachineImageId(deployment.getImage().getId());
        vm.setProviderOwnerId(deployment.getCreatedBy());
        vm.setProviderRegionId(provider.getContext().getRegionId());
        return vm;
    }
}
