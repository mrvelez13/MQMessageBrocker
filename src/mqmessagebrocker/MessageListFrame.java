/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqmessagebrocker;

import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import exceptions.MQMMessageBrockerConnectionRefusedException;
import exceptions.MQMMessageBrockerUnexpectedException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import model.CustomizedMQMessage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import mqconnector.ExchangeConnectorMQ;
import view.MessageDetailFrame;

/**
 *
 * @author jonathan.velez
 */
public class MessageListFrame extends JFrame implements ActionListener
{

    ExchangeConnectorMQ connection;
    JMenuItem deleteItem;
    JMenuItem detailItem;
    JMenuItem exportOneItem;
    JMenuItem exportAllItem;
    private JPopupMenu popupMenu;
    String qname;
    String correID;
    /**
     * Creates new form MessageListFrame
     */
    public MessageListFrame()
    {
        initComponents();
    }
    
    public MessageListFrame( ArrayList<MQMessage> messages, ExchangeConnectorMQ conn, String queue ) throws IOException
    {
        initComponents();
        deleteItem = new JMenuItem("Borrar");
        detailItem = new JMenuItem("Abrir");
        exportOneItem = new JMenuItem("Exportar seleccionado");
        exportAllItem = new JMenuItem("Exportar todo");
        deleteItem.addActionListener( this );
        detailItem.addActionListener( this );
        exportOneItem.addActionListener( this );
        exportAllItem.addActionListener( this );
        popupMenu = new JPopupMenu();
        popupMenu.add( deleteItem );
        popupMenu.add( detailItem );
        popupMenu.add( exportOneItem );
        popupMenu.add( exportAllItem );
        qname = queue.trim();
        String correlationID = null;
        correID= correlationID;
        loadTable( messages );
        connection = conn;
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMessageList = new JTable() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                return false;               
            };
        };
        regTotalesLbl = new javax.swing.JLabel();
        regTotCountLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableMessageList.setAutoCreateRowSorter(true);
        tableMessageList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        tableMessageList.setAlignmentX(0.0F);
        tableMessageList.setAlignmentY(0.0F);
        tableMessageList.setSurrendersFocusOnKeystroke(true);
        tableMessageList.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tableMessagesRowSelected(evt);
            }
        });
        jScrollPane1.setViewportView(tableMessageList);
        tableMessageList.getAccessibleContext().setAccessibleName("");
        tableMessageList.getAccessibleContext().setAccessibleDescription("");

        regTotalesLbl.setText("Registros totales:");

        regTotCountLbl.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(regTotalesLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regTotCountLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regTotalesLbl)
                    .addComponent(regTotCountLbl))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMessagesRowSelected(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tableMessagesRowSelected
    {//GEN-HEADEREND:event_tableMessagesRowSelected
        MessagesListTableModel model = ( MessagesListTableModel ) tableMessageList.getModel();
        
        if( evt.getClickCount() == 2 && !evt.isConsumed() )
        {
            if( evt.getButton() == MouseEvent.BUTTON1 )
            {
                evt.consume();
                MessageDetailFrame msd = new MessageDetailFrame( (String) model.getValueAt( tableMessageList.getSelectedRow(),1), (Date) model.getValueAt( tableMessageList.getSelectedRow(),3), (String) model.getValueAt( tableMessageList.getSelectedRow(),2), (String) model.getValueAt( tableMessageList.getSelectedRow(),4) );
                msd.setLocationRelativeTo( this );
                msd.setVisible( true );                
            }
        }
    }//GEN-LAST:event_tableMessagesRowSelected

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] )
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() )
            {
                if( "Nimbus".equals( info.getName() ) )
                {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        }
        catch( ClassNotFoundException ex )
        {
            java.util.logging.Logger.getLogger( MessageListFrame.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger( MessageListFrame.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger( MessageListFrame.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( MessageListFrame.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new MessageListFrame().setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel regTotCountLbl;
    private javax.swing.JLabel regTotalesLbl;
    private javax.swing.JTable tableMessageList;
    // End of variables declaration//GEN-END:variables

    private void loadTable( ArrayList<MQMessage> messages ) throws IOException
    {
        int i = 0;
        MessagesListTableModel model;
        List rowlist = new ArrayList();
        ArrayList<String> row = new ArrayList<String>();
        
        for( MQMessage msj : messages )
        {
            CustomizedMQMessage customMessage = new CustomizedMQMessage( msj );
            customMessage.setNumber( ++i );
            rowlist.add( customMessage );
        }
        model = new MessagesListTableModel( rowlist );
        regTotCountLbl.setText( Integer.toString( messages.size() ) );
        tableMessageList.setModel( model ); 
        tableMessageList.setComponentPopupMenu( popupMenu );
//        tableMessageList.setVisible( true );
    }
    
    @Override
    public void actionPerformed( ActionEvent evt )
    {
        JMenuItem menu = (JMenuItem) evt.getSource();
        
        if ( menu == deleteItem )
        {
            try
            {
                deleteItem();
            }
            catch( MQMMessageBrockerConnectionRefusedException ex )
            {
                Logger.getLogger( MessageListFrame.class.getName() ).log( Level.SEVERE, null, ex );
            }
            catch( MQMMessageBrockerUnexpectedException ex )
            {
                Logger.getLogger( MessageListFrame.class.getName() ).log( Level.SEVERE, null, ex );
            }
            catch( MQException ex )
            {
                Logger.getLogger( MessageListFrame.class.getName() ).log( Level.SEVERE, null, ex );
            }
            catch( IOException ex )
            {
                Logger.getLogger( MessageListFrame.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        else if ( menu == detailItem )
        {
            detailItem();
        }
        else if ( menu == exportOneItem )
        {
            exportOneItem();
        }
        else if ( menu == exportAllItem )
        {
            exportAllItems();
        }
    }
    
    public void deleteItem() throws MQMMessageBrockerConnectionRefusedException, MQMMessageBrockerUnexpectedException, MQException, IOException
    {   {MessagesListTableModel model = ( MessagesListTableModel ) tableMessageList.getModel();
        String messageContent = (String) model.getValueAt( tableMessageList.getSelectedRow(),1);
        String messageName = model.getValueAt( tableMessageList.getSelectedRow(),0).toString();
        JOptionPane.showMessageDialog(null,"Message Name"+messageName);
        if ( connection != null )
        {
            String correlationId = null;
            if ( connection.dropMessages( qname , correlationId ) )
//            if ( connection.deleteMessage(qname,correID) )
            {
                loadTable( new ArrayList<MQMessage>() );
            }
        }
    }
    }
    public void detailItem()
    {
        MessagesListTableModel model = ( MessagesListTableModel ) tableMessageList.getModel();
        MessageDetailFrame msd = new MessageDetailFrame( (String) model.getValueAt( tableMessageList.getSelectedRow(),1), (Date) model.getValueAt( tableMessageList.getSelectedRow(),3), (String) model.getValueAt( tableMessageList.getSelectedRow(),2), (String) model.getValueAt( tableMessageList.getSelectedRow(),4) );
        msd.setLocationRelativeTo( this );
        msd.setVisible( true );
    }
    
    public void exportOneItem()
    {
        MessagesListTableModel model = ( MessagesListTableModel ) tableMessageList.getModel();
        String messageContent = (String) model.getValueAt( tableMessageList.getSelectedRow(),1);
        String messageName = model.getValueAt( tableMessageList.getSelectedRow(),0).toString();

        String folder = "";
        JFileChooser jF1 = new JFileChooser();  
        int contador = 0;

        jF1.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        jF1.setDialogTitle( "Export selected item" );
        jF1.setApproveButtonText("Save");
        jF1.setApproveButtonToolTipText( "Save the selected item from table" );

        // disable the "All files" option. 
        // 
        jF1.setAcceptAllFileFilterUsed(false); 
    //  
        if (jF1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : "
                    + jF1.getSelectedFile().getAbsolutePath());
            folder = jF1.getSelectedFile().getAbsolutePath();

            if (new File(folder + "/" + qname).mkdirs() || new File(folder + "/" + qname).exists()) {
                File archivo = new File(folder + "/" + qname + "/" + messageName);
                BufferedWriter bw;

                if (archivo.exists()) {
                    try {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write(messageContent);
                        bw.close();

                        JOptionPane.showMessageDialog(this, "Export successful!\n"
                                + "Path: " + archivo.getAbsolutePath() );

                    } catch (IOException ex) {
                        Logger.getLogger(MessageListFrame.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write(messageContent);
                        bw.close();

                        JOptionPane.showMessageDialog(this, "Export successful!\n"
                                + "Path: " + archivo.getAbsolutePath() );
                                
                    } catch (IOException ex) {
                        Logger.getLogger(MessageListFrame.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }

        } else {
            System.out.println("No Selection ");
        }

    }
        
    public void exportAllItems()
    {
        MessagesListTableModel model = ( MessagesListTableModel ) tableMessageList.getModel();
        //String folder =  System.getProperty("user.home");
        int contador =0;
        String folder2=null;
        JFileChooser jF1 = new JFileChooser();  

    jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
    // disable the "All files" option. 
    // 
    jF1.setAcceptAllFileFilterUsed(false); 
    //  
    if (jF1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
     System.out.println("getCurrentDirectory(): " 
     + jF1.getCurrentDirectory()); 
    
     System.out.println("getSelectedFile() : " 
     + jF1.getSelectedFile().getAbsolutePath());
      folder2 = jF1.getSelectedFile().getAbsolutePath();
         
    } 
    else { 
     System.out.println("No Selection "); 
     } 
     

for ( CustomizedMQMessage customMessage : model.getDataList() )
        {
            String messageContent = customMessage.getData();
            int messageName = customMessage.getNumber();
//            JOptionPane.showMessageDialog(null,"messageContent"+messageContent);
//            JOptionPane.showMessageDialog(null,"messageName"+messageName);
//             JOptionPane.showMessageDialog(null,"este es chooser"+choosertitle );
            new File( folder2 ).mkdirs();
//             JOptionPane.showMessageDialog(null,"folder2"+folder2);
            if ( new File( folder2 + "/" + qname ).mkdirs() || new File( folder2 + "/" + qname ).exists() )
            {   
                contador= contador +1;
                File archivo = new File(folder2 + "/" + qname + "/" + messageName );
                BufferedWriter bw;
        
                if(archivo.exists())
                {
                    try
                    {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write( messageContent );
                        bw.close();
                    }
                    catch( IOException ex )
                    {
                        Logger.getLogger( MessageListFrame.class.getName() ).log( Level.SEVERE, null, ex );
                    }
                }
                else
                {
                    try
                    {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write( messageContent );
                        bw.close();
                    }
                    catch( IOException ex )
                    {
                        Logger.getLogger( MessageListFrame.class.getName() ).log( Level.SEVERE, null, ex );
                    }

                }
            }         
        }
        if (contador>=1){
        JOptionPane.showMessageDialog(this,"Se Exporto Correctamente");
//        MessageDetailFrame msd = new MessageDetailFrame( (String) model.getValueAt( tableMessageList.getSelectedRow(),1), (Date) model.getValueAt( tableMessageList.getSelectedRow(),3), (String) model.getValueAt( tableMessageList.getSelectedRow(),2), (String) model.getValueAt( tableMessageList.getSelectedRow(),4) );
//        msd.setLocationRelativeTo( this );
//        msd.setVisible( true );
             }else{
             JOptionPane.showMessageDialog(this,"Algo fallo al exportar");
             }
 

    }
}
