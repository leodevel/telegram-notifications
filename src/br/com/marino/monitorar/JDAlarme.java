package br.com.marino.monitorar;

import br.com.marino.monitorar.enums.Ordem;
import br.com.marino.monitorar.enums.Tipo;
import br.com.marino.monitorar.enums.TipoConsulta;
import br.com.marino.monitorar.enums.TipoVariavel;
import br.com.marino.monitorar.models.Alarme;
import br.com.marino.monitorar.models.Grupo;
import br.com.marino.monitorar.services.AlarmeService;
import br.com.marino.monitorar.services.GrupoService;
import br.com.marino.monitorar.utils.Utils;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class JDAlarme extends javax.swing.JDialog {

    private int action;
    private Alarme alarm;
    private List<Grupo> grupos;
    private final DefaultListModel model;

    public JDAlarme(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        action = 1;

        setTitle("Novo alarme");

        model = new DefaultListModel();
        jlLayers.setModel(model);

        carregarDados();

    }

    public JDAlarme(java.awt.Frame parent, boolean modal,
            Alarme alarme) {
        super(parent, modal);
        initComponents();

        this.alarm = alarme;

        action = 2;

        setTitle("Alterando cadastro");

        setValuesFields();

        model = new DefaultListModel();
        jlLayers.setModel(model);

        carregarDados();

    }

    private void carregarDados() {

        try {

            grupos = GrupoService.getInstance().getAll();

            model.clear();

            for (Grupo grupoAlarme : grupos) {
                model.addElement(grupoAlarme.getNome());
            }

            ArrayList<Integer> indices = new ArrayList<>();

            if (alarm.getGrupos() == null || alarm.getGrupos().isEmpty()) {
                return;
            }

            for (Integer grupoAlarmeId : alarm.getGrupos()) {
                for (int i = 0; i < grupos.size(); i++) {
                    if (grupoAlarmeId == grupos.get(i).getId()) {
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
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensagemSairDaRegra = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlLayers = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtColuna = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbTipoVariavel = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSql = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtColunaOrdenar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbOrdem = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cbTipoConsulta = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTabela = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtIntervalo = new javax.swing.JSpinner();

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensagem"));

        jLabel15.setForeground(new java.awt.Color(0, 51, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Mensagem de SUCESSO que será enviada");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMensagemSairDaRegra.setColumns(20);
        txtMensagemSairDaRegra.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtMensagemSairDaRegra.setLineWrap(true);
        txtMensagemSairDaRegra.setRows(5);
        jScrollPane2.setViewportView(txtMensagemSairDaRegra);

        jLabel14.setForeground(new java.awt.Color(0, 51, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Mensagem de FALHA que será enviada");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMensagem.setColumns(20);
        txtMensagem.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtMensagem.setLineWrap(true);
        txtMensagem.setRows(5);
        jScrollPane1.setViewportView(txtMensagem);

        jLabel13.setText("Mensagem:");

        jLabel11.setText("Mensagem:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane2))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        jLabel8.setText("Grupos de alarmes:");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane3.setViewportView(jlLayers);

        jLabel16.setForeground(new java.awt.Color(0, 51, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Obs.: Pressione CTRL + Clique sobre o grupo para selecionar varios grupos");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Condição / SQL"));

        jLabel5.setText("Coluna:");

        jLabel6.setText("Tipo:");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAIOR", "MAIOR_OU_IGUAL", "MENOR", "MENOR_OU_IGUAL", "IGUAL" }));

        jLabel7.setText("Valor:");

        txtValor.setText("now-5");

        jLabel12.setForeground(new java.awt.Color(0, 51, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Ex.: now-5m (data/hora atual menos 5 minutos)");

        cbTipoVariavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DATA_HORA", "NUMERO", "TEXTO" }));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Condição");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbTipoVariavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtValor)))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtColuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoVariavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("SQL:");

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSql.setColumns(20);
        txtSql.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtSql.setLineWrap(true);
        txtSql.setRows(5);
        jScrollPane4.setViewportView(txtSql);

        jLabel20.setForeground(new java.awt.Color(0, 51, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("<html>Obs.: Se a SQL obtiver resultados, então a mensagem de falha será encaminhada. Caso contrário, a mensagem de sucesso será enviada.</html>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setText("Coluna:");

        jLabel3.setText("Ordem:");

        cbOrdem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASC", "DESC" }));

        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Obs.: Será pego o primeiro registro encontrado");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Ordenar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtColunaOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel17)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtColunaOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, 0))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MANUAL", "SCRIPT_SQL" }));

        jLabel19.setText("Tipo de consulta:");

        jLabel21.setForeground(new java.awt.Color(0, 153, 0));
        jLabel21.setText("Gerar SQL a partir da definição abaixo");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        jLabel1.setText("Tabela:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(cbTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jLabel22.setText("Intervalo (s):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIntervalo)
                        .addGap(18, 18, 18)
                        .addComponent(chkIniciarAutomaticamente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkIniciarAutomaticamente)
                        .addComponent(jLabel22)
                        .addComponent(txtIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel16)
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            Alarme u = getObj();

            if (action == 1) {

                AlarmeService.getInstance().insert(u);

                File log = Paths.get("logs/alarme_" + u.getId() + ".log").toFile();
                if (log.exists()) {
                    log.delete();
                }
                log.createNewFile();

                this.alarm = u;

                JOptionPane.showMessageDialog(null, "Alarme cadastrado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);
            } else {

                u.setId(alarm.getId());
                AlarmeService.getInstance().update(alarm, u);

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

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked

        try {

            txtSql.setText(AlarmeService.getInstance().getSql(getObj(), TipoConsulta.MANUAL));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a ação. Erro: " + ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jLabel21MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbOrdem;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cbTipoConsulta;
    private javax.swing.JComboBox<String> cbTipoVariavel;
    private javax.swing.JCheckBox chkIniciarAutomaticamente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> jlLayers;
    private javax.swing.JTextField txtColuna;
    private javax.swing.JTextField txtColunaOrdenar;
    private javax.swing.JSpinner txtIntervalo;
    private javax.swing.JTextArea txtMensagem;
    private javax.swing.JTextArea txtMensagemSairDaRegra;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtSql;
    private javax.swing.JTextField txtTabela;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Alarme getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarme alarm) {
        this.alarm = alarm;
    }

    private Alarme getObj() throws Exception {

        String nome = txtNome.getText().trim();
        String tabela = txtTabela.getText().trim();
        boolean iniciarAutomaticamente = chkIniciarAutomaticamente.isSelected();

        String colunaOrdenar = txtColunaOrdenar.getText().trim();
        Ordem ordem = Ordem.valueOf(cbOrdem.getSelectedItem().toString());

        String coluna = txtColuna.getText().trim();
        TipoVariavel tipoVariavel = TipoVariavel.valueOf(cbTipoVariavel.getSelectedItem().toString());
        Tipo tipo = Tipo.valueOf(cbTipo.getSelectedItem().toString());
        String valor = txtValor.getText().trim();

        String mensagem = txtMensagem.getText().trim();
        String mensagemSaiuDaRegra = txtMensagemSairDaRegra.getText().trim();

        List<Integer> gruposIds = new ArrayList<>();
        for (Integer index : jlLayers.getSelectedIndices()) {
            gruposIds.add(grupos.get(index).getId());
        }

        String sql = txtSql.getText().trim();
        TipoConsulta tipoConsulta = TipoConsulta.valueOf(cbTipoConsulta.getSelectedItem().toString());

        int intervalo = (int) txtIntervalo.getValue();
        
        if (tipoConsulta == TipoConsulta.MANUAL) {
            if (Utils.isNullOrEmpty(nome, tabela, colunaOrdenar, ordem, coluna, tipoVariavel,
                    valor, mensagem, mensagemSaiuDaRegra) || gruposIds.isEmpty()) {
                throw new Exception("Todos os campos são de preenchimento obrigatório!");
            }
        } else {
            if (Utils.isNullOrEmpty(nome, tabela, sql, mensagem, mensagemSaiuDaRegra) || gruposIds.isEmpty()) {
                throw new Exception("Todos os campos são de preenchimento obrigatório!");
            }
        }

        Alarme alarme = new Alarme();

        alarme.setNome(nome);
        alarme.setTabela(tabela);
        alarme.setIniciarAutomatico(iniciarAutomaticamente);

        alarme.setColunaOrdenar(colunaOrdenar);
        alarme.setOrdem(ordem);

        alarme.setColuna(coluna);
        alarme.setTipoVariavel(tipoVariavel);
        alarme.setTipo(tipo);
        alarme.setValor(valor);

        alarme.setTipoConsulta(tipoConsulta);
        alarme.setSql(sql);

        alarme.setMensagem(mensagem);
        alarme.setMensagemSairDaRegra(mensagemSaiuDaRegra);

        alarme.setGrupos(gruposIds);

        alarme.setIntervalo(intervalo);
        
        return alarme;

    }

    private void setValuesFields() {

        txtNome.setText(alarm.getNome());
        txtTabela.setText(alarm.getTabela());
        chkIniciarAutomaticamente.setSelected(alarm.getIniciarAutomatico() == null ? false : alarm.getIniciarAutomatico());

        txtColunaOrdenar.setText(alarm.getColunaOrdenar());
        cbOrdem.setSelectedItem(alarm.getOrdem().toString());

        txtColuna.setText(alarm.getColuna());
        cbTipoVariavel.setSelectedItem(alarm.getTipoVariavel().toString());
        cbTipo.setSelectedItem(alarm.getTipo().toString());
        txtValor.setText(alarm.getValor());

        if (alarm.getTipoConsulta() != null) {
            cbTipoConsulta.setSelectedItem(alarm.getTipoConsulta().toString());
        }
        txtSql.setText(alarm.getSql());

        txtMensagem.setText(alarm.getMensagem());
        txtMensagemSairDaRegra.setText(alarm.getMensagemSairDaRegra());

        if (alarm.getIntervalo() != null){
            txtIntervalo.setValue(alarm.getIntervalo());
        }
        
    }

}
