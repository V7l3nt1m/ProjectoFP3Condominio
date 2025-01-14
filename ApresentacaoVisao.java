/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: ApresentacaoVisao.java
Data: 07.06.2024
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager.*;


public class ApresentacaoVisao extends JFrame
{
    private PainelCentro painelCentro;
    private PainelSul painelSul;
    private PainelNorte painelNorte;


    public ApresentacaoVisao()
    {
        super("Tela de Boas Vindas");
        definirTema();
        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(painelSul = new PainelSul(), BorderLayout.SOUTH);

        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    class PainelNorte extends JPanel
    {
        private JLabel image, titulo, labelVazia;
        private Color customColor;
        
        public PainelNorte()
        {   
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            customColor = Color.decode("#e6e8e9");

            setBackground(customColor);
            add(image = new JLabel());
            image.setIcon(new ImageIcon("imagens/logoIco.png"));
            image.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o ícone horizontalmente

            add(labelVazia = new JLabel(" "));
            labelVazia.setAlignmentX(Component.CENTER_ALIGNMENT);

            titulo = new JLabel("Termos e Condições");
            titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            titulo.setFont(new Font("Monospaced", Font.BOLD,24)); // Centraliza o texto horizontalmente
            add(titulo);

            add(labelVazia = new JLabel(" "));
            labelVazia.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

     

    class PainelCentro extends JPanel implements ActionListener
    {
        JTextArea areaPrincipal;
        JCheckBox concordarJCB;
        JScrollPane scroll;

        public PainelCentro()
        {
            setLayout(new GridLayout(2,1));


            add(scroll = new JScrollPane(areaPrincipal = new JTextArea(21,20)));
            areaPrincipal.setText("\n\nBem vindo ao Sistema de Gestao de Padaria (SGP) \n\n" + "Este projeto tem o objetivo gerenciar as entradas, saidas, processo de producao \ne estoque de uma padaria\n\n" + 
            "Este projecto foi criado no ambito da cadeira de Fundamentos de Programação II no\n" + 
            "Curso de Engenharia Informática da UCAN. \n" + 
            "\nDados do Programador:\n" + "Nome: Valentim Loth Simão Prado\n" + "Nº Processo: 1000033031\n" + 
            "\nEste sistema é de uso exclusivo aos estudantes e professores desta instituição." + 
            "");
            
            areaPrincipal.setFont(new Font("Monospaced", Font.PLAIN,15));
            areaPrincipal.setOpaque(false); // Torna o JTextArea transparente
            areaPrincipal.setBorder(new EmptyBorder(-30,20,-100,-100));
            areaPrincipal.setFocusable(false);
            scroll.setBorder(BorderFactory.createEmptyBorder());

            add(concordarJCB = new JCheckBox("Concordo com os termos de uso!"));
            concordarJCB.setBorder(new EmptyBorder(0,20,0,0));
            concordarJCB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == concordarJCB)
            {
                if(concordarJCB.isSelected())
                    painelSul.activarBotao(true);
                else
                    painelSul.activarBotao(false);
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton entrarJB, sairJB;

        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar"));
            add(sairJB = new JButton("Sair"));

            activarBotao(false);
            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == entrarJB)
            {   
               new LoginVisao();
               dispose();
            }
            else if (evt.getSource() == sairJB)
            {
                System.exit(0);
            }
        }

        public void activarBotao(boolean status)
        {
            entrarJB.setEnabled(status);
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
        new ApresentacaoVisao();
    }
}