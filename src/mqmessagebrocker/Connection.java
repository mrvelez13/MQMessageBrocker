/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqmessagebrocker;

/**
 *
 * @author jonathan.velez
 */
public class Connection
{    
    private String connectionName = "";
    private String hostName = "";
    private String mqConnectionType = "";
    private String mqManager = "";
    private String mqHost = "";
    private String mqPort = "";
    private String mqSecurePort= "";
    private String mqChannel = "";
    private String mqSecureChannel = "";
    private String mqManagerUser = "";
    private String mqManagerPassword = "";   
    
    private String mqEnableSSLConn = "";
    private String mqPathSSLFiles = "";
    private String mqFileNameTruststore = "";
    private String mqFileNameKeystore = "";
    private String sslFilesPath = "";
    
    
    private String mqIsNeededSSLPassword = "";
    private String mqPassTrustStore = "";
    private String mqPassKeyStore = "";
    private String mqIsSSLEncriptedPassword = "";
    private String mqIsNeededSSLUser = "";
    private String sslCipherProperty = "";

    public String getSslFilesPath() {
        return sslFilesPath;
    }

    public void setSslFilesPath(String sslFilesPath) {
        this.sslFilesPath = sslFilesPath;
    }

    
    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    
    public String getMqConnectionType() {
        return mqConnectionType;
    }

    public void setMqConnectionType(String mqConnectionType) {
        this.mqConnectionType = mqConnectionType;
    }

    public String getMqManager() {
        return mqManager;
    }

    public void setMqManager(String mqManager) {
        this.mqManager = mqManager;
    }

    public String getMqHost() {
        return mqHost;
    }

    public void setMqHost(String mqHost) {
        this.mqHost = mqHost;
    }

    public String getMqPort() {
        return mqPort;
    }

    public void setMqPort(String mqPort) {
        this.mqPort = mqPort;
    }

    public String getMqSecurePort() {
        return mqSecurePort;
    }

    public void setMqSecurePort(String mqSecurePort) {
        this.mqSecurePort = mqSecurePort;
    }

    public String getMqChannel() {
        return mqChannel;
    }

    public void setMqChannel(String mqChannel) {
        this.mqChannel = mqChannel;
    }

    public String getMqSecureChannel() {
        return mqSecureChannel;
    }

    public void setMqSecureChannel(String mqSecureChannel) {
        this.mqSecureChannel = mqSecureChannel;
    }

    public String getMqManagerUser() {
        return mqManagerUser;
    }

    public void setMqManagerUser(String mqManagerUser) {
        this.mqManagerUser = mqManagerUser;
    }

    public String getMqManagerPassword() {
        return mqManagerPassword;
    }

    public void setMqManagerPassword(String mqManagerPassword) {
        this.mqManagerPassword = mqManagerPassword;
    }

    public String getMqEnableSSLConn() {
        return mqEnableSSLConn;
    }

    public void setMqEnableSSLConn(String mqEnableSSLConn) {
        this.mqEnableSSLConn = mqEnableSSLConn;
    }

    public String getMqPathSSLFiles() {
        return mqPathSSLFiles;
    }

    public void setMqPathSSLFiles(String mqPathSSLFiles) {
        this.mqPathSSLFiles = mqPathSSLFiles;
    }

    public String getMqFileNameTruststore() {
        return mqFileNameTruststore;
    }

    public void setMqFileNameTruststore(String mqFileNameTruststore) {
        this.mqFileNameTruststore = mqFileNameTruststore;
    }

    public String getMqFileNameKeystore() {
        return mqFileNameKeystore;
    }

    public void setMqFileNameKeystore(String mqFileNameKeystore) {
        this.mqFileNameKeystore = mqFileNameKeystore;
    }

    public String getMqIsNeededSSLPassword() {
        return mqIsNeededSSLPassword;
    }

    public void setMqIsNeededSSLPassword(String mqIsNeededSSLPassword) {
        this.mqIsNeededSSLPassword = mqIsNeededSSLPassword;
    }

    public String getMqPassTrustStore() {
        return mqPassTrustStore;
    }

    public void setMqPassTrustStore(String mqPassTrustStore) {
        this.mqPassTrustStore = mqPassTrustStore;
    }

    public String getMqPassKeyStore() {
        return mqPassKeyStore;
    }

    public void setMqPassKeyStore(String mqPassKeyStore) {
        this.mqPassKeyStore = mqPassKeyStore;
    }

    public String getMqIsSSLEncriptedPassword() {
        return mqIsSSLEncriptedPassword;
    }

    public void setMqIsSSLEncriptedPassword(String mqIsSSLEncriptedPassword) {
        this.mqIsSSLEncriptedPassword = mqIsSSLEncriptedPassword;
    }

    public String getMqIsNeededSSLUser() {
        return mqIsNeededSSLUser;
    }

    public void setMqIsNeededSSLUser(String mqIsNeededSSLUser) {
        this.mqIsNeededSSLUser = mqIsNeededSSLUser;
    }

    public String getSslCipherProperty() {
        return sslCipherProperty;
    }

    public void setSslCipherProperty(String sslCipherProperty) {
        this.sslCipherProperty = sslCipherProperty;
    }
    
    @Override
    public String toString()
    {
        return connectionName;
    }
}
