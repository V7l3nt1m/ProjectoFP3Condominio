/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MenuPrincipal.java
Data: 18.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.File;



public class MenuPrincipal extends JFrame
{  
    private PainelSuperior pSuper;
    private PainelCentro pCentro;


    public MenuPrincipal(String user)
    {
        super("Menu Principal | Usuario: " + user);
        definirTema();
        setLayout(new BorderLayout(1,3));
        
        getContentPane().add(pSuper = new PainelSuperior(), BorderLayout.NORTH);
        getContentPane().add(pCentro = new PainelCentro(), BorderLayout.CENTER);


        setSize(1350, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class PainelSuperior extends JPanel
    {
        private JButton btnUnidade,btnMorador,btnDespesa,btnManutencao,btnTabelas,btnAjuda,btnSair;

        private JLabel logo, nomeUser, lblUni, lblMorador, lblDespesa,lblManutencao, lblTable, lblAjuda, lblSair;
        private ImageIcon imagemLogo, despesaImg, sairImg, ajudaImg,manutencaoImg, unidadeImg,tableImg, moradorImg;
        private ManipuladorEventos manipulador;
        private Color customColor, customColor2;
        private JPanel operJp;
        private JPopupMenu popupMenu1,popupMenu2, popupMenu3, popupMenu4, popupMenu5, popupMenu6;
        private JMenuItem itemCadastrarUnidade, itemVerUnidades;//unidade
        private JMenuItem itemCadastrarMorador, itemVerMoradores;//moradores
        private JMenuItem itemCadastrarDespesas, itemVerDespesas;//despesas
        private JMenuItem itemCadastrarManutencao, itemVerManutencao;//manutencao

        private JMenuItem itemSobreAutor, itemSobreSoftw; //aba sobre

        private JMenuItem itemTipoUnidade, itemBloco, itemTipoDocumento, itemCategoriaDespesa, itemStatusManutencao;//tabelas

        public PainelSuperior()
        {         
            customColor = Color.decode("#F5F5F5");
            customColor2 = Color.decode("#932C10");
            setBackground(customColor);   
            setBorder(BorderFactory.createEmptyBorder(0,0,-60,1));
            setLayout(new GridLayout(2,21));

            popupMenu1 = new JPopupMenu();
            itemCadastrarUnidade = new JMenuItem("Nova Entrada");
            itemVerUnidades = new JMenuItem("Ver Registro");

            popupMenu1.add(itemCadastrarUnidade);
            popupMenu1.add(new JSeparator());
            popupMenu1.add(itemVerUnidades);
            
            lblUni = new JLabel("Unidade");
            lblUni.setHorizontalAlignment(SwingConstants.CENTER);
            lblUni.setVerticalAlignment(SwingConstants.TOP);
            unidadeImg = new ImageIcon("imagens/cadastro.png");
            btnUnidade = new JButton(unidadeImg);
            btnUnidade.setBackground(customColor2);

//tabelas
            lblTable = new JLabel("Tabelas");
            lblTable.setHorizontalAlignment(SwingConstants.CENTER);
            lblTable.setVerticalAlignment(SwingConstants.TOP);
            tableImg = new ImageIcon("imagens/table.png");
            btnTabelas = new JButton(tableImg);
            btnTabelas.setBackground(customColor2);


            popupMenu5 = new JPopupMenu();
            itemTipoUnidade = new JMenuItem("Tipo de Unidade");
            itemBloco = new JMenuItem("Bloco");
            itemTipoDocumento = new JMenuItem("Tipo de Documento");
            itemCategoriaDespesa = new JMenuItem("Categoria de Despesas");
            itemStatusManutencao = new JMenuItem("Status da Manutencao");

            popupMenu5.add(itemTipoUnidade);
            popupMenu5.add(new JSeparator());
            popupMenu5.add(itemBloco);
            popupMenu5.add(new JSeparator());
            popupMenu5.add(itemTipoDocumento);
            popupMenu5.add(new JSeparator());
            popupMenu5.add(itemCategoriaDespesa);
            popupMenu5.add(new JSeparator());
            popupMenu5.add(itemStatusManutencao);

//fim das tabelas

            lblAjuda = new JLabel("Ajuda");
            lblAjuda.setHorizontalAlignment(SwingConstants.CENTER);
            lblAjuda.setVerticalAlignment(SwingConstants.TOP);
            ajudaImg = new ImageIcon("imagens/ajuda.png");
            btnAjuda = new JButton(ajudaImg);
            btnAjuda.setBackground(customColor2);

            popupMenu6 = new JPopupMenu();
            itemSobreAutor = new JMenuItem("Sobre o Autor");
            itemSobreSoftw = new JMenuItem("Sobre o Software");


            popupMenu6 = new JPopupMenu();
            popupMenu6.add(itemSobreAutor);
            popupMenu6.add(new JSeparator());
            popupMenu6.add(itemSobreSoftw);

            lblMorador = new JLabel("Morador");
            lblMorador.setHorizontalAlignment(SwingConstants.CENTER);
            lblMorador.setVerticalAlignment(SwingConstants.TOP);
            moradorImg = new ImageIcon("imagens/morador.png");
            btnMorador = new JButton(moradorImg);
            btnMorador.setBackground(customColor2);

            lblDespesa = new JLabel("Despesas");
            lblDespesa.setHorizontalAlignment(SwingConstants.CENTER);
            lblDespesa.setVerticalAlignment(SwingConstants.TOP);
            despesaImg = new ImageIcon("imagens/despesas.png");
            btnDespesa = new JButton(despesaImg);
            btnDespesa.setBackground(customColor2);

            popupMenu3 = new JPopupMenu();
            popupMenu3.add(itemCadastrarDespesas = new JMenuItem("Nova Entrada"));
            popupMenu3.add(new JSeparator());
            popupMenu3.add(itemVerDespesas = new JMenuItem("Ver Registro"));

            
            popupMenu2 = new JPopupMenu();
            popupMenu2.add(itemCadastrarMorador = new JMenuItem("Nova Entrada"));
            popupMenu2.add(new JSeparator());
            popupMenu2.add(itemVerMoradores = new JMenuItem("Ver Registro"));

            lblManutencao = new JLabel("Manutencao");
            lblManutencao.setHorizontalAlignment(SwingConstants.CENTER);
            lblManutencao.setVerticalAlignment(SwingConstants.TOP);
            manutencaoImg = new ImageIcon("imagens/manu.png");
            btnManutencao = new JButton(manutencaoImg);
            btnManutencao.setBackground(customColor2);
            
            popupMenu4 = new JPopupMenu();
            popupMenu4.add(itemCadastrarManutencao = new JMenuItem("Nova Entrada"));
            popupMenu4.add(new JSeparator());
            popupMenu4.add(itemVerManutencao = new JMenuItem("Ver Registro"));


            lblSair = new JLabel("Sair");
            lblSair.setHorizontalAlignment(SwingConstants.CENTER);
            lblSair.setVerticalAlignment(SwingConstants.TOP);
            sairImg = new ImageIcon("imagens/desligar.png");
            btnSair = new JButton(sairImg);
            btnSair.setBackground(customColor2);
            btnSair.setPreferredSize(new Dimension(50,50));


            add(btnUnidade);
            add(btnMorador);
            add(btnDespesa);
            add(btnManutencao);
            add(btnTabelas);
            add(btnAjuda);
            add(btnSair);
            for(int i = 0; i<10;i++)
                add(new JLabel());

            add(lblUni);
            add(lblMorador);
            add(lblDespesa);
            add(lblManutencao);
            add(lblTable);
            add(lblAjuda);
            add(lblSair);

             for(int i = 0; i<10;i++)
                add(new JLabel());
            
          
            manipulador = new ManipuladorEventos();

            btnUnidade.addActionListener(manipulador);
            btnTabelas.addActionListener(manipulador);
            btnAjuda.addActionListener(manipulador);
            btnMorador.addActionListener(manipulador);
            btnDespesa.addActionListener(manipulador);
            btnManutencao.addActionListener(manipulador);
            btnSair.addActionListener(manipulador);

            itemCadastrarUnidade.addActionListener(manipulador);
            itemVerUnidades.addActionListener(manipulador);
            itemCadastrarMorador.addActionListener(manipulador);
            itemVerMoradores.addActionListener(manipulador);
            itemCadastrarDespesas.addActionListener(manipulador);
            itemVerDespesas.addActionListener(manipulador);
            itemCadastrarManutencao.addActionListener(manipulador);
            itemVerManutencao.addActionListener(manipulador);

            itemTipoUnidade.addActionListener(manipulador);
            itemBloco.addActionListener(manipulador);
            itemTipoDocumento.addActionListener(manipulador);
            itemCategoriaDespesa.addActionListener(manipulador);
            itemStatusManutencao.addActionListener(manipulador);

        }

         private class ManipuladorEventos implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                if(evt.getSource() == btnSair)
                {
                    int resultado = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","Saindo",JOptionPane.YES_NO_OPTION);
                    if(resultado == 0)
                    {
                        System.exit(0);
                    }
                }
                else if(evt.getSource() == btnTabelas)
                {
                    popupMenu5.show(btnTabelas, 0, btnTabelas.getHeight());
                }
                else if(evt.getSource() == itemTipoUnidade)
                {
                    Tabela2.editarNovosItems("TipoUnidade.tab", "Novo Tipo de Unidade");
                }
                 else if(evt.getSource() == itemBloco)
                {
                    Tabela2.editarNovosItems("Bloco.tab", "Novo BLoco");
                }
                else if(evt.getSource() == itemTipoDocumento)
                {
                    Tabela2.editarNovosItems("TipoDocumento.tab", "Novo Tipo de Documento");
                }
                else if(evt.getSource() == itemCategoriaDespesa)
                {
                    Tabela2.editarNovosItems("CategoriaDespesa.tab", "Nova Categoria Despesa");
                }
                else if(evt.getSource() == itemStatusManutencao)
                {
                    Tabela2.editarNovosItems("StatusManutencao.tab", "Novo Status de Manutencao");
                }
                else if(evt.getSource() == btnAjuda)
                {
                    popupMenu6.show(btnAjuda,0, btnAjuda.getHeight());
                }
                else if(evt.getSource() == btnUnidade)
                {
                    popupMenu1.show(btnUnidade,0, btnUnidade.getHeight());
                }
                else if(evt.getSource() == btnMorador)
                {
                    popupMenu2.show(btnMorador, 0,btnMorador.getHeight());
                }
                else if(evt.getSource() == btnDespesa)
                {
                    popupMenu3.show(btnDespesa, 0,btnMorador.getHeight());
                }
                else if(evt.getSource() == btnManutencao)
                {
                    popupMenu4.show(btnManutencao, 0,btnMorador.getHeight());
                }
                else if(evt.getSource() == itemCadastrarUnidade)
                {
                    UnidadeModelo modelo = new UnidadeModelo();
                    new UnidadeVisao(false,modelo);
                }
                else if(evt.getSource() == itemVerUnidades)
                    new InformacoesUnidadesVisao();
                else if(evt.getSource() == itemCadastrarMorador)
                {
                    UnidadeModelo modelo = new UnidadeModelo();
                    new MoradorVisao(false,modelo);
                }
                else if(evt.getSource() == itemVerMoradores)
                    JOptionPane.showMessageDialog(null,"Item Ver Registro de Moradores");
                else if(evt.getSource() == itemCadastrarDespesas)
                    JOptionPane.showMessageDialog(null,"Item Cadastrar Despesas");
                else if(evt.getSource() == itemVerDespesas)
                    JOptionPane.showMessageDialog(null,"Item Ver Registro de Despesas");
                else if(evt.getSource() == itemCadastrarManutencao)
                    JOptionPane.showMessageDialog(null,"Item Cadastrar Manutencao");
                else if(evt.getSource() == itemVerManutencao)
                    JOptionPane.showMessageDialog(null,"Item Ver Registro de Manutencao");
            }
        }

    }

    private class PainelCentro extends JPanel
    {
        private JLabel fundo;
        private ImageIcon logo,logoRedim;
        private Image redimLogo,image; 

        public PainelCentro()
        {
            setBackground(Color.WHITE);
            logo = new ImageIcon("imagens/logo.png");
            image = logo.getImage();            
            
            redimLogo = image.getScaledInstance(800,700, Image.SCALE_SMOOTH);
            logoRedim = new ImageIcon(redimLogo);
            fundo = new JLabel(logoRedim);

            add(fundo);
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
        new MenuPrincipal("User nao logado"); 
    }
}

