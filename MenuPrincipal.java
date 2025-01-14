/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MenuPrincipal.java
Data: 25.05.2024
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
        super("Menu Principal | Operador: " + user);
        definirTema();
        //separador
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
        private JButton btnOperacoes,btnEstoque,btnProducao,btnTabelas,btnAjuda,btnSair;
        private JLabel logo, nomeUser, lblOper, lblEstoq, lblProdu, lblTable, lblAjuda, lblSair;
        private ImageIcon imagemLogo, operLogo, sairImg, ajudaImg, estoqueImg,tableImg, produImg;
        private ManipuladorEventos manipulador;
        private Color customColor, customColor2;
        private JPanel operJp;
        private JPopupMenu popupMenu1,popupMenu2, popupMenu3, popupMenu4, popupMenu5, popupMenu6, popupMenu7;
        private JMenuItem itemProdutos, itemMaterial, itemVendas;
        private JMenuItem itemNovaEntrada,itemEditarEntrada, itemNovaVenda, itemSobreAutor, itemSobreSoftw;
        private JMenuItem itemMateriaisPrima, itemUnidadeMedida, itemProduto, itemNivelMinimo, itemDioceses, itemParoquias;

        private JButton btnDefesa;
        private JMenuItem itemCadastroDefesa, itemPesquisaDefesa;
        private JSeparator sep1, sep2, sep3, sep4,sep5;


        public PainelSuperior()
        {         
            customColor = Color.decode("#F5F5F5");
            customColor2 = Color.decode("#932C10");
            setBackground(customColor);   
            setBorder(BorderFactory.createEmptyBorder(0,0,-60,1));
            setLayout(new GridLayout(2,21));

            sep1 = new JSeparator();
            sep2 = new JSeparator();
            sep3 = new JSeparator();
            sep4 = new JSeparator();
            sep5 = new JSeparator();


            popupMenu1 = new JPopupMenu();
            itemNovaEntrada = new JMenuItem("Nova Entrada");
            itemEditarEntrada = new JMenuItem("Editar Entrada");
            itemNovaVenda = new JMenuItem("Nova Venda");

            popupMenu1.add(itemNovaEntrada);
            popupMenu1.add(sep1);
            popupMenu1.add(itemNovaVenda);
            
            lblOper = new JLabel("Operações");
            lblOper.setHorizontalAlignment(SwingConstants.CENTER);
            lblOper.setVerticalAlignment(SwingConstants.TOP);
            operLogo = new ImageIcon("imagens/cadastro.png");
            btnOperacoes = new JButton(operLogo);
            btnOperacoes.setBackground(customColor2);

//tabelas
            lblTable = new JLabel("Tabelas");
            lblTable.setHorizontalAlignment(SwingConstants.CENTER);
            lblTable.setVerticalAlignment(SwingConstants.TOP);
            tableImg = new ImageIcon("imagens/table.png");
            btnTabelas = new JButton(tableImg);
            btnTabelas.setBackground(customColor2);

           

            popupMenu4 = new JPopupMenu();
            itemMateriaisPrima = new JMenuItem("Materias Prima");
            itemUnidadeMedida = new JMenuItem("Unidades de Medida");
            itemProduto = new JMenuItem("Produtos");
            itemNivelMinimo = new JMenuItem("Nivel Minimo");

            popupMenu4.add(itemMateriaisPrima);
            popupMenu4.add(sep2);
            popupMenu4.add(itemUnidadeMedida);
            popupMenu4.add(sep3);
            popupMenu4.add(itemProduto);
            popupMenu4.add(new JSeparator());
            popupMenu4.add(itemNivelMinimo);

//fim das tabelas

            lblAjuda = new JLabel("Ajuda");
            lblAjuda.setHorizontalAlignment(SwingConstants.CENTER);
            lblAjuda.setVerticalAlignment(SwingConstants.TOP);
            ajudaImg = new ImageIcon("imagens/ajuda.png");
            btnAjuda = new JButton(ajudaImg);
            btnAjuda.setBackground(customColor2);

            popupMenu5 = new JPopupMenu();
            itemSobreAutor = new JMenuItem("Sobre o Autor");
            itemSobreSoftw = new JMenuItem("Sobre o Software");


            popupMenu5.add(itemSobreAutor);
            popupMenu5.add(sep4);
            popupMenu5.add(itemSobreSoftw);

            lblProdu = new JLabel("Producao");
            lblProdu.setHorizontalAlignment(SwingConstants.CENTER);
            lblProdu.setVerticalAlignment(SwingConstants.TOP);
            produImg = new ImageIcon("imagens/product.png");
            btnProducao = new JButton(produImg);
            btnProducao.setBackground(customColor2);

            lblEstoq = new JLabel("Estoque/Registros");
            lblEstoq.setHorizontalAlignment(SwingConstants.CENTER);
            lblEstoq.setVerticalAlignment(SwingConstants.TOP);
            estoqueImg = new ImageIcon("imagens/estoque.png");
            btnEstoque = new JButton(estoqueImg);
            btnEstoque.setBackground(customColor2);
            
            popupMenu2 = new JPopupMenu();
            popupMenu2.add(itemProdutos = new JMenuItem("Produtos"));
            popupMenu2.add(new JSeparator());
            popupMenu2.add(itemMaterial = new JMenuItem("Materia Prima"));
            popupMenu2.add(new JSeparator());
            popupMenu2.add(itemVendas = new JMenuItem("Vendas"));

            lblSair = new JLabel("Sair");
            lblSair.setHorizontalAlignment(SwingConstants.CENTER);
            lblSair.setVerticalAlignment(SwingConstants.TOP);
            sairImg = new ImageIcon("imagens/desligar.png");
            btnSair = new JButton(sairImg);
            btnSair.setBackground(customColor2);
            btnSair.setPreferredSize(new Dimension(50,50));

            itemDioceses = new JMenuItem("Dioceses");
            itemParoquias = new JMenuItem("Paroquias");
            popupMenu4.add(itemDioceses);
            popupMenu4.add(itemParoquias);


            popupMenu7 = new JPopupMenu();

            btnDefesa = new JButton("Defesa");
            itemCadastroDefesa = new JMenuItem("Cadastro");
            itemPesquisaDefesa = new JMenuItem("Pesquisa");
            popupMenu7.add(itemCadastroDefesa);
            popupMenu7.add(itemPesquisaDefesa);

            add(btnOperacoes);
            add(btnEstoque);
            add(btnProducao);
            add(btnTabelas);
            add(btnAjuda);
            add(btnSair);
            add(btnDefesa);
            for(int i = 0; i<10;i++)
                add(new JLabel());

            add(lblOper);
            add(lblEstoq);
            add(lblProdu);
            add(lblTable);
            add(lblAjuda);
            add(lblSair);

             for(int i = 0; i<10;i++)
                add(new JLabel());
            
          
            manipulador = new ManipuladorEventos();

            btnSair.addActionListener(manipulador);
            btnTabelas.addActionListener(manipulador);
            btnAjuda.addActionListener(manipulador);
            btnOperacoes.addActionListener(manipulador);
            btnEstoque.addActionListener(manipulador);
            btnProducao.addActionListener(manipulador);
            itemMaterial.addActionListener(manipulador);
            itemMateriaisPrima.addActionListener(manipulador);
            itemUnidadeMedida.addActionListener(manipulador);
            itemProduto.addActionListener(manipulador);
            itemProdutos.addActionListener(manipulador);
            itemNovaEntrada.addActionListener(manipulador);
            itemNivelMinimo.addActionListener(manipulador);
            itemNovaVenda.addActionListener(manipulador);
            itemVendas.addActionListener(manipulador);

            itemDioceses.addActionListener(manipulador);
            itemParoquias.addActionListener(manipulador);
            itemCadastroDefesa.addActionListener(manipulador);
            itemPesquisaDefesa.addActionListener(manipulador);
            btnDefesa.addActionListener(manipulador);
        }

         private class ManipuladorEventos implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                EstoqueFile estoqueFile = new EstoqueFile();
                boolean verifProdutos = estoqueFile.verificarStoqueProdutos();
                boolean verifMateria = estoqueFile.verificarStoqueMatPrim();

                if(evt.getSource() == btnSair)
                {
                    int resultado = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","Saindo",JOptionPane.YES_NO_OPTION);
                    if(resultado == 0)
                    {
                        System.exit(0);
                    }
                }
                else if(evt.getSource() == btnDefesa)
                {
                    popupMenu7.show(btnDefesa,0,btnDefesa.getHeight());
                }
                else if(evt.getSource() == itemPesquisaDefesa)
                {
                    new PesquisarDados();
                }
                else if(evt.getSource() == itemCadastroDefesa)
                {
                    new ProducaoVisaoDefesa();
                }
                else if(evt.getSource() == itemDioceses)
                {
                    Tabela2.editarNovosItems("Diocese.tab", "Nova Diocese/Arquidiocese");
                }
                else if(evt.getSource() == itemParoquias)
                {
                    Tabela3_2.editarNovosItems("Diocese.tab","Paroquia.tab","Dioceses/Arquidioceses","Paroquias","Nova Paroquia");
                }       
                else if(evt.getSource() == btnTabelas)
                {
                    popupMenu4.show(btnTabelas, 0, btnTabelas.getHeight());
                }
                else if(evt.getSource() == itemMateriaisPrima)
                {
                    Tabela2.editarNovosItems("MateriaPrima.tab", "Nova Materia Prima");
                }
                 else if(evt.getSource() == itemProduto)
                {
                    Tabela2.editarNovosItems("Produto.tab", "Novo Produto");
                }
                else if(evt.getSource() == itemUnidadeMedida)
                {
                    Tabela3_2.editarNovosItems("MateriaPrima.tab", "UnidadeMedida.tab", "Materias Primas", "Unidades de Medidas", "Nova Unidade de Medida");
                }
                else if(evt.getSource() == itemNivelMinimo)
                {
                    Tabela3_2.editarNovosItems("MateriaPrima.tab", "NivelMinimo.tab", "Materias Primas", "Nivel Minimo", "Novo Nivel");
                }
                else if(evt.getSource() == btnAjuda)
                {
                    popupMenu5.show(btnAjuda,0, btnAjuda.getHeight());
                }
                else if(evt.getSource() == btnOperacoes)
                {
                    popupMenu1.show(btnOperacoes,0, btnOperacoes.getHeight());
                }
                else if(evt.getSource() == btnEstoque)
                {
                    popupMenu2.show(btnEstoque, 0,btnEstoque.getHeight());
                }
                else if(evt.getSource() == itemProdutos)
                    new EstoqueProdutoVisao();
                else if(evt.getSource() == itemVendas)
                    new VendaRegistroVisao();
                else if(evt.getSource() == itemMaterial)
                {
                    File estoqueFileE = new File("EstoqueFile.dat");
                    File entradafileE = new File("EntradaFile.dat");
                    if(estoqueFileE.exists() && estoqueFileE.exists())
                        new EstoqueVisao();
                    else
                        JOptionPane.showMessageDialog(null, "Não há registros", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if(evt.getSource() == itemNovaEntrada)
                {
                    new EntradaVisao(false, new EntradaModelo());
                }
                else if(evt.getSource() == btnProducao)
                    if(verifMateria)
                    {
                        new ProducaoVisao(false, new ProducaoModelo());
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Nao ha Materia Prima em Estoque", "Verificador de Estoque", JOptionPane.ERROR_MESSAGE);
                else if(evt.getSource() == itemNovaVenda)
                     if(verifProdutos)
                    {
                        new VendaVisao(false, new VendaModelo());
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Nao ha produtos em Estoque", "Verificador de Estoque", JOptionPane.ERROR_MESSAGE);
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

