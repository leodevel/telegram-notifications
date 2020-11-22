package br.com.marino.monitorar.models;

import br.com.marino.monitorar.enums.TipoNotificacao;
import br.com.marino.monitorar.utils.Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TipoNotificacaoAlarme {
    
    public static final String PATH = "config";
    private TipoNotificacao tipoNotificacao;

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public static String getFile(Integer alarmeId){
        return PATH + "/tipo_notificacao_alarme_" + alarmeId + ".xml"; 
    }
    
    public static XStream getStream() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias(getAlias(), TipoNotificacaoAlarme.class);
        return xstream;
    }

    public static String getAlias() {
        return "tipo_notificacao_alarme";
    }

    public String toXML() {
        XStream xstream = getStream();
        String xml = xstream.toXML(this);
        return xml;
    }

    public static TipoNotificacaoAlarme getTipoNotificacaoAlarme(Integer alarmeId) {

        String file = getFile(alarmeId);
        
        Utils.createFileCofig(file, "<" + getAlias() + "/>");
        
        XStream stream = getStream();
        TipoNotificacaoAlarme obj = (TipoNotificacaoAlarme) stream.fromXML(Paths.get(file).toFile());

        return obj;

    }

    public static void update(TipoNotificacaoAlarme newObj, Integer alarmeId) throws IOException {

        String file = getFile(alarmeId);
        
        Utils.createFileCofig(file, "<" + getAlias() + "/>");
        
        String xml = newObj.toXML();

        Files.write(Paths.get(file), new ArrayList<>(Arrays.asList(xml)),
                StandardCharsets.UTF_8);

    }

}
