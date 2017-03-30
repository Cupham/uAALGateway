package echowand.service;

import java.util.logging.Logger;

import echowand.common.EPC;
import echowand.info.NodeProfileInfo;
import echowand.info.PropertyInfo;
import echowand.object.LocalObjectDateTimeDelegate;
import echowand.object.LocalObjectNotifyDelegate;
import echowand.object.NodeProfileObjectDelegate;
import echowand.util.Constraint;

/**
 * @author ymakino
 */
public class NodeProfileObjectConfig extends LocalObjectConfig {
    private static final Logger LOGGER = Logger.getLogger(NodeProfileObjectConfig.class.getName());
    private static final String CLASS_NAME = NodeProfileObjectConfig.class.getName();
    
    private NodeProfileInfo nodeProfileInfo;
    
    private LazyConfiguration dateTimeConfiguration;
    private LazyConfiguration nodeProfileConfiguration;
    private LazyConfiguration notifyConfiguration;
    
    /**
     * NodeProfileObjectConfig
     */
    public NodeProfileObjectConfig() {
        super(new NodeProfileInfo());
        nodeProfileInfo = (NodeProfileInfo)getObjectInfo();
        
        dateTimeConfiguration = new LazyConfiguration() {
            @Override
            public void configure(LocalObjectConfig config, Core core) {
                nodeProfileInfo.add(EPC.x97, true, false, false, 2);
                nodeProfileInfo.add(EPC.x98, true, false, false, 4);
                config.addDelegate(new LocalObjectDateTimeDelegate());
            }
        };
        
        nodeProfileConfiguration = new LazyConfiguration() {
            @Override
            public void configure(LocalObjectConfig config, Core core) {
                addDelegate(new NodeProfileObjectDelegate(core.getLocalObjectManager()));
            }
        };
        
        notifyConfiguration = new LazyConfiguration() {
            @Override
            public void configure(LocalObjectConfig config, Core core) {
                addDelegate(new LocalObjectNotifyDelegate(core.getSubnet(), core.getTransactionManager()));
            }
        };
        
        addLazyConfiguration(dateTimeConfiguration);
        addLazyConfiguration(nodeProfileConfiguration);
        addLazyConfiguration(notifyConfiguration);
    }
    
    /**
     * LocalObjectDateTimeDelegate
     */
    public void enableDateTimeDelegate() {
        if (!isDateTimeDelegateEnabled()) {
            addLazyConfiguration(dateTimeConfiguration);
        }
    }
    
    
    /**
     * LocalObjectDateTimeDelegate
     */
    public void disableDateTimeDelegate() {
        removeLazyConfiguration(dateTimeConfiguration);
    }
    
    /**
     * LocalObjectDateTimeDelegate
     */
    public boolean isDateTimeDelegateEnabled() {
        return containsLazyConfiguration(dateTimeConfiguration);
    }
    
    /**
     * NodeProfileObjectDelegate
     */
    public void enableNodeProfileDelegate() {
        if (!isNodeProfileDelegateEnabled()) {
            addLazyConfiguration(nodeProfileConfiguration);
        }
    }
    
    /**
     * NodeProfileObjectDelegate
     */
    public void disableNodeProfileDelegate() {
        removeLazyConfiguration(nodeProfileConfiguration);
    }
    
    /**
     * NodeProfileObjectDelegate
     */
    public boolean isNodeProfileDelegateEnabled() {
        return containsLazyConfiguration(nodeProfileConfiguration);
    }
    
    /**
     * LocalObjectNotifyDelegate
     */
    public void enableNotifyDelegate() {
        if (!isNotifyDelegateEnabled()) {
            addLazyConfiguration(notifyConfiguration);
        }
    }
    
    /**
     * LocalObjectNotifyDelegate
     */
    public void disableNotifyDelegate() {
        removeLazyConfiguration(notifyConfiguration);
    }
    
    /**
     * LocalObjectNotifyDelegate
     * @return LocalObjectNotifyDelegate
     */
    public boolean isNotifyDelegateEnabled() {
        return containsLazyConfiguration(notifyConfiguration);
    }
    
    /**
     * 
     */
    public boolean addProperty(EPC epc, boolean gettable, boolean settable, boolean observable, int size) {
        return nodeProfileInfo.add(epc, gettable, settable, observable, size);
    }
    
    /**
     * 
     */
    public boolean addProperty(EPC epc, boolean gettable, boolean settable, boolean observable, int size, Constraint constraint) {
        return nodeProfileInfo.add(epc, gettable, settable, observable, size, constraint);
    }
    
    /**
     * 
     */
    public boolean addProperty(EPC epc, boolean gettable, boolean settable, boolean observable, byte[] data) {
        return nodeProfileInfo.add(epc, gettable, settable, observable, data);
    }
    
    /**
     * 
     */
    public boolean addProperty(EPC epc, boolean gettable, boolean settable, boolean observable, byte[] data, Constraint constraint) {
        return nodeProfileInfo.add(epc, gettable, settable, observable, data, constraint);
    }
    
    /**
     * 
     */
    public boolean addProperty(PropertyInfo prop) {
        return nodeProfileInfo.add(prop);
    }
    
    /**
     * 
     */
    public boolean removeProperty(EPC epc) {
        return nodeProfileInfo.remove(epc);
    }
}
