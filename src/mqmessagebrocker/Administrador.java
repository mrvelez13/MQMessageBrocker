/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqmessagebrocker;

import java.util.logging.Level;
import java.util.logging.Logger;
import mqconnector.ExchangeConnectorMQ;
import mqconnector.MQMMessageBrockerConnectionRefusedException;
import mqconnector.MQMMessageBrockerUnexpectedException;

/**
 *
 * @author jonathan.velez
 */
public class Administrador extends javax.swing.JFrame {
    
    ExchangeConnectorMQ conn;

    /**
     * Creates new form Administrador
     */
    public Administrador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        gestorLbl = new javax.swing.JLabel();
        servidorLbl = new javax.swing.JLabel();
        puertoLbl = new javax.swing.JLabel();
        canalLbl = new javax.swing.JLabel();
        gestorTxt = new javax.swing.JTextField();
        servidorTxt = new javax.swing.JTextField();
        puertoTxt = new javax.swing.JTextField();
        canalTxt = new javax.swing.JTextField();
        conectarBtn = new javax.swing.JButton();
        estadoLbl = new javax.swing.JLabel();
        statusLbl = new javax.swing.JLabel();
        colaMqLbl = new javax.swing.JLabel();
        colaMqTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensajeTxa = new javax.swing.JTextArea();
        ponerMsgBtn = new javax.swing.JButton();
        errorLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IBM MQ Message Manager");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración de la conexión"));
        jPanel1.setToolTipText("Configuración de la conexión");

        gestorLbl.setText("Gestor");
        gestorLbl.setVerifyInputWhenFocusTarget(false);

        servidorLbl.setText("Servidor");

        puertoLbl.setText("Puerto");

        canalLbl.setText("Canal");

        gestorTxt.setText("PBCOLMQMGR");

        servidorTxt.setText("127.0.0.8");

        puertoTxt.setText("1414");

        canalTxt.setText("MUREX.SVRCONN");

        conectarBtn.setText("Conectar");
        conectarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarBtnActionPerformed(evt);
            }
        });

        estadoLbl.setText("Estado:");

        statusLbl.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        statusLbl.setForeground(new java.awt.Color(255, 0, 0));
        statusLbl.setText("Desconectado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gestorLbl)
                            .addComponent(servidorLbl)
                            .addComponent(puertoLbl)
                            .addComponent(canalLbl))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gestorTxt)
                            .addComponent(servidorTxt)
                            .addComponent(puertoTxt)
                            .addComponent(canalTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(estadoLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(conectarBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gestorLbl)
                    .addComponent(gestorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(servidorLbl)
                    .addComponent(servidorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puertoLbl)
                    .addComponent(puertoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(canalLbl)
                    .addComponent(canalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conectarBtn)
                    .addComponent(estadoLbl)
                    .addComponent(statusLbl)))
        );

        colaMqLbl.setText("Cola MQ");

        mensajeTxa.setColumns(20);
        mensajeTxa.setRows(5);
        jScrollPane1.setViewportView(mensajeTxa);

        ponerMsgBtn.setText("Poner mensaje");
        ponerMsgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponerMsgBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(errorLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 307, Short.MAX_VALUE)
                                .addComponent(ponerMsgBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(colaMqLbl)
                                .addGap(18, 18, 18)
                                .addComponent(colaMqTxt)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(colaMqLbl)
                            .addComponent(colaMqTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ponerMsgBtn)
                            .addComponent(errorLbl))))
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void conectarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarBtnActionPerformed
        
        if ( conn == null )
        {
            conn = new ExchangeConnectorMQ( gestorTxt.getText() , servidorTxt.getText() , puertoTxt.getText(), canalTxt.getText() );
        }
        
        try
        {
            if( conectarBtn.getText().equals( "Conectar" ) )
            {
                if( conn.connect() )
                {
                    statusLbl.setForeground(new java.awt.Color(0, 153, 0));
                    statusLbl.setText("Conectado");
                    conectarBtn.setText( "Desconectar" );
                }
                else
                {
                    statusLbl.setForeground(new java.awt.Color(255, 0, 0));
                    statusLbl.setText("Desconectado");
                }
            }
            else
            {
                if ( !conn.disconnect() );
                {
                    statusLbl.setForeground(new java.awt.Color(255, 0, 0));
                    statusLbl.setText("Desconectado");
                    conectarBtn.setText( "Conectar" );
                }
            }
        } catch (MQMMessageBrockerConnectionRefusedException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MQMMessageBrockerUnexpectedException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_conectarBtnActionPerformed

    private void ponerMsgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponerMsgBtnActionPerformed
        if( mensajeTxa.getText().equals("") )
        {
            errorLbl.setText( "El área del mensaje está vacía." );
            return;
        }
        
        if ( colaMqTxt.getText().equals( "" ) )
        {
            errorLbl.setText( "El nombre de la Cola MQ está vacío." );
            return;
        }
        
        if ( conn != null )
        {
            try {
                if ( conn.status() )
                {
                    try {
                        conn.sendMxML( mensajeTxa.getText(), colaMqTxt.getText() );
                        errorLbl.setText("Mensaje puesto con éxito.");
                    } catch (MQMMessageBrockerConnectionRefusedException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MQMMessageBrockerUnexpectedException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    errorLbl.setText( "El gestor está desconectado." );
                    return;
                }
            } catch (MQMMessageBrockerConnectionRefusedException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MQMMessageBrockerUnexpectedException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            errorLbl.setText( "El gestor está desconectado." );
            return;
        }
        
    }//GEN-LAST:event_ponerMsgBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel canalLbl;
    private javax.swing.JTextField canalTxt;
    private javax.swing.JLabel colaMqLbl;
    private javax.swing.JTextField colaMqTxt;
    private javax.swing.JButton conectarBtn;
    private javax.swing.JLabel errorLbl;
    private javax.swing.JLabel estadoLbl;
    private javax.swing.JLabel gestorLbl;
    private javax.swing.JTextField gestorTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mensajeTxa;
    private javax.swing.JButton ponerMsgBtn;
    private javax.swing.JLabel puertoLbl;
    private javax.swing.JTextField puertoTxt;
    private javax.swing.JLabel servidorLbl;
    private javax.swing.JTextField servidorTxt;
    private javax.swing.JLabel statusLbl;
    // End of variables declaration//GEN-END:variables
}