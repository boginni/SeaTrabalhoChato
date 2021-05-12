/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack;

import body.mainScreenPack.Ults.ColorFade;
import body.mainScreenPack.Ults.TableController;
import body.mainScreenPack.Ults.TableModelCell;
import body.mainScreenPack.Ults.TableModelColumProp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author boginni
 */
public class TableHandlerPanel extends javax.swing.JPanel{

    //<editor-fold desc="Table Moddel">
    Object[][] tableValues = {
        {"default", "main", "table", "values"}
    };

    String[] tableHeader = {
        "default", "main", "table", "header"
    };

    boolean[] tableEditables = {
        true, false, false, true
    };
    
    

    public TableModel tableModel = new DefaultTableModel(tableValues, tableHeader);

    void initTableModdel() {
        tableModel = new DefaultTableModel(tableValues, tableHeader);
        tableMain.setModel(tableModel);

        if (tableMain.getPreferredSize().width < tableMain.getParent().getWidth()) {
            tableMain.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        } else {
            tableMain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }

        TableController.boundTable(tableModel, tableHeader);
    }

    //</editor-fold>

    /**
     * Creates new form TableHandlerPanel
     */
    public TableHandlerPanel() {
        initComponents();
        initTableModdel();

        //dummyTable();
        tableMain.getTableHeader().setReorderingAllowed(false);

        tableMain.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int frist = tableMain.getSelectedColumn();
                int count = tableMain.getSelectedColumnCount();
                columCurrentSelected = frist;
                TableModelColumProp prop = (count == 1 && frist >= 0)?columProps[frist]:null;
                setCurrentProp(prop);

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        tableMain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int frist = tableMain.getSelectedColumn();
                int count = tableMain.getSelectedColumnCount();
                columCurrentSelected = frist;
                TableModelColumProp prop = (count == 1 && frist >= 0)?columProps[frist]:null;
                setCurrentProp(prop);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        new Timer().schedule(ColorFade.colorFadeTick, 100l, (long) (1000.0 / 60.0));
    }





