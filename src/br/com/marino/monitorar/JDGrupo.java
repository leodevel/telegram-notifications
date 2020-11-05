package br.com.marino.monitorar;

import br.com.marino.monitorar.models.Chat;
import br.com.marino.monitorar.models.Grupo;
import br.com.marino.monitorar.services.GrupoService;
import br.com.marino.monitorar.services.TelegramService;
import br.com.marino.monitorar.utils.AddEditListener;
import br.com.marino.monitorar.utils.Utils;
import java.util.List;
import javax.swing.JOptionPane;

public class JDGrupo extends javax.swing.JDialog {

    private final int action;
    private Grupo grupo;
    private final AddEditListener addEditListener;

    public JDGrupo(java.awt.Frame parent, boolean modal, AddEditListener addEditListener) {
        super(parent, modal);

        initComponents();

        this.addEditListener = addEditListener;
        action = 1;

        setTitle("Novo grupo");

    }

    public JDGrupo(java.awt.Frame parent, boolean modal,
            AddEditListener addEditListener, Grupo grupo) {

        super(parent, modal);
        initComponents();

        this.grupo = grupo;
        this.addEditListener = addEditListener;
        action = 2;

        setTitle("Alterando grupo");

        setValuesFields();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtToken = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtChatId = new javax.swing.JTextField();
        lbTestarTelegram = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alarme");

        jLabel1.setText("Token (Bot):");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        jLabel10.setText("Nome:");

        jLabel2.setText("Chat Id (Grupo):");

        lbTestarTelegram.setForeground(new java.awt.Color(0, 51, 255));
        lbTestarTelegram.setText("ENVIAR MENSAGEM DE TESTE");
        lbTestarTelegram.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbTestarTelegram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTestarTelegramMouseClicked(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Buscar Grupos >");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setText("<html>Obs.: Ao clicar em 'Buscar Grupos', os grupos serão obtidos com base nas ultimas atualizações, ou seja, últimas conversas que ocorreram no grupo. Se o grupo não aparecer na lista então escreva alguma mensagem no grupo para que ele aparece na listagem.</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addComponent(txtToken)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbTestarTelegram)
                        .addGap(18, 18, 18)
                        .addComponent(btSalvar))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtChatId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtChatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar)
                    .addComponent(lbTestarTelegram))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        try {

            Grupo g = getObj();

            if (action == 1) {

                GrupoService.getInstance().insert(g);
                addEditListener.insert(g);

                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);
            } else {

                g.setId(this.grupo.getId());
                GrupoService.getInstance().update(grupo, g);
                addEditListener.update(grupo, g);

                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);

            }

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a ação. Erro: " + ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btSalvarActionPerformed

    private void lbTestarTelegramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTestarTelegramMouseClicked

        try {
            TelegramService.sendTestMessage(getObj());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao fazer o teste de envio",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_lbTestarTelegramMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        String token = txtToken.getText().trim();

        if (token == null || token.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o token para que se possa "
                    + "buscar por grupos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Chat> chats = TelegramService.getGruposByToken(token);

        if (chats != null) {

            Object[] opcoes = new Object[chats.size()];
            
            for (int i = 0; i < chats.size(); i++) {
                opcoes[i] = chats.get(i).getId()+ " | " + chats.get(i).getTitle();
            }
            
            Object res = JOptionPane.showInputDialog(null, "Escolha um grupo", "Grupos",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, "");

            if (res == null) {
                return;
            }
            
            txtChatId.setText(res.toString().substring(0, res.toString().indexOf("|")).trim());

        }

    }//GEN-LAST:event_jLabel3MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbTestarTelegram;
    private javax.swing.JTextField txtChatId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtToken;
    // End of variables declaration//GEN-END:variables

    private Grupo getObj() throws Exception {

        String nome = txtNome.getText().trim();
        String token = txtToken.getText().trim();
        String chatId = txtChatId.getText().trim();

        if (Utils.isNullOrEmpty(nome, token, chatId)){
            throw new Exception("Todos os campos são de preenchimento obrigatório!");
        }
        
        Grupo u = new Grupo();
        u.setNome(nome);
        u.setToken(token);
        u.setChatId(chatId);

        return u;

    }

    private void setValuesFields() {

        txtNome.setText(grupo.getNome());
        txtToken.setText(grupo.getToken());
        txtChatId.setText(grupo.getChatId());

    }

}
