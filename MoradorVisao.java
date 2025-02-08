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
import java.time.LocalDate;


public class MoradorVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    boolean editar;

    public MoradorVisao(boolean alterar, MoradorModelo modelo)
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
            casasIco = new ImageIcon("imagens/moradores.jpg");
            
            lblCasa = new JLabel();
            lblCasa.setIcon(casasIco);
            add(lblCasa);
        }
    }

    public class PainelCentro extends JPanel implements ActionListener
    {
        private JTextField idJTF,nomeJTF,numDocJTF, telefoneJTF,emailJTF,nPortaJTF, numeroUnidadeJTF, moradorResponsavelJTF;
        private JComboBox isResponsavelJCB, TipoDocumentoJCB;
        private String opcoes[] = {"sim","nao"};
        private JLabel idLbl, nomeLbl, numDocLbl, telefoneLbl, emailLbl, nPortaLbl, numeroUnidadeLbl, moradorResponsavelLbl, isResponsavelLbl, tipoDocumentoLbl;
        private MoradorFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(9,2));
            file = new MoradorFile();
            //idJTF.setText("" + file.getProximoCodigo());
            idJTF = new JTextField();
            idJTF.setText("" + file.getProximoCodigo());
            idJTF.setFocusable(false);
            add(nomeLbl = new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            add(tipoDocumentoLbl = new JLabel("Tipo de Documento"));
            TipoDocumentoJCB = UInterfaceBox.createJComboBoxsTabela2("TipoDocumento.tab");
            add(TipoDocumentoJCB);

            add(numDocLbl = new JLabel("Numero do Documento"));
            numDocJTF = new JTextField();
            add(numDocJTF);

            add(telefoneLbl = new JLabel("Telefone"));
            add(telefoneJTF = new JTextField());

            add(emailLbl = new JLabel("Email"));
            add(emailJTF = new JTextField());

            add(isResponsavelLbl = new JLabel("Morador Responsavel?"));
            add(isResponsavelJCB = new JComboBox(opcoes));

            add(moradorResponsavelLbl = new JLabel("Numero do Documento do Responsavel"));
            moradorResponsavelJTF = new JTextField();
            moradorResponsavelJTF.setEnabled(false);
            add(moradorResponsavelJTF);


            add(numeroUnidadeLbl = new JLabel("Numero da Unidade"));
            add(numeroUnidadeJTF = new JTextField());

            add(nPortaLbl = new JLabel("Numero da Porta"));
            add(nPortaJTF = new JTextField());

            isResponsavelJCB.addActionListener(this);
            nPortaJTF.addActionListener(this);
            numeroUnidadeJTF.addActionListener(this);
            moradorResponsavelJTF.addActionListener(this);
        }

        public PainelCentro(MoradorModelo modelo)
        {
            setLayout(new GridLayout(10,2));
            file = new MoradorFile();
            idJTF = new JTextField();

			idJTF.setText( "" + modelo.getId());
            idJTF.setFocusable(false);
            add(nomeLbl = new JLabel("Nome"));
            nomeJTF = new JTextField();     
            nomeJTF.setText(modelo.getNome());       
            add(nomeJTF);

            add(tipoDocumentoLbl = new JLabel("Tipo de Documento"));
            TipoDocumentoJCB = UInterfaceBox.createJComboBoxsTabela2("TipoDocumento.tab");
            TipoDocumentoJCB.setSelectedItem(modelo.getTipoDocumento());
            add(TipoDocumentoJCB);

            add(numDocLbl = new JLabel("Numero do Documento"));
            numDocJTF = new JTextField();
            numDocJTF.setText(""+modelo.getNumDoc());
            add(numDocJTF);

            add(telefoneLbl = new JLabel("Telefone"));
            telefoneJTF = new JTextField();
            telefoneJTF.setText(modelo.getTelefone());
            add(telefoneJTF);

            add(emailLbl = new JLabel("Email"));
            emailJTF = new JTextField();
            emailJTF.setText(modelo.getEmail());
            add(emailJTF);

            add(isResponsavelLbl = new JLabel("Morador Responsavel?"));
            isResponsavelJCB = new JComboBox(opcoes);
            isResponsavelJCB.setSelectedItem(modelo.isResponsavel() == true ? "sim" : "nao");
            add(isResponsavelJCB);


            add(moradorResponsavelLbl = new JLabel("Numero do Documento do Responsavel"));
            moradorResponsavelJTF = new JTextField();
            moradorResponsavelJTF.setText(""+modelo.getMoradorResponsavelId());
            moradorResponsavelJTF.setEnabled(false);
            add(moradorResponsavelJTF);


            add(numeroUnidadeLbl = new JLabel("Numero da Unidade"));
            numeroUnidadeJTF = new JTextField();
            numeroUnidadeJTF.setText(""+modelo.getUnidade());
            add(numeroUnidadeJTF);
            
            add(nPortaLbl = new JLabel("Numero da Porta"));
            nPortaJTF = new JTextField();
            nPortaJTF.setText(modelo.getNPorta());
            add(nPortaJTF);

            isResponsavelJCB.addActionListener(this);
            nPortaJTF.addActionListener(this);
            numeroUnidadeJTF.addActionListener(this);
            moradorResponsavelJTF.addActionListener(this);
        }


    public int getId()
    {
        return Integer.parseInt( idJTF.getText().trim());
    }

    public void setId(int id)
    {
        idJTF.setText(""+id);
    }

    public String getNome()
    {
        return nomeJTF.getText().trim();
    }

    public void setNome(String nome)
    {
        nomeJTF.setText(nome);
    }

    public String getNumDoc()
    {
        return numDocJTF.getText().trim();
    }

    public void setNumDoc(String numDoc)
    {
        numDocJTF.setText(numDoc);
    }

    public String getTelefone()
    {
        return telefoneJTF.getText().trim();
    }

    public void setTelefone(String telefone)
    {
        telefoneJTF.setText(telefone);
    }

    public String getEmail()
    {
        return emailJTF.getText().trim();
    }

    public void setEmail(String email)
    {
        emailJTF.setText(email);
    }

    public String getNPorta()
    {
        return nPortaJTF.getText().trim();
    }

    public void setNPorta(String nPorta)
    {
        nPortaJTF.setText(nPorta);
    }

    public String getNumeroUnidade()
    {
        return numeroUnidadeJTF.getText().trim();
    }

    public void setNumeroUnidade(String numeroUnidade)
    {
        numeroUnidadeJTF.setText(numeroUnidade);
    }

    public String getMoradorResponsavel()
    {
        return moradorResponsavelJTF.getText().trim();
    }

    public void setMoradorResponsavel(String moradorResponsavel)
    {
        moradorResponsavelJTF.setText(moradorResponsavel);
    }

    public boolean getIsResponsavel()
    {
        if(isResponsavelJCB.getSelectedItem().equals("sim"))
            return true;
        return false;
    }

    public void setIsResponsavel(boolean isResponsavel)
    {
        isResponsavelJCB.setSelectedItem(isResponsavel ? "sim" : "nao");   
    }

    public String getTipoDocumento()
    {
        return String.valueOf(TipoDocumentoJCB.getSelectedItem());
    }

    public void setTipoDocumento(String tipoDocumento)
    {
        TipoDocumentoJCB.setSelectedItem(tipoDocumento);
    }

 

    public boolean isEmpty(Object valor)
    {
        return String.valueOf(valor).equals("") || valor == null || String.valueOf(valor).equals("0") || String.valueOf(valor).equals("0.0");
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == isResponsavelJCB && getIsResponsavel() == true)
            {
                moradorResponsavelJTF.setEnabled(false);
                numeroUnidadeJTF.setEnabled(true);
                nPortaJTF.setEnabled(true);
            }
            else
            {
                numeroUnidadeJTF.setEnabled(false);
                nPortaJTF.setEnabled(false);
                moradorResponsavelJTF.setEnabled(true);
            }
        
        if(evt.getSource() == moradorResponsavelJTF)
        {
            if(getIsResponsavel() == false)
            {
                MoradorModelo modeloResponsavel = MoradorDadosTable.pesquisarMoradorIdPorNumDoc(getMoradorResponsavel());
                if(modeloResponsavel != null)
                {
                    setNumeroUnidade(""+modeloResponsavel.getUnidade());
                    setNPorta(""+modeloResponsavel.getNPorta());
                }
                else
                {
                    setNumeroUnidade("");
                    setNPorta("");
                }
            }
        }
    }


     public boolean verificarCampos()
        {
            if(isEmpty(getId()) || isEmpty(getTipoDocumento()) || isEmpty(getNumDoc()) ||  isEmpty(getTelefone()) || isEmpty(getEmail()) || isEmpty(getIsResponsavel()) || isEmpty(getNPorta()) || isEmpty(getNumeroUnidade()))
                    return false;
                return true; 
        }

        public void salvar()
		{
            if(getIsResponsavel() == true)
            {
                UnidadeModelo modeloUni = UnidadeDadosTable.pesquisarUnidadePorNumeroUni(getNumeroUnidade());
                if(modeloUni != null)
                {
                    MoradorModelo modelo = new MoradorModelo(getId(), modeloUni.getId(), -1, getNome(), getTipoDocumento(), getNumDoc(), getTelefone(), getEmail(), getNPorta(), getIsResponsavel());
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    modelo.salvar();
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Numero da Unidade Invalido", "Verificador de campos", JOptionPane.ERROR_MESSAGE);
                }
            }			
            else
            {
                MoradorModelo modeloResponsavel = MoradorDadosTable.pesquisarMoradorIdPorNumDoc(getMoradorResponsavel());
                if(modeloResponsavel != null)
                {
                    MoradorModelo modelo = new MoradorModelo(getId(), Integer.parseInt(getNumeroUnidade()), modeloResponsavel.getId(), getNome(), getTipoDocumento(), getNumDoc(), getTelefone(), getEmail(), getNPorta(), getIsResponsavel());
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    modelo.salvar();
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Numero do Documento Invalido", "Verificador de campos", JOptionPane.ERROR_MESSAGE);
                }
            }
		}
    
        public void alterar()
		{			
			if(getIsResponsavel() == true)
            {
                UnidadeModelo modeloUni = UnidadeDadosTable.pesquisarUnidadePorNumeroUni(getNumeroUnidade());
                if(modeloUni != null)
                {
                    MoradorModelo modelo = new MoradorModelo(getId(), modeloUni.getId(), -1, getNome(), getTipoDocumento(), getNumDoc(), getTelefone(), getEmail(), getNPorta(), getIsResponsavel());
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    modelo.editar();
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Numero da Unidade Invalido", "Verificador de campos", JOptionPane.ERROR_MESSAGE);
                }
            }			
            else
            {
                MoradorModelo modeloResponsavel = MoradorDadosTable.pesquisarMoradorIdPorNumDoc(getMoradorResponsavel());
                if(modeloResponsavel != null)
                {
                    MoradorModelo modelo = new MoradorModelo(getId(), Integer.parseInt(getNumeroUnidade()), modeloResponsavel.getId(), getNome(), getTipoDocumento(), getNumDoc(), getTelefone(), getEmail(), getNPorta(), getIsResponsavel());
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    modelo.editar();
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Numero do Documento Invalido", "Verificador de campos", JOptionPane.ERROR_MESSAGE);
                }
            }	
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
        MoradorModelo modelo = new MoradorModelo();
        new MoradorVisao(false,modelo); 
    }
}
