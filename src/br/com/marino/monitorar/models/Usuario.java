package br.com.marino.monitorar.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Usuario implements Entity {

    public static String FILE = "config/usuarios.xml";

    private String nome;
    private String login;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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
        xstream.alias(getAlias(), Usuario.class);
        return xstream;
    }

    @Override
    public String getAlias() {
        return "usuario";
    }

}