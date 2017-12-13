/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.ibm.mq.MQC;
import com.ibm.mq.MQMessage;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.TypesUtilities;

/**
 *
 * @author jonathan.velez
 */
public class CustomizedMQMessage
{
    public CustomizedMQMessage( MQMessage msj )
    {
        try
        {
            initVariables( msj );
        }
        catch( IOException ex )
        {
            Logger.getLogger( CustomizedMQMessage.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    private int number;
    private String data;
    private int totalBytesOfStoredMessage;
    private String totalBytesAsoc;
    private GregorianCalendar putDate;
    private String userID;

    private int messageType;
    private String messageTypeAsoc;
    private String messageFormat;
    private String queueManagerToReplicate;
    private String queueToReplicate;
    private String applicationWhoPutIt;
    private String applicationID;
    private int priority;
    private int persistence;
    private int messageSequenceNumber;
    private byte[] messageID;
    private byte[] correlationID;
    private int expiry;
    private int encoding;
    private int backoutCount;
    
    private void initVariables( MQMessage msj ) throws IOException
    {
        data = msj.readStringOfByteLength( msj.getDataLength() );
        totalBytesOfStoredMessage = msj.getTotalMessageLength();
        totalBytesAsoc = TypesUtilities.humanReadableByteCount( totalBytesOfStoredMessage, false );            
        putDate = msj.putDateTime;       
        userID = msj.userId;

        messageType = msj.messageType;
        messageTypeAsoc = "Default";
        switch( messageType )
        {
            case MQC.MQMT_DATAGRAM : messageTypeAsoc = "DATAGRAM"; break;//El mensaje no requiere respuesta
            case MQC.MQMT_REQUEST : messageTypeAsoc = "REQUEST"; break;//El mensaje requiere respuesta y en los campos queueToReplicate y queueManagerToReplicate deben contener la cola y gestor donde se debe poner la respuesta
            case MQC.MQMT_REPLY : messageTypeAsoc = "REPLY"; break;//El mensaje es una respuesta a un mensaje tipo REQUEST anterior
            case MQC.MQMT_REPORT : messageTypeAsoc = "REPORT"; break;//El mensaje indica una ocurrencia inesperada ya sea una excepción o un mensaje de tipo correctivo
        }

        messageFormat = msj.format;            
        queueManagerToReplicate = msj.replyToQueueManagerName;            
        queueToReplicate = msj.replyToQueueName;
        applicationWhoPutIt = msj.putApplicationName;
        applicationID = msj.applicationIdData;
        priority = msj.priority;
        persistence = msj.persistence;
        messageSequenceNumber = msj.messageSequenceNumber;            
        messageID = msj.messageId;
        correlationID = msj.correlationId;
        expiry = msj.expiry; //in tenths of a second
        encoding = msj.encoding;            
        backoutCount = msj.backoutCount;
    }

    public String getData()
    {
        return data;
    }

    public void setData( String data )
    {
        this.data = data;
    }

    public int getTotalBytesOfStoredMessage()
    {
        return totalBytesOfStoredMessage;
    }

    public void setTotalBytesOfStoredMessage( int totalBytesOfStoredMessage )
    {
        this.totalBytesOfStoredMessage = totalBytesOfStoredMessage;
    }

    public String getTotalBytesAsoc()
    {
        return totalBytesAsoc;
    }

    public void setTotalBytesAsoc( String totalBytesAsoc )
    {
        this.totalBytesAsoc = totalBytesAsoc;
    }

    public GregorianCalendar getPutDate()
    {
        return putDate;
    }

    public void setPutDate( GregorianCalendar putDate )
    {
        this.putDate = putDate;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID( String userID )
    {
        this.userID = userID;
    }

    public int getMessageType()
    {
        return messageType;
    }

    public void setMessageType( int messageType )
    {
        this.messageType = messageType;
    }

    public String getMessageTypeAsoc()
    {
        return messageTypeAsoc;
    }

    public void setMessageTypeAsoc( String messageTypeAsoc )
    {
        this.messageTypeAsoc = messageTypeAsoc;
    }

    public String getMessageFormat()
    {
        return messageFormat;
    }

    public void setMessageFormat( String messageFormat )
    {
        this.messageFormat = messageFormat;
    }

    public String getQueueManagerToReplicate()
    {
        return queueManagerToReplicate;
    }

    public void setQueueManagerToReplicate( String queueManagerToReplicate )
    {
        this.queueManagerToReplicate = queueManagerToReplicate;
    }

    public String getQueueToReplicate()
    {
        return queueToReplicate;
    }

    public void setQueueToReplicate( String queueToReplicate )
    {
        this.queueToReplicate = queueToReplicate;
    }

    public String getApplicationWhoPutIt()
    {
        return applicationWhoPutIt;
    }

    public void setApplicationWhoPutIt( String applicationWhoPutIt )
    {
        this.applicationWhoPutIt = applicationWhoPutIt;
    }

    public String getApplicationID()
    {
        return applicationID;
    }

    public void setApplicationID( String applicationID )
    {
        this.applicationID = applicationID;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority( int priority )
    {
        this.priority = priority;
    }

    public int getPersistence()
    {
        return persistence;
    }

    public void setPersistence( int persistence )
    {
        this.persistence = persistence;
    }

    public int getMessageSequenceNumber()
    {
        return messageSequenceNumber;
    }

    public void setMessageSequenceNumber( int messageSequenceNumber )
    {
        this.messageSequenceNumber = messageSequenceNumber;
    }

    public byte[] getMessageID()
    {
        return messageID;
    }

    public void setMessageID( byte[] messageID )
    {
        this.messageID = messageID;
    }

    public byte[] getCorrelationID()
    {
        return correlationID;
    }

    public void setCorrelationID( byte[] correlationID )
    {
        this.correlationID = correlationID;
    }

    public int getExpiry()
    {
        return expiry;
    }

    public void setExpiry( int expiry )
    {
        this.expiry = expiry;
    }

    public int getEncoding()
    {
        return encoding;
    }

    public void setEncoding( int encoding )
    {
        this.encoding = encoding;
    }

    public int getBackoutCount()
    {
        return backoutCount;
    }

    public void setBackoutCount( int backoutCount )
    {
        this.backoutCount = backoutCount;
    }
    
    public int getNumber()
    {
        return number;
    }

    public void setNumber( int number )
    {
        this.number = number;
    }
}
