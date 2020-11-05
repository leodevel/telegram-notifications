package br.com.marino.monitorar.models;

import br.com.marino.monitorar.enums.Ordem;
import br.com.marino.monitorar.enums.Tipo;
import br.com.marino.monitorar.enums.TipoConsulta;
import br.com.marino.monitorar.enums.TipoNotificacao;
import br.com.marino.monitorar.enums.TipoVariavel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.List;

public class Alarme implements Entity {
    
    public static String FILE = "config/alarmes.xml";
    
    private int id;
    
    private String nome;
    private String tabela;
    private String colunaOrdenar;
    private Ordem ordem;
    
    private String coluna;
    private TipoVariavel tipoVariavel;
    private Tipo tipo;
    private String valor;
    
    private String mensagem;
    private String mensagemSairDaRegra;
    
    private TipoConsulta tipoConsulta;
    private String sql;
    
    private Boolean iniciarAutomatico;
    
    private List<Integer> grupos;    
    
    private TipoNotificacao tipoNotificacao;
    
    private Integer intervalo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getColunaOrdenar() {
        return colunaOrdenar;
    }

    public void setColunaOrdenar(String colunaOrdenar) {
        this.colunaOrdenar = colunaOrdenar;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public TipoVariavel getTipoVariavel() {
        return tipoVariavel;
    }

    public void setTipoVariavel(TipoVariavel tipoVariavel) {
        this.tipoVariavel = tipoVariavel;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public String getMensagemSairDaRegra() {
        return mensagemSairDaRegra;
    }

    public void setMensagemSairDaRegra(String mensagemSairDaRegra) {
        this.mensagemSairDaRegra = mensagemSairDaRegra;
    }    

    public List<Integer> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Integer> grupos) {
        this.grupos = grupos;
    }    

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
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
        xstream.alias(getAlias(), Alarme.class);
        xstream.useAttributeFor(Alarme.class, "id");
        return xstream;
    }

    @Override
    public String getAlias() {
        return "alarme";
    }
    
}