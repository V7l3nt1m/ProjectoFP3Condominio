/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: UnidadeModelo.java
Data: 20/01/2025
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

public class UnidadeModelo implements RegistGeneric
{
    int id, andares, numQuartos, garagemCapacidade,andaresDisponivel;
    StringBufferModelo numeroUnidade, tipoUnidade,bloco;
    Double area; 
    StringBufferModelo dataDeCadastro; 
    boolean statusUnidade;
    LocalDate dataAtual = LocalDate.now();


    public UnidadeModelo()
    {
        id = 0;
        andares = 0;
        numQuartos = 0;
        garagemCapacidade = 0;
        andaresDisponivel = 0;
        area = 0.0;
        statusUnidade = true;


		numeroUnidade = new StringBufferModelo("", 20); 
		tipoUnidade = new StringBufferModelo("", 20);
        bloco = new StringBufferModelo("", 10); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 15);
    }

    public UnidadeModelo(int id, int andares, int numQuartos, int andaresDisponivel, double area, String numeroUnidade, String tipoUnidade, String bloco,int garagemCapacidade, String statusUnidade)
    {
        this.id = id;
        this.andares = andares;
        this.numQuartos = numQuartos;
        this.andaresDisponivel = andaresDisponivel;
        this.area = area;
        this.garagemCapacidade = garagemCapacidade;

		this.numeroUnidade = new StringBufferModelo(numeroUnidade, 20); 
		this.tipoUnidade = new StringBufferModelo(tipoUnidade, 20);
		this.bloco = new StringBufferModelo(bloco, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 15);

        if(statusUnidade.equals("indisponivel"))
            this.statusUnidade = false;
        else
            this.statusUnidade = true;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int newId)
    {
        id = newId;
    }

    public String getTipoUnidade()
    {
        return tipoUnidade.toStringEliminatingSpaces(); 
    }

    public void setTipoUnidade(String newTipoUnidade)
    {
        tipoUnidade = new StringBufferModelo(newTipoUnidade, 20);
    }

    public String getBloco()
    {
        return bloco.toStringEliminatingSpaces(); 
    }

    public void setBloco(String newBloco)
    {
        bloco = new StringBufferModelo(newBloco, 10);
    }

    public double getArea()
    {
        return area;
    }

    public void setArea(double newArea)
    {
        area = newArea;
    }

    public String getNumeroUni()
    {
        return numeroUnidade.toStringEliminatingSpaces();
    }

    public void setNumeroUni(String newNumeroUni)
    {
        numeroUnidade = new StringBufferModelo(newNumeroUni, 20);
    }

    public int getAndares()
    {
        return andares;
    }

    public void setAndares(int newAndares)
    {
        andares = newAndares;
    }

    public int getAndaresDisponiveis()
    {
        return andaresDisponivel;
    }

    public void setAndaresDisponiveis(int newAndaresDispo)
    {
        andaresDisponivel = newAndaresDispo;
    }

    public int getNumQuartos()
    {
        return numQuartos;
    }

    public void setNumQuartos(int newNumQuartos)
    {
        numQuartos = newNumQuartos;
    }

    public int getGaragemCapaci()
    {
        return garagemCapacidade;
    }

    public void setGaragemCapaci(int newGaragenCapac)
    {
        garagemCapacidade = newGaragenCapac;
    }

    public boolean getStatusUnidade()
    {
        return statusUnidade;
    }

    public void setStatusUnidade(boolean newStatusUnidade)
    {
        statusUnidade = newStatusUnidade;
    }

    public String getDataCadastro()
    {
        return dataDeCadastro.toStringEliminatingSpaces();
    }

    
    public String toString()
    {
        String str = "Dados da Unidade Modelo\n\n";

        str += "Id: " + getId() + "\n";
        str += "Tipo de Unidade: " + getTipoUnidade() + "\n";
        str += "Numero da Unidade: " + getNumeroUni() + "\n";
        str += "Bloco: " + getBloco() + "\n";
        str += "Andares: " + getAndares() + "\n";
        str += "Area: " + getArea() + "\n";
        str += "Andares Disponiveis: " + getAndaresDisponiveis() + "\n";
        str += "Numero de Quartos: " + getNumQuartos() + "\n";
        str += "Capacidade da Garagem: " + getGaragemCapaci() + "\n";
        str += "Estado da Unidade: " + getStatusUnidade() + "\n"; 
        str += "Data de Cadastro: " + getDataCadastro() + "\n";
        return str;
    }

    public long sizeof()
    {
        
        try
        {
            return 65*2 + 4*5 + 8 + 1;// 212 bytes
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
            tipoUnidade.write(stream);
            numeroUnidade.write(stream);
            bloco.write(stream);
            stream.writeInt(andares);
            stream.writeDouble(area);
            stream.writeInt(andaresDisponivel);
            stream.writeInt(numQuartos);
            stream.writeInt(garagemCapacidade);
            dataDeCadastro.write(stream);
            stream.writeBoolean(statusUnidade);
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
            tipoUnidade.read(stream);
            numeroUnidade.read(stream);
            bloco.read(stream);
            andares = stream.readInt();
            area = stream.readDouble();
            andaresDisponivel = stream.readInt();
            numQuartos = stream.readInt();
            garagemCapacidade = stream.readInt();
            dataDeCadastro.read(stream);
            statusUnidade = stream.readBoolean();	
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
    }

    public void salvar()
    {
        UnidadePNode node = new UnidadePNode(this);
		node.save();
        new UnidadeFile().salvarDados(this);
    }
    
}