package br.com.marino.monitorar.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Grupo implements Entity {

    public static String FILE = "config/grupos.xml";

    private int id;
    private String nome;
    private String token;
    private String chatId;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
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
        xstream.alias(getAlias(), Grupo.class);
        xstream.useAttributeFor(Grupo.class, "id");
        return xstream;
    }

    @Override
    public String getAlias() {
        return "grupo";
    }

}