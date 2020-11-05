package br.com.marino.monitorar.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD implements Entity {

    public static String FILE = "config/conexaobd.xml";

    private String host;
    private String usuario;
    private String senha;

    public ConexaoBD() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(host, usuario, senha);
    }

    /*public String toXML() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("conexaobd", ConexaoBD.class);
        String xml = xstream.toXML(this);
        return xml;
    }

    public static ConexaoBD getConexaoBD() {

        XStream stream = new XStream(new DomDriver());
        stream.alias("conexaobd", ConexaoBD.class);
        ConexaoBD obj = (ConexaoBD) stream.fromXML(Paths.get(FILE).toFile());

        return obj;

    }

    public static void update(ConexaoBD obj) throws Exception {

        String xml = obj.toXML();

        Files.write(Paths.get(FILE), new ArrayList<>(Arrays.asList(xml)),
                StandardCharsets.UTF_8);

    }*/

    @Override
    public String toXML() {
        XStream xstream = getStream();
        String xml = xstream.toXML(this);
        return xml;
    }

    @Override
    public String getFilename() {
        return FILE;
    }

    @Override
    public XStream getStream() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias(getAlias(), ConexaoBD.class);        
        return xstream;
    }

    @Override
    public String getAlias() {
        return "conexaobd";
    }
    
}
