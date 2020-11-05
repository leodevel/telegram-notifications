package br.com.marino.monitorar.models;

import br.com.marino.monitorar.enums.TipoNotificacao;
import java.util.List;

public class SendTelegram {
    
    private Alarme alarme;
    private TipoNotificacao tipoNotificacao;
    private List<Grupo> grupos;

    public SendTelegram(Alarme alarme, TipoNotificacao tipoNotificacao, 
            List<Grupo> grupos) {
        this.alarme = alarme;
        this.tipoNotificacao = tipoNotificacao;
        this.grupos = grupos;
    }

    public SendTelegram() {
    }
    
    public Alarme getAlarme() {
        return alarme;
    }

    public void setAlarme(Alarme alarme) {
        this.alarme = alarme;
    }

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    } 

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }    
    
}