    // <editor-fold defaultstate="collapsed" desc="Dummy Table">
    public void dummyTable() {
        txtHeaders.setText("Fid	C�digo	Nome	Telefone	Documento	Contrato	Ponto de Acesso	Endere�o	X (Longitude)	Y (Latitude)	Bairro	CEP	E-mail	N�mero Porta	Status	Dedicado	Tipo	Splitter/Porta	Tipo de Tecnologia	Comp. Fibra (m)	Sinal (dBm)	Plano	Data de Ativa��o do Plano	Data de Cancelamento do Plano	Vendedor	Esta��o	VLAN	OLT	Porta OLT	�rea OLT	C�digo ONU	MAC ONU	Contido em Mancha de Atendimento	Proveniente de integra��o");
        txtValues.setText("1761594	45926	Eraldo Rodrigo Travassos Chagas	(91) 98105-0502	00607212276	44443	CTO: CTO_PA_SMG_PPS_B14c4	R. Agostinho Siqueira - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4766023357945	-1,6126234265432	PERPETUO SOCORRO	68660000	eraldotec1@gmail.com	494	Inativo	N�o	Residencial	1x16/6		100		PLANO 100 MBPS	17/2/2021		WALFREDO FERREIRA MACEDO								N�o	N�o			\n"
                + "1761618	45934	Mizael Jose Oliveira De Lima	(91) 98957-3979	02731256214	44454	CTO: CTO_PA_SMG_PPS_A8d1	R. RAIMUNDO CARVALHO 	-47,4791261067796	-1,6038967161848	PADRE ANGELO MARIA	68660000	mizael.lima22@outlook.com	137	Inativo	N�o	Residencial			30		PLANO 200 MBPS	17/2/2021		WALFREDO FERREIRA MACEDO								N�o	N�o			\n"
                + "1761682		Cliente Test				CTO: CTO_PA_SMG_PPS_B12a2	R. Agostinho Siqueira - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4747277081166	-1,6110627600638		68660000		717	Inativo	N�o	Residencial																N�o	N�o			\n"
                + "1761452	45929	Divaldo Carvalho Pinto	(91) 99206-7088	24941689291	44447	CTO: CTO_PA_SMG_B4b4	R. Pio XII, 123, S�o Miguel do Guam� - PA, 68660-000, Brasil	-47,4767361820046	-1,6218272422184	Patauateua	68660000		123	Inativo	N�o	Residencial	1x16/5							JOSE FERNANDO DA SILVA TRINDADE								N�o	N�o			\n"
                + "1761448	45919	Joelson Da Silva Barbosa	(91985768184	81292872268	44436	CTO: CTO_PA_SMG_B3b3	R. Liberdade - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4783347055229	-1,6243345943440	PATAUATEUA	68660000	berna.vieras@gmail.com	593	Inativo	N�o	Residencial			20		PLANO 200 MBPS	17/2/2021		RODRIGO BRASIL DE SOUSA								N�o	N�o			\n"
                + "1761322	45898	Josiane Oliveira Dos Santos	(91) 99350-5497	02374785289	44414	CTO: CTO_PA_SMG_B5c4	R. Cipriano Mendes Rodrigues , 57 , S�o Miguel do Guam� - PA, 68660-000, Brasil	-47,4723154068036	-1,6194957877775	Patauateua	68660000	josyolliver03@gmail.com	57	Inativo	N�o	Residencial	1x16/3		100		PLANO 100 MBPS	17/2/2021		JOSE FERNANDO DA SILVA TRINDADE								N�o	N�o			\n"
                + "1761621		Jose Odivaldo Do Nascimento Oliveira	(91) 98032-5058	48769312249		CTO: CTO_PA_SMG_A2c4	Passagem  S�o Francisco , 217 , S�o Miguel do Guam� - PA, 68660-000,	-47,4829161108830	-1,6271840629258	Vila sorriso	68660000		217	Ativo	N�o	Residencial	1x16/2	Fibra �ptica						JOSE FERNANDO DA SILVA TRINDADE	POP S�O MIGUEL		L16	2				N�o	N�o			\n"
                + "1761623	45954	Jose Odivaldo Do Nascimento Oliveira	(91) 98032-5058	48769312249	44471		Passagem  S�o Francisco , 217 , S�o Miguel do Guam� - PA, 68660-000,	-47,4829161108830	-1,6271840629258	Vila sorriso	68660000		217	Atendimento Reservado	N�o	Residencial		Fibra �ptica						JOSE FERNANDO DA SILVA TRINDADE								N�o	N�o			\n"
                + "1767728	46531	Maria Gorete Carvalho Dos Reis	(91) 9829-10496	13286404268		CTO: CTO_PA_SMG_PPS_B14a2	Tv. S�o Jorge - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4768223644178	-1,6120288913940	Perpetuo Socorro	68660000		418	Ativo	N�o	Residencial	1x16/3	Fibra �ptica							POP P. SOCORRO		L-15	14				N�o	N�o			\n"
                + "1767731	45764	Maria De Nazare Pereira Da Silva	(91) 98224-3659	80674330200		CTO: CTO_PA_SMG_A8b3	R. Bernardo Carvalho - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4857143411047	-1,6186565943063	OLHO D'�GUA	68660000		300	Atendimento Reservado	N�o	Residencial	1x16/1	Fibra �ptica	60		PLANO 200 MBPS	18/2/2021										N�o	N�o			\n"
                + "1767742	46281	Ana Gabriela Brito Da Silva	(91) 98038-9807	02082220222		CTO: CTO_PA_SMG_A5c4	R. Timborana - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4905992944772	-1,6214970150313	CASTANHEIRA	68660000	gabriellabs93@gmail.com	S/N	Inativo	N�o	Residencial	1x16/4		130		PLANO 200 MBPS	22/2/2021										N�o	N�o			\n"
                + "1805474	46729	Railson De Sousa Gama	(91) 98037-6330	09027449252		CTO: CTO_PA_SMG_B13a2	Tv. S�o Pedro - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4724804110454	-1,6168423188588	Pi�arreira	68660000		15	Inativo	N�o	Residencial	1x8/2															N�o	N�o			\n"
                + "1808786	47119	Adriana De Oliveira Teixeira	(91) 99154-9558	98172077220	45641	CTO: CTO_PA_SMG_PPS_B2a2	R. Gon�alo Braga, 868, Perp�tuo Socorro, S�o Miguel do Guam� - PA, 68660-000, Brasil	-47,4742402441489	-1,6019981700938	Perp�tuo Socorro	68660000		868	Ativo	N�o	Residencial	1x8/1	Fibra �ptica						RODRIGO BRASIL DE SOUSA	POP P. SOCORRO		L-15	2				N�o	N�o			\n"
                + "1808902	47140	Francilene De Castro Lima	(91) 99616-1076	83044086272	45664	CTO: CTO_PA_SMG_PPS_B5c1	R. SEBASTI�O SOARES	-47,4712937055226	-1,5996216376724	JARDIM AMERICA	68660000	castrofrancy629@gmail.com	4	Ativo	N�o	Residencial	1x8/7	Fibra �ptica	70		PLANO 200 MBPS	5/3/2021		RENATO PANTOJA	POP P. SOCORRO		L-15	5				N�o	N�o			\n"
                + "1811950	47498	Layse De Nazare Gonzaga Braga	(91) 99986-0676	00808578251		CTO: CTO_PA_SMG_B14b3	R. Sete de janeiro - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4778016355817	-1,6165973622829	Perpetuo Socorro	68660000		255	Inativo	N�o	Residencial	1x16/7															N�o	N�o			\n"
                + "1812143	47359	Josenilton Pereira Reis	(91) 98890-8527	09225439253	45865	CTO: CTO_PA_SMG_PPS_A2c4	Tv. Antonio piaui, 804-900, S�o Miguel do Guam� - PA, 68660-000, Brasil	-47,4791222537289	-1,6017138828609	Padre �ngelo	68660000		623	Ativo	N�o	Residencial	1x16/4	Fibra �ptica						DAVID DE SOUSA MARQUES	POP P. SOCORRO		L-16	2				N�o	N�o			\n"
                + "1811915		Layse De Nazar� Gonzaga Braga	(91) 99986-0676	008.085.783-51		CTO: CTO_PA_SMG_B14b3	Av. Magalh�es Barata, 255, S�o Miguel do Guam� - PA, 68660-000, Brasil	-47,4777911242583	-1,6165590126151	Perp�tuo socorro	68660000		255	Inativo	N�o	Residencial	1x16/6							JAILSON DANIEL ALMEIDA								N�o	N�o			\n"
                + "1812073	130	Policia Civil Do Estado Do Para	 91 98516-6998		46030	CTO: CTO_PA_SMG_A8c4	Tv. Jorge Carneiro - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4845996588956	-1,6185052320276		68660000		189	Aguardando Ativa��o	N�o	Residencial	1x16/2	Fibra �ptica			PLANO 200 MBPS	10/3/2021										N�o	N�o			\n"
                + "1812075	47521	Maria Do Socorro Do Carmo Batista	(91) 98201-4556			CTO: CTO_PA_SMG_A6c4	R. Cantidio Nunes - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4827626822083	-1,6201694206761	S�o Manoel	68660000		4	Inativo	N�o	Residencial	1x16/2															N�o	N�o			\n"
                + "1812193	47529	Ivin Stefany Oliveira Rodrigues	(91) 98024-8068	04129894250		CTO: CTO_PA_SMG_B7a5	R. S�o Pedro - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4648945889553	-1,6145769565676	Umarizal	68660000		S/N	Aguardando Ativa��o	N�o	Residencial	1x8/1	Fibra �ptica														N�o	N�o			\n"
                + "1812011	47509	Danielly Santos De Jesus	(91) 99832-5797	04808404230		CTO: CTO_PA_SMG_PPS_B12c1	R. Agostinho Siqueira - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4748217288360	-1,6120106377050	Perpetuo Socorro	68660000		668	Ativo	N�o	Residencial	1x16/1	Fibra �ptica							POP P. SOCORRO		L-15	12				N�o	N�o			\n"
                + "1812210	47559	Dario Augusto De Souza	(91) 99169-0559	01878492268	46073	CTO: CTO_PA_SMG_B1a2	R. Pio XII, 569, plPerp�tuo Socorro S�o Miguel do Guam� - PA, 68660-000, Brasil	-47,4785238543018	-1,6170986611391	Perp�tuo Socorro	68660000		569	Atendimento Reservado	N�o	Residencial	1x16/1	Fibra �ptica						RODRIGO BRASIL DE SOUSA								N�o	N�o			\n"
                + "1835453	48790	Clayton Dias De Paiva	(91) 98101-7455	74461168204	47319	CTO: CTO_PA_SMG_B15a2	R. NEY PEIXOTO - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4797039766867	-1,6158862320033	PERPETUO SOCORRO	68660000	clayton_dias1984@hotmail.com	236	Aguardando Ativa��o	N�o	Residencial	1x16/1	Fibra �ptica	90		PLANO 100 MBPS	29/3/2021										N�o	N�o			\n"
                + "1835639	48806	Eriton Januario De Freitas	(91) 98170-3530		47338	CTO: CTO_PA_SMG_A8c1	R. Jorge Carneiro- S�o Miguel do Guam�, PA - BR, 68660-000	-47,4881463577813	-1,6183687332612	Vila Nova	68660000	cristianealvesdosreis@gmail.com	178	Atendimento Reservado	N�o	Residencial	1x16/4	Fibra �ptica			PLANO 200 MBPS	1/4/2021										N�o	N�o			\n"
                + "1835669	48817	Benedito Reis Dos Santos Costa	(91) 99120-7937		47345	CTO: CTO_PA_SMG_PPS_A13c1	Estrada S�o Miguel - S�o Miguel do Guam�, PA - BR, 68660-000	-47,4901753644182	-1,6141003188553	Vila Fran�a	68660000		2	Aguardando Ativa��o	N�o	Residencial	1x16/5	Fibra �ptica														N�o	N�o			");

        btnAplyHeaderActionPerformed(null);
        btnAplyValuesActionPerformed(null);
    }
    // </editor-fold >

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panTableValues = new javax.swing.JPanel();
        btnAplyHeader = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHeaders = new javax.swing.JTextArea();
        final javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        final javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtValues = new javax.swing.JTextArea();
        btnAplyValues = new javax.swing.JButton();
        panPropColum = new javax.swing.JPanel();
        final javax.swing.JPanel Prop_panGridBag = new javax.swing.JPanel();
        final javax.swing.JLabel prop_lbl1 = new javax.swing.JLabel();
        prop_txtColumName = new javax.swing.JTextField();
        prop_txtNewLine = new javax.swing.JTextField();
        prop_txtTab = new javax.swing.JTextField();
        prop_ckbTab = new javax.swing.JCheckBox();
        final javax.swing.JLabel prop_lbl2 = new javax.swing.JLabel();
        prop_cmbCase = new javax.swing.JComboBox<>();
        prop_txtEmpty = new javax.swing.JTextField();
        prop_ckbEmpty = new javax.swing.JCheckBox();
        prop_btnSave = new javax.swing.JButton();
        prop_btnReset = new javax.swing.JButton();
        prop_spnID = new javax.swing.JSpinner();
        prop_lbl3 = new javax.swing.JLabel();
        prop_btnSelectColum = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMain = new javax.swing.JTable();

