package br.com.marino.monitorar;

import br.com.marino.monitorar.models.Alarme;
import br.com.marino.monitorar.modules.AlarmeModule;
import br.com.marino.monitorar.services.AlarmeService;
import br.com.marino.monitorar.services.ConexaoBDService;
import br.com.marino.monitorar.utils.ReadListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class JPAlarmeModulo extends javax.swing.JPanel implements ReadListener {

    private AlarmeModule alarmeService;
    private Alarme alarme;
    private final Inicio pai;

    public JPAlarmeModulo(Inicio pai, Alarme alarme) {
        initComponents();
        this.pai = pai;
        this.alarme = alarme;
        setDados();
        lbValorLido.setVisible(false);
        if (alarme.getIniciarAutomatico() != null && alarme.getIniciarAutomatico()) {
            modulo();
        }
    }

    private void setDados() {
        lbNomeAlarme.setText(alarme.getNome());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lbNomeAlarme = new javax.swing.JLabel();
        btIniciar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btLogs = new javax.swing.JButton();
        lbValorLido = new javax.swing.JLabel();

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marino/monitorar/imagens/cancelar.png"))); // NOI18N

        lbNomeAlarme.setText("MONITORAR VAISALA");

        btIniciar.setText("Iniciar");
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btLogs.setText("Logs");
        btLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbNomeAlarme, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btLogs)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNomeAlarme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btIniciar)
                        .addComponent(btEditar)
                        .addComponent(btExcluir)
                        .addComponent(btLogs))
                    .addComponent(lbIcon, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, 0))
        );

        lbValorLido.setText("<html><b>Valor lido: </b></html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbValorLido)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbValorLido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed

        int escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente "
                + "excluir ?", "Escolha", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (escolha != 0) {
            return;
        }

        try {

            AlarmeService.getInstance().delete(alarme);

            File log = Paths.get("logs/alarme_" + alarme.getId() + ".log").toFile();
            if (log.exists()) {
                log.delete();
            }

            pai.removerAlarme(alarme);

            JOptionPane.showMessageDialog(null, "Alarme excluído com sucesso!",
                    "Êxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btExcluirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed

        alarme = AlarmeService.getInstance().getById(alarme.getId());

        JDAlarme alarmScreen = new JDAlarme(
                pai,
                true,
                alarme);

        alarmScreen.setVisible(true);

        this.alarme = alarmScreen.getAlarm();

        setDados();

    }//GEN-LAST:event_btEditarActionPerformed

    private void btLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogsActionPerformed

        File log = Paths.get("logs/alarme_" + alarme.getId() + ".log").toFile();

        if (log.exists()) {

            try {
                Desktop.getDesktop().open(log);
            } catch (IOException ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo de log encontrado!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btLogsActionPerformed

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed

        modulo();

    }//GEN-LAST:event_btIniciarActionPerformed

    private void modulo() {

        if (btIniciar.getText().equals("Iniciar")) {

            try {

                alarmeService = new AlarmeModule(alarme, ConexaoBDService.getInstance().get(), this);
                alarmeService.start();

                btIniciar.setText("Parar");

                lbIcon.setIcon(new javax.swing.ImageIcon(getClass().
                        getResource("/br/com/marino/monitorar/imagens/testar.png")));

                btEditar.setEnabled(false);
                btExcluir.setEnabled(false);
                lbValorLido.setVisible(true);
                read("");

            } catch (Exception ex) {
            }

        } else {

            try {
                alarmeService.encerrar();
                alarmeService.interrupt();
                alarmeService.join();
            } catch (InterruptedException ex) {
            }

            alarmeService = null;
            btIniciar.setText("Iniciar");

            lbIcon.setIcon(new javax.swing.ImageIcon(getClass()
                    .getResource("/br/com/marino/monitorar/imagens/cancelar.png")));

            btEditar.setEnabled(true);
            btExcluir.setEnabled(true);
            lbValorLido.setVisible(false);
            read("");

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btIniciar;
    private javax.swing.JButton btLogs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbNomeAlarme;
    private javax.swing.JLabel lbValorLido;
    // End of variables declaration//GEN-END:variables

    public Alarme getAlarme() {
        return alarme;
    }

    public void setAlarme(Alarme alarme) {
        this.alarme = alarme;
    }

    @Override
    public void read(Object value) {
        lbValorLido.setText("<html><b>Valor lido: </b>" + value + "</html>");
    }

}
