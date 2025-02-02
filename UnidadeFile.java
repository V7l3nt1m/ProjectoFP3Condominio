/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: UnidadeFile.java
Data: 30/01/2025
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class UnidadeFile extends ObjectsFile
{
	
	public UnidadeFile()
	{
		super("Unidades.DAT", new UnidadeModelo() );
	}

	public void pesquisarUnidadePorBlocoNumero(String pesquisa)
	{
		UnidadeModelo modelo = new UnidadeModelo();
		String output = "";
		try
		{
			stream.seek(4);
			
			for (int i = 0; i < getNregistos(); ++i)
			{
				modelo.read( stream );
				
				if (modelo.getNumeroUni().startsWith(pesquisa) || modelo.getBloco().equalsIgnoreCase( pesquisa ) )
				{
					output += modelo.toString();
					output += "---------------------------------------";
					JOptionPane.showMessageDialog(null, modelo.toString(), 
					"Gestao de Padaria", JOptionPane.INFORMATION_MESSAGE);
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}

	public String[][] listarUnidades()
	{
		UnidadeModelo modelo = new UnidadeModelo();
		String [][] dados=null;
		int index = 0;
		int contador =0;
		try
		{
			stream.seek(4);
			for (int c = 0; c < getNregistos(); ++c)
			{
				modelo.read( stream );
				index++;
			}
			dados = new String[index][11];

			stream.seek(4);

			for (int c = 0; c < getNregistos(); ++c)
			{
				modelo.read( stream );
				
				dados[contador][0] = "" + modelo.getId();
				dados[contador][1] = modelo.getTipoUnidade();
				dados[contador][2] = "" + modelo.getNumeroUni();
				dados[contador][3] = modelo.getBloco();
				dados[contador][4] = "" + modelo.getAndares();
				dados[contador][5] = "" + modelo.getArea();
				dados[contador][6] = "" + modelo.getAndaresDisponiveis();
				dados[contador][7] = "" + modelo.getNumQuartos();
				dados[contador][8] = "" + modelo.getGaragemCapaci();
				dados[contador][9] = "" + modelo.getStatusUnidade();
				dados[contador][10] = modelo.getDataCadastro();
				contador++;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	

		return dados;
	}
	
	public void salvarDados(UnidadeModelo modelo)
	{
		try
		{
			//colocar o File Pointer no final do ficheiro
			stream.seek( stream.length() );
			
			//escrever os dados no ficheiro
			modelo.write(stream);

			incrementarProximoCodigo();

			JOptionPane.showMessageDialog(null, "Dados Salvos com Sucesso!");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Material");
		}
	}

}