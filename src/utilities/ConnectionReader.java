package utilities;

import exceptions.NodeNotExistException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import mqmessagebrocker.Connection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Esta clase contienen la funcionalidad necesaria para convertir a objetos Java todas las conexiones que hayan sido almacenadas en la aplicación.
 * @author Jonathan V�lez G. -- mrvelez13@gmail.com
 * @date	Octubre 10, 2017 *
 */
public class ConnectionReader
{
    private  String environmentFileName = "";

    private  Properties environmentFileContent;

    /*Variables para reconocer el ambiente donde el m�dulo se est� ejecutando..*/
    private  String hostName, hostIp, user = "";

    /*Variables que se poblar�n con la parametr�a para el ambiente espec�fico, una vez �ste sea reconocido.*/
    private  String connectionName;
    private  String environmentName;
    private  String queuePrefix;
    private  String mqManager;
    private  String mqChannel;
    private  String mqSecureChannel;
    private  String mqHost;
    private  String mqPort;
    private  String mqSecurePort;
    private  String mqConnectionType;
    private  String sslDir;
    private  String mqEnableSecureConnection;
    private  String keystore;
    private  String trustore;
    private  String requiredPassword;
    private  String encriptedPassword;
    private  String keystorePassword;
    private  String trustorePassword;
    private  String neededProperties;
    private  String mqTransportProperty;
    private  String mqCipherSuite;
    private  String mqRequiredUser;
    private  String mqUser;
    private  String mqUserPassword;
    private  ArrayList<Connection> Connections = new ArrayList<Connection>();
    
    public  ConnectionReader() throws Exception
    {
        Writer file;
        try
        {
            environmentFileName = "connections.xml";
            
            environmentFileContent = new Properties();
            File xmlFile = new File( environmentFileName );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            
	
            if( !xmlFile.exists() )
            {
                file = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( environmentFileName ), "UTF-8"  ) );
                file.write( "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>" + "\n" + "<connection>" + "\n" + "</connection>" );
                file.close();
            }
            
