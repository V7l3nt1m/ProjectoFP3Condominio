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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class UnidadeVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;
    private UnidadeFile file;


    public UnidadeVisao(boolean alterar, UnidadeModelo modelo)
    {
        super("Registrar Unidades Habitacionais");
        definirTema();
        setLayout(new BorderLayout());
        editar = alterar;

        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);

        if (!alterar) {
            painelCentro = new PainelCentro();
        } else {
            painelCentro = new PainelCentro(modelo);
        }
        getContentPane().add(painelCentro, BorderLayout.CENTER);

        getContentPane().add(painelSul = new PainelSul(), BorderLayout.SOUTH);

    
        pack();
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

    public class PainelCentro extends JPanel implements ActionListener
    {
        private JTextField idJTF, areaJTF,numeroUniJTF, andaresJTF, numQuartosJTF, garagemCapacidadeJTF, imagemCaminhoJTF;
        private JComboBox tipoUnidadeJCB, blocoJCB,statusUnidadeJCB;
        private ImageIcon casasImagem;
        private Image imagemCasa; 
        private JLabel lblArea, lblNumeroUni, lblAndares, lblNumQuartos, lblGaragemCap,lblStatusUnidade,lblImagem, lblImagemSelecionada, lblimg;
        private JLabel lblTipoUnidade, lblBloco;
        private String status[] = {"disponivel","indisponivel"};
        private JButton imagemJB;   
        private JFileChooser imagemJFC;
        private int returnValue;
        private JFrame frame = new JFrame("Selecionar Arquivo");
        private JPanel painelImagem;
        public String pastaDestino = "imagensUnidades/";

        public PainelCentro()
        {
            setLayout(new GridLayout(11,2));
            lblTipoUnidade = new JLabel("Tipo de Unidade");
            tipoUnidadeJCB = UInterfaceBox.createJComboBoxsTabela2("TipoUnidade.tab");

            lblBloco = new JLabel("Bloco");
            blocoJCB = UInterfaceBox.createJComboBoxsTabela2("Bloco.tab");

            idJTF = new JTextField();
            file = new UnidadeFile();
			idJTF.setText("" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            lblArea = new JLabel("Area (M²)");
            areaJTF = new JTextField();
            areaJTF.setText(""+0.0);

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

            lblImagem = new JLabel("Imagem da Unidade");
            imagemJB = new JButton("Upload imagem");
    

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

            add(lblImagem);
            add(imagemJB);

            add(lblImagemSelecionada = new JLabel("Imagem Selecionada: "));
            imagemCaminhoJTF = new JTextField();
            imagemCaminhoJTF.setEnabled(false);
            add(imagemCaminhoJTF);

            imagemJB.addActionListener(this);
            
        }

        public PainelCentro(UnidadeModelo modelo)
        {
            setLayout(new GridLayout(11,2));
            lblTipoUnidade = new JLabel("Tipo de Unidade");
            tipoUnidadeJCB = UInterfaceBox.createJComboBoxsTabela2("TipoUnidade.tab");
            tipoUnidadeJCB.setSelectedItem(modelo.getTipoUnidade());
        
            lblBloco = new JLabel("Bloco");
            blocoJCB = UInterfaceBox.createJComboBoxsTabela2("Bloco.tab");
            blocoJCB.setSelectedItem(modelo.getBloco());

            idJTF = new JTextField();
			idJTF.setText( "" + modelo.getId());
            idJTF.setFocusable(false);

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

            lblStatusUnidade = new JLabel("Estado da Unidade");
            statusUnidadeJCB = new JComboBox(status);
            statusUnidadeJCB.setSelectedItem(modelo.getStatusUnidade());

            lblImagem = new JLabel("Imagem da Unidade");
            imagemJB = new JButton("Upload imagem");

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

            add(lblGaragemCap);
            add(garagemCapacidadeJTF);

            add(lblNumQuartos);
            add(numQuartosJTF);

            add(lblStatusUnidade);
            add(statusUnidadeJCB);

            add(lblImagem);
            add(imagemJB);

            add(lblImagemSelecionada = new JLabel("Imagem Selecionada: "));
            imagemCaminhoJTF = new JTextField();
            setImagemCaminho(modelo.getImagem());
            imagemCaminhoJTF.setEnabled(false);
            add(imagemCaminhoJTF);

            imagemJB.addActionListener(this);

        }


    public int getId()
    {
        return Integer.parseInt( idJTF.getText().trim());
    }

    public void setId(int id)
    {
        idJTF.setText(""+id);
    }

    private String getImagemCaminho()
    {
        return imagemCaminhoJTF.getText().trim();
    }

    public void setImagemCaminho(String newCaminhoImagem)
    {
        imagemCaminhoJTF.setText(newCaminhoImagem);
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

    public String copiarImagemComID(String origem, String pastaDestinoo, String id)
    {
        try {
            Path origemPath = Path.of(origem);
            String nomeOriginal = new File(origem).getName(); // Nome do arquivo original
            String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf(".")); // Obtém a extensão
            
            // Novo nome do arquivo com ID
            String novoNome = "imagem_" + id + extensao;
            
            // Caminho completo do novo arquivo
            Path destinoPath = Path.of(pastaDestino, novoNome);

            // Cria a pasta de destino se não existir
            Files.createDirectories(destinoPath.getParent());

            // Copia a imagem para o novo local
            Files.copy(origemPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Imagem copiada com sucesso para: " + destinoPath);

            return ""+destinoPath;
        } catch (IOException e) {
            System.err.println("Erro ao copiar a imagem: " + e.getMessage());
        }
    
        return null;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == imagemJB)
        {
            imagemJFC = new JFileChooser();
            returnValue = imagemJFC.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
                File selectedFile = imagemJFC.getSelectedFile(); // Obtém o arquivo selecionado
                JOptionPane.showMessageDialog(frame,"Arquivo selecionado: " + selectedFile.getAbsolutePath());
                String nomeImagemGuardar = copiarImagemComID(selectedFile.getAbsolutePath(), pastaDestino, getNumeroUni());

                setImagemCaminho(nomeImagemGuardar);

                // Obtém a imagem original do ImageIcon
            }
            else
                JOptionPane.showMessageDialog(null,"NENHUM ARQUIVO SELECIONADO");
        }
    }


     public boolean verificarCampos()
        {
            if(isEmpty(getId()) || isEmpty(getTipoUnidade()) || isEmpty(getBloco()) || 
            isEmpty(getArea()) || isEmpty(getNumeroUni()) || isEmpty(getAndares()) || isEmpty(getNumQuartos())
            || isEmpty(getGaragemCapaci()) || isEmpty(getImagemCaminho()))
                    return false;
                return true; 
        }


        public void salvar()
		{			
			UnidadeModelo modelo = new UnidadeModelo(getId(), getAndares(), getNumQuartos(),getArea(),
            getNumeroUni(), getTipoUnidade(), getBloco(),getGaragemCapaci(), getStatusUnidade(), getImagemCaminho());

            JOptionPane.showMessageDialog(null, modelo.toString());

			modelo.salvar();
			dispose();
		}
    
        public void alterar()
		{
			UnidadeModelo modelo = new UnidadeModelo(getId(), getAndares(), getNumQuartos(), getArea(),
            getNumeroUni(), getTipoUnidade(), getBloco(),getGaragemCapaci(), getStatusUnidade(), getImagemCaminho());
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
        UnidadeModelo modelo = new UnidadeModelo();
        new UnidadeVisao(false,modelo); 
    }
}
