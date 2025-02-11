/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: PagamentoVisao.java
Data: 10/02/2025
--------------------------------------*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.time.LocalDate;


public class PagamentoVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;

    public PagamentoVisao(boolean alterar, PagamentoModelo modelo)
    {
        super("Registrar Pagamentos");
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
            casasIco = new ImageIcon("imagens/pagamento2.jpg");
            
            lblCasa = new JLabel();
            lblCasa.setIcon(casasIco);
            add(lblCasa);
        }
    }

    public class PainelCentro extends JPanel
    {
        private JTextField idJTF,numDocJTF, numeroUnidadeJTF, valor_a_pagarJTF, descricaoJTF;
        private JComboBox TipoPagamentoJCB, statusPagamentoJCB;
        private String opcoes[] = {"pendente", "atrasado","concluido"};
        private JLabel idLbl, numDocLbl, dataPagamentolbl, numeroUnidadeLbl, tipoPagamentoLBL, valorapagarlbl, statusPagamentolbl, lblDescricao;
        private JPanel painelData;
        private JTextFieldData dataPagamento;
        private PagamentoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(7,2));
            file = new PagamentoFile();
            idJTF = new JTextField();
            idJTF.setText("" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            add(numDocLbl = new JLabel("Numero do Documento"));
            add(numDocJTF = new JTextField());

            add(numeroUnidadeLbl = new JLabel("Numero da Unidade"));
            add(numeroUnidadeJTF = new JTextField());

            add(tipoPagamentoLBL = new JLabel("Tipo de Pagamento"));
            add(TipoPagamentoJCB = UInterfaceBox.createJComboBoxsTabela2("TipoPagamento.tab"));

            add(lblDescricao = new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());

            add(valorapagarlbl = new JLabel("Valor a Pagar"));
            add(valor_a_pagarJTF = new JTextField());
            
            add(statusPagamentolbl = new JLabel("Status do Pagamento"));
            add(statusPagamentoJCB = new JComboBox(opcoes));

            add(dataPagamentolbl = new JLabel("Data do Pagamento"));
            painelData = new JPanel( new GridLayout(1, 1) );
            dataPagamento = new JTextFieldData("Data?");
            painelData.add( dataPagamento.getDTestField() );
			painelData.add( dataPagamento.getDButton() );
            add(painelData);


        }

        public PainelCentro(PagamentoModelo modelo)
        {
            setLayout(new GridLayout(7,2));
            file = new PagamentoFile();
            idJTF = new JTextField();

			idJTF.setText( "" + modelo.getId());
            idJTF.setFocusable(false);

            add(numDocLbl = new JLabel("Numero do Documento"));

            MoradorModelo modeloMor = PagamentoDadosTable.pesquisarMoradorNumDocPorId(modelo.getIdMorador());
            numDocJTF = new JTextField();
            numDocJTF.setText(modeloMor.getNumDoc());
            add(numDocJTF);

            add(numeroUnidadeLbl = new JLabel("Numero da Unidade"));
            UnidadeModelo modeloUni = PagamentoDadosTable.pesquisarUnidadeNumUniPorId(modelo.getIdUnidade());
            numeroUnidadeJTF = new JTextField();
            numeroUnidadeJTF.setText(modeloUni.getNumeroUni());
            add(numeroUnidadeJTF);

            add(tipoPagamentoLBL = new JLabel("Tipo de Pagamento"));
            TipoPagamentoJCB = UInterfaceBox.createJComboBoxsTabela2("TipoPagamento.tab");
            TipoPagamentoJCB.setSelectedItem(modelo.getTipoPagamento());
            add(TipoPagamentoJCB);

            add(lblDescricao = new JLabel("Descricao"));
            descricaoJTF = new JTextField();
            descricaoJTF.setText(modelo.getDescricao());
            add(descricaoJTF);

            add(valorapagarlbl = new JLabel("Valor a Pagar"));
            valor_a_pagarJTF = new JTextField();
            valor_a_pagarJTF.setText(""+modelo.getValorPagar());
            add(valor_a_pagarJTF);
            
            add(statusPagamentolbl = new JLabel("Status do Pagamento"));
            statusPagamentoJCB = new JComboBox(opcoes);
            statusPagamentoJCB.setSelectedItem(modelo.getStatusPagamento());
            add(statusPagamentoJCB);

            add(dataPagamentolbl = new JLabel("Data do Pagamento"));
            painelData = new JPanel( new GridLayout(1, 1) );
            dataPagamento = new JTextFieldData("Data?");
            dataPagamento.getDTestField().setText(modelo.getDataPagamento());
            painelData.add( dataPagamento.getDTestField() );
			painelData.add( dataPagamento.getDButton() );
            add(painelData);
        }


    public int getId()
    {
        return Integer.parseInt( idJTF.getText().trim());
    }

    public void setId(int id)
    {
        idJTF.setText(""+id);
    }

    public String getDescricao() {
        return descricaoJTF.getText().trim();
}

// Setter
public void setDescricao(String descricaoJTF) {
    this.descricaoJTF.setText(descricaoJTF);
}



    public String getNumDoc()
    {
        return numDocJTF.getText().trim();
    }

    public void setNumDoc(String numDoc)
    {
        numDocJTF.setText(numDoc);
    }

    public double getValorPagar()
    {
        return Double.parseDouble(valor_a_pagarJTF.getText().trim());
    }

    public void setValorPagar(double newValorapagar)
    {
        valor_a_pagarJTF.setText(""+newValorapagar);
    }

    public String getNumeroUnidade()
    {
        return numeroUnidadeJTF.getText().trim();
    }

    public void setNumeroUnidade(String numeroUnidade)
    {
        numeroUnidadeJTF.setText(numeroUnidade);
    }


    public String getTipoPagamento()
    {
        return String.valueOf(TipoPagamentoJCB.getSelectedItem());
    }

    public void setTipoPagamento(String tipoPagamento)
    {
        TipoPagamentoJCB.setSelectedItem(tipoPagamento);
    }

    public String getStatusPagamento()
    {
        return String.valueOf(statusPagamentoJCB.getSelectedItem()); 
    }

    public void setStatusPagamento(String newStatusPagamento)
    {
        statusPagamentoJCB.setSelectedItem(newStatusPagamento);
    }

     public String getDataPagamento()
        {
            return dataPagamento.getDTestField().getText();
        }

         public void setDataPagamento(String newdataPagamento)
        {
            dataPagamento.getDTestField().setText(newdataPagamento);
        }

    public boolean isEmpty(Object valor)
    {
        return String.valueOf(valor).equals("") || valor == null || String.valueOf(valor).equals("0") || String.valueOf(valor).equals("0.0");
    }

    public boolean verificarCampos()
        {
            if(isEmpty(getId()) || isEmpty(getTipoPagamento()) || isEmpty(getNumDoc()) ||  isEmpty(getValorPagar()) || isEmpty(getStatusPagamento()) || isEmpty(getNumeroUnidade()))
                    return false;
                return true; 
        }

        public void salvar()
		{
            MoradorModelo modMor = MoradorDadosTable.pesquisarMoradorIdPorNumDoc(getNumDoc());
            UnidadeModelo modUni = UnidadeDadosTable.pesquisarUnidadePorNumeroUni(getNumeroUnidade());
            PagamentoModelo modelo = new PagamentoModelo(getId(),modMor.getId(), modUni.getId(),getValorPagar(), getTipoPagamento(), getStatusPagamento(),getDescricao(), getDataPagamento());
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
		}
    
        public void alterar()
		{			
            MoradorModelo modMor = MoradorDadosTable.pesquisarMoradorIdPorNumDoc(getNumDoc());
            UnidadeModelo modUni = UnidadeDadosTable.pesquisarUnidadePorNumeroUni(getNumeroUnidade());

            PagamentoModelo modelo = new PagamentoModelo(getId(),modMor.getId(), modUni.getId(),getValorPagar(), getTipoPagamento(), getStatusPagamento(),getDescricao(), getDataPagamento());
            JOptionPane.showMessageDialog(null, modelo.toString());
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
        PagamentoModelo modelo = new PagamentoModelo();
        new PagamentoVisao(false,modelo); 
    }
}