        jButton3.setText("Salvar como");

        jButton4.setText("Abrir");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 240), 2));

        panTableValues.setBackground(new java.awt.Color(220, 220, 220));

        btnAplyHeader.setText("Aplicar");
        btnAplyHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplyHeaderActionPerformed(evt);
            }
        });

        txtHeaders.setColumns(20);
        txtHeaders.setRows(5);
        jScrollPane1.setViewportView(txtHeaders);

        jLabel1.setText("HEADER");

        jLabel2.setText("VALORES");

        txtValues.setColumns(20);
        txtValues.setRows(5);
        jScrollPane2.setViewportView(txtValues);

        btnAplyValues.setText("Atualizar");
        btnAplyValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplyValuesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panTableValuesLayout = new javax.swing.GroupLayout(panTableValues);
        panTableValues.setLayout(panTableValuesLayout);
        panTableValuesLayout.setHorizontalGroup(
            panTableValuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTableValuesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTableValuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panTableValuesLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTableValuesLayout.createSequentialGroup()
                        .addGroup(panTableValuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAplyValues, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAplyHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                        .addGap(19, 19, 19))))
        );
        panTableValuesLayout.setVerticalGroup(
            panTableValuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTableValuesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplyHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplyValues)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tabela", panTableValues);

        Prop_panGridBag.setLayout(new java.awt.GridBagLayout());

        prop_lbl1.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        Prop_panGridBag.add(prop_lbl1, gridBagConstraints);

        prop_txtColumName.setText("Colum Name");
        prop_txtColumName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_txtColumNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.2;
        Prop_panGridBag.add(prop_txtColumName, gridBagConstraints);

        prop_txtNewLine.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                prop_txtNewLinePropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        Prop_panGridBag.add(prop_txtNewLine, gridBagConstraints);

        prop_txtTab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                prop_txtTabPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        Prop_panGridBag.add(prop_txtTab, gridBagConstraints);

        prop_ckbTab.setText("Tab");
        prop_ckbTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_ckbTabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        Prop_panGridBag.add(prop_ckbTab, gridBagConstraints);

        prop_ckbNewLine.setText("Nova linha");
        prop_ckbNewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_ckbNewLineActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        Prop_panGridBag.add(prop_ckbNewLine, gridBagConstraints);

        prop_lbl2.setText("Case");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        Prop_panGridBag.add(prop_lbl2, gridBagConstraints);

        prop_cmbCase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "UpperCase", "LowerCase" }));
        prop_cmbCase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_cmbCaseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        Prop_panGridBag.add(prop_cmbCase, gridBagConstraints);

        prop_txtEmpty.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        Prop_panGridBag.add(prop_txtEmpty, gridBagConstraints);

        prop_ckbEmpty.setText("Vazio");
        prop_ckbEmpty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_ckbEmptyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        Prop_panGridBag.add(prop_ckbEmpty, gridBagConstraints);

        prop_btnSave.setText("Salvar");
        prop_btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        Prop_panGridBag.add(prop_btnSave, gridBagConstraints);

        prop_btnReset.setText("Restaurar");
        prop_btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_btnResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 2;
        Prop_panGridBag.add(prop_btnReset, gridBagConstraints);

        prop_spnID.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        Prop_panGridBag.add(prop_spnID, gridBagConstraints);

        prop_lbl3.setText("ColumID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        Prop_panGridBag.add(prop_lbl3, gridBagConstraints);

        prop_btnSelectColum.setText("Selecionar Coluna");
        prop_btnSelectColum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prop_btnSelectColumActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        Prop_panGridBag.add(prop_btnSelectColum, gridBagConstraints);

        javax.swing.GroupLayout panPropColumLayout = new javax.swing.GroupLayout(panPropColum);
        panPropColum.setLayout(panPropColumLayout);
        panPropColumLayout.setHorizontalGroup(
            panPropColumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPropColumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_panGridBag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panPropColumLayout.setVerticalGroup(
            panPropColumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPropColumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Prop_panGridBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Propiedades", panPropColum);

        tableMain.setModel(tableModel);
        tableMain.setColumnSelectionAllowed(true);
        tableMain.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(tableMain);

        jTabbedPane2.addTab("Original", jScrollPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    interface MainTableChanges{
        public void updateValues();
        public void updateHeaders(TableModel table);
    }

    static ArrayList<MainTableChanges> changeslisteners = new ArrayList<>();

    public static void addChangeListener(MainTableChanges target){
        changeslisteners.add(target);
    }

    public static void removeChangeListener(MainTableChanges target){
        changeslisteners.remove(target);
    }

    TableModelColumProp columProps[];
    boolean columSelected = false;
    int columCurrentSelected = -1;
    void setCurrentProp(TableModelColumProp prop){
        boolean enabled = prop != null;

        prop_ckbEmpty.setEnabled(enabled);
        prop_ckbNewLine.setEnabled(enabled);
        prop_ckbTab.setEnabled(enabled);
        prop_cmbCase.setEnabled(enabled);
        prop_txtColumName.setEnabled(enabled);

        prop_txtEmpty.setEnabled(enabled);
        prop_txtNewLine.setEnabled(enabled);
        prop_txtTab.setEnabled(enabled);
        prop_spnID.setEnabled(enabled);

        prop_btnReset.setEnabled(enabled);
        prop_btnSave.setEnabled(enabled);

        if(!enabled){
            prop_txtColumName.setText("Invalid");
            prop_spnID.setValue(-1);
            columSelected = false;


            return;
        }
        columSelected = true;

        prop_ckbEmpty.setSelected(prop.isSbr_overrideEmpty());
        prop_txtEmpty.setText(prop.getVar_empty());
        prop_txtEmpty.setEnabled(prop.isSbr_overrideEmpty());

        prop_ckbTab.setSelected(prop.isSbr_overrideTab());
        prop_txtTab.setText(prop.getVar_tab());
        prop_txtTab.setEnabled(prop.isSbr_overrideTab());

        prop_ckbNewLine.setSelected(prop.isSbr_overrideNewLine());
        prop_txtNewLine.setText("");
        prop_txtNewLine.setEnabled(prop.isSbr_overrideNewLine());

        prop_txtColumName.setText(tableModel.getColumnName(columCurrentSelected));
        prop_cmbCase.setSelectedIndex(prop.getVar_caseType());
        prop_spnID.setValue(columCurrentSelected);
        prop_spnID.setEnabled(false);
        prop_txtColumName.setEnabled(false);


    }

    private void btnAplyValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplyValuesActionPerformed
        // TODO add your handling code here:
        String table = txtValues.getText();
        String[] rows = table.split("\n");

        int rowCount = rows.length;
        int columCont = tableHeader.length;
        tableValues = new Object[rowCount][columCont];

        columProps = new TableModelColumProp[columCont];

        System.out.println("Quantidade -> "+columCont);
        for(int i = 0; i < columCont; i++){
             columProps[i] = new TableModelColumProp();
        }

        for (int i = 0; i < rows.length; i++) {
            String[] curRow = rows[i].split("\t");

            for (int j = 0; j < curRow.length && j < columCont; j++) {
                tableValues[i][j] = new TableModelCell(curRow[j], columProps[j]);
            }
        }

        initTableModdel();
    }//GEN-LAST:event_btnAplyValuesActionPerformed

    private void btnAplyHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplyHeaderActionPerformed
        // TODO add your handling code here:
        String table = txtHeaders.getText();

        String[] rows = table.split("\n");
        String headers[] = rows[0].split("\t");

        tableHeader = headers;
        tableValues = new Object[][]{};
        TableModel oldHeader = tableModel;
        initTableModdel();

        changeslisteners.forEach(t -> t.updateHeaders(tableModel));

    }//GEN-LAST:event_btnAplyHeaderActionPerformed
    
    private void prop_txtColumNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_txtColumNameActionPerformed
        if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        
    }//GEN-LAST:event_prop_txtColumNameActionPerformed

    private void prop_ckbTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_ckbTabActionPerformed
        if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        boolean b = prop_ckbTab.isSelected();
        
        prop.setSbr_overrideTab(b);
        prop_txtTab.setEnabled(b);
    }//GEN-LAST:event_prop_ckbTabActionPerformed

    private void prop_ckbEmptyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_ckbEmptyActionPerformed
         if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        boolean b = prop_ckbEmpty.isSelected();
        prop.setSbr_overrideEmpty(b);
        prop_txtEmpty.setEnabled(b);
    }//GEN-LAST:event_prop_ckbEmptyActionPerformed

    private void prop_btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_btnSaveActionPerformed
        if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        prop.setVar_empty(prop_txtEmpty.getText());
        prop.setVar_newLine(prop_txtNewLine.getText());
        prop.setVar_tab(prop_txtTab.getText());
        
        tableMain.repaint();

        TableController.requestCellsUpdate();
    }//GEN-LAST:event_prop_btnSaveActionPerformed

    private void prop_btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_btnResetActionPerformed
       if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        prop.RezetValues();
    }//GEN-LAST:event_prop_btnResetActionPerformed

    private void prop_ckbNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_ckbNewLineActionPerformed
         if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        boolean b = prop_ckbEmpty.isSelected();
        prop.setSbr_overrideNewLine(b);
        prop_txtNewLine.setEnabled(b);
    }//GEN-LAST:event_prop_ckbNewLineActionPerformed

    private void prop_cmbCaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_cmbCaseActionPerformed
        // TODO add your handling code here:
         if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        prop.setVar_caseType(prop_cmbCase.getSelectedIndex());

        TableController.requestCellsUpdate();
        tableMain.repaint();
    }//GEN-LAST:event_prop_cmbCaseActionPerformed

    private void prop_btnSelectColumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prop_btnSelectColumActionPerformed
        // TODO add your handling code here:
        if(tableMain.getSelectedColumn() == -1){
            return;
        }
        tableMain.setRowSelectionInterval(0, tableModel.getRowCount()-1);
    }//GEN-LAST:event_prop_btnSelectColumActionPerformed

    private void prop_txtTabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_prop_txtTabPropertyChange
        if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        prop.setVar_tab(prop_txtTab.getText());
    }//GEN-LAST:event_prop_txtTabPropertyChange

    private void prop_txtNewLinePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_prop_txtNewLinePropertyChange
        if(!columSelected){
            return;
        }
        TableModelColumProp prop = columProps[columCurrentSelected];
        prop.setVar_newLine(prop_txtNewLine.getText());
    }//GEN-LAST:event_prop_txtNewLinePropertyChange

    //</editor-fold>

    //<editor-fold desc="Componentes Auto-Gerados">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplyHeader;
    private javax.swing.JButton btnAplyValues;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panPropColum;
    private javax.swing.JPanel panTableValues;
    private javax.swing.JButton prop_btnReset;
    private javax.swing.JButton prop_btnSave;
    private javax.swing.JButton prop_btnSelectColum;
    private javax.swing.JCheckBox prop_ckbEmpty;
    private final javax.swing.JCheckBox prop_ckbNewLine = new javax.swing.JCheckBox();
    private javax.swing.JCheckBox prop_ckbTab;
    private javax.swing.JComboBox<String> prop_cmbCase;
    private javax.swing.JLabel prop_lbl3;
    private javax.swing.JSpinner prop_spnID;
    private javax.swing.JTextField prop_txtColumName;
    private javax.swing.JTextField prop_txtEmpty;
    private javax.swing.JTextField prop_txtNewLine;
    private javax.swing.JTextField prop_txtTab;
    private javax.swing.JTable tableMain;
    private javax.swing.JTextArea txtHeaders;
    private javax.swing.JTextArea txtValues;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}

