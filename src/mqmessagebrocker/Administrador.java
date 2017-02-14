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
        mensajeTxa.setEditable(false );
        cipherCbx.setEnabled( false );
        rutaKeyStoreTxt.setEnabled( false );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensajeTxa = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        connConfJPanel = new javax.swing.JPanel();
        gestorLbl = new javax.swing.JLabel();
        servidorLbl = new javax.swing.JLabel();
        puertoLbl = new javax.swing.JLabel();
        canalLbl = new javax.swing.JLabel();
        gestorTxt = new javax.swing.JTextField();
        servidorTxt = new javax.swing.JTextField();
        puertoTxt = new javax.swing.JTextField();
        canalTxt = new javax.swing.JTextField();
        connTypeLbl = new javax.swing.JLabel();
        connTypeCbx = new javax.swing.JComboBox<>();
        javax.swing.JPanel secureConfJPanel = new javax.swing.JPanel();
        errorLbl = new javax.swing.JLabel();
        usuarioLbl = new javax.swing.JLabel();
        usuarioTxt = new javax.swing.JTextField();
        reqContrasenaChbx = new javax.swing.JCheckBox();
        contrasenaLbl = new javax.swing.JLabel();
        contrasenaTxt = new javax.swing.JPasswordField();
        cipherCbx = new javax.swing.JComboBox<>();
        cipherLbl = new javax.swing.JLabel();
        rutaKeyStoreLbl = new javax.swing.JLabel();
        rutaKeyStoreTxt = new javax.swing.JTextField();
        conectarBtn = new javax.swing.JButton();
        statusLbl = new javax.swing.JLabel();
        estadoLbl = new javax.swing.JLabel();
        colaMqLbl = new javax.swing.JLabel();
        colaMqTxt = new javax.swing.JTextField();
        ponerMsgBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IBM MQ Message Manager");
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setToolTipText("");
        jSplitPane1.setAutoscrolls(true);
        jSplitPane1.setDoubleBuffered(true);
        jSplitPane1.setFocusCycleRoot(true);
        jSplitPane1.setFocusTraversalPolicyProvider(true);
        jSplitPane1.setInheritsPopupMenu(true);
        jSplitPane1.setOneTouchExpandable(true);

        jScrollPane1.setAutoscrolls(true);

        mensajeTxa.setColumns(20);
        mensajeTxa.setRows(5);
        mensajeTxa.setAlignmentX(0.0F);
        jScrollPane1.setViewportView(mensajeTxa);
        mensajeTxa.getAccessibleContext().setAccessibleParent(jPanel1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );

        jSplitPane1.setTopComponent(jPanel1);

        jInternalFrame1.setAutoscrolls(true);
        jInternalFrame1.setVisible(true);

        connConfJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración de la conexión"));
        connConfJPanel.setToolTipText("Configuración de la conexión");

        gestorLbl.setText("Gestor");
        gestorLbl.setVerifyInputWhenFocusTarget(false);

        servidorLbl.setText("Servidor");

        puertoLbl.setText("Puerto");

        canalLbl.setText("Canal");

        gestorTxt.setText("PBCOLMQMGR");

        servidorTxt.setText("127.0.0.8");

        puertoTxt.setText("1414");

        canalTxt.setText("MUREX.SVRCONN");

        connTypeLbl.setText("Tipo de Conexión");

        connTypeCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CLIENT", "SECURE" }));
        connTypeCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connTypeCbxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout connConfJPanelLayout = new javax.swing.GroupLayout(connConfJPanel);
        connConfJPanel.setLayout(connConfJPanelLayout);
        connConfJPanelLayout.setHorizontalGroup(
            connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connConfJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(connConfJPanelLayout.createSequentialGroup()
                        .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gestorLbl)
                            .addComponent(servidorLbl)
                            .addComponent(puertoLbl)
                            .addComponent(canalLbl))
                        .addGap(61, 61, 61)
                        .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(servidorTxt)
                            .addComponent(puertoTxt)
                            .addComponent(canalTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(gestorTxt)))
                    .addGroup(connConfJPanelLayout.createSequentialGroup()
                        .addComponent(connTypeLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connTypeCbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        connConfJPanelLayout.setVerticalGroup(
            connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connConfJPanelLayout.createSequentialGroup()
                .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gestorLbl)
                    .addComponent(gestorTxt))
                .addGap(14, 14, 14)
                .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(servidorLbl)
                    .addComponent(servidorTxt))
                .addGap(17, 17, 17)
                .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puertoLbl)
                    .addComponent(puertoTxt))
                .addGap(18, 18, 18)
                .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(canalLbl)
                    .addComponent(canalTxt))
                .addGap(18, 18, 18)
                .addGroup(connConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connTypeLbl)
                    .addComponent(connTypeCbx)))
        );

        secureConfJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración de conexión segura"));
        secureConfJPanel.setToolTipText("Configuración de conexión segura");

        usuarioLbl.setText("Usuario");

        reqContrasenaChbx.setText("No requiere contraseña");
        reqContrasenaChbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqContrasenaChbxActionPerformed(evt);
            }
        });

        contrasenaLbl.setText("Contraseña");

        cipherCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ECDHE_ECDSA_3DES_EDE_CBC_SHA256", "ECDHE_ECDSA_AES_128_CBC_SHA256", "ECDHE_ECDSA_AES_128_GCM_SHA256", "ECDHE_ECDSA_AES_256_CBC_SHA384", "ECDHE_ECDSA_AES_256_GCM_SHA384", "ECDHE_ECDSA_NULL_SHA256", "ECDHE_ECDSA_RC4_128_SHA256", "ECDHE_RSA_3DES_EDE_CBC_SHA256", "ECDHE_RSA_AES_128_CBC_SHA256", "ECDHE_RSA_AES_128_GCM_SHA256", "ECDHE_RSA_AES_256_CBC_SHA384", "ECDHE_RSA_AES_256_GCM_SHA384", "ECDHE_RSA_NULL_SHA256", "ECDHE_RSA_RC4_128_SHA256", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_NULL_SHA256", "TLS_RSA_WITH_RC4_128_SHA256" }));

        cipherLbl.setText("Cipher");

        rutaKeyStoreLbl.setText("Ruta keystore");

        conectarBtn.setText("Conectar");
        conectarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarBtnActionPerformed(evt);
            }
        });

        statusLbl.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        statusLbl.setForeground(new java.awt.Color(255, 0, 0));
        statusLbl.setText("Desconectado");

        estadoLbl.setText("Estado:");

        javax.swing.GroupLayout secureConfJPanelLayout = new javax.swing.GroupLayout(secureConfJPanel);
        secureConfJPanel.setLayout(secureConfJPanelLayout);
        secureConfJPanelLayout.setHorizontalGroup(
            secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secureConfJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(secureConfJPanelLayout.createSequentialGroup()
                        .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contrasenaLbl)
                            .addComponent(usuarioLbl))
                        .addGap(18, 18, 18)
                        .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usuarioTxt)
                            .addComponent(contrasenaTxt)))
                    .addGroup(secureConfJPanelLayout.createSequentialGroup()
                        .addComponent(reqContrasenaChbx)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(secureConfJPanelLayout.createSequentialGroup()
                        .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rutaKeyStoreLbl)
                            .addComponent(cipherLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cipherCbx, 0, 440, Short.MAX_VALUE)
                            .addComponent(rutaKeyStoreTxt)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, secureConfJPanelLayout.createSequentialGroup()
                        .addComponent(errorLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadoLbl)
                        .addGap(18, 18, 18)
                        .addComponent(statusLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(conectarBtn)))
                .addContainerGap())
        );
        secureConfJPanelLayout.setVerticalGroup(
            secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secureConfJPanelLayout.createSequentialGroup()
                .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioLbl)
                    .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contrasenaLbl)
                    .addComponent(contrasenaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reqContrasenaChbx)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cipherCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cipherLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rutaKeyStoreLbl)
                    .addComponent(rutaKeyStoreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(secureConfJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conectarBtn)
                    .addComponent(statusLbl)
                    .addComponent(estadoLbl)
                    .addComponent(errorLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        errorLbl.getAccessibleContext().setAccessibleParent(jInternalFrame1);

        colaMqLbl.setText("Cola MQ");

        ponerMsgBtn.setText("Poner mensaje");
        ponerMsgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponerMsgBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(connConfJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(colaMqLbl)
                        .addGap(18, 18, 18)
                        .addComponent(colaMqTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ponerMsgBtn))
                    .addComponent(secureConfJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(connConfJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ponerMsgBtn)
                            .addComponent(colaMqTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colaMqLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secureConfJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        secureConfJPanel.getAccessibleContext().setAccessibleName("Configuración de conexión segura");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setBottomComponent(jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSplitPane1)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSplitPane1)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                    }
                    catch (MQMMessageBrockerConnectionRefusedException | MQMMessageBrockerUnexpectedException ex)
                    {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                        StringBuilder sb = new StringBuilder(ex.toString());
                        for (StackTraceElement ste : ex.getStackTrace())
                        {
                            sb.append("\n\tat ");
                            sb.append(ste);
                        }
                        String trace = sb.toString();
                        mensajeTxa.setText( ex.getCause().toString()+"\n"+trace );
                        mensajeTxa.setEditable(false);
                    }
                }
                else
                {
                    errorLbl.setText( "El gestor está desconectado." );
                    return;
                }
            } 
            catch (MQMMessageBrockerConnectionRefusedException | MQMMessageBrockerUnexpectedException ex)
            {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                StringBuilder sb = new StringBuilder(ex.toString());
                for (StackTraceElement ste : ex.getStackTrace())
                {
                    sb.append("\n\tat ");
                    sb.append(ste);
                }
                String trace = sb.toString();
                mensajeTxa.setText( ex.getCause().toString()+"\n"+trace );
                mensajeTxa.setEditable(false);
            }
        }
        else
        {
            errorLbl.setText( "El gestor está desconectado." );
            return;
        }
        
    }//GEN-LAST:event_ponerMsgBtnActionPerformed

    private void reqContrasenaChbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqContrasenaChbxActionPerformed
        if ( reqContrasenaChbx.isSelected() )
        {
            contrasenaTxt.setEnabled( false );
        }
        else
        {
            contrasenaTxt.setEnabled( true );
        }
    }//GEN-LAST:event_reqContrasenaChbxActionPerformed

    private void connTypeCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connTypeCbxActionPerformed
        String selected = connTypeCbx.getSelectedItem().toString();
        setConnType( selected );
        if ( "SECURE".equals( selected ) )
        {
            cipherCbx.setEnabled( true );
            rutaKeyStoreTxt.setEnabled( true );
        }
        else
        {
            cipherCbx.setEnabled( false );
            rutaKeyStoreTxt.setEnabled( false );
        }
    }//GEN-LAST:event_connTypeCbxActionPerformed

    private void conectarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarBtnActionPerformed

        if ( conn == null )
        {
            conn = new ExchangeConnectorMQ( gestorTxt.getText() , servidorTxt.getText() , puertoTxt.getText(), canalTxt.getText(), connType, usuarioTxt.getText() );
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
                    mensajeTxa.setText( "" );
                    mensajeTxa.setEditable(true);
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
        } catch (MQMMessageBrockerConnectionRefusedException | MQMMessageBrockerUnexpectedException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            StringBuilder sb = new StringBuilder(ex.toString());
            for (StackTraceElement ste : ex.getStackTrace())
            {
                sb.append("\n\tat ");
                sb.append(ste);
            }
            String trace = sb.toString();
            mensajeTxa.setText( ex.getCause().toString()+"\n"+trace );
            mensajeTxa.setEditable(false);
        }
    }//GEN-LAST:event_conectarBtnActionPerformed

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
                Administrador admin = new Administrador();
                admin.setConnType( admin.connTypeCbx.getSelectedItem().toString() );
                admin.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel canalLbl;
    private javax.swing.JTextField canalTxt;
    private javax.swing.JComboBox<String> cipherCbx;
    private javax.swing.JLabel cipherLbl;
    private javax.swing.JLabel colaMqLbl;
    private javax.swing.JTextField colaMqTxt;
    private javax.swing.JButton conectarBtn;
    private javax.swing.JPanel connConfJPanel;
    private javax.swing.JComboBox<String> connTypeCbx;
    private javax.swing.JLabel connTypeLbl;
    private javax.swing.JLabel contrasenaLbl;
    private javax.swing.JPasswordField contrasenaTxt;
    private javax.swing.JLabel errorLbl;
    private javax.swing.JLabel estadoLbl;
    private javax.swing.JLabel gestorLbl;
    private javax.swing.JTextField gestorTxt;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea mensajeTxa;
    private javax.swing.JButton ponerMsgBtn;
    private javax.swing.JLabel puertoLbl;
    private javax.swing.JTextField puertoTxt;
    private javax.swing.JCheckBox reqContrasenaChbx;
    private javax.swing.JLabel rutaKeyStoreLbl;
    private javax.swing.JTextField rutaKeyStoreTxt;
    private javax.swing.JLabel servidorLbl;
    private javax.swing.JTextField servidorTxt;
    private javax.swing.JLabel statusLbl;
    private javax.swing.JLabel usuarioLbl;
    private javax.swing.JTextField usuarioTxt;
    // End of variables declaration//GEN-END:variables

    private String connType;

    public String getConnType() {
        return connType;
    }

    public void setConnType(String connType) {
        this.connType = connType;
    }
    

}
