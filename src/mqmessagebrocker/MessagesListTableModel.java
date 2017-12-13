/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqmessagebrocker;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;
import model.CustomizedMQMessage;

/**
 *
 * @author jonathan.velez
 */
public class MessagesListTableModel extends AbstractTableModel implements Serializable
{   
    public MessagesListTableModel( List data )
    {
        dataList.addAll(( Collection<? extends CustomizedMQMessage> ) data);
    }
    private String[] columnNames = {"#","Data","Size","Date", 
            "User ID", "Message Type", "Format", "Queue Manager To Replicate", "Queue Name To Replicate", 
            "Application","App ID", "Priority", "Persistence", "Sequence", "Message ID", "Correlation ID",
            "Expiry", "Encoding", "Backout count" };
    
    private ArrayList<CustomizedMQMessage> dataList = new ArrayList<CustomizedMQMessage>();
    
    @Override
    public int getRowCount()
    {
        return dataList.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt( int row, int col )
    {
        CustomizedMQMessage msj = ( CustomizedMQMessage ) dataList.get( row );
        
        switch( col )
        {
            case 0:
                return msj.getNumber();
            case 1:
                return msj.getData();
            case 2:
                return msj.getTotalBytesAsoc();
            case 3:
                return msj.getPutDate().getTime();
            case 4:
                return msj.getUserID();
            case 5:
                return msj.getMessageTypeAsoc();
            case 6:
                return msj.getMessageFormat();
            case 7:
                return msj.getQueueManagerToReplicate();
            case 8:
                return msj.getQueueToReplicate();
            case 9:
                return msj.getApplicationWhoPutIt();
            case 10:
                return msj.getApplicationID();
            case 11:
                return Integer.toString( msj.getPriority() );
            case 12:
                return Integer.toString( msj.getPersistence() );
            case 13:
                return Integer.toString( msj.getMessageSequenceNumber() );
            case 14:
                return msj.getMessageID().toString();
            case 15:
                return msj.getCorrelationID().toString();
            case 16:
                return Integer.toString( msj.getExpiry() );
            case 17:
                return Integer.toString( msj.getEncoding() );
            case 18:
                return Integer.toString( msj.getBackoutCount() );
            case 19:
                return msj.getApplicationID();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName( int col )
    {
        return columnNames[ col ];
    }
    
    public void addMessageToTable( List row )
    {
        dataList.addAll(( Collection<? extends CustomizedMQMessage> ) row);
        fireTableDataChanged();
    }

    public ArrayList<CustomizedMQMessage> getDataList()
    {
        return dataList;
    }

    public void setDataList( ArrayList<CustomizedMQMessage> dataList )
    {
        this.dataList = dataList;
    }
}
