/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: InformacoesMoradoresVisao.java
Data: 07/02/2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.util.*;

public class InformacoesMoradoresVisao extends JFrame
{
    private PainelCentro painelCentro;
    private PainelCentro2 painelCentro2;

    private JTabbedPane tabPanel;


    public InformacoesMoradoresVisao()
    {
        super("Registro de Moradores");
        definirTema();
        getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(painelCentro2 = new PainelCentro2(), BorderLayout.CENTER);

        tabPanel = new JTabbedPane();
        tabPanel.addTab("Registro de Moradores", painelCentro);
        tabPanel.addTab("Pesquisa Registro de Moradores", painelCentro2);


        getContentPane().add(tabPanel, BorderLayout.NORTH);

        setSize(1000,420);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    class PainelCentro extends JPanel implements MouseListener, ActionListener
    {
        private String[] colunas = {
            "ID", 
            "Nome", 
            "Tipo de Doc", 
            "Numero do Documento", 
            "Telefone", 
            "Email", 
            "Numero da Unidade", 
            "Numero da Porta", 
            "Responsavel", 
            "BI do Responsavel", 
            "Data de Cadastro"
        };
        private JScrollPane sp;
        private JTable tabelaUnidades;
        private JPopupMenu popMenu;
        private JMenuItem editar, eliminar;
        private Vector<String> dados = new Vector();

        public PainelCentro()
        {
            setLayout(new GridLayout(1,1));
            tabelaUnidades = new JTable(MoradorDadosTable.listarMoradores(), colunas);
            sp = new JScrollPane(tabelaUnidades);
            add(sp);

            popMenu = new JPopupMenu();
            popMenu.add(editar = new JMenuItem("Editar"));
            popMenu.add(eliminar = new JMenuItem("Eliminar"));

            eliminar.addActionListener(this);
            editar.addActionListener(this);
            tabelaUnidades.addMouseListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == editar)
            {
                int selectedRow = tabelaUnidades.getSelectedRow();
                String id = ""+tabelaUnidades.getValueAt(selectedRow,0);
                MoradorModelo modelo;
                modelo = MoradorDadosTable.pesquisarMoradorPorId(id);
                dispose();
                new MoradorVisao(true, modelo);
            }
            else
            {
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja Eliminar os dados","Eliminar dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                System.out.println(resposta);
                if(resposta == 2 || resposta == -1)
                {
                    JOptionPane.showMessageDialog(null, "Operacao cancelada", "Eliminar os dados", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int selectedRow = tabelaUnidades.getSelectedRow();
                    String id = ""+tabelaUnidades.getValueAt(selectedRow,0);
                    MoradorModelo modelo;
                    modelo = MoradorDadosTable.pesquisarMoradorPorId(id);
                    modelo.eliminar();
                    dispose();
                }
            }
            
        }

        public void mousePressed(MouseEvent evt)
        {
           showPopup(evt);
        }

        public void mouseReleased(MouseEvent evt)
        {
           showPopup(evt);
        }

        public void mouseExited(MouseEvent evt)
        {
           showPopup(evt);
        }

         public void mouseEntered(MouseEvent evt)
        {
           showPopup(evt);
        }

        public void mouseClicked(MouseEvent evt)
        {
           showPopup(evt);
        }


        private void showPopup(MouseEvent evt)
        {
            if(evt.isPopupTrigger() && evt.getComponent() instanceof JTable)
            {
                int row = tabelaUnidades.rowAtPoint(evt.getPoint());

                if(row >= 0 )
                {
                    tabelaUnidades.setRowSelectionInterval(row, row);
                    popMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        }  
    }
     
    class PainelCentro2 extends JPanel implements ActionListener
    {
        private JLabel pesqlbl;
        private JTextField pesquisarJTF;
        private JButton pesquisarBtn;

        public PainelCentro2()
        {
            setLayout(new FlowLayout());
            add(pesqlbl = new JLabel("Pesquisa por:"));
            add(pesquisarJTF = new JTextField(10));
            add(pesquisarBtn = new JButton("Pesquisar"));
            pesquisarBtn.addActionListener(this);
        }

        public String getPesquisa()
        {
            return pesquisarJTF.getText().trim();
        }

        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getSource() == pesquisarBtn)
            {
                JOptionPane.showMessageDialog(null, MoradorDadosTable.pesquisarMoradorIdPorNumDoc(getPesquisa()).toString());
            }
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
        new InformacoesMoradoresVisao();
    }
}