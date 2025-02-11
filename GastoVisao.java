/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: GastoVisao.java
Data: 20/01/2025
--------------------------------------*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.time.LocalDate;


public class GastoVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;
    private GastoFile file;


    public GastoVisao(boolean alterar, GastoModelo modelo)
    {
        super("Registrar Gastos");
        definirTema();
        setLayout(new BorderLayout());
        editar = alterar;

        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);

        if (!alterar)
            getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        else
            getContentPane().add(painelCentro = new PainelCentro(modelo), BorderLayout.CENTER);

        getContentPane().add(painelSul = new PainelSul(), BorderLayout.SOUTH);

        setSize(490,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class PainelNorte extends JPanel
    {
        private JLabel lblCasa;
        private ImageIcon casasIco, redimImage;
        private Image redimLogo,image; 

        public PainelNorte()
        {          
            casasIco = new ImageIcon("imagens/gastos.jpg");
            
            lblCasa = new JLabel();
            lblCasa.setIcon(casasIco);
            add(lblCasa);
        }
    }

    public class PainelCentro extends JPanel
    {
        private JTextField idJTF, fornecedorJTF, valorGastoJTF, descricaoJTF;
        private JComboBox tipoGastoJCB, categoriaGastoJCB,statusManutencaoJCB;
        
        private JLabel lblId, lblFornecedor, lblDataAgendamento, lblValorGasto, lblDescricao, lblTipoGasto, lblDataGasto, lblCategoriaGasto, lblStatusManutencao;
        private JPanel painelData, painelData2;
        private JTextFieldData dataGasto, dataAgendamento;
        private String status[] = {"em andamento","concluido", "pendente"};
        private String tipoGastoOpces [] = {"despesa", "manutencao"};
        private GastoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(8,2));
            idJTF = new JTextField();
            file = new GastoFile();
			idJTF.setText("" + file.getProximoCodigo());
            idJTF.setFocusable(false);
            
            add(lblTipoGasto = new JLabel("Tipo de Gasto"));
            add(tipoGastoJCB = UInterfaceBox.createJComboBoxsTabela2("CategoriaDespesa.tab"));

            add(lblCategoriaGasto = new JLabel("Categoria de Gasto"));
            add(categoriaGastoJCB = new JComboBox(tipoGastoOpces));

            add(lblValorGasto = new JLabel("Valor Gasto"));
            add(valorGastoJTF = new JTextField());

            add(lblDescricao = new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());

            add(lblDataGasto = new JLabel("Data do Gasto"));
            painelData = new JPanel( new GridLayout(1, 1) );
            dataGasto = new JTextFieldData("Data?");
            painelData.add( dataGasto.getDTestField() );
			painelData.add( dataGasto.getDButton() );
            add(painelData);

            add(lblDataAgendamento = new JLabel("Data de Agendamento de Manutencao"));
            painelData2 = new JPanel( new GridLayout(1, 1) );
            dataAgendamento = new JTextFieldData("Data?");
            painelData2.add( dataAgendamento.getDTestField() );
			painelData2.add( dataAgendamento.getDButton() );
            add(painelData2);

            add(lblStatusManutencao = new JLabel("Status Manutencao"));
            add(statusManutencaoJCB = new JComboBox(status));

            add(lblFornecedor = new JLabel("Fornecedor"));
            add(fornecedorJTF = new JTextField());
            
        }

        public PainelCentro(GastoModelo modelo)
        {
            setLayout(new GridLayout(9,2));
            idJTF = new JTextField();
			idJTF.setText( "" + modelo.getId());
            idJTF.setFocusable(false);
            
            add(lblTipoGasto = new JLabel("Tipo de Gasto"));
            add(tipoGastoJCB = UInterfaceBox.createJComboBoxsTabela2("CategoriaDespesa.tab"));
            tipoGastoJCB.setSelectedItem(modelo.getTipoGasto());

            add(lblCategoriaGasto = new JLabel("Categoria de Gasto"));
            add(categoriaGastoJCB = new JComboBox(tipoGastoOpces));
            categoriaGastoJCB.setSelectedItem(modelo.getCategoriaGasto());

            add(lblValorGasto = new JLabel("Valor Gasto"));
            add(valorGastoJTF = new JTextField());
            valorGastoJTF.setText(""+modelo.getValorGasto());

            add(lblDescricao = new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());
            descricaoJTF.setText(modelo.getDescricao());

            add(lblDataGasto = new JLabel("Data do Gasto"));
            painelData = new JPanel( new GridLayout(1, 1) );
            dataGasto = new JTextFieldData("Data?");
            dataGasto.getDTestField().setText(modelo.getDataGasto());
            painelData.add( dataGasto.getDTestField() );
			painelData.add( dataGasto.getDButton() );
            add(painelData);

            add(lblDataAgendamento = new JLabel("Data de Agendamento de Manutencao"));
            painelData2 = new JPanel( new GridLayout(1, 1) );
            dataAgendamento = new JTextFieldData("Data?");
            dataAgendamento.getDTestField().setText(modelo.getDataAgendamento());
            painelData2.add( dataAgendamento.getDTestField() );
			painelData2.add( dataAgendamento.getDButton() );
            add(painelData2);

            add(lblStatusManutencao = new JLabel("Status Manutencao"));
            add(statusManutencaoJCB = new JComboBox(status));
            statusManutencaoJCB.setSelectedItem(modelo.getStatusManutencao());

            add(lblFornecedor = new JLabel("Fornecedor"));
            add(fornecedorJTF = new JTextField());
            fornecedorJTF.setText(modelo.getFornecedor());

           
        }


    public int getId()
    {
        return Integer.parseInt( idJTF.getText().trim());
    }

    public void setId(int id)
    {
        idJTF.setText(""+id);
    }

    
public String getFornecedor()
{
    return fornecedorJTF.getText().trim();
}

public void setFornecedor(String newFornecedor)
{
    fornecedorJTF.setText(newFornecedor);
}

public double getValorGasto()
{
    return Double.parseDouble(valorGastoJTF.getText().trim());
}

public void setValorGasto(double newValorGasto)
{
    valorGastoJTF.setText("" + newValorGasto);
}

public String getDescricao()
{
    return descricaoJTF.getText().trim();
}

public void setDescricao(String newDescricao)
{
    descricaoJTF.setText(newDescricao);
}

public String getTipoGasto()
{
    return String.valueOf(tipoGastoJCB.getSelectedItem());
}

public void setTipoGasto(String newTipoGasto)
{
    tipoGastoJCB.setSelectedItem(newTipoGasto);
}

public String getCategoriaGasto()
{
    return String.valueOf(categoriaGastoJCB.getSelectedItem());
}

public void setCategoriaGasto(String newCategoriaGasto)
{
    categoriaGastoJCB.setSelectedItem(newCategoriaGasto);
}

public String getStatusManutencao()
{
    return String.valueOf(statusManutencaoJCB.getSelectedItem());
}

public void setStatusManutencao(String newStatusManutencao)
{
    statusManutencaoJCB.setSelectedItem(newStatusManutencao);
}

public String getDataGasto()
{
    return dataGasto.getDTestField().getText();
}

public void setDataGasto(String newDataGasto)
{
    dataGasto.getDTestField().setText(newDataGasto);
}

public String getDataAgendamento()
{
    return dataAgendamento.getDTestField().getText();
}

public void setDataAgendamento(String newDataAgendamento)
{
    dataAgendamento.getDTestField().setText(newDataAgendamento);
}

    public boolean isEmpty(Object valor)
    {
        return String.valueOf(valor).equals("") || valor == null || String.valueOf(valor).equals("0") || String.valueOf(valor).equals("0.0");
    }


     public boolean verificarCampos()
        {
            if(isEmpty(getId()) || isEmpty(getTipoGasto()) || isEmpty(getDataGasto()) || isEmpty(getCategoriaGasto()) || isEmpty(getValorGasto()))
                    return false;
                return true; 
        }

        public void salvar()
		{			
			GastoModelo modelo = new GastoModelo(getId(), getDescricao(), getDataGasto(), getValorGasto(), getCategoriaGasto(), getTipoGasto(), getDataAgendamento(), getStatusManutencao(), getFornecedor());

            JOptionPane.showMessageDialog(null, modelo.toString());

			modelo.salvar();
			dispose();
		}
    
        public void alterar()
		{
			GastoModelo modelo = new GastoModelo(getId(), getDescricao(), getDataGasto(), getValorGasto(), getCategoriaGasto(), getTipoGasto(), getDataAgendamento(), getStatusManutencao(), getFornecedor());
			JOptionPane.showMessageDialog(null, modelo.toString() );
            modelo.editar();		
			dispose();
		}

    }


    public class PainelSul extends JPanel implements ActionListener
    {

        private JButton salvarJB, cancelarJB;
		
		public PainelSul()
		{
			add( salvarJB = new JButton("Salvar") );
			add( cancelarJB = new JButton("Cancelar") );

            salvarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
		}

            public void actionPerformed(ActionEvent evt)
            {
                 if(evt.getSource() == salvarJB)
                {
                    if(painelCentro.verificarCampos())
                    {
                        if(editar)
                            painelCentro.alterar();
                        else
                            painelCentro.salvar();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Campo vazios", "Verificador de campos", JOptionPane.ERROR_MESSAGE);
                }
                else
                    dispose();
            }
    }

     public void definirTema() 
	 {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } 
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }


    public static void main(String args[])
    {
        Vector_Tabelas.inic();
        GastoModelo modelo = new GastoModelo();
        new GastoVisao(false,modelo); 
    }
}
