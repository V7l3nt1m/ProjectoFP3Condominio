/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: UnidadeVisao.java
Data: 20/01/2025
--------------------------------------*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.time.LocalDate;


public class UnidadeVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;

    public UnidadeVisao(boolean alterar, UnidadeModelo modelo)
    {
        super("Registrar Unidades Habitacionais");
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
            casasIco = new ImageIcon("imagens/casas.jpg");
            
            lblCasa = new JLabel();
            lblCasa.setIcon(casasIco);
            add(lblCasa);
        }
    }

    public class PainelCentro extends JPanel
    {
        private JTextField idJTF, areaJTF,numeroUniJTF, andaresJTF, numQuartosJTF, garagemCapacidadeJTF, andaresDisponivelJTF;
        private JComboBox tipoUnidadeJCB, blocoJCB,statusUnidadeJCB;
        
        private JLabel lblArea, lblNumeroUni, lblAndares, lblNumQuartos, lblGaragemCap, lblAndaresDisp,lblStatusUnidade;
        private JLabel lblTipoUnidade, lblBloco;
        private String status[] = {"indisponivel", "disponivel"};

        public PainelCentro()
        {
            setLayout(new GridLayout(8,2));
            lblTipoUnidade = new JLabel("Tipo de Unidade");
            tipoUnidadeJCB = UInterfaceBox.createJComboBoxsTabela2("TipoUnidade.tab");

            lblBloco = new JLabel("Bloco");
            blocoJCB = UInterfaceBox.createJComboBoxsTabela2("Bloco.tab");

            idJTF = new JTextField();
            //UnidadeFile unidadeFile = new UnidadeFile();
			idJTF.setText("" + 1);

            lblArea = new JLabel("Area (M²)");
            areaJTF = new JTextField();

            lblNumeroUni = new JLabel("Numero da Unidade");
            numeroUniJTF = new JTextField();

            lblAndares = new JLabel("Numero de Andares");
            andaresJTF = new JTextField();

            lblNumQuartos = new JLabel("Numero de Quartos");
            numQuartosJTF = new JTextField();

            lblGaragemCap = new JLabel("Capacidade da Garagem");
            garagemCapacidadeJTF = new JTextField();

            lblStatusUnidade = new JLabel("Estado da Unidade");
            statusUnidadeJCB = new JComboBox(status);
            statusUnidadeJCB.setFocusable(false);


            add(lblTipoUnidade);
            add(tipoUnidadeJCB);

            add(lblBloco);
            add(blocoJCB);

            add(lblAndares);
            add(andaresJTF);
            
            add(lblArea);
            add(areaJTF);

            add(lblNumeroUni);
            add(numeroUniJTF);

            add(lblGaragemCap);
            add(garagemCapacidadeJTF);

            add(lblNumQuartos);
            add(numQuartosJTF);

            add(lblStatusUnidade);
            add(statusUnidadeJCB);
            
        }

        public PainelCentro(UnidadeModelo modelo)
        {
            setLayout(new GridLayout(7,2));
            lblTipoUnidade = new JLabel("Tipo de Unidade");
            tipoUnidadeJCB = UInterfaceBox.createJComboBoxsTabela2("TipoUnidade.tab");
            tipoUnidadeJCB.setSelectedItem(modelo.getTipoUnidade());
        
            lblBloco = new JLabel("Bloco");
            blocoJCB = UInterfaceBox.createJComboBoxsTabela2("Bloco.tab");
            blocoJCB.setSelectedItem(modelo.getBloco());

            idJTF = new JTextField();
			idJTF.setText( "" + modelo.getId());

            lblArea = new JLabel("Area (M²)");
            areaJTF = new JTextField();
            areaJTF.setText(""+modelo.getArea());

            lblNumeroUni = new JLabel("Numero da Unidade");
            numeroUniJTF = new JTextField();
            numeroUniJTF.setText(""+modelo.getNumeroUni());

            lblAndares = new JLabel("Numero de Andares");
            andaresJTF = new JTextField();
            andaresJTF.setText(""+modelo.getAndares());

            lblNumQuartos = new JLabel("Numero de Quartos");
            numQuartosJTF = new JTextField();
            numQuartosJTF.setText(""+modelo.getNumQuartos());

            lblGaragemCap = new JLabel("Capacidade da Garagem");
            garagemCapacidadeJTF = new JTextField();
            garagemCapacidadeJTF.setText(""+modelo.getGaragemCapaci());

            lblAndaresDisp = new JLabel();
            andaresDisponivelJTF = new JTextField();
            andaresDisponivelJTF.setText(""+modelo.getAndaresDisponiveis());

            lblStatusUnidade = new JLabel("Estado da Unidade");
            statusUnidadeJCB = new JComboBox(status);
            statusUnidadeJCB.setSelectedItem(modelo.getStatusUnidade());

            add(lblTipoUnidade);
            add(tipoUnidadeJCB);

            add(lblBloco);
            add(blocoJCB);
            
            add(lblArea);
            add(areaJTF);

            add(lblNumeroUni);
            add(numeroUniJTF);

            add(lblAndares);
            add(andaresJTF);

            add(lblAndaresDisp);
            add(andaresDisponivelJTF);

            add(lblGaragemCap);
            add(garagemCapacidadeJTF);

            add(lblNumQuartos);
            add(numQuartosJTF);

            add(lblStatusUnidade);
            add(statusUnidadeJCB);
        }


    public int getId()
    {
        return Integer.parseInt( idJTF.getText().trim());
    }

    public void setId(int id)
    {
        idJTF.setText(""+id);
    }

    public String getTipoUnidade()
    {
        return String.valueOf(tipoUnidadeJCB.getSelectedItem()); 
    }

    public void setTipoUnidade(String newTipoUnidade)
    {
        tipoUnidadeJCB.setSelectedItem(newTipoUnidade);
    }

    public String getBloco()
    {
        return String.valueOf(blocoJCB.getSelectedItem()); 
    }

    public void setBloco(String newBloco)
    {
        blocoJCB.setSelectedItem(newBloco);
    }

    public double getArea()
    {
        return Double.parseDouble(areaJTF.getText().trim());
    }

    public void setArea(double newArea)
    {
        areaJTF.setText("" + newArea);
    }

    public String getNumeroUni()
    {
        return numeroUniJTF.getText().trim();
    }

    public void setNumeroUni(String newNumeroUni)
    {
        numeroUniJTF.setText(newNumeroUni);
    }

    public int getAndares()
    {
        return Integer.parseInt( andaresJTF.getText().trim());
    }

    public void setAndares(int newAndares)
    {
        andaresJTF.setText(""+newAndares);
    }

    public int getAndaresDisponiveis()
    {
        return Integer.parseInt( andaresDisponivelJTF.getText().trim());
    }

    public void setAndaresDisponiveis(int newAndaresDispo)
    {
        andaresDisponivelJTF.setText(""+newAndaresDispo);
    }

    public int getNumQuartos()
    {
        return Integer.parseInt( numQuartosJTF.getText().trim());
    }

    public void setNumQuartos(int newNumQuartos)
    {
        numQuartosJTF.setText(""+newNumQuartos);
    }

    public int getGaragemCapaci()
    {
        return Integer.parseInt( garagemCapacidadeJTF.getText().trim());
    }

    public void setGaragemCapaci(int newGaragenCapac)
    {
        garagemCapacidadeJTF.setText(""+newGaragenCapac);
    }

    public String getStatusUnidade()
    {
        return String.valueOf(statusUnidadeJCB.getSelectedItem()); 
    }

    public void setStatusUnidade(String newStatusUnidade)
    {
        statusUnidadeJCB.setSelectedItem(newStatusUnidade);
    }

    public boolean isEmpty(Object valor)
    {
        return String.valueOf(valor).equals("") || valor == null || String.valueOf(valor).equals("0") || String.valueOf(valor).equals("0.0");
    }


     public boolean verificarCampos()
        {
            if(isEmpty(getId()) || isEmpty(getTipoUnidade()) || isEmpty(getBloco()) || 
            isEmpty(getArea()) || isEmpty(getNumeroUni()) || isEmpty(getAndares()) || isEmpty(getNumQuartos())
            || isEmpty(getGaragemCapaci()) )
                    return false;
                return true; 
        }

        public void salvar()
		{			
			UnidadeModelo modelo = new UnidadeModelo(getId(), getAndares(), getNumQuartos(),getAndares(), getArea(),
            getNumeroUni(), getTipoUnidade(), getBloco(),getGaragemCapaci(), getStatusUnidade());
			JOptionPane.showMessageDialog(null, modelo.toString() );
			//dispose();
		}
    
    public void alterar()
		{			
			UnidadeModelo modelo = new UnidadeModelo(getId(), getAndares(), getNumQuartos(),getAndaresDisponiveis(), getArea(),
            getNumeroUni(), getTipoUnidade(), getBloco(),getGaragemCapaci(), getStatusUnidade());
			JOptionPane.showMessageDialog(null, modelo.toString() );		
			//dispose();
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
        UnidadeModelo modelo = new UnidadeModelo();
        new UnidadeVisao(false,modelo); 
    }
}
