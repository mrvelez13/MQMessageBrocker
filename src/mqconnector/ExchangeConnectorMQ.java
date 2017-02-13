/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqconnector;

import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import java.io.EOFException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author jonathan.velez
 */
public class ExchangeConnectorMQ
{   
    private final String qManager;
    private final String qServer;
    private final String qPuerto;
    private final String qCanal;
    private final String qConnType;
    
    private String qInName;
    private String qOutName;
    private MQQueueManager qMgr;
    private String qUser;
    private String qPassword;
    private int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
    
    public ExchangeConnectorMQ( String qmanager, String qserver, String qpuerto, String qcanal, String qconnType )
    {
		super();
		
		qManager = qmanager;
		qServer = qserver;
                qPuerto = qpuerto;
                qCanal = qcanal;
                qConnType = qconnType;
		
    }

    
    public boolean connect() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException 
    {	
	MQQueue remoteQueue = null;
        System.err.println( qManager + " " + qServer + " " + qPuerto + " " + qCanal );
	try
        {           
            if ( "SECURE".equals( qConnType ) )
            {
                System.err.println("No soportado todavía...");
            } 
            else
            {
                String hostname = qServer;
                String port = qPuerto;
                MQEnvironment.hostname = hostname;
                MQEnvironment.port = Integer.parseInt(port);
                MQEnvironment.userID = "";
                MQEnvironment.password = "";
                MQEnvironment.channel =  qCanal;
                MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES_CLIENT);
            }
			
            qMgr = new MQQueueManager(qManager);
            //qMgr.accessQueue(qServer, openOptions)
            if ( qMgr.isConnected() )
            {
                return true;
            }
            else
            {
                return false;
            }
	}
        catch (MQException ex)
        {
            System.err.println( "MQException generada. Finalizando " + ex.getMessage() );
            System.err.println( "Se ha presentado un error de conexion con la cola " + qInName );
            System.err.println( ex.getLocalizedMessage() );
            throw new MQMMessageBrockerConnectionRefusedException( "No se pudo establecer la conexión con el servicio", ex );
	}
        catch (Exception e)
        {
            System.err.println("Se generó una excepción no esperada."+ e.getMessage());
            System.err.println("Se ha presentado un error de conexion con la cola " + qInName);
            throw new MQMMessageBrockerUnexpectedException("Ocurrio un error inesperado",e);
	} 
//        finally
//        {
//            try
//            {
//                if (remoteQueue.isOpen())
//                {
//                    remoteQueue.close();
//                }
//                
//                qMgr.disconnect();
//                
//            } 
//            catch (Exception ex)
//            {
//		 System.err.println( ex );
//            }
//            
//             System.err.println("Esperando finalización de conexión con MQ");
//	}

        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean disconnect() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException 
    {	
	
        try
        {   
            qMgr.disconnect();
        } 
        catch (Exception ex)
        {
             System.err.println( ex );
        }
        
        return false;
    }

    public void sendMxML(String xmlFile, String qName) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException
    {
        MQQueue remoteQueue = null;
        System.err.println( qManager + " " + qServer + " " + qPuerto + " " + qCanal );
        
        try
        {
            String conType = "CLIENT";
            
            if ( "SECURE".equals( conType ) )
            {
                System.err.println("No soportado todavía...");
            } 
            else
            {
                String hostname = qServer;
                String port = qPuerto;
                MQEnvironment.hostname = hostname;
                MQEnvironment.port = Integer.parseInt(port);
                MQEnvironment.userID = "";
                MQEnvironment.password = "";
                MQEnvironment.channel =  qCanal;
                MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES_CLIENT);
            }
			
            qMgr = new MQQueueManager(qManager);
            remoteQueue =  qMgr.accessQueue(qName,openOptions);
            
            MQMessage msg = new MQMessage();
            
            msg.writeString(xmlFile);
            
            MQPutMessageOptions pmo = new MQPutMessageOptions(); // Defaults PM Options
            remoteQueue.put(msg,pmo);
            if (remoteQueue.isOpen())
            {
                remoteQueue.close();
            }
	}
        catch (MQException ex)
        {
            System.err.println( "MQException generada. Finalizando " + ex.getMessage() );
            System.err.println( "Se ha presentado un error de conexion con la cola " + qInName );
            System.err.println( ex.getLocalizedMessage() );
            throw new MQMMessageBrockerConnectionRefusedException( "No se pudo establecer la conexión con el servicio", ex );
	}
        catch (Exception e)
        {
            System.err.println("Se generó una excepción no esperada."+ e.getMessage());
            System.err.println("Se ha presentado un error de conexion con la cola " + qInName);
            throw new MQMMessageBrockerUnexpectedException("Ocurrio un error inesperado",e);
	}
    }
    
    public boolean status() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException 
    {	
        if ( qMgr != null )
        {
            return qMgr.isConnected();
        }
        else{
            return false;
        }
    }
}
