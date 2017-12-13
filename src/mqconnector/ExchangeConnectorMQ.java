/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqconnector;

import exceptions.MQMMessageBrockerUnexpectedException;
import exceptions.MQMMessageBrockerConnectionRefusedException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import mqmessagebrocker.Administrador;
import mqmessagebrocker.Connection;

/**
 *
 * @author jonathan.velez
 */
public class ExchangeConnectorMQ
{   
    Connection connection;
    
    private MQQueueManager qMgr;
    private final int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
    
    public ExchangeConnectorMQ( Connection connection )
    {	
	this.connection = connection;		
    }

    
    public boolean connect() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException 
    {	
	try
        {   	
            qMgr = getInstanceqMgr();
            
            if ( qMgr.isConnected() )
            {
                return true;
            }
            else
            {
                return false;
            }
	}
        catch ( Exception e )
        {
            throw new MQMMessageBrockerUnexpectedException("Ocurrio un error inesperado",e);
	} 
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
    
    public boolean status() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException 
    {	
        if ( qMgr != null )
        {
            try
            {
                qMgr.commit();
            }
            catch( Exception e )
            {
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public void sendMxML( String file, String queueName ) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException
    {
        MQQueue remoteQueue = null;
        try
        {
            String conType = "CLIENT";
            
            if ( qMgr.isConnected() == false )
            {
                qMgr = getInstanceqMgr();
            }
            
            remoteQueue =  qMgr.accessQueue( queueName, openOptions );
            
            MQMessage msg = new MQMessage();
            
            msg.writeString( file );
            
            MQPutMessageOptions pmo = new MQPutMessageOptions(); // Defaults PM Options
            remoteQueue.put( msg,pmo );
            
            if ( remoteQueue.isOpen() )
            {
                remoteQueue.close();
            }
	}
        catch (MQException ex)
        {
            throw new MQMMessageBrockerConnectionRefusedException( "No se pudo establecer la conexión con el servicio", ex );
	}
        catch (Exception e)
        {
            throw new MQMMessageBrockerUnexpectedException("Ocurrio un error inesperado",e);
	}
    }
    
    public ArrayList<MQMessage> readOnce( String queueName ) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException, MQException, IOException
    {	
	MQQueue remoteQueue = null;
	int ownOpenOptions = MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING +  MQC.MQOO_BROWSE;
	try 
        {
            if ( qMgr.isConnected() == false )
            {
                qMgr = getInstanceqMgr();
            }
            
            remoteQueue = qMgr.accessQueue( queueName, ownOpenOptions );
            MQMessage retrievedMessage = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.waitInterval = MQC.MQWI_UNLIMITED;
            gmo.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_CONVERT + MQC.MQGMO_BROWSE_NEXT;
//            gmo.options | MQC.MQGMO_WAIT;
			
            //Read once the messages and wait while there isn't any in queue.
            int currentDepth = remoteQueue.getCurrentDepth();
            ArrayList<MQMessage> messages = new ArrayList<MQMessage>();
//            component.setMinimum( 1 );
//            component.setMaximum( currentDepth );
            for( int i = 0; i < currentDepth; i++ )
            {
                remoteQueue.get( retrievedMessage, gmo );
                messages.add( retrievedMessage );
//                messages.add( retrievedMessage.readStringOfByteLength( retrievedMessage.getDataLength() ) );
                retrievedMessage = new MQMessage();
            }
            
            //clear Message so we can use it again
            retrievedMessage.clearMessage();
            retrievedMessage.correlationId = MQC.MQCI_NONE;
            retrievedMessage.messageId = MQC.MQMI_NONE;
            retrievedMessage.groupId = MQC.MQGI_NONE;
	
            return messages;
	}
        catch( MQMMessageBrockerConnectionRefusedException | MQMMessageBrockerUnexpectedException | IOException | MQException e)
        {
            throw e;
        }
        finally
        {
            try
            {
                if ( remoteQueue.isOpen() )
                {
                    remoteQueue.close();
		} 
            }
            catch ( Exception ex )
            {
                throw ex;
            }
        }
    }
    
        public ArrayList<MQMessage> dropMessages( String queueName ) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException, MQException, IOException
    {	
	MQQueue remoteQueue = null;
	int ownOpenOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
	try 
        {
            if ( qMgr.isConnected() == false )
            {
                qMgr = getInstanceqMgr();
            }
            
            remoteQueue = qMgr.accessQueue( queueName, ownOpenOptions );
            MQMessage retrievedMessage = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.waitInterval = MQC.MQWI_UNLIMITED;
            gmo.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_CONVERT + MQC.MQGMO_BROWSE_NEXT;
//            gmo.options | MQC.MQGMO_WAIT;
			
            //Read once the messages and wait while there isn't any in queue.
            int currentDepth = remoteQueue.getCurrentDepth();
            ArrayList<MQMessage> messages = new ArrayList<MQMessage>();
//            component.setMinimum( 1 );
//            component.setMaximum( currentDepth );
            for( int i = 0; i < currentDepth; i++ )
            {
                remoteQueue.get( retrievedMessage, gmo );
                messages.add( retrievedMessage );
//                messages.add( retrievedMessage.readStringOfByteLength( retrievedMessage.getDataLength() ) );
                retrievedMessage = new MQMessage();
            }
            
            //clear Message so we can use it again
            retrievedMessage.clearMessage();
            retrievedMessage.correlationId = MQC.MQCI_NONE;
            retrievedMessage.messageId = MQC.MQMI_NONE;
            retrievedMessage.groupId = MQC.MQGI_NONE;
	
            return messages;
	}
        catch( MQMMessageBrockerConnectionRefusedException | MQMMessageBrockerUnexpectedException | IOException | MQException e)
        {
            throw e;
        }
        finally
        {
            try
            {
                if ( remoteQueue.isOpen() )
                {
                    remoteQueue.close();
		} 
            }
            catch ( Exception ex )
            {
                throw ex;
            }
        }
    }
    
    public int getQueueLength( String queueName ) throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException, MQException
    {	
	MQQueue remoteQueue = null;
	int messages = 0;
	try 
        {
            if ( qMgr.isConnected() == false )
            {
                qMgr = getInstanceqMgr();
            }
            
            remoteQueue = qMgr.accessQueue( queueName, openOptions );
            messages = remoteQueue.getCurrentDepth();
            
            return messages;
	}
        catch( MQMMessageBrockerConnectionRefusedException | MQMMessageBrockerUnexpectedException | MQException e)
        {
            throw e;
        }
        finally
        {
            try
            {
                if ( remoteQueue.isOpen() )
                {
                    remoteQueue.close();
		} 
                
                qMgr.disconnect();
            }
            catch ( Exception ex )
            {
                throw ex;
            }
        }
    }
    
    
    
    /**
	 * Método que retorna una instancia del administrador de la conexión a MQ. Se creó para evitar la repetición de código y aportar a la modificabilidad. 
	 * Se exige que toda conexión a MQ tenga configuración de conexión segura SSL, aunque este método puede conectarse también a través de una conexión no segura.
	 * @return Una instancia de MQQueueManager objeto nativo de IBM 
	 * @author jonathan.velez Accenture Mayo 2017 PMO22491 Upgrade Murex
	 */
	@SuppressWarnings("unchecked")
	public MQQueueManager getInstanceqMgr() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException
	{	
		try
		{
                    if ( connection != null )
                    {
                        MQEnvironment.hostname = connection.getMqHost();
                        
                        if ( "SECURE".equals( connection.getMqConnectionType() ) )
			{
                            /*Asigno contraseñas de KeyStore y TrustStore*/
                            String passwdTruststore = connection.getMqPassTrustStore();
                            String passwdKeystore = connection.getMqPassKeyStore();
	            
                            // Asigno rutas donde se encuentran los archivos ssl
                            String pathTruststore = connection.getMqPathSSLFiles();
                            String pathKeystore = connection.getMqPathSSLFiles();
                            
                            //La contraseña de los archivos SSL están encriptadas?
                            if ( "S".equals( connection.getMqIsSSLEncriptedPassword() ) )
                            {   
                                // Desencripcion de keystore y truststore password
                                passwdTruststore = connection.getMqPassTrustStore();
                                passwdKeystore = connection.getMqPassKeyStore();
                            }
                            
                            MQEnvironment.port = Integer.parseInt( connection.getMqSecurePort() );
                            MQEnvironment.channel = connection.getMqSecureChannel();
                            
                            MQEnvironment.properties.put( MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES ); 
                            	
                            if ( "S".equals( connection.getMqEnableSSLConn() ) && !"".equals( connection.getSslCipherProperty() ) )
                            {
                            	MQEnvironment.properties.put( MQC.SSL_CIPHER_SUITE_PROPERTY, connection.getSslCipherProperty() );
                            } 
                            else
                            {
                            	MQEnvironment.properties.put( MQC.SSL_CIPHER_SUITE_PROPERTY, "SSL_RSA_WITH_RC4_128_MD5" );
                            }

                            if ( "S".equals( connection.getMqIsNeededSSLUser() ) )
                            {
                                MQEnvironment.userID = connection.getMqManagerUser();
				MQEnvironment.password = connection.getMqManagerPassword();
                            }
                            
                            // System Properties for SSL
                            if ( "S".equals( connection.getMqEnableSSLConn() ) )
                            {
                                System.setProperty( "javax.net.ssl.trustStore", pathTruststore );
				System.setProperty( "javax.net.ssl.keyStore", pathKeystore );
				
				if ( "S".equals( connection.getMqIsNeededSSLPassword() ) )
				{
                                    System.setProperty( "javax.net.ssl.keyStorePassword", passwdKeystore );
                                    System.setProperty( "javax.net.ssl.trustStorePassword", passwdTruststore );
				} 
					
				System.setProperty( "javax.net.ssl.keyStoreType", "JKS" );
				System.setProperty( "javax.net.ssl.trustStoreType", "JKS" );
                            } 
			}
                        else 
			{
                            MQEnvironment.port = Integer.parseInt( connection.getMqPort() );
                            MQEnvironment.userID = connection.getMqManagerUser();
                            MQEnvironment.password = connection.getMqManagerPassword();
                            MQEnvironment.channel =  connection.getMqChannel();
			}
                        
                        qMgr = new MQQueueManager( connection.getMqManager() );
			return qMgr;
                    }
                    return qMgr;
		} 
		catch ( MQException ex )
		{
                    throw new MQMMessageBrockerConnectionRefusedException( "No se pudo establecer la conexi\u00F3n con el servicio", ex );
		} 
		catch ( Exception e )
		{
                    throw new MQMMessageBrockerUnexpectedException( "Ocurrio un error inesperado", e );
		}
	}
}
