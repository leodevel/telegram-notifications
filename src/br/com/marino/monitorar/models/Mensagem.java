package br.com.marino.monitorar.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.List;

public class Mensagem implements Entity {
    
    public static String FILE = "config/mensagens.xml";
    
    private int id;
    
    private String nome;  
    private String mensagem;    
    private Integer intervalo;
    private String sql;
    
    private String primaryKey;
    
    private Boolean iniciarAutomatico;    
    private List<Integer> groupsAlarmes;        

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIniciarAutomatico() {
        return iniciarAutomatico;
    }

    public void setIniciarAutomatico(Boolean iniciarAutomatico) {
        this.iniciarAutomatico = iniciarAutomatico;
    }    

    public List<Integer> getGroupsAlarmes() {
        return groupsAlarmes;
    }

    public void setGroupsAlarmes(List<Integer> groupsAlarmes) {
        this.groupsAlarmes = groupsAlarmes;
    }    
    
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
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
        xstream.alias(getAlias(), Mensagem.class);
        xstream.useAttributeFor(Mensagem.class, "id");
        return xstream;
    }

    @Override
    public String getAlias() {
        return "alarme";
    }
    
}