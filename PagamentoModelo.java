/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: PagamentoModelo.java
Data: 10/02/2025
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

public class PagamentoModelo implements RegistGeneric
{
    int id, idMorador, idUnidade;
    StringBufferModelo tipoPagamento;
    Double valorPago; 
    StringBufferModelo dataDeCadastro,statusPagamento, dataPagamento = null;
    StringBufferModelo descricao = null; 
    boolean statusRegisto;
    LocalDate dataAtual = LocalDate.now();


    public PagamentoModelo()
    {
        id = 0;
        idMorador = 0;
        idUnidade = 0;
        valorPago = 0.0;
        statusRegisto = true;


		tipoPagamento = new StringBufferModelo("", 20);
        statusPagamento = new StringBufferModelo("", 10);
        descricao = new StringBufferModelo("", 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 15);
        dataPagamento =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 10);
    }

    public PagamentoModelo(int id, int idMorador, int idUnidade, double valorPago, String tipoPagamento, String statusPagamento,String descricao, String dataPagamento)
    {
        this.id = id;
        this.idMorador = idMorador;
        this.idUnidade = idUnidade;
        this.valorPago = valorPago;

		this.tipoPagamento = new StringBufferModelo(tipoPagamento, 20);
		this.statusPagamento = new StringBufferModelo(statusPagamento, 10);
        this.descricao = new StringBufferModelo(descricao, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 15);
        this.dataPagamento = new StringBufferModelo(dataPagamento, 10);
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

    public int getIdMorador()
    {
        return idMorador;
    }

    public void setIdMorador(int newIdMorador)
    {
        idMorador = newIdMorador;
    }

    public int getIdUnidade()
    {
        return idUnidade;
    }

    public void setIdUnidade(int newIdUnidade)
    {
        idUnidade = newIdUnidade;
    }

    public String getTipoPagamento()
    {
        return tipoPagamento.toStringEliminatingSpaces(); 
    }

    public void setTipoPagamento(String newStatusPagamento)
    {
        tipoPagamento = new StringBufferModelo(newStatusPagamento, 20);
    }

    public String getStatusPagamento()
    {
        return statusPagamento.toStringEliminatingSpaces(); 
    }
    
    public double getValorPagar()
    {
        return valorPago;
    }

    public void setValorPagar(double newValorPagar)
    {
        valorPago = newValorPagar;
    }


    public String getDescricao() {
        return descricao.toStringEliminatingSpaces();
}

// Setter
public void setDescricao(String descricao) {
    this.descricao = new StringBufferModelo(descricao,40);
}


    public void setStatusPagamento(String newStatusPagamento)
    {
        statusPagamento = new StringBufferModelo(newStatusPagamento, 10);
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

    public String getDataPagamento()
    {
        return dataPagamento.toStringEliminatingSpaces();
    }
    public void setDataPagamento(String newDataPagamento)
    {
        dataPagamento = new StringBufferModelo(newDataPagamento, 10);
    }

    
    public String toString()
    {
        String str = "Dados do Pagamento Modelo\n\n";

        str += "Id: " + getId() + "\n";
        str += "Id do Morador: " + getIdMorador() + "\n";
        str += "Id da Unidade: " + getIdUnidade() + "\n";
        str += "Tipo de Pagamento: " + getTipoPagamento() + "\n";
        str += "Valor a pagar: " + getValorPagar() + "\n";
        str += "Descricao: " + getDescricao() + "\n";
        str += "Status do Pagamento: " + getStatusPagamento() + "\n";
        str += "Data de Pagamento: " + getDataPagamento() + "\n";
        str += "Data de Cadastro: " + getDataCadastro() + "\n";
        str += "Status do Registro: " + getStatusRegisto() + "\n";
        return str;
    }

    public long sizeof()
    {
        
        try
        {
            return 85*2 + 4*3 + 8 + 1;// 212 bytes
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
            stream.writeInt(idMorador);
            stream.writeInt(idUnidade);
            tipoPagamento.write(stream);
            stream.writeDouble(valorPago);
            descricao.write(stream);
            statusPagamento.write(stream);
            dataPagamento.write(stream);
            dataDeCadastro.write(stream);
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
            idMorador = stream.readInt();
            idUnidade = stream.readInt();
            tipoPagamento.read(stream);
            valorPago = stream.readDouble();
            descricao.read(stream);
            statusPagamento.read(stream);
            dataPagamento.read(stream);
            dataDeCadastro.read(stream);
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
        PagamentoPNode node = new PagamentoPNode(this);
		node.save();
        new PagamentoFile().salvarDados(this);
    }

    public void eliminar()
    {
        PagamentoPNode node = new PagamentoPNode(this);
		node.eliminar();
    }

    public void editar()
    {
        PagamentoPNode node = new PagamentoPNode(this);
		node.editar();
    }
}