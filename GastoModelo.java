/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: GastoModelo.java
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

public class GastoModelo implements RegistGeneric
{
    int id;
    StringBufferModelo tipoGasto,dataGasto, categoriaGasto, dataAgendamento,statusManutencao,fornecedor;
    Double valorGasto; 
    StringBufferModelo dataDeCadastro;
    StringBufferModelo descricao = null; 
    LocalDate dataAtual = LocalDate.now();
    boolean statusRegisto;

    public GastoModelo()
    {
        id = 0;
        valorGasto = 0.0;
        statusRegisto = true;

		tipoGasto = new StringBufferModelo("", 30);
        descricao = new StringBufferModelo("", 30);
        categoriaGasto = new StringBufferModelo("", 30);
        statusManutencao = new StringBufferModelo("", 30);
        fornecedor = new StringBufferModelo("", 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 30);
        dataGasto =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 30);
        dataAgendamento =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 30);
    }

    public GastoModelo(int id, String descricao, String dataGasto, double valorGasto, String categoriaGasto, String tipoGasto, String dataAgendamento, String statusManutencao, String fornecedor)
    {
        this.id = id;
        this.valorGasto = valorGasto;

		this.tipoGasto = new StringBufferModelo(tipoGasto, 30);
        this.descricao = new StringBufferModelo(descricao, 30);
        this.categoriaGasto = new StringBufferModelo(categoriaGasto, 30);
		this.statusManutencao = new StringBufferModelo(statusManutencao, 30);
        this.fornecedor = new StringBufferModelo(fornecedor, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataDeCadastro =  new StringBufferModelo((dataAtual.format(formatter)).toString(), 30);
        this.dataGasto = new StringBufferModelo(dataGasto, 30);
        this.dataAgendamento = new StringBufferModelo(dataAgendamento, 30);

        statusRegisto = true;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoGasto() {
        return tipoGasto.toStringEliminatingSpaces();
    }
    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = new StringBufferModelo(tipoGasto, 20);
    }
    public String getDataGasto() {
        return dataGasto.toStringEliminatingSpaces();
    }
    public void setDataGasto(String dataGasto) {
        this.dataGasto = new StringBufferModelo(dataGasto, 10);
    }
    public String getCategoriaGasto() {
        return categoriaGasto.toStringEliminatingSpaces();
    }
    public void setCategoriaGasto(String categoriaGasto) {
        this.categoriaGasto = new StringBufferModelo(categoriaGasto, 20);
    }
    public String getDataAgendamento() {
        return dataAgendamento.toStringEliminatingSpaces();
    }
    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = new StringBufferModelo(dataAgendamento, 10);
    }
    public String getStatusManutencao() {
        return statusManutencao.toStringEliminatingSpaces();
    }
    public void setStatusManutencao(String statusManutencao) {
        this.statusManutencao = new StringBufferModelo(statusManutencao, 10);
    }
    public String getFornecedor() {
        return fornecedor.toStringEliminatingSpaces();
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = new StringBufferModelo(fornecedor, 30);
    }
    public double getValorGasto() {
        return valorGasto;
    }
    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }
    public String getDataDeCadastro() {
        return dataDeCadastro.toStringEliminatingSpaces();
    }
    public void setDataDeCadastro(String dataDeCadastro) {
        this.dataDeCadastro = new StringBufferModelo(dataDeCadastro, 15);
    }
    public String getDescricao() {
        return descricao.toStringEliminatingSpaces();
    }
    public void setDescricao(String descricao) {
        this.descricao = new StringBufferModelo(descricao, 30);
    }
    public boolean getStatusRegisto() {
        return statusRegisto;
    }
    public void setStatusRegisto(boolean statusRegisto) {
        this.statusRegisto = statusRegisto;
    }
    
    public String toString() 
    {
        String str = "Dados do Gasto Modelo\n\n";
        
        str += "Id: " + getId() + "\n";
        str += "Tipo de Gasto: " + getTipoGasto() + "\n";
        str += "Categoria do Gasto: " + getCategoriaGasto() + "\n";
        str += "Valor: " + getValorGasto() + "\n";
        str += "Descricao: " + getDescricao() + "\n";
        str += "Data do Gasto: " + getDataGasto() + "\n";
        str += "Data de Agendamento: " + getDataAgendamento() + "\n";
        str += "Status da Manutencao: " + getStatusManutencao() + "\n";
        str += "Fornecedor: " + getFornecedor() + "\n";
        str += "Status do Registro: " + getStatusRegisto() + "\n";
        str += "Data de Cadastro: " + getDataDeCadastro() + "\n";

        return str;
    }

    public long sizeof()
    {
        
        try
        {
            return 240*2 + 4 + 8 + 1;// 212 bytes
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
tipoGasto.write(stream);
categoriaGasto.write(stream);
stream.writeDouble(valorGasto);
descricao.write(stream);
dataGasto.write(stream);
dataAgendamento.write(stream);
statusManutencao.write(stream);
fornecedor.write(stream);
stream.writeBoolean(statusRegisto);
dataDeCadastro.write(stream);

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
			stream.writeInt(id);
tipoGasto.write(stream);
categoriaGasto.write(stream);
stream.writeDouble(valorGasto);
descricao.write(stream);
dataGasto.write(stream);
dataAgendamento.write(stream);
statusManutencao.write(stream);
fornecedor.write(stream);
stream.writeBoolean(statusRegisto);
dataDeCadastro.write(stream);

		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
    }

    public void salvar()
    {
        GastoPNode node = new GastoPNode(this);
		node.save();
        new GastoFile().salvarDados(this);
    }

    public void eliminar()
    {
        GastoPNode node = new GastoPNode(this);
		node.eliminar();
    }

    public void editar()
    {
        GastoPNode node = new GastoPNode(this);
		node.editar();
    }
}