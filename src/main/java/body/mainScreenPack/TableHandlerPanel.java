/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack;

import static body.mainScreenPack.TableHandlerPanel.colorFades;
import head.ClipBoard;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author boginni
 */
public class TableHandlerPanel extends javax.swing.JPanel implements body.mainScreenPack.FloatingTable.onClickListener {

    //<editor-fold desc="Table Change Listener">
    interface TableChangeListener {

        void onHeaderChange(TableChangeEvent e);

        void onValueChange(TableChangeEvent e);

        void onTableChange(TableChangeEvent e);

    }

    public class TableChangeEvent {

        public int changeColum;
        public int changeRoll;
        public Object newValue;
        public Object oldValue;

        private void callHeaderChange() {
            for (Object changeTarget : changeTargets) {
                ((TableChangeListener) changeTarget).onHeaderChange(this);
                ((TableChangeListener) changeTarget).onTableChange(this);
            }
        }

        private void callValueChange() {
            for (Object changeTarget : changeTargets) {
                ((TableChangeListener) changeTarget).onValueChange(this);
                ((TableChangeListener) changeTarget).onTableChange(this);
            }
        }

    }

    private static ArrayList<Object> changeTargets = new ArrayList<>();

    public static void addTableChangeListener(Object target) {
        if (target instanceof TableChangeListener) {
            changeTargets.add(target);
        }
    }
    //</editor-fold>

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

    class MyTableModel extends DefaultTableModel {

        private MyTableModel(Object[][] tableValues, String[] tableHeader) {
            super(tableValues, tableHeader);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            try {
                return tableEditables[columnIndex];
            } catch (Exception e) {
                return true;
            }
        }
    }

    public TableModel tableModel = new MyTableModel(tableValues, tableHeader);
    public TableModel layer1;

    void initTableModdel() {
        tableModel = new MyTableModel(tableValues, tableHeader);
        tableMain.setModel(tableModel);
        //layer1 = new MyTableModel(tableValues, tableHeader);

        if (tableMain.getPreferredSize().width < tableMain.getParent().getWidth()) {
            tableMain.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        } else {
            tableMain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }

        TableChanged(tableModel);

    }

    public void TableChanged(TableModel newTable) {

    }
    //</editor-fold>

    /**
     * Creates new form TableHandlerPanel
     */
    public TableHandlerPanel() {
        initComponents();
        initTableModdel();

        //dummyTable();
        FloatingTable.addClickListener(this);
        UptadeButtons();
        tableMain.getTableHeader().setReorderingAllowed(false);

        new Timer().schedule(colorFadeTick, 100l, (long) (1000.0 / 60.0));
    }

    //<editor-fold desc="Floating Table">
    //<editor-fold desc="COLOR FADE">
   
    static ArrayList<ColorFade> colorFades = new ArrayList<>();

    static TimerTask colorFadeTick = new TimerTask() {
        @Override
        public void run() {
            for (int i = colorFades.size() - 1; i >= 0 && !toTerminate; i--) {
                ColorFade c = colorFades.get(i);
                if (c != null && c.pass > 0) {
                    c.tick();
                } else {
                    colorFades.remove(i);
                }

            }

        }

    };
    //</editor-fold>

    FloatingTable floatingTable;
    @Deprecated
    public TableLayoutPanel layoutTableHandlerLayoutPanel;

    public void setTableLayout(TableLayoutPanel newLay) {
        layoutTableHandlerLayoutPanel = newLay;

    }

    public void initTable() {
        if (floatingTable != null) {
            floatingTable.dispatchEvent(new WindowEvent(floatingTable, WindowEvent.WINDOW_CLOSING));
        }
        TableModel layout = layoutTableHandlerLayoutPanel.tableLayoutModel;
        TableModel original = tableModel;

        int maxCol = Integer.parseInt(spnColCont.getValue().toString());
        int gapH = Integer.parseInt(spnHorizontalGap.getValue().toString());
        int gapV = Integer.parseInt(spnVerticalGap.getValue().toString());        
        
        LayoutManager grid = new GridLayout(0, maxCol, gapH, gapV);
        floatingTable = new FloatingTable(original, layout, 0, grid);
        floatingTable.setBackground(Color.blue);
        floatingTable.repaint();

        floatingTable.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                floatingTable = null;
                UptadeButtons();
            }
        });

        pgbRow.setMaximum(tableModel.getRowCount() - 1);
        ckbFixActionPerformed(null);
        ckbShowDecActionPerformed(null);
        UptadeButtons();
        AllCellsColor(Color.BLACK, Color.white, 2000);
    }
    static boolean toTerminate = false;
    
    void AllCellsColor(Color StarColor, Color endColor, int time) {
        colorFades.clear();
        for (FloatingTableCell cell : floatingTable.getCells()) {
            if (cell instanceof StaticCell) {
                new ColorFade((StaticCell) cell, StarColor, endColor, time);
            }
        }

    }

    void UptadeButtons() {

        boolean notNull = floatingTable != null;
        int curRow = 0, maxRow = 0;
        if (notNull) {
            curRow = floatingTable.getRow();
            maxRow = tableModel.getRowCount();
            lblCurLine.setText(String.valueOf(curRow));
            pgbRow.setValue(curRow);
            ckbFix.setSelected(false);
            ckbShowDec.setSelected(false);
        }

        btnNext.setEnabled(notNull && curRow < maxRow - 1);
        btnLast.setEnabled(notNull && curRow > 0);
        ckbFix.setEnabled(notNull);
        ckbShowDec.setEnabled(notNull);
    }

    @Override
    public void mouseClick(FloatingTableCell cell, int click) {

        if (cell instanceof FloatingTable.HeaderTableCell) {
            cell = (FloatingTable.HeaderTableCell) cell;
            switch(click){
                case 1:
                    ClipBoard.setClipBoard(cell.value, true);
                    if(cell instanceof StaticCell)
                        new ColorFade((StaticCell)cell, Color.blue, new Color(200, 220, 240), 1500);
                    break;
                    
                case 2:
                    
                    cell.setValue("");
                    if(cell instanceof StaticCell)
                        new ColorFade((StaticCell)cell, Color.red, new Color(240, 220, 220), 1500);
                    break;
                case 3:
                    
                    cell.setValue(ClipBoard.getClipBoard());
                    if(cell instanceof StaticCell)
                        new ColorFade((StaticCell)cell, Color.green, new Color(200, 240, 220), 1500);
                    break;
            }
            cell.updateValue();
            return;
        }
        
        if(cell instanceof ButtonCell){
            switch(click){
                case FloatingTable.BTN_NEXT:
                    btnNextActionPerformed(null);
                    return;
                case FloatingTable.BTN_LAST:
                    btnLastActionPerformed(null);
                    return;
            };
        }
        
        if (cell instanceof StaticCell && click == 1) {
            ClipBoard.setClipBoard(cell.value, true);
            new ColorFade((StaticCell)cell, Color.blue, new Color(200, 220, 240), 1500);
            return;
        }

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Dummy Table">
    private void dummyTable() {
        txtHeaders.setText("Fid	Código	Nome	Telefone	Documento	Contrato	Ponto de Acesso	Endereço	X (Longitude)	Y (Latitude)	Bairro	CEP	E-mail	Número Porta	Status	Dedicado	Tipo	Splitter/Porta	Tipo de Tecnologia	Comp. Fibra (m)	Sinal (dBm)	Plano	Data de Ativação do Plano	Data de Cancelamento do Plano	Vendedor	Estação	VLAN	OLT	Porta OLT	Área OLT	Código ONU	MAC ONU	Contido em Mancha de Atendimento	Proveniente de integração");
        txtValues.setText("1761594	45926	Eraldo Rodrigo Travassos Chagas	(91) 98105-0502	00607212276	44443	CTO: CTO_PA_SMG_PPS_B14c4	R. Agostinho Siqueira - São Miguel do Guamá, PA - BR, 68660-000	-47,4766023357945	-1,6126234265432	PERPETUO SOCORRO	68660000	eraldotec1@gmail.com	494	Inativo	Não	Residencial	1x16/6		100		PLANO 100 MBPS	17/2/2021		WALFREDO FERREIRA MACEDO								Não	Não			\n"
                + "1761618	45934	Mizael Jose Oliveira De Lima	(91) 98957-3979	02731256214	44454	CTO: CTO_PA_SMG_PPS_A8d1	R. RAIMUNDO CARVALHO 	-47,4791261067796	-1,6038967161848	PADRE ANGELO MARIA	68660000	mizael.lima22@outlook.com	137	Inativo	Não	Residencial			30		PLANO 200 MBPS	17/2/2021		WALFREDO FERREIRA MACEDO								Não	Não			\n"
                + "1761682		Cliente Test				CTO: CTO_PA_SMG_PPS_B12a2	R. Agostinho Siqueira - São Miguel do Guamá, PA - BR, 68660-000	-47,4747277081166	-1,6110627600638		68660000		717	Inativo	Não	Residencial																Não	Não			\n"
                + "1761452	45929	Divaldo Carvalho Pinto	(91) 99206-7088	24941689291	44447	CTO: CTO_PA_SMG_B4b4	R. Pio XII, 123, São Miguel do Guamá - PA, 68660-000, Brasil	-47,4767361820046	-1,6218272422184	Patauateua	68660000		123	Inativo	Não	Residencial	1x16/5							JOSE FERNANDO DA SILVA TRINDADE								Não	Não			\n"
                + "1761448	45919	Joelson Da Silva Barbosa	(91985768184	81292872268	44436	CTO: CTO_PA_SMG_B3b3	R. Liberdade - São Miguel do Guamá, PA - BR, 68660-000	-47,4783347055229	-1,6243345943440	PATAUATEUA	68660000	berna.vieras@gmail.com	593	Inativo	Não	Residencial			20		PLANO 200 MBPS	17/2/2021		RODRIGO BRASIL DE SOUSA								Não	Não			\n"
                + "1761322	45898	Josiane Oliveira Dos Santos	(91) 99350-5497	02374785289	44414	CTO: CTO_PA_SMG_B5c4	R. Cipriano Mendes Rodrigues , 57 , São Miguel do Guamá - PA, 68660-000, Brasil	-47,4723154068036	-1,6194957877775	Patauateua	68660000	josyolliver03@gmail.com	57	Inativo	Não	Residencial	1x16/3		100		PLANO 100 MBPS	17/2/2021		JOSE FERNANDO DA SILVA TRINDADE								Não	Não			\n"
                + "1761621		Jose Odivaldo Do Nascimento Oliveira	(91) 98032-5058	48769312249		CTO: CTO_PA_SMG_A2c4	Passagem  São Francisco , 217 , São Miguel do Guamá - PA, 68660-000,	-47,4829161108830	-1,6271840629258	Vila sorriso	68660000		217	Ativo	Não	Residencial	1x16/2	Fibra Óptica						JOSE FERNANDO DA SILVA TRINDADE	POP SÃO MIGUEL		L16	2				Não	Não			\n"
                + "1761623	45954	Jose Odivaldo Do Nascimento Oliveira	(91) 98032-5058	48769312249	44471		Passagem  São Francisco , 217 , São Miguel do Guamá - PA, 68660-000,	-47,4829161108830	-1,6271840629258	Vila sorriso	68660000		217	Atendimento Reservado	Não	Residencial		Fibra Óptica						JOSE FERNANDO DA SILVA TRINDADE								Não	Não			\n"
                + "1767728	46531	Maria Gorete Carvalho Dos Reis	(91) 9829-10496	13286404268		CTO: CTO_PA_SMG_PPS_B14a2	Tv. São Jorge - São Miguel do Guamá, PA - BR, 68660-000	-47,4768223644178	-1,6120288913940	Perpetuo Socorro	68660000		418	Ativo	Não	Residencial	1x16/3	Fibra Óptica							POP P. SOCORRO		L-15	14				Não	Não			\n"
                + "1767731	45764	Maria De Nazare Pereira Da Silva	(91) 98224-3659	80674330200		CTO: CTO_PA_SMG_A8b3	R. Bernardo Carvalho - São Miguel do Guamá, PA - BR, 68660-000	-47,4857143411047	-1,6186565943063	OLHO D'ÁGUA	68660000		300	Atendimento Reservado	Não	Residencial	1x16/1	Fibra Óptica	60		PLANO 200 MBPS	18/2/2021										Não	Não			\n"
                + "1767742	46281	Ana Gabriela Brito Da Silva	(91) 98038-9807	02082220222		CTO: CTO_PA_SMG_A5c4	R. Timborana - São Miguel do Guamá, PA - BR, 68660-000	-47,4905992944772	-1,6214970150313	CASTANHEIRA	68660000	gabriellabs93@gmail.com	S/N	Inativo	Não	Residencial	1x16/4		130		PLANO 200 MBPS	22/2/2021										Não	Não			\n"
                + "1805474	46729	Railson De Sousa Gama	(91) 98037-6330	09027449252		CTO: CTO_PA_SMG_B13a2	Tv. São Pedro - São Miguel do Guamá, PA - BR, 68660-000	-47,4724804110454	-1,6168423188588	Piçarreira	68660000		15	Inativo	Não	Residencial	1x8/2															Não	Não			\n"
                + "1808786	47119	Adriana De Oliveira Teixeira	(91) 99154-9558	98172077220	45641	CTO: CTO_PA_SMG_PPS_B2a2	R. Gonçalo Braga, 868, Perpétuo Socorro, São Miguel do Guamá - PA, 68660-000, Brasil	-47,4742402441489	-1,6019981700938	Perpétuo Socorro	68660000		868	Ativo	Não	Residencial	1x8/1	Fibra Óptica						RODRIGO BRASIL DE SOUSA	POP P. SOCORRO		L-15	2				Não	Não			\n"
                + "1808902	47140	Francilene De Castro Lima	(91) 99616-1076	83044086272	45664	CTO: CTO_PA_SMG_PPS_B5c1	R. SEBASTIÃO SOARES	-47,4712937055226	-1,5996216376724	JARDIM AMERICA	68660000	castrofrancy629@gmail.com	4	Ativo	Não	Residencial	1x8/7	Fibra Óptica	70		PLANO 200 MBPS	5/3/2021		RENATO PANTOJA	POP P. SOCORRO		L-15	5				Não	Não			\n"
                + "1811950	47498	Layse De Nazare Gonzaga Braga	(91) 99986-0676	00808578251		CTO: CTO_PA_SMG_B14b3	R. Sete de janeiro - São Miguel do Guamá, PA - BR, 68660-000	-47,4778016355817	-1,6165973622829	Perpetuo Socorro	68660000		255	Inativo	Não	Residencial	1x16/7															Não	Não			\n"
                + "1812143	47359	Josenilton Pereira Reis	(91) 98890-8527	09225439253	45865	CTO: CTO_PA_SMG_PPS_A2c4	Tv. Antonio piaui, 804-900, São Miguel do Guamá - PA, 68660-000, Brasil	-47,4791222537289	-1,6017138828609	Padre Ângelo	68660000		623	Ativo	Não	Residencial	1x16/4	Fibra Óptica						DAVID DE SOUSA MARQUES	POP P. SOCORRO		L-16	2				Não	Não			\n"
                + "1811915		Layse De Nazaré Gonzaga Braga	(91) 99986-0676	008.085.783-51		CTO: CTO_PA_SMG_B14b3	Av. Magalhães Barata, 255, São Miguel do Guamá - PA, 68660-000, Brasil	-47,4777911242583	-1,6165590126151	Perpétuo socorro	68660000		255	Inativo	Não	Residencial	1x16/6							JAILSON DANIEL ALMEIDA								Não	Não			\n"
                + "1812073	130	Policia Civil Do Estado Do Para	 91 98516-6998		46030	CTO: CTO_PA_SMG_A8c4	Tv. Jorge Carneiro - São Miguel do Guamá, PA - BR, 68660-000	-47,4845996588956	-1,6185052320276		68660000		189	Aguardando Ativação	Não	Residencial	1x16/2	Fibra Óptica			PLANO 200 MBPS	10/3/2021										Não	Não			\n"
                + "1812075	47521	Maria Do Socorro Do Carmo Batista	(91) 98201-4556			CTO: CTO_PA_SMG_A6c4	R. Cantidio Nunes - São Miguel do Guamá, PA - BR, 68660-000	-47,4827626822083	-1,6201694206761	São Manoel	68660000		4	Inativo	Não	Residencial	1x16/2															Não	Não			\n"
                + "1812193	47529	Ivin Stefany Oliveira Rodrigues	(91) 98024-8068	04129894250		CTO: CTO_PA_SMG_B7a5	R. São Pedro - São Miguel do Guamá, PA - BR, 68660-000	-47,4648945889553	-1,6145769565676	Umarizal	68660000		S/N	Aguardando Ativação	Não	Residencial	1x8/1	Fibra Óptica														Não	Não			\n"
                + "1812011	47509	Danielly Santos De Jesus	(91) 99832-5797	04808404230		CTO: CTO_PA_SMG_PPS_B12c1	R. Agostinho Siqueira - São Miguel do Guamá, PA - BR, 68660-000	-47,4748217288360	-1,6120106377050	Perpetuo Socorro	68660000		668	Ativo	Não	Residencial	1x16/1	Fibra Óptica							POP P. SOCORRO		L-15	12				Não	Não			\n"
                + "1812210	47559	Dario Augusto De Souza	(91) 99169-0559	01878492268	46073	CTO: CTO_PA_SMG_B1a2	R. Pio XII, 569, plPerpétuo Socorro São Miguel do Guamá - PA, 68660-000, Brasil	-47,4785238543018	-1,6170986611391	Perpétuo Socorro	68660000		569	Atendimento Reservado	Não	Residencial	1x16/1	Fibra Óptica						RODRIGO BRASIL DE SOUSA								Não	Não			\n"
                + "1835453	48790	Clayton Dias De Paiva	(91) 98101-7455	74461168204	47319	CTO: CTO_PA_SMG_B15a2	R. NEY PEIXOTO - São Miguel do Guamá, PA - BR, 68660-000	-47,4797039766867	-1,6158862320033	PERPETUO SOCORRO	68660000	clayton_dias1984@hotmail.com	236	Aguardando Ativação	Não	Residencial	1x16/1	Fibra Óptica	90		PLANO 100 MBPS	29/3/2021										Não	Não			\n"
                + "1835639	48806	Eriton Januario De Freitas	(91) 98170-3530		47338	CTO: CTO_PA_SMG_A8c1	R. Jorge Carneiro- São Miguel do Guamá, PA - BR, 68660-000	-47,4881463577813	-1,6183687332612	Vila Nova	68660000	cristianealvesdosreis@gmail.com	178	Atendimento Reservado	Não	Residencial	1x16/4	Fibra Óptica			PLANO 200 MBPS	1/4/2021										Não	Não			\n"
                + "1835669	48817	Benedito Reis Dos Santos Costa	(91) 99120-7937		47345	CTO: CTO_PA_SMG_PPS_A13c1	Estrada São Miguel - São Miguel do Guamá, PA - BR, 68660-000	-47,4901753644182	-1,6141003188553	Vila França	68660000		2	Aguardando Ativação	Não	Residencial	1x16/5	Fibra Óptica														Não	Não			");

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
        jPanel2 = new javax.swing.JPanel();
        btnAplyHeader = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHeaders = new javax.swing.JTextArea();
        final javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        final javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtValues = new javax.swing.JTextArea();
        btnAplyValues = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnLast = new javax.swing.JButton();
        lblCurLine = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnInitTable = new javax.swing.JButton();
        spnColCont = new javax.swing.JSpinner();
        final javax.swing.JLabel lblTitleLinha = new javax.swing.JLabel();
        ckbFix = new javax.swing.JCheckBox();
        ckbShowDec = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        spnVerticalGap = new javax.swing.JSpinner();
        final javax.swing.JLabel lblTitleLinha1 = new javax.swing.JLabel();
        spnHorizontalGap = new javax.swing.JSpinner();
        final javax.swing.JLabel lblTitleLinha2 = new javax.swing.JLabel();
        spnCurRow = new javax.swing.JSpinner();
        final javax.swing.JLabel lblTitleLinha3 = new javax.swing.JLabel();
        pgbRow = new javax.swing.JProgressBar();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMain = new javax.swing.JTable();

        jButton3.setText("Salvar como");

        jButton4.setText("Abrir");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 240), 2));

        jPanel2.setBackground(new java.awt.Color(220, 220, 220));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAplyValues, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAplyHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                        .addGap(19, 19, 19))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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

        jTabbedPane1.addTab("Tabela", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnLast.setText("<");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel3.add(btnLast, new java.awt.GridBagConstraints());

        lblCurLine.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCurLine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurLine.setText("000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 17;
        jPanel3.add(lblCurLine, gridBagConstraints);

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel3.add(btnNext, new java.awt.GridBagConstraints());

        btnInitTable.setText("Iniciar");
        btnInitTable.setPreferredSize(new java.awt.Dimension(85, 23));
        btnInitTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInitTableActionPerformed(evt);
            }
        });

        spnColCont.setModel(new javax.swing.SpinnerNumberModel(8, 1, null, 1));

        lblTitleLinha.setText("Colunas Max por Linha");

        ckbFix.setText("Fixar na Tela");
        ckbFix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbFixActionPerformed(evt);
            }
        });

        ckbShowDec.setText("Mostrar Decoração");
        ckbShowDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbShowDecActionPerformed(evt);
            }
        });

        spnVerticalGap.setModel(new javax.swing.SpinnerNumberModel(2, 1, null, 1));

        lblTitleLinha1.setText("VerticalGap");

        spnHorizontalGap.setModel(new javax.swing.SpinnerNumberModel(2, 1, null, 1));

        lblTitleLinha2.setText("HorizontalGap");

        spnCurRow.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        lblTitleLinha3.setText("Linha Inicial");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInitTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spnColCont, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitleLinha, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addComponent(ckbFix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbShowDec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spnVerticalGap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitleLinha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spnHorizontalGap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitleLinha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spnCurRow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitleLinha3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pgbRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInitTable, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(pgbRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnCurRow)
                    .addComponent(lblTitleLinha3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnHorizontalGap)
                    .addComponent(lblTitleLinha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnVerticalGap)
                    .addComponent(lblTitleLinha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnColCont)
                    .addComponent(lblTitleLinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbFix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbShowDec)
                .addGap(52, 52, 52))
        );

        jTabbedPane1.addTab("Tabela Flutuante", jPanel1);

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

    private void ckbFixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbFixActionPerformed
        // TODO add your handling code here:
        floatingTable.setAlwaysOnTop(ckbFix.isSelected());
    }//GEN-LAST:event_ckbFixActionPerformed

    private void btnAplyValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplyValuesActionPerformed
        // TODO add your handling code here:
        String table = txtValues.getText();

        String[] rows = table.split("\n");
        int rowCount = rows.length;
        int columCont = tableHeader.length;
        tableValues = new Object[rowCount][columCont];
        for (int i = 0; i < rows.length; i++) {
            String[] curRow = rows[i].split("\t");

            for (int j = 0; j < curRow.length && j < columCont; j++) {
                tableValues[i][j] = curRow[j];
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
        
        layoutTableHandlerLayoutPanel.updateHeaders(tableModel);

    }//GEN-LAST:event_btnAplyHeaderActionPerformed

    private void btnInitTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInitTableActionPerformed
        // TODO add your handling code here:
        initTable();
    }//GEN-LAST:event_btnInitTableActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        floatingTable.sumRow(-1);
        UptadeButtons();
        
        int r,g,b, change = 35;
        r = 220 + (swapColor? change:0);
        g = 220 + (swapColor? change:0);
        b = 220 + (swapColor? change:0);
        
        AllCellsColor(new Color(255, 220, 220), new Color(r, g, b), 1000);
        swapColor = !swapColor;
    }//GEN-LAST:event_btnLastActionPerformed
    boolean swapColor = false;
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        floatingTable.sumRow(1);
        UptadeButtons();
        
        
        int r,g,b, change = 35;
        r = 220 + (swapColor? change:0);
        g = 220 + (swapColor? change:0);
        b = 220 + (swapColor? change:0);
        
        AllCellsColor(new Color(230, 255, 255), new Color(r, g, b), 1000);
        swapColor = !swapColor;
    }//GEN-LAST:event_btnNextActionPerformed

    private void ckbShowDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbShowDecActionPerformed
        // TODO add your handling code here:
        floatingTable.dispose();
        floatingTable.setUndecorated(ckbShowDec.isSelected());
        floatingTable.setVisible(true);
    }//GEN-LAST:event_ckbShowDecActionPerformed

    //<editor-fold desc="Componentes Auto-Gerados">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplyHeader;
    private javax.swing.JButton btnAplyValues;
    private javax.swing.JButton btnInitTable;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JCheckBox ckbFix;
    private javax.swing.JCheckBox ckbShowDec;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblCurLine;
    private javax.swing.JProgressBar pgbRow;
    private javax.swing.JSpinner spnColCont;
    private javax.swing.JSpinner spnCurRow;
    private javax.swing.JSpinner spnHorizontalGap;
    private javax.swing.JSpinner spnVerticalGap;
    private javax.swing.JTable tableMain;
    private javax.swing.JTextArea txtHeaders;
    private javax.swing.JTextArea txtValues;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}

