package ru.abr.dit.DAO;

public class UPGDAO {

    private String host;

    private String port;

    private String login;

    private String pass;

    private String protocol;

    private String path;

    private String namespaceURI;

    private String namespace;

    private String servicename;

    private String sourceURI;

    public UPGDAO(String host, String port, String login, String pass, String protocol, String path, String namespaceURI, String namespace, String servicename) {
        this.host = host;
        this.port = port;
        this.login = login;
        this.pass = pass;
        this.protocol = protocol;
        this.path = path;
        this.namespaceURI = namespaceURI;
        this.namespace = namespace;
        this.servicename = servicename;
        this.sourceURI  = protocol + host + ":" + port + path + "/" + servicename;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getSourceURI() {
        return sourceURI;
    }

    public void setSourceURI(String sourceURI) {
        this.sourceURI = sourceURI;
    }
}
