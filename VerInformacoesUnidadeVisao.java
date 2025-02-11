/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: VerInformacoesUnidadeVisao.java
Data: 11/02/2025
--------------------------------------*/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class VerInformacoesUnidadeVisao extends JFrame
{
    private JLabel logo, informacoeslbl;
    private ImageIcon iconImg;


    private UnidadeFile file;

    public VerInformacoesUnidadeVisao(UnidadeModelo modelo)
    {
        super("Ver Informacoes das unidades");
        definirTema();
        setLayout(null);
        setResizable(false);
        instanciarObj(modelo);
        setSize(1100,500);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void instanciarObj(UnidadeModelo modelo)
    {
        Color customColor = Color.decode("#B83416");

        ImageIcon iconImg = new ImageIcon(modelo.getImagem()); // Carrega a imagem
        Image img = iconImg.getImage(); // Obtém a instância da imagem
        Image imgRedimensionada = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Define novo tamanho

        logo = new JLabel(new ImageIcon(imgRedimensionada)); // Adiciona imagem redimensionada ao JLabel
        logo.setBounds(-150,-100,800,620);
        add(logo);

        informacoeslbl = new JLabel("Informacões da Unidade");
        informacoeslbl.setFont(new Font("Monospaced", Font.BOLD,20));
        informacoeslbl.setBounds(688,20,400,30);
        informacoeslbl.setForeground(customColor);
        add(informacoeslbl);

        int y = 60;
        int incremento = 20; 

        JLabel idlbl = new JLabel("ID: " + modelo.getId());
        idlbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        idlbl.setBounds(535, y, 400, 30);
        idlbl.setForeground(customColor);
        add(idlbl);

        y += incremento;
        JLabel tipoUnidadeLbl = new JLabel("Tipo de Unidade: " + modelo.getTipoUnidade());
        tipoUnidadeLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        tipoUnidadeLbl.setBounds(535, y, 400, 30);
        tipoUnidadeLbl.setForeground(customColor);
        add(tipoUnidadeLbl);

        y += incremento;
        JLabel numeroUniLbl = new JLabel("Número da Unidade: " + modelo.getNumeroUni());
        numeroUniLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        numeroUniLbl.setBounds(535, y, 400, 30);
        numeroUniLbl.setForeground(customColor);
        add(numeroUniLbl);

        y += incremento;
        JLabel blocoLbl = new JLabel("Bloco: " + modelo.getBloco());
        blocoLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        blocoLbl.setBounds(535, y, 400, 30);
        blocoLbl.setForeground(customColor);
        add(blocoLbl);

        y += incremento;
        JLabel andaresLbl = new JLabel("Andares: " + modelo.getAndares());
        andaresLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        andaresLbl.setBounds(535, y, 400, 30);
        andaresLbl.setForeground(customColor);
        add(andaresLbl);

        y += incremento;
        JLabel areaLbl = new JLabel("Área: " + modelo.getArea());
        areaLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        areaLbl.setBounds(535, y, 400, 30);
        areaLbl.setForeground(customColor);
        add(areaLbl);

        y += incremento;
        JLabel andaresDispLbl = new JLabel("Andares Disponíveis: " + modelo.getAndaresDisponiveis());
        andaresDispLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        andaresDispLbl.setBounds(535, y, 400, 30);
        andaresDispLbl.setForeground(customColor);
        add(andaresDispLbl);

        y += incremento;
        JLabel numQuartosLbl = new JLabel("Número de Quartos: " + modelo.getNumQuartos());
        numQuartosLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        numQuartosLbl.setBounds(535, y, 400, 30);
        numQuartosLbl.setForeground(customColor);
        add(numQuartosLbl);

        y += incremento;
        JLabel garagemCapLbl = new JLabel("Capacidade da Garagem: " + modelo.getGaragemCapaci());
        garagemCapLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        garagemCapLbl.setBounds(535, y, 400, 30);
        garagemCapLbl.setForeground(customColor);
        add(garagemCapLbl);

        y += incremento;
        JLabel statusLbl = new JLabel("Estado da Unidade: " + modelo.getStatusUnidade());
        statusLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        statusLbl.setBounds(535, y, 400, 30);
        statusLbl.setForeground(customColor);
        add(statusLbl);

        y += incremento;
        JLabel imagemLbl = new JLabel("Caminho da Imagem: " + modelo.getImagem());
        imagemLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        imagemLbl.setBounds(535, y, 550, 30);
        imagemLbl.setForeground(customColor);
        add(imagemLbl);

        y += incremento;
        JLabel dataCadastroLbl = new JLabel("Data de Cadastro: " + modelo.getDataCadastro());
        dataCadastroLbl.setFont(new Font("Monospaced", Font.BOLD, 17));
        dataCadastroLbl.setBounds(535, y, 400, 30);
        dataCadastroLbl.setForeground(customColor);
        add(dataCadastroLbl);

        

        
        
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
    }
}
