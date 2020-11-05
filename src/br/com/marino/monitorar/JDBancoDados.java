package br.com.marino.monitorar;

import br.com.marino.monitorar.models.ConexaoBD;
import br.com.marino.monitorar.services.ConexaoBDService;
import br.com.marino.monitorar.utils.Utils;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class JDBancoDados extends javax.swing.JDialog {

    public JDBancoDados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarDadosConexao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtHostBd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSenhaBd = new javax.swing.JTextField();
        lbTestarConexao = new javax.swing.JLabel();
        txtUsuarioBd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Banco de dados");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Host:");

        jLabel3.setText("Senha:");

        lbTestarConexao.setForeground(new java.awt.Color(0, 51, 255));
        lbTestarConexao.setText("TESTAR CONEXÃO");
        lbTestarConexao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbTestarConexao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTestarConexaoMouseClicked(evt);
            }
        });

        jLabel2.setText("Usuário:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTestarConexao)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsuarioBd, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSenhaBd, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                            .addComponent(txtHostBd))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHostBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuarioBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSenhaBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTestarConexao)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            ConexaoBD u = getObjConexaoBd();
            ConexaoBDService.getInstance().update(u);

            JOptionPane.showMessageDialog(null, "Dados de conexão atualizado com sucesso!",
                    "Êxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a ação. Erro: " + ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void lbTestarConexaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTestarConexaoMouseClicked
        try {
            Connection con = getObjConexaoBd().getConnection();
            con.close();
            JOptionPane.showMessageDialog(null, "Conexão bem sucedida!", "Êxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lbTestarConexaoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbTestarConexao;
    private javax.swing.JTextField txtHostBd;
    private javax.swing.JTextField txtSenhaBd;
    private javax.swing.JTextField txtUsuarioBd;
    // End of variables declaration//GEN-END:variables

    private ConexaoBD getObjConexaoBd() throws Exception {

        String host = txtHostBd.getText().trim();
        String usuario = txtUsuarioBd.getText().trim();
        String senha = txtSenhaBd.getText().trim();

        if (Utils.isNullOrEmpty(host, usuario, senha)) {
            throw new Exception("Todos os campos são de preenchimento obrigatório!");
        }

        ConexaoBD conexaoBD = new ConexaoBD();
        conexaoBD.setHost(host);
        conexaoBD.setUsuario(usuario);
        conexaoBD.setSenha(senha);

        return conexaoBD;

    }

    private void carregarDadosConexao() {

        try {
            ConexaoBD conexaoBD = ConexaoBDService.getInstance().get();
            txtHostBd.setText(conexaoBD.getHost());
            txtUsuarioBd.setText(conexaoBD.getUsuario());
            txtSenhaBd.setText(conexaoBD.getSenha());
        } catch (Exception ex) {
        }

    }

}