            loadEnvironmentAttributes();           
        }
        catch( Exception e )
        {
                throw e;
        }
    }

    private void loadEnvironmentAttributes() throws Exception
    {
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
            hostIp = addr.getHostAddress();

//		    NTSystem NTSystem = new NTSystem();
//		    user = NTSystem.getName();
            loadEnvironmentFile();
            
        }
        catch ( UnknownHostException | NodeNotExistException ex )
        {
            ex.printStackTrace();
            throw ex;
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    private  void loadEnvironmentFile() throws Exception
    {
        environmentFileContent = new Properties();
        File xmlFile = new File( environmentFileName );
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        boolean envRecognized = false;

        try
        {	
            if ( xmlFile.exists() )
            {
                dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                /*Se obtienen la lista del nodos "env" del archivo connections.xml para ser analizados y comparados.*/
                NodeList nodeList = doc.getElementsByTagName("env");
                
                for ( int i = 0; i < nodeList.getLength(); i++ )
                {
                    Node node = nodeList.item(i);
                    if ( node.getNodeType() == Node.ELEMENT_NODE )
                    {
//                        xmlValidator( element );
                        
                        Connection connection = new Connection();
                        
                        Element element = (Element) node;
                        NodeList nl = element.getElementsByTagName( "hostName" ).item( 0 ).getChildNodes();
                        Node no = ( Node ) nl.item( 0 );
                        environmentName = no != null ? no.getNodeValue() : "";
                        /*Nombre del ambiente*/
                        NodeList nodeListName = element.getElementsByTagName( "name" ).item( 0 ).getChildNodes();
                        Node nodeName = ( Node ) nodeListName.item( 0 );
                        connectionName = nodeName != null ? nodeName.getNodeValue() : "";
                        /*Prefijo para las colas MQ*/
                        NodeList nodeListQueuePrefix = element.getElementsByTagName( "queuePrefix" ).item( 0 ).getChildNodes();
                        Node nodeQueuePrefix = ( Node ) nodeListQueuePrefix.item( 0 );
                        queuePrefix = nodeQueuePrefix != null ? nodeQueuePrefix.getNodeValue() : "";
                        /*Nombre del gestor de colas MQ*/
                        NodeList nodeListMqManager = element.getElementsByTagName( "mqManager" ).item( 0 ).getChildNodes();
                        Node nodeMqManager = ( Node ) nodeListMqManager.item( 0 );
                        mqManager = nodeMqManager != null ? nodeMqManager.getNodeValue() : "";
                        /*Canal de conexi�n a MQ por defecto*/
                        NodeList nodeListMqChannel = element.getElementsByTagName( "mqChannel" ).item( 0 ).getChildNodes();
                        Node nodeMqChannel = ( Node ) nodeListMqChannel.item( 0 );
                        mqChannel = nodeMqChannel != null ? nodeMqChannel.getNodeValue() : "";
                        /*Canal seguro de conexi�n a MQ*/
                        NodeList nodeListMqSecureChannel = element.getElementsByTagName( "mqSecureChannel" ).item( 0 ).getChildNodes();
                        Node nodeMqSecureChannel = ( Node ) nodeListMqSecureChannel.item( 0 );
                        mqSecureChannel = nodeMqSecureChannel != null ? nodeMqSecureChannel.getNodeValue() : "";
                        /*IP del Servidor MQ*/
                        NodeList nodeListMqHost = element.getElementsByTagName( "mqHost" ).item( 0 ).getChildNodes();
                        Node nodeMqHost = ( Node ) nodeListMqHost.item( 0 );
                        mqHost = nodeMqHost != null ? nodeMqHost.getNodeValue() : "";
                        /*Puerto de conexi�n al canal MQ*/
                        NodeList nodeListMqPort = element.getElementsByTagName( "mqPort" ).item( 0 ).getChildNodes();
                        Node nodeMqPort = ( Node ) nodeListMqPort.item( 0 );
                        mqPort = nodeMqPort != null ? nodeMqPort.getNodeValue() : "";
                        /*Puerto de conexi�n a canal MQ Seguro*/
                        NodeList nodeListMqSecurePort = element.getElementsByTagName( "mqSecurePort" ).item( 0 ).getChildNodes();
                        Node nodeMqSecurePort = ( Node ) nodeListMqSecurePort.item( 0 );
                        mqSecurePort = nodeMqSecurePort != null ? nodeMqSecurePort.getNodeValue() : "";
                        /*Tipo de conexi�n. Indica si se trata de una conexi�n segura o cliente*/
                        NodeList nodeListMqConnectionType = element.getElementsByTagName( "mqConnectionType" ).item( 0 ).getChildNodes();
                        Node nodeMqConnectionType = ( Node ) nodeListMqConnectionType.item( 0 );
                        mqConnectionType = nodeMqConnectionType != null ? nodeMqConnectionType.getNodeValue() : "";
                        /*Path del directorio donde se encuentra el keystore y trustore*/
                        NodeList nodeListSslDir = element.getElementsByTagName( "sslDir" ).item( 0 ).getChildNodes();
                        Node nodeSslDir = ( Node ) nodeListSslDir.item( 0 );
                        sslDir = nodeSslDir != null ? nodeSslDir.getNodeValue() : "";
                        /*�Habilitar la conexi�n segura?*/
                        NodeList nodeListMqEnableSecureConnection = element.getElementsByTagName( "mqEnableSecureConnection" ).item( 0 ).getChildNodes();
                        Node nodeMqEnableSecureConnection = ( Node ) nodeListMqEnableSecureConnection.item( 0 );
                        mqEnableSecureConnection = nodeMqEnableSecureConnection != null ?nodeMqEnableSecureConnection.getNodeValue() : "";
                        /*Nombre del archivo keystore con extensi�n jks*/
                        NodeList nodeListKeystore = element.getElementsByTagName( "keystore" ).item( 0 ).getChildNodes();
                        Node nodeKeystore = ( Node ) nodeListKeystore.item( 0 );
                        keystore = nodeKeystore != null ? nodeKeystore.getNodeValue() : "";
                        /*Nombre del archivo trustore con extensi�n jks*/
                        NodeList nodeListTrustore = element.getElementsByTagName( "trustore" ).item( 0 ).getChildNodes();
                        Node nodeTrustore  = ( Node ) nodeListTrustore.item( 0 );
                        trustore = nodeTrustore != null ? nodeTrustore.getNodeValue() : "";
                        /*�El keystore y/o trustore requiere password?*/
                        NodeList nodeListRequiredPassword = element.getElementsByTagName( "requiredPassword" ).item( 0 ).getChildNodes();
                        Node nodeRequiredPassword = ( Node ) nodeListRequiredPassword.item( 0 );
                        requiredPassword = nodeRequiredPassword != null ? nodeRequiredPassword.getNodeValue() : "";
                        /*�El password est� encriptado?*/
                        NodeList nodeListEncriptedPassword = element.getElementsByTagName( "encriptedPassword" ).item( 0 ).getChildNodes();
                        Node nodeEncriptedPassword = ( Node ) nodeListEncriptedPassword.item( 0 );
                        encriptedPassword = nodeEncriptedPassword != null ? nodeEncriptedPassword.getNodeValue() : "";
                        /*Password del Keystore*/
                        NodeList nodeListKeystorePassword = element.getElementsByTagName( "keystorePassword" ).item( 0 ).getChildNodes();
                        Node nodeKeystorePassword = ( Node ) nodeListKeystorePassword.item( 0 );
                        keystorePassword = nodeKeystorePassword != null ? nodeKeystorePassword.getNodeValue() : "";
                        /*Password del Trustore*/
                        NodeList nodeListTrustorePassword = element.getElementsByTagName( "trustorePassword" ).item( 0 ).getChildNodes();
                        Node nodeTrustorePassword = ( Node ) nodeListTrustorePassword.item( 0 );
                        trustorePassword = nodeTrustorePassword != null ? nodeTrustorePassword.getNodeValue() : "";
                        /*�La configuraci�n de conexi�n a MQ requiere propiedades adicionales?*/
                        NodeList nodeListNeededProperties = element.getElementsByTagName( "neededProperties" ).item( 0 ).getChildNodes();
                        Node nodeNeededProperties = ( Node ) nodeListNeededProperties.item( 0 );
                        neededProperties = nodeNeededProperties != null ? nodeNeededProperties.getNodeValue() : "";
                        /*Constante de MQ Transport Property que define si es BINDINGS o CLIENT o por defecto*/
                        NodeList nodeListMqTransportProperty = element.getElementsByTagName( "mqTransportProperty" ).item( 0 ).getChildNodes();
                        Node nodeMqTransportProperty = ( Node ) nodeListMqTransportProperty.item( 0 );
                        mqTransportProperty = nodeMqTransportProperty != null ? nodeMqTransportProperty.getNodeValue() : "";
                        /*ChiperSuit - Algoritmo de cifrado de mensajes intercambiados entre cliente y MQ Server*/
                        NodeList nodeListMqCipherSuite = element.getElementsByTagName( "mqCipherSuite" ).item( 0 ).getChildNodes();
                        Node nodeMqCipherSuite = ( Node ) nodeListMqCipherSuite.item( 0 );
                        mqCipherSuite = nodeMqCipherSuite != null ? nodeMqCipherSuite.getNodeValue() : "";	                	
                        /*�Se requiere usuario MQ para la conexi�n?*/
                        NodeList nodeListMqRequieredUser = element.getElementsByTagName( "mqRequiredUser" ).item( 0 ).getChildNodes();
                        Node nodeMqRequiredUser = ( Node ) nodeListMqRequieredUser.item( 0 );
                        mqRequiredUser = nodeMqRequiredUser != null ? nodeMqRequiredUser.getNodeValue() : "";	                	
                        /*Usuario MQ*/
                        NodeList nodeListMqUser = element.getElementsByTagName( "mqUser" ).item( 0 ).getChildNodes();
                        Node nodeMqUser = ( Node ) nodeListMqUser.item( 0 );
                        mqUser = nodeMqUser != null ? nodeMqUser.getNodeValue() : "";
                        /*Password Usuario MQ*/
                        NodeList nodeListMqUserPassword = element.getElementsByTagName( "mqUserPassword" ).item( 0 ).getChildNodes();
                        Node nodeMqUserPassword = ( Node ) nodeListMqUserPassword.item( 0 );
                        mqUserPassword = nodeMqUserPassword != null ? nodeMqUserPassword.getNodeValue() : "";
                        
                        
                        connection.setConnectionName( connectionName );
                        connection.setHostName( environmentName );
                        connection.setMqManager(  mqManager );
                        connection.setMqChannel( mqChannel );
                        connection.setMqSecureChannel(  mqSecureChannel );
                        connection.setMqHost( mqHost );
                        connection.setMqPort( mqPort );
                        connection.setMqSecurePort( mqSecurePort );
                        connection.setMqConnectionType( mqConnectionType );
                        connection.setMqPathSSLFiles( sslDir );
                        connection.setMqEnableSSLConn( mqEnableSecureConnection );
                        connection.setMqFileNameKeystore( keystore );
                        connection.setMqFileNameTruststore(trustore );
                        connection.setMqIsNeededSSLPassword( requiredPassword );
                        connection.setMqIsSSLEncriptedPassword( encriptedPassword );
                        connection.setMqPassKeyStore( keystorePassword );
                        connection.setMqPassTrustStore( trustorePassword );
                        connection.setSslCipherProperty( mqCipherSuite );
                        connection.setMqIsNeededSSLUser( mqRequiredUser );
                        connection.setMqManagerUser( mqUser );
                        connection.setMqManagerPassword( mqUserPassword );
                        
                        Connections.add( connection );                        
                    }
                    else
                    {
                        throw new ParserConfigurationException("El archivo de configuraci�n \"environment.xml\" contiene errores en su estructura.");
                    }
                }
            }
            else
            {
                FileNotFoundException e = new FileNotFoundException( "El archivo environment.xml no fue encontrado en la ruta /impyval/lib" );
                throw e;
            }
        }
        catch ( SAXException | ParserConfigurationException | IOException e1)
        {
            throw e1;
        }
    }
    
    public void createNewConnectionConfiguration( Connection connection ) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance(); 
        domFactory.setIgnoringComments(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        File xmlFile = new File( environmentFileName );
        Document doc = builder.parse( xmlFile ); 

        NodeList nodes = doc.getElementsByTagName("env");
        
        Element env = doc.createElement("env");        
        
        Element ename = doc.createElement("name");
        Text tname = doc.createTextNode( connection.getConnectionName() );
        ename.appendChild( tname );
        env.appendChild( ename );
        
        Element eip = doc.createElement("ip");
        Text tip = doc.createTextNode( connection.getMqHost() );
        eip.appendChild( tip );
        env.appendChild( eip );
        
        Element ehostName = doc.createElement("hostName"); 
        Text tmqhostname = doc.createTextNode( connection.getHostName() ); 
        ehostName.appendChild( tmqhostname );
        env.appendChild( ehostName );
        
        Element equeuePrefix = doc.createElement("queuePrefix");
        Text tqueuePrefix = doc.createTextNode( "" );
        equeuePrefix.appendChild( tqueuePrefix );
        env.appendChild( equeuePrefix );
                
        Element emqChannel = doc.createElement("mqChannel"); 
        Text mqchannel = doc.createTextNode( connection.getMqChannel() );
        emqChannel.appendChild( mqchannel );
        env.appendChild( emqChannel );
        
        Element emqManager = doc.createElement("mqManager"); 
        Text mqmanager = doc.createTextNode( connection.getMqManager() );
        emqManager.appendChild( mqmanager );
        env.appendChild( emqManager );
        
        Element emqSecureChannel = doc.createElement("mqSecureChannel");
        Text tmqSecureChannel = doc.createTextNode( connection.getMqSecureChannel() );
        emqSecureChannel.appendChild( tmqSecureChannel );
        env.appendChild( emqSecureChannel );
        
        Element emqRequiredUser = doc.createElement("mqRequiredUser");
        Text tmqRequiredUser = doc.createTextNode( connection.getMqIsNeededSSLUser() );
        emqRequiredUser.appendChild( tmqRequiredUser );
        env.appendChild( emqRequiredUser );
        
        Element emqUser = doc.createElement("mqUser");
        Text tmqUser = doc.createTextNode( connection.getMqManagerUser() );
        emqUser.appendChild( tmqUser );
        env.appendChild( emqUser );
        
        Element emqUserPassword = doc.createElement("mqUserPassword");
        Text tmqUserPassword = doc.createTextNode( connection.getMqManagerPassword() );
        emqUserPassword.appendChild( tmqUserPassword );
        env.appendChild( emqUserPassword );
        
        Element emqHost = doc.createElement("mqHost");
        Text tmqHost = doc.createTextNode( connection.getMqHost() );
        emqHost.appendChild( tmqHost );
        env.appendChild( emqHost );
        
        Element emqPort = doc.createElement("mqPort"); 
        Text tmqport = doc.createTextNode( connection.getMqPort() );
        emqPort.appendChild( tmqport );
        env.appendChild( emqPort );
        
        Element emqSecurePort = doc.createElement("mqSecurePort");
        Text tmqSecurePort = doc.createTextNode( connection.getMqSecurePort() );
        emqSecurePort.appendChild( tmqSecurePort );
        env.appendChild( emqSecurePort );
        
        Element emqConnectionType = doc.createElement("mqConnectionType"); 
        Text tmqConnectionType = doc.createTextNode( connection.getMqConnectionType() );
        emqConnectionType.appendChild( tmqConnectionType );
        env.appendChild( emqConnectionType );
        
        Element esslDir = doc.createElement("sslDir");
        Text tsslDir = doc.createTextNode( "" );
        esslDir.appendChild( tsslDir );
        env.appendChild( esslDir );
        
        Element emqEnableSecureConnection = doc.createElement("mqEnableSecureConnection"); 
        Text tmqEnableSecureConnection = doc.createTextNode( connection.getSslFilesPath() );
        emqEnableSecureConnection.appendChild( tmqEnableSecureConnection );
        env.appendChild( emqEnableSecureConnection );
                
        Element ekeystore = doc.createElement("keystore"); 
        Text tkeystore = doc.createTextNode( connection.getMqFileNameKeystore() );
        ekeystore.appendChild( tkeystore );
        env.appendChild( ekeystore );
                
        Element etrustore = doc.createElement("trustore"); 
        Text ttrustore = doc.createTextNode( connection.getMqFileNameTruststore() );
        etrustore.appendChild( ttrustore );
        env.appendChild( etrustore );
                
        Element erequiredPassword = doc.createElement("requiredPassword"); 
        Text trequiredPassword = doc.createTextNode( connection.getMqIsNeededSSLPassword() );
        erequiredPassword.appendChild( trequiredPassword );
        env.appendChild( erequiredPassword );
                
        Element eencriptedPassword = doc.createElement("encriptedPassword"); 
        Text tencriptedPassword = doc.createTextNode( connection.getMqIsSSLEncriptedPassword() );
        eencriptedPassword.appendChild( tencriptedPassword );
        env.appendChild( eencriptedPassword );
                
        Element ekeystorePassword = doc.createElement("keystorePassword");
        Text tkeystorePassword = doc.createTextNode( connection.getMqPassKeyStore() );
        ekeystorePassword.appendChild( tkeystorePassword );
        env.appendChild( ekeystorePassword );
        
        Element etrustorePassword = doc.createElement("trustorePassword");
        Text ttrustorePassword = doc.createTextNode( connection.getMqPassTrustStore() );
        etrustorePassword.appendChild( ttrustorePassword );
        env.appendChild( etrustorePassword );
        
        Element eneededProperties = doc.createElement("neededProperties");
        Text tneededProperties = doc.createTextNode( "" );
        eneededProperties.appendChild( tneededProperties );
        env.appendChild( eneededProperties );
        
        Element emqTransportProperty = doc.createElement("mqTransportProperty");
        Text tmqTransportProperty = doc.createTextNode( "" );
        emqTransportProperty.appendChild( tmqTransportProperty );
        env.appendChild( emqTransportProperty );
        
        Element emqCipherSuite = doc.createElement("mqCipherSuite");
        Text tmqCipherSuite = doc.createTextNode( connection.getSslCipherProperty() );
        emqCipherSuite.appendChild( tmqCipherSuite );
        env.appendChild( emqCipherSuite );
        
        if ( nodes.item( 0 ) == null )
        {
            Element root = doc.getDocumentElement();
            root.appendChild( env );
//            doc.appendChild( env );
//            Node connections = doc.getElementById( "connection" );
//            connections.appendChild( env );           
        }
        else
        {
            nodes.item( 0 ).getParentNode().insertBefore(env, nodes.item( 0 ));            
        }
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult( new File("connections.xml") );
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);

//        String xmlOutput = result.getWriter().toString();
//        System.out.println(xmlOutput);
    }
    
    public String validateConnectionExistence( String connectionName ) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        environmentFileContent = new Properties();
	File xmlFile = new File( environmentFileName );
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	
        if( xmlFile.exists() )
        {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("env");
            
            for ( int i = 0; i < nodeList.getLength(); i++ )
            {
                Node node = nodeList.item(i);
                if ( node.getNodeType() == Node.ELEMENT_NODE )
                {
                    Element element = (Element) node;
                    NodeList nl = element.getElementsByTagName( "name" ).item( 0 ).getChildNodes();
		    Node no = ( Node ) nl.item( 0 );
                    if ( connectionName.equals( no.getNodeValue() ) )
                    {
                        return "exists";
                    }
                }
            }
        }
        else
        {
            String pathForNewConnectionFile = "connections.xml";
            File file = new File( pathForNewConnectionFile );
            BufferedWriter bfwtr = new BufferedWriter( new FileWriter( file ) );
            bfwtr.write( "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>" + "\n" + "<connection>" + "\n" + "</connection>" );
            return "El archivo no connections.xml no existía y tuvo que ser creado.";
        }
        
        return "not exist";
    }
    
    public void deleteConnection( String connectionName ) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        environmentFileContent = new Properties();
	File xmlFile = new File( environmentFileName );
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	
        if( xmlFile.exists() )
        {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("env");
            
            for ( int i = 0; i < nodeList.getLength(); i++ )
            {
                Node node = nodeList.item(i);
                if ( node.getNodeType() == Node.ELEMENT_NODE )
                {
                    Element element = (Element) node;
                    NodeList nl = element.getElementsByTagName( "name" ).item( 0 ).getChildNodes();
		    Node no = ( Node ) nl.item( 0 );
                    if ( connectionName.equals( no.getNodeValue() ) )
                    {
                        element.getParentNode().removeChild( element );
//                        doc.normalize();
                    }
                }
            }
            
            XPathFactory xpathFactory = XPathFactory.newInstance();
            // XPath to find empty text nodes.
            
            
            try {
                XPathExpression xpathExp = xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");  
                NodeList emptyTextNodes;
                emptyTextNodes = (NodeList) xpathExp.evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < emptyTextNodes.getLength(); i++)
                {
                    Node emptyTextNode = emptyTextNodes.item(i);
                    emptyTextNode.getParentNode().removeChild(emptyTextNode);
                }
            }
            catch (XPathExpressionException ex)
            {
                Logger.getLogger(ConnectionReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty( OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult( new File("connections.xml") );
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        }
    }
    
    public void updateConnection( Connection connToUpdate)  throws SAXException, IOException, ParserConfigurationException, TransformerException{
        environmentFileContent = new Properties();
	File xmlFile = new File( environmentFileName );
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	
        if( xmlFile.exists() )
        {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("env");
            
            for ( int i = 0; i < nodeList.getLength(); i++ )
            {
                Node node = nodeList.item(i);
                if ( node.getNodeType() == Node.ELEMENT_NODE )
                {
                    Element element = (Element) node;
                    NodeList nl = element.getElementsByTagName( "name" ).item( 0 ).getChildNodes();
		    Node no = ( Node ) nl.item( 0 );
                    if ( connToUpdate.getConnectionName().equals( no.getNodeValue() ) )
                    {
                        Node dname  = ( Node ) element.getElementsByTagName( "name" ).item( 0 ).getChildNodes().item( 0 );
                        if( dname != null )
                        {
                            dname.setNodeValue( connToUpdate.getConnectionName() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "name" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dname  = ( Node ) element.getElementsByTagName( "name" ).item( 0 ).getChildNodes().item( 0 );
                            dname.setNodeValue( connToUpdate.getConnectionName() );
                        }

                        Node dip  = ( Node ) element.getElementsByTagName( "ip" ).item( 0 ).getChildNodes().item( 0 );
                        if( dip != null )
                        {
                            dip.setNodeValue( connToUpdate.getMqHost() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "ip" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dip  = ( Node ) element.getElementsByTagName( "ip" ).item( 0 ).getChildNodes().item( 0 );
                            dip.setNodeValue( connToUpdate.getMqHost() );
                        }

                        Node dhostName  = ( Node ) element.getElementsByTagName( "hostName" ).item( 0 ).getChildNodes().item( 0 );
                        if( dhostName != null )
                        {
                            dhostName.setNodeValue( connToUpdate.getHostName() ); 
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "hostName" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dhostName  = ( Node ) element.getElementsByTagName( "hostName" ).item( 0 ).getChildNodes().item( 0 );
                            dhostName.setNodeValue( connToUpdate.getHostName() ); 
                        }

                        Node dqueuePrefix  = ( Node ) element.getElementsByTagName( "queuePrefix" ).item( 0 ).getChildNodes().item( 0 );
                        if ( dqueuePrefix != null )
                        {
                            dqueuePrefix.setNodeValue( "" );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "queuePrefix" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dqueuePrefix  = ( Node ) element.getElementsByTagName( "queuePrefix" ).item( 0 ).getChildNodes().item( 0 );
                            dqueuePrefix.setNodeValue( "" );
                        }

                        Node dmqChannel  = ( Node ) element.getElementsByTagName( "mqChannel" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqChannel != null )
                        {
                            dmqChannel.setNodeValue( connToUpdate.getMqChannel() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqChannel" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqChannel  = ( Node ) element.getElementsByTagName( "mqChannel" ).item( 0 ).getChildNodes().item( 0 );
                            dmqChannel.setNodeValue( connToUpdate.getMqChannel() );
                        }

                        Node dmqManager  = ( Node ) element.getElementsByTagName( "mqManager" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dmqManager != null )
                        {
                            dmqManager.setNodeValue( connToUpdate.getMqManager() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqManager" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqManager  = ( Node ) element.getElementsByTagName( "mqManager" ).item( 0 ).getChildNodes().item( 0 );
                            dmqManager.setNodeValue( connToUpdate.getMqManager() );
                        }

                        Node dmqSecureChannel  = ( Node ) element.getElementsByTagName( "mqSecureChannel" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqSecureChannel != null )
                        {
                            dmqSecureChannel.setNodeValue( connToUpdate.getMqSecureChannel() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqSecureChannel" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqSecureChannel  = ( Node ) element.getElementsByTagName( "mqSecureChannel" ).item( 0 ).getChildNodes().item( 0 );
                            dmqSecureChannel.setNodeValue( connToUpdate.getMqSecureChannel() );
                        }

                        Node dmqRequiredUser  = ( Node ) element.getElementsByTagName( "mqRequiredUser" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqRequiredUser != null )
                        {
                            dmqRequiredUser.setNodeValue( connToUpdate.getMqIsNeededSSLUser() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqRequiredUser" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqRequiredUser  = ( Node ) element.getElementsByTagName( "mqRequiredUser" ).item( 0 ).getChildNodes().item( 0 );
                            dmqRequiredUser.setNodeValue( connToUpdate.getMqIsNeededSSLUser() );
                        }

                        Node dmqUser  = ( Node ) element.getElementsByTagName( "mqUser" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqUser != null )
                        {
                            dmqUser.setNodeValue( connToUpdate.getMqManagerUser() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqUser" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqUser  = ( Node ) element.getElementsByTagName( "mqUser" ).item( 0 ).getChildNodes().item( 0 );
                            dmqUser.setNodeValue( connToUpdate.getMqManagerUser() );
                        }

                        Node dmqUserPassword  = ( Node ) element.getElementsByTagName( "mqUserPassword" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqUserPassword != null )
                        {
                            dmqUserPassword.setNodeValue( connToUpdate.getMqManagerPassword() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqUserPassword" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqUserPassword  = ( Node ) element.getElementsByTagName( "mqUserPassword" ).item( 0 ).getChildNodes().item( 0 );
                            dmqUserPassword.setNodeValue( connToUpdate.getMqManagerPassword() );
                        }

                        Node dmqHost  = ( Node ) element.getElementsByTagName( "mqHost" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqHost != null )
                        {
                            dmqHost.setNodeValue( connToUpdate.getMqHost() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqHost" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqHost  = ( Node ) element.getElementsByTagName( "mqHost" ).item( 0 ).getChildNodes().item( 0 );
                            dmqHost.setNodeValue( connToUpdate.getMqHost() );
                        }

                        Node dmqPort  = ( Node ) element.getElementsByTagName( "mqPort" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dmqPort != null )
                        {
                            dmqPort.setNodeValue( connToUpdate.getMqPort() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqPort" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqPort  = ( Node ) element.getElementsByTagName( "mqPort" ).item( 0 ).getChildNodes().item( 0 ); 
                            dmqPort.setNodeValue( connToUpdate.getMqPort() );
                        }

                        Node dmqSecurePort  = ( Node ) element.getElementsByTagName( "mqSecurePort" ).item( 0 ).getChildNodes().item( 0 );
                        if ( dmqSecurePort != null )
                        {
                            dmqSecurePort.setNodeValue( connToUpdate.getMqSecurePort() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqSecurePort" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqSecurePort  = ( Node ) element.getElementsByTagName( "mqSecurePort" ).item( 0 ).getChildNodes().item( 0 );
                            dmqSecurePort.setNodeValue( connToUpdate.getMqSecurePort() );
                        }

                        Node dmqConnectionType  = ( Node ) element.getElementsByTagName( "mqConnectionType" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dmqConnectionType != null )
                        {
                            dmqConnectionType.setNodeValue( connToUpdate.getMqConnectionType() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqConnectionType" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqConnectionType  = ( Node ) element.getElementsByTagName( "mqConnectionType" ).item( 0 ).getChildNodes().item( 0 ); 
                            dmqConnectionType.setNodeValue( connToUpdate.getMqConnectionType() );
                        }

                        Node dsslDir  = ( Node ) element.getElementsByTagName( "sslDir" ).item( 0 ).getChildNodes().item( 0 );
                        if( dsslDir != null )
                        {
                            dsslDir.setNodeValue( "" );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "sslDir" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dsslDir  = ( Node ) element.getElementsByTagName( "sslDir" ).item( 0 ).getChildNodes().item( 0 );
                            dsslDir.setNodeValue( "" );
                        }

                        Node dmqEnableSecureConnection  = ( Node ) element.getElementsByTagName( "mqEnableSecureConnection" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dmqEnableSecureConnection != null )
                        {
                            dmqEnableSecureConnection.setNodeValue( connToUpdate.getSslFilesPath() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqEnableSecureConnection" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqEnableSecureConnection  = ( Node ) element.getElementsByTagName( "mqEnableSecureConnection" ).item( 0 ).getChildNodes().item( 0 ); 
                            dmqEnableSecureConnection.setNodeValue( connToUpdate.getSslFilesPath() );
                        }

                        Node dkeystore  = ( Node ) element.getElementsByTagName( "keystore" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dkeystore != null )
                        {
                            dkeystore.setNodeValue( connToUpdate.getMqFileNameKeystore() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "keystore" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dkeystore  = ( Node ) element.getElementsByTagName( "keystore" ).item( 0 ).getChildNodes().item( 0 ); 
                            dkeystore.setNodeValue( connToUpdate.getMqFileNameKeystore() );
                        }

                        Node dtrustore  = ( Node ) element.getElementsByTagName( "trustore" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dtrustore != null )
                        {
                            dtrustore.setNodeValue( connToUpdate.getMqFileNameTruststore() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "trustore" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dtrustore  = ( Node ) element.getElementsByTagName( "trustore" ).item( 0 ).getChildNodes().item( 0 ); 
                            dtrustore.setNodeValue( connToUpdate.getMqFileNameTruststore() );
                        }

                        Node drequiredPassword  = ( Node ) element.getElementsByTagName( "requiredPassword" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( drequiredPassword != null )
                        {
                            drequiredPassword.setNodeValue( connToUpdate.getMqIsNeededSSLPassword() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "requiredPassword" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            drequiredPassword  = ( Node ) element.getElementsByTagName( "requiredPassword" ).item( 0 ).getChildNodes().item( 0 ); 
                            drequiredPassword.setNodeValue( connToUpdate.getMqIsNeededSSLPassword() );
                        }

                        Node dencriptedPassword  = ( Node ) element.getElementsByTagName( "encriptedPassword" ).item( 0 ).getChildNodes().item( 0 ); 
                        if( dencriptedPassword != null )
                        {
                            dencriptedPassword.setNodeValue( connToUpdate.getMqIsSSLEncriptedPassword() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "encriptedPassword" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dencriptedPassword  = ( Node ) element.getElementsByTagName( "encriptedPassword" ).item( 0 ).getChildNodes().item( 0 ); 
                            dencriptedPassword.setNodeValue( connToUpdate.getMqIsSSLEncriptedPassword() );
                        }

                        Node dkeystorePassword  = ( Node ) element.getElementsByTagName( "keystorePassword" ).item( 0 ).getChildNodes().item( 0 );
                        if( dkeystorePassword != null )
                        {
                            dkeystorePassword.setNodeValue( connToUpdate.getMqPassKeyStore() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "keystorePassword" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dkeystorePassword  = ( Node ) element.getElementsByTagName( "keystorePassword" ).item( 0 ).getChildNodes().item( 0 );
                            dkeystorePassword.setNodeValue( connToUpdate.getMqPassKeyStore() );
                        }

                        Node dtrustorePassword  = ( Node ) element.getElementsByTagName( "trustorePassword" ).item( 0 ).getChildNodes().item( 0 );
                        if( dtrustorePassword != null )
                        {
                            dtrustorePassword.setNodeValue( connToUpdate.getMqPassTrustStore() );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "trustorePassword" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dtrustorePassword  = ( Node ) element.getElementsByTagName( "trustorePassword" ).item( 0 ).getChildNodes().item( 0 );
                            dtrustorePassword.setNodeValue( connToUpdate.getMqPassTrustStore() );
                        }

                        Node dneededProperties  = ( Node ) element.getElementsByTagName( "neededProperties" ).item( 0 ).getChildNodes().item( 0 );
                        if( dneededProperties != null )
                        {
                            dneededProperties.setNodeValue( "" );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "neededProperties" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dneededProperties  = ( Node ) element.getElementsByTagName( "neededProperties" ).item( 0 ).getChildNodes().item( 0 );
                            dneededProperties.setNodeValue( "" );
                        }

                        Node dmqTransportProperty  = ( Node ) element.getElementsByTagName( "mqTransportProperty" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqTransportProperty != null )
                        {
                            dmqTransportProperty.setNodeValue( "" );
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqTransportProperty" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqTransportProperty  = ( Node ) element.getElementsByTagName( "mqTransportProperty" ).item( 0 ).getChildNodes().item( 0 );
                            dmqTransportProperty.setNodeValue( "" );
                            
                        }

                        Node dmqCipherSuite  = ( Node ) element.getElementsByTagName( "mqCipherSuite" ).item( 0 ).getChildNodes().item( 0 );
                        if( dmqCipherSuite != null )
                        {
                            dmqCipherSuite.setNodeValue( connToUpdate.getSslCipherProperty() );                      
                        }
                        else
                        {
                            ((Node)element.getElementsByTagName( "mqCipherSuite" ).item(0).getChildNodes() ).appendChild( doc.createTextNode( "" ) );
                            dmqCipherSuite  = ( Node ) element.getElementsByTagName( "mqCipherSuite" ).item( 0 ).getChildNodes().item( 0 );
                            dmqCipherSuite.setNodeValue( connToUpdate.getSslCipherProperty() );
                        }                        
//                        doc.normalize();
                    }
                }
            }
            
            XPathFactory xpathFactory = XPathFactory.newInstance();
            // XPath to find empty text nodes.
            
            
            try {
                XPathExpression xpathExp = xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");  
                NodeList emptyTextNodes;
                emptyTextNodes = (NodeList) xpathExp.evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < emptyTextNodes.getLength(); i++)
                {
                    Node emptyTextNode = emptyTextNodes.item(i);
                    emptyTextNode.getParentNode().removeChild(emptyTextNode);
                }
            }
            catch (XPathExpressionException ex)
            {
                Logger.getLogger(ConnectionReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty( OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult( new File("connections.xml") );
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        }
    }
    
    private  void xmlValidator( Element element ) throws NodeNotExistException
    {
        ArrayList<String> missingNodes = new ArrayList<>();

        if ( element.getElementsByTagName( "name" ).item( 0 ) == null )
        {
                missingNodes.add("<name>");
        }
        if ( element.getElementsByTagName( "ip" ).item( 0 ) == null )
        {
                missingNodes.add("<ip>");
        }
        if ( element.getElementsByTagName( "hostName" ).item( 0 ) == null )
        {
                missingNodes.add("<hostName>");
        }
        if ( element.getElementsByTagName( "queuePrefix" ).item( 0 ) == null )
        {
                missingNodes.add("<queuePrefix>");
        }
        if ( element.getElementsByTagName( "mqChannel" ).item( 0 ) == null )
        {
                missingNodes.add("<mqChannel>");
        }
        if ( element.getElementsByTagName( "mqManager" ).item( 0 ) == null )
        {
                missingNodes.add("<mqManager>");
        }
        if ( element.getElementsByTagName( "mqSecureChannel" ).item( 0 ) == null )
        {
                missingNodes.add("<mqSecureChannel>");
        }
        if ( element.getElementsByTagName( "mqRequiredUser" ).item( 0 ) == null )
        {
                missingNodes.add("<mqRequiredUser>");
        }
        if ( element.getElementsByTagName( "mqUser" ).item( 0 ) == null )
        {
                missingNodes.add("<mqUser>");
        }
        if ( element.getElementsByTagName( "mqUserPassword" ).item( 0 ) == null )
        {
                missingNodes.add("<mqUserPassword>");
        }
        if ( element.getElementsByTagName( "mqHost" ).item( 0 ) == null )
        {
                missingNodes.add("<mqHost>");
        }
        if ( element.getElementsByTagName( "mqPort" ).item( 0 ) == null )
        {
                missingNodes.add("<mqPort>");
        }
        if ( element.getElementsByTagName( "mqSecurePort" ).item( 0 ) == null )
        {
                missingNodes.add("<mqSecurePort>");
        }
        if ( element.getElementsByTagName( "mqConnectionType" ).item( 0 ) == null )
        {
                missingNodes.add("<mqConnectionType>");
        }
        if ( element.getElementsByTagName( "sslDir" ).item( 0 ) == null )
        {
                missingNodes.add("<sslDir>");
        }
        if ( element.getElementsByTagName( "mqEnableSecureConnection" ).item( 0 ) == null )
        {
                missingNodes.add("<mqEnableSecureConnection>");
        }
        if ( element.getElementsByTagName( "keystore" ).item( 0 ) == null )
        {
                missingNodes.add("<keystore>");
        }
        if ( element.getElementsByTagName( "trustore" ).item( 0 ) == null )
        {
                missingNodes.add("<trustore>");
        }
        if ( element.getElementsByTagName( "requiredPassword" ).item( 0 ) == null )
        {
                missingNodes.add("<requiredPassword>");
        }
        if ( element.getElementsByTagName( "encriptedPassword" ).item( 0 ) == null )
        {
                missingNodes.add("<encriptedPassword>");
        }
        if ( element.getElementsByTagName( "keystorePassword" ).item( 0 ) == null )
        {
                missingNodes.add("<keystorePassword>");
        }
        if ( element.getElementsByTagName( "trustorePassword" ).item( 0 ) == null )
        {
                missingNodes.add("<trustorePassword>");
        }
        if ( element.getElementsByTagName( "neededProperties" ).item( 0 ) == null )
        {
                missingNodes.add("<neededProperties>");
        }
        if ( element.getElementsByTagName( "mqTransportProperty" ).item( 0 ) == null )
        {
                missingNodes.add("<mqTransportProperty>");
        }
        if ( element.getElementsByTagName( "mqCipherSuite" ).item( 0 ) == null )
        {
                missingNodes.add("<mqCipherSuite>");
        }

        if( missingNodes.size() > 0 )
        {
            String errormsj = "Los siguientes nodos no aparecen en el archivo environment.xml.";

            for ( String str : missingNodes )
            {
                errormsj = errormsj + "\n" + str;
            }

            NodeNotExistException e = new NodeNotExistException( errormsj, new NullPointerException() );
            System.exit( -1 );
        }
    }

    public  String getConnectionName() {
        return connectionName;
    }

    public  void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public  ArrayList<Connection> getConnections() {
        return Connections;
    }

    public  void setConnections(ArrayList<Connection> Connections) {
        this.Connections = Connections;
    }

    
    public  String getEnvironmentFileName() {
        return environmentFileName;
    }

    public  void setEnvironmentFileName(String environmentFileName) {
        this.environmentFileName = environmentFileName;
    }

    public  Properties getEnvironmentFileContent() {
        return environmentFileContent;
    }

    public  void setEnvironmentFileContent(Properties environmentFileContent) {
        this.environmentFileContent = environmentFileContent;
    }

    public  String getEnvironmentName() {
        return environmentName;
    }

    public  void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public  String getHostName() {
        return hostName;
    }

    public  void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public  String getHostIp() {
        return hostIp;
    }

    public  void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public  String getUser() {
        return user;
    }

    public  void setUser(String user) {
        this.user = user;
    }

    public  String getQueuePrefix() {
        return queuePrefix;
    }

    public  void setQueuePrefix(String queuePrefix) {
        this.queuePrefix = queuePrefix;
    }

    public  String getMqManager() {
        return mqManager;
    }

    public  void setMqManager(String mqManager) {
        this.mqManager = mqManager;
    }

    public  String getMqChannel() {
        return mqChannel;
    }

    public  void setMqChannel(String mqChannel) {
        this.mqChannel = mqChannel;
    }

    public  String getMqSecureChannel() {
        return mqSecureChannel;
    }

    public  void setMqSecureChannel(String mqSecureChannel) {
        this.mqSecureChannel = mqSecureChannel;
    }

    public  String getMqHost() {
        return mqHost;
    }

    public  void setMqHost(String mqHost) {
        this.mqHost = mqHost;
    }

    public  String getMqPort() {
        return mqPort;
    }

    public  void setMqPort(String mqPort) {
        this.mqPort = mqPort;
    }

    public  String getMqSecurePort() {
        return mqSecurePort;
    }

    public  void setMqSecurePort(String mqSecurePort) {
        this.mqSecurePort = mqSecurePort;
    }

    public  String getMqConnectionType() {
        return mqConnectionType;
    }

    public  void setMqConnectionType(String mqConnectionType) {
        this.mqConnectionType = mqConnectionType;
    }

    public  String getSslDir() {
        return sslDir;
    }

    public  void setSslDir(String sslDir) {
        this.sslDir = sslDir;
    }

    public  String getMqEnableSecureConnection() {
        return mqEnableSecureConnection;
    }

    public  void setMqEnableSecureConnection(String mqEnableSecureConnection) {
        this.mqEnableSecureConnection = mqEnableSecureConnection;
    }

    public  String getKeystore() {
        return keystore;
    }

    public  void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public  String getTrustore() {
        return trustore;
    }

    public  void setTrustore(String trustore) {
        this.trustore = trustore;
    }

    public  String getRequiredPassword() {
        return requiredPassword;
    }

    public  void setRequiredPassword(String requiredPassword) {
        this.requiredPassword = requiredPassword;
    }

    public  String getEncriptedPassword() {
        return encriptedPassword;
    }

    public  void setEncriptedPassword(String encriptedPassword) {
        this.encriptedPassword = encriptedPassword;
    }

    public  String getKeystorePassword() {
        return keystorePassword;
    }

    public  void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public  String getTrustorePassword() {
        return trustorePassword;
    }

    public  void setTrustorePassword(String trustorePassword) {
        this.trustorePassword = trustorePassword;
    }

    public  String getNeededProperties() {
        return neededProperties;
    }

    public  void setNeededProperties(String neededProperties) {
        this.neededProperties = neededProperties;
    }

    public  String getMqTransportProperty() {
        return mqTransportProperty;
    }

    public  void setMqTransportProperty(String mqTransportProperty) {
        this.mqTransportProperty = mqTransportProperty;
    }

    public  String getMqCipherSuite() {
        return mqCipherSuite;
    }

    public  void setMqCipherSuite(String mqCipherSuite) {
        this.mqCipherSuite = mqCipherSuite;
    }

    public  String getMqRequiredUser() {
        return mqRequiredUser;
    }

    public  void setMqRequiredUser(String mqRequiredUser) {
        this.mqRequiredUser = mqRequiredUser;
    }

    public  String getMqUser() {
        return mqUser;
    }

    public  void setMqUser(String mqUser) {
        this.mqUser = mqUser;
    }

    public  String getMqUserPassword() {
        return mqUserPassword;
    }

    public  void setMqUserPassword(String mqUserPassword) {
        this.mqUserPassword = mqUserPassword;
    }
}
