package br.com.marino.monitorar;

import br.com.marino.monitorar.enums.Ordem;
import br.com.marino.monitorar.enums.Tipo;
import br.com.marino.monitorar.enums.TipoConsulta;
import br.com.marino.monitorar.enums.TipoVariavel;
import br.com.marino.monitorar.models.Alarme;
import br.com.marino.monitorar.models.Grupo;
import br.com.marino.monitorar.models.Mensagem;
import br.com.marino.monitorar.services.GrupoService;
import br.com.marino.monitorar.services.MensagemService;
import br.com.marino.monitorar.utils.Utils;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class JDMensagem extends javax.swing.JDialog {

    private int action;
    private Mensagem alarm;
    private List<Grupo> grupoAlarmes;
    private final DefaultListModel model;

    public JDMensagem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        action = 1;

        setTitle("Nova mensagem");

        model = new DefaultListModel();
        jlLayers.setModel(model);

        carregarDados();

    }

    public JDMensagem(java.awt.Frame parent, boolean modal,
            Mensagem alarme) {
        super(parent, modal);
        initComponents();

        this.alarm = alarme;

        action = 2;

        setTitle("Alterando cadastro");

//        setValuesFields();

        model = new DefaultListModel();
        jlLayers.setModel(model);

        carregarDados();

    }

    private void carregarDados() {

        try {

            grupoAlarmes = GrupoService.getInstance().getAll();

            model.clear();

            for (Grupo grupoAlarme : grupoAlarmes) {
                model.addElement(grupoAlarme.getNome());
            }

            ArrayList<Integer> indices = new ArrayList<>();

            if (alarm.getGroupsAlarmes() == null || alarm.getGroupsAlarmes().isEmpty()) {
                return;
            }

            for (Integer grupoAlarmeId : alarm.getGroupsAlarmes()) {
                for (int i = 0; i < grupoAlarmes.size(); i++) {
                    if (grupoAlarmeId == grupoAlarmes.get(i).getId()) {
                        indices.add(i);
                    }
                }
            }

            int[] indicesInt = new int[indices.size()];
            for (int i = 0; i < indicesInt.length; i++) {
                indicesInt[i] = indices.get(i);
            }

            jlLayers.setSelectedIndices(indicesInt);

        } catch (Exception ex) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        chkIniciarAutomaticamente = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlLayers = new javax.swing.JList<>();
        jLabel22 = new javax.swing.JLabel();
        txtIntervalo = new javax.swing.JSpinner();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSql = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alarme");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Nome:");

        chkIniciarAutomaticamente.setText("Iniciar automaticamente");

        jLabel8.setText("Grupos de alarmes:");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane3.setViewportView(jlLayers);

        jLabel22.setText("Intervalo (s):");

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSql.setColumns(20);
        txtSql.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtSql.setLineWrap(true);
        txtSql.setRows(5);
        jScrollPane4.setViewportView(txtSql);

        jLabel9.setText("SQL:");

        jLabel11.setText("Mensagem:");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMensagem.setColumns(20);
        txtMensagem.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtMensagem.setLineWrap(true);
        txtMensagem.setRows(5);
        jScrollPane1.setViewportView(txtMensagem);

        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("<html>Obs.: A SQL precisa ter uma coluna com o nome primary_key. Essa coluna será usada para controle para que a marcação não seja enviada de forma duplicada.</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIntervalo, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(chkIniciarAutomaticamente))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkIniciarAutomaticamente)
                        .addComponent(jLabel22)
                        .addComponent(txtIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            //Mensagem u = getObj();
            Mensagem u = null;

            if (action == 1) {

                MensagemService.getInstance().insert(u);

                File log = Paths.get("logs/mensagem" + u.getId() + ".log").toFile();
                if (log.exists()) {
                    log.delete();
                }
                log.createNewFile();

                this.alarm = u;

                JOptionPane.showMessageDialog(null, "Mensagem cadastrado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);
            } else {

                u.setId(alarm.getId());
                u.setPrimaryKey(alarm.getPrimaryKey());
                MensagemService.getInstance().update(alarm, u);

                this.alarm = u;

                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);

            }

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a ação. Erro: " + ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkIniciarAutomaticamente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> jlLayers;
    private javax.swing.JSpinner txtIntervalo;
    private javax.swing.JTextArea txtMensagem;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtSql;
    // End of variables declaration//GEN-END:variables

//    public int getAction() {
//        return action;
//    }
//
//    public void setAction(int action) {
//        this.action = action;
//    }
//
//    public Mensagem getAlarm() {
//        return alarm;
//    }
//
//    public void setAlarm(Mensagem alarm) {
//        this.alarm = alarm;
//    }
//
//    private Mensagem getObj() throws Exception {
//
//        String nome = txtNome.getText().trim();
//        String tabela = txtTabela.getText().trim();
//        boolean iniciarAutomaticamente = chkIniciarAutomaticamente.isSelected();
//
//        String colunaOrdenar = txtColunaOrdenar.getText().trim();
//        Ordem ordem = Ordem.valueOf(cbOrdem.getSelectedItem().toString());
//
//        String coluna = txtColuna.getText().trim();
//        TipoVariavel tipoVariavel = TipoVariavel.valueOf(cbTipoVariavel.getSelectedItem().toString());
//        Tipo tipo = Tipo.valueOf(cbTipo.getSelectedItem().toString());
//        String valor = txtValor.getText().trim();
//
//        String mensagem = txtMensagem.getText().trim();
//        String mensagemSaiuDaRegra = txtMensagemSairDaRegra.getText().trim();
//
//        List<Integer> gruposAlarmesId = new ArrayList<>();
//        for (Integer index : jlLayers.getSelectedIndices()) {
//            gruposAlarmesId.add(grupoAlarmes.get(index).getId());
//        }
//
//        String sql = txtSql.getText().trim();
//        TipoConsulta tipoConsulta = TipoConsulta.valueOf(cbTipoConsulta.getSelectedItem().toString());
//
//        int intervalo = (int) txtIntervalo.getValue();
//        
//        if (tipoConsulta == TipoConsulta.MANUAL) {
//            if (Utils.isNullOrEmpty(nome, tabela, colunaOrdenar, ordem, coluna, tipoVariavel,
//                    valor, mensagem, mensagemSaiuDaRegra) || gruposAlarmesId.isEmpty()) {
//                throw new Exception("Todos os campos são de preenchimento obrigatório!");
//            }
//        } else {
//            if (Utils.isNullOrEmpty(nome, tabela, sql, mensagem, mensagemSaiuDaRegra) || gruposAlarmesId.isEmpty()) {
//                throw new Exception("Todos os campos são de preenchimento obrigatório!");
//            }
//        }
//
//        Alarme alarme = new Alarme();
//
//        alarme.setNome(nome);
//        alarme.setTabela(tabela);
//        alarme.setIniciarAutomatico(iniciarAutomaticamente);
//
//        alarme.setColunaOrdenar(colunaOrdenar);
//        alarme.setOrdem(ordem);
//
//        alarme.setColuna(coluna);
//        alarme.setTipoVariavel(tipoVariavel);
//        alarme.setTipo(tipo);
//        alarme.setValor(valor);
//
//        alarme.setTipoConsulta(tipoConsulta);
//        alarme.setSql(sql);
//
//        alarme.setMensagem(mensagem);
//        alarme.setMensagemSairDaRegra(mensagemSaiuDaRegra);
//
//        alarme.setGroupsAlarmes(gruposAlarmesId);
//
//        alarme.setIntervalo(intervalo);
//        
//        return alarme;
//
//    }
//
//    private void setValuesFields() {
//
//        txtNome.setText(alarm.getNome());
//        txtTabela.setText(alarm.getTabela());
//        chkIniciarAutomaticamente.setSelected(alarm.getIniciarAutomatico() == null ? false : alarm.getIniciarAutomatico());
//
//        txtColunaOrdenar.setText(alarm.getColunaOrdenar());
//        cbOrdem.setSelectedItem(alarm.getOrdem().toString());
//
//        txtColuna.setText(alarm.getColuna());
//        cbTipoVariavel.setSelectedItem(alarm.getTipoVariavel().toString());
//        cbTipo.setSelectedItem(alarm.getTipo().toString());
//        txtValor.setText(alarm.getValor());
//
//        if (alarm.getTipoConsulta() != null) {
//            cbTipoConsulta.setSelectedItem(alarm.getTipoConsulta().toString());
//        }
//        txtSql.setText(alarm.getSql());
//
//        txtMensagem.setText(alarm.getMensagem());
//        txtMensagemSairDaRegra.setText(alarm.getMensagemSairDaRegra());
//
//        if (alarm.getIntervalo() != null){
//            txtIntervalo.setValue(alarm.getIntervalo());
//        }
//        
//    }

}
