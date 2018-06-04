/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contextualmenus;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.Parent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import mqconnector.ExchangeConnectorMQ;
import mqmessagebrocker.MessageListFrame;

/**
 *
 * @author jonathan.velez
 */
public class DetailMessageContextualMenu extends JPopupMenu
{
    JMenuItem itemDelete;
    JMenuItem itemDetail;
        
    ExchangeConnectorMQ conn;
    
    public DetailMessageContextualMenu()
    {
        itemDelete =  new JMenuItem( "Borrar" );
        itemDetail =  new JMenuItem( "Ver contenido" );
        
        add( itemDelete );
        add( itemDetail );
    }
}
