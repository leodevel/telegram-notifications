package br.com.marino.monitorar.services;

import br.com.marino.monitorar.models.Chat;
import br.com.marino.monitorar.models.Grupo;
import static br.com.marino.monitorar.modules.TelegramModule.API;
import io.github.openunirest.http.HttpResponse;
import io.github.openunirest.http.JsonNode;
import io.github.openunirest.http.Unirest;
import io.github.openunirest.http.options.Options;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.swing.JOptionPane;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class TelegramService {
    
    
    public static void setConfigUnirest() {

        try {

            Options.enableCookieManagement(false);

            SSLContext sslcontext = SSLContexts.custom().
                    loadTrustMaterial(null, new TrustSelfSignedStrategy()).
                    build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

            Unirest.setTimeouts(3000, 1500);
            Unirest.setDefaultHeader("Accept", "application/json");
            Unirest.setHttpClient(httpclient);

        } catch (Exception ex) {
        }

    }

    public static List<Chat> getGruposByToken(String token){
        
        List<Chat> chats = new ArrayList<>();
        
        try{
            
            URIBuilder ub = new URIBuilder(API + "bot" + token + "/getUpdates");

            String url = ub.toString();

            setConfigUnirest();            

            HttpResponse<JsonNode> response = Unirest.get(url).asJson();

            if (response.getStatus() == 200 && response.getBody().getObject().getBoolean("ok")) {
                
                JSONArray result = response.getBody().getObject().getJSONArray("result");
                
                for (int i = 0; i < result.length(); i++) {
                    
                    JSONObject chatJson = result.getJSONObject(i).getJSONObject("message").getJSONObject("chat");                    
                    
                    Chat chat = new Chat();
                    chat.setId(String.valueOf(chatJson.getLong("id")));
                    chat.setTitle(chatJson.getString("title"));
                    chat.setType(chatJson.getString("type"));
                    
                    if (!chats.stream().anyMatch(obj -> obj.getId().equalsIgnoreCase(chat.getId()))){
                        chats.add(chat);
                    }
                    
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao tentar buscar os grupos do usuário",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            
            
        }catch (Exception ex){
        }
        
        return chats;
        
    }
    
    public static void sendTestMessage(Grupo grupoAlarme) {

        try {

            URIBuilder ub = new URIBuilder(API + "bot" + grupoAlarme.getToken() + "/sendMessage");
            ub.addParameter("chat_id", grupoAlarme.getChatId());
            ub.addParameter("text", "Testando o envio de mensagem - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            String url = ub.toString();

            setConfigUnirest();

            HttpResponse<JsonNode> response = Unirest.get(url).asJson();

            if (response.getStatus() == 200 && response.getBody().getObject().getBoolean("ok")) {
                JOptionPane.showMessageDialog(null, "Teste de envio enviado com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao fazer o teste de envio",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao fazer o teste de envio",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }
    
}