class ColorFade {

        Color startColor;
        double pass;
        double r, g, b;
        double rs, gs, bs;
        public StaticCell tarCell;
        private Color tarColor;

        public ColorFade(StaticCell c, Color newColor, Color target, int interval) {
            tarColor = new Color(target.getRGB());
            tarCell = c;
            if (newColor != null) {
                tarCell.lblValue.setBackground(newColor);
            }
            startColor = c.lblValue.getBackground();
            r = target.getRed() - startColor.getRed();
            g = target.getGreen() - startColor.getGreen();
            b = target.getBlue() - startColor.getBlue();

            pass = (interval * 60.0d) / 1000.0;
            if (pass < 3.0) {
                pass = 0;
                return;
            }

            rs = r / pass;
            gs = g / pass;
            bs = b / pass;

            r = startColor.getRed();
            g = startColor.getGreen();
            b = startColor.getBlue();
            
            removeRunning();
            
            colorFades.add(this);
        }
        @Deprecated
        void removeRunning(){
            for(int i = 0; i< colorFades.size(); i++){
                if(colorFades.get(i).tarCell == this.tarCell){
                    colorFades.remove(i);
                    return;
                }
            }
        }

        public void tick() {
            Color newCor = new Color((int) r, (int) g, (int) b);
            tarCell.lblValue.setBackground(newCor);
            tarCell.lblHeader.setBackground(newCor.darker());
            r += rs;
            g += gs;
            b += bs;
            pass--;
            if (r > 255 || r < 0) {
                pass = 0;
            }

            if (g > 255 || g < 0) {
                pass = 0;
            }

            if (b > 255 || b < 0) {
                pass = 0;
            }
        }

        private void terminate() {
            tarCell.lblValue.setBackground(tarColor);
            pass = 0;
        }

    }

