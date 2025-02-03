/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MoradorModelo.java
Data: 03/02/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class MoradorModelo implements RegistGeneric
{
    int id, unidade, moradorResponsavelId;
    StringBufferModelo nome, tipoDocumento,numDoc,telefone,email,nPorta;
    StringBufferModelo dataDeCadastro; 
    boolean statusRegisto, isResponsavel;
    LocalDate dataAtual = LocalDate.now();


    public MoradorModelo()
    {
        id = 0;
        unidade = 0; 
        moradorResponsavelId = 0;
        statusRegisto = true;
        isResponsavel = true;

		nome = new StringBufferModelo("", 20); 
		tipoDocumento = new StringBufferModelo("", 20);
        numDoc = new StringBufferModelo("", 20);
        telefone = new StringBufferModelo("", 20);
        email = new StringBufferModelo("", 20);
        nPorta = new StringBufferModelo("", 20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 15);
    }

    public MoradorModelo(int id, int unidade, int moradorResponsavelId, String nome, String tipoDocumento, String numDoc, String telefone, String email, String nPorta, boolean isResponsavel)
    {
        this.id = id;
        this.unidade = unidade;
        this.moradorResponsavelId = moradorResponsavelId;

		this.nome = new StringBufferModelo(nome, 20); 
		this.tipoDocumento = new StringBufferModelo(tipoDocumento, 20);
        this.numDoc = new StringBufferModelo(numDoc, 20);
        this.telefone = new StringBufferModelo(telefone, 20);
        this.email = new StringBufferModelo(email, 20);
        this.nPorta = new StringBufferModelo(nPorta, 20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 15);

        this.isResponsavel = isResponsavel;

        statusRegisto = true;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int newId)
    {
        id = newId;
    }

    public int getUnidade()
{
    return unidade;
}

public void setUnidade(int newUnidade)
{
    unidade = newUnidade;
}

public int getMoradorResponsavelId()
{
    return moradorResponsavelId;
}

public void setMoradorResponsavelId(int newMoradorResponsavelId)
{
    moradorResponsavelId = newMoradorResponsavelId;
}

public String getNome()
{
    return nome.toStringEliminatingSpaces();
}

public void setNome(String newNome)
{
    nome = new StringBufferModelo(newNome, 20); 
}

public String getTipoDocumento()
{
    return tipoDocumento.toStringEliminatingSpaces();
}

public void setTipoDocumento(String newTipoDocumento)
{
    tipoDocumento = new StringBufferModelo(newTipoDocumento, 20);
}

public String getNumDoc()
{
    return numDoc.toStringEliminatingSpaces();
}

public void setNumDoc(String newNumDoc)
{
    numDoc = new StringBufferModelo(newNumDoc, 20);
}

public String getTelefone()
{
    return telefone.toStringEliminatingSpaces();
}

public void setTelefone(String newTelefone)
{
    telefone = new StringBufferModelo(newTelefone, 20);
}

public String getEmail()
{
    return email.toStringEliminatingSpaces();
}

public void setEmail(String newEmail)
{
    email = new StringBufferModelo(newEmail, 20);
}

public String getNPorta()
{
    return nPorta.toStringEliminatingSpaces();
}

public void setNPorta(String newNPorta)
{
    nPorta = new StringBufferModelo(newNPorta, 20);
}

public boolean isResponsavel()
{
    return isResponsavel;
}

public void setResponsavel(boolean newIsResponsavel)
{
    isResponsavel = newIsResponsavel;
}

    public String getDataCadastro()
    {
        return dataDeCadastro.toStringEliminatingSpaces();
    }

    public boolean getStatusRegisto()
    {
        return statusRegisto;
    }

    public void setStatusRegisto(boolean newStatus)
    {
        statusRegisto = newStatus;
    }

    public String toString()
    {
        String str = "Dados do MOrador Modelo\n\n";

        str += "Id: " + getId() + "\n";
        str += "Nome: " + getNome() + "\n";
        str += "Tipo de Documento: " + getTipoDocumento() + "\n";
        str += "Número de Documento: " + getNumDoc() + "\n";
        str += "Telefone: " + getTelefone() + "\n";
        str += "Email: " + getEmail() + "\n";
        str += "Número da Porta: " + getNPorta() + "\n";
        str += "Responsável: " + (isResponsavel() ? "Sim" : "Não") + "\n";
        str += "Data de Cadastro: " + getDataCadastro() + "\n";
        str += "Unidade: " + getUnidade() + "\n";
        str += "Morador Responsável ID: " + getMoradorResponsavelId() + "\n";
        str += "Status: " + getStatusRegisto() + "\n";

        return str;
    }

    public long sizeof()
    {
        
        try
        {
            return 100*2 + 4*3 + 8 + 1*2;
        }
        catch(Exception ex)
        {
            return 0;
        }		
    }

    public void write(RandomAccessFile stream)
	{
		try
        {
            stream.writeInt(id);
            nome.write(stream);
            tipoDocumento.write(stream);
            numDoc.write(stream);
            telefone.write(stream);
            email.write(stream);
            nPorta.write(stream);
            stream.writeBoolean(isResponsavel);
            dataDeCadastro.write(stream);
            stream.writeInt(unidade);
            stream.writeInt(moradorResponsavelId);
            stream.writeBoolean(statusRegisto);

        }
        catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
	}

    public void read(RandomAccessFile stream)
	{
       try
		{
			id = stream.readInt();
            nome.read(stream);
            tipoDocumento.read(stream);
            numDoc.read(stream);
            telefone.read(stream);
            email.read(stream);
            nPorta.read(stream);
            isResponsavel = stream.readBoolean();
            dataDeCadastro.read(stream);
            unidade = stream.readInt();
            moradorResponsavelId = stream.readInt();
            statusRegisto = stream.readBoolean();

		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
    }

    public void salvar()
    {
        MoradorPNode node = new MoradorPNode(this);
		node.save();
        new MoradorFile().salvarDados(this);
    }

    public void eliminar()
    {
        MoradorPNode node = new MoradorPNode(this);
		node.eliminar();
    }

    public void editar()
    {
        MoradorPNode node = new MoradorPNode(this);
		node.editar();
    }
}