/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqconnector;

import exceptions.MQMMessageBrockerUnexpectedException;
import exceptions.MQMMessageBrockerConnectionRefusedException;

/**
 *
 * @author jonathan.velez
 */
public abstract class ExchangeConnector
{
    private StringProcessor processor;

	public  void onNewXml(String xmlFile, byte[] correlationId){
		//Logger log4j = Logger.getLogger("taxmodule");
		
		if (processor == null) {
			
			new NullPointerException("El procesador es nulo");			
		}
			
		processor.process(xmlFile, correlationId);
	}
	
	
	/**
	 * @param processor
	 * @uml.property  name="processor"
	 */
	protected void setProcessor(final StringProcessor processor) {
		this.processor = processor;
	}
	
	/**
	 * Reads the full XML from Murex MxMLExchange Module
	 * @return  a string containing the full XML File
	 * @throws ValuationModuleConnectionRefusedException 
	 * @throws ValuationModuleUnexpectedException 
	 * @throws ValuationModuleUnexpectedException 
	 */
	public abstract void waitForXml(StringProcessor processor) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException;
	/**
	 * Send a XML File to Murex MxMLExchange Module 
	 * @param xmlFile 
	 * @throws ValuationModuleConnectionRefusedException 
	 * @throws ValuationModuleUnexpectedException 
	 */
	public abstract void sendMxML(String xmlFile) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException;
	
	/**
	 * Send a XML File to Murex MxMLExchange Module 
	 * @param xmlFile 
	 * @throws ValuationModuleConnectionRefusedException 
	 * @throws ValuationModuleUnexpectedException 
	 */
	public abstract void sendMxML(String xmlFile, byte[] correlationId) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException;
	
	/**
	 * Get a XML file from a Queue
	 * @return string data
	 * @throws ValuationModuleConnectionRefusedException
	 * @throws ValuationModuleUnexpectedException
	 */
	public abstract String readOnce() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException;

	/**
	 * Get the queue length
	 * @return message number
	 * @throws TaxModuleConnectionRefusedException
	 */
	public abstract int getQueueLength() throws MQMMessageBrockerConnectionRefusedException;

}
