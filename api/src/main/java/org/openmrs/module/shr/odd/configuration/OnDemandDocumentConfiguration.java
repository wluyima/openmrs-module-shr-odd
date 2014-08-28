package org.openmrs.module.shr.odd.configuration;

import org.marc.everest.formatters.FormatterUtil;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.shr.cdahandler.configuration.CdaHandlerConfiguration;

/**
 * Configuration for the OnDemandDocument module
 */
public final class OnDemandDocumentConfiguration {
	
	// Singleton objects
	private static final Object s_lockObject = new Object();
	private static OnDemandDocumentConfiguration s_instance;
	// Some data is borrowed from the cdaImport configuration
	private CdaHandlerConfiguration m_cdaImportConfiguration;
	
	// Private Ctor
	private OnDemandDocumentConfiguration()
	{
		
	}
	
	/**
     * Creates or gets the instance of the configuration
     */
    public static final OnDemandDocumentConfiguration getInstance()
    {
    	if(s_instance == null)
    		synchronized (s_lockObject) {
    			if(s_instance == null)
    			{
    				s_instance = new OnDemandDocumentConfiguration();
    				s_instance.initialize();
    			}
            }
    	return s_instance;
    }

    /**
     * Get the ODD document ID root
     */
    public String getOnDemandDocumentRoot()
    {
    	return this.m_cdaImportConfiguration.getShrRoot() + ".10";
    }
    
	/**
     * Read a global property
     */
    private <T> T getOrCreateGlobalProperty(String propertyName, T defaultValue)
    {
		String propertyValue = Context.getAdministrationService().getGlobalProperty(propertyName);
		if(propertyValue != null && !propertyValue.isEmpty())
			return (T)FormatterUtil.fromWireFormat(propertyValue, defaultValue.getClass());
		else
		{
			Context.getAdministrationService().saveGlobalProperty(new GlobalProperty(propertyName, defaultValue.toString()));
			return defaultValue;
		}
    }
    
    /**
     * Initialize the singleton
     */
    private void initialize()
    {
    	
    }
}