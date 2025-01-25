/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MoradorVisao.java
Data: 21/01/2025
--------------------------------------*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;


public class MoradorVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;

    public MoradorVisao(boolean alterar, UnidadeModelo modelo)
    {
        super("Registrar Moradores");
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
        private JTextField idJTF;
        
        private JLabel lblArea;

        public PainelCentro()
        {
            setLayout(new GridLayout(8,2));

            idJTF = new JTextField();
            //UnidadeFile unidadeFile = new UnidadeFile();
			idJTF.setText("" + 1);
        }

        public PainelCentro(UnidadeModelo modelo)
        {
            setLayout(new GridLayout(7,2));
            
            idJTF = new JTextField();
			idJTF.setText( "" + modelo.getId());

        }


    public int getId()
    {
        return Integer.parseInt( idJTF.getText().trim());
    }

    public void setId(int id)
    {
        idJTF.setText(""+id);
    }

 

    public boolean isEmpty(Object valor)
    {
        return String.valueOf(valor).equals("") || valor == null || String.valueOf(valor).equals("0") || String.valueOf(valor).equals("0.0");
    }


     public boolean verificarCampos()
        {
            if(isEmpty(getId()))
                    return false;
                return true; 
        }

        public void salvar()
		{			
			JOptionPane.showMessageDialog(null,"Salvar");
			//dispose();
		}
    
    public void alterar()
		{			
			JOptionPane.showMessageDialog(null,"Salvar");	
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
        new MoradorVisao(false,modelo); 
    }
}
