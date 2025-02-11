/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: GastoDadosTable.java
Data: 10/02/2025
--------------------------------------*/

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class GastoDadosTable extends AbstractHashTableCoalashed
{
    public GastoDadosTable(String filename, int tableSize)
    {
        super(filename, tableSize);
    }

    public GastoDadosTable()
    {

    }

    public int calcularHashCodeReHashing(String key)
	{
		return 0;
	}

      //calcula a posicao de entrada na tabela apartir da chave key
	   // devolve o hush code de uma determinada tabela
    
    public int calcularHashCode(String key)
	   {
		return  Math.abs(key.hashCode()) % tableSize;
	   }
	   //devolve o no q esta na posicao <tablePosition> da tabela
	   public SaveWriteReadInteface getNode(int tablePosition)
	   {
		GastoPNode node = (GastoPNode)getEmptyNode();

		long pos = getFilePosition( tablePosition );

		try
		{	
			stream.seek( pos );

			node.read( stream );
		
			//se nome nao vazio
			if ( node.isEmptyNode() == false )
			{
				return node;
			}
		}		
		catch (Exception ex)
		{
			ShowMessage.displayMessage("[GetNode] Falha no posicionamento do ficheiro " + fileName,
			"ERROR", true);	
		}
	
		return null;
	 }

     public GastoPNode getNode(String key) throws NullPointerException
	{		
		//calcula a posicao de entrada na tabela apartir da chave key
		int tablePosition = calcularHashCode( key );
	
		GastoPNode tmp = (GastoPNode)getNode( tablePosition );
	
	        if (tmp != null)
	        {
	            while ( !tmp.getKey().equalsIgnoreCase(""))
	            {
	                    if ( tmp.getKey().equalsIgnoreCase( key ) )
	                            return tmp;

	                    tmp = tmp.getNext();
	            }			
	        }		
		return tmp;
	}	

     public static boolean exists(String key)
	{
		GastoDadosTable table = new GastoDadosTable("GASTOS.DAT", 100);
		
		GastoPNode tmp = table.getNode(key);
		
		return !tmp.isEmptyNode();
	}

	
	 
	public static Vector getAllNodes()
	{
		Vector listaNodes = new Vector();
		
		GastoDadosTable hashCadaver = new GastoDadosTable("GASTOS.DAT", 100);
		
		GastoPNode tmp = new GastoPNode();
			
		hashCadaver.openFile();
		
		try
		{
			hashCadaver.stream.seek(8);
			
			for(int i=0; i < hashCadaver.tableSize; i++)
			{
				tmp.read( hashCadaver.stream );
				
				if( !tmp.isEmptyNode()  )
				{
					listaNodes.addElement(tmp.getKey());
					System.out.println( ""+tmp.getModel().getDescricao() );
				}
				else
					System.out.println(i);
			}
		}
		catch(Exception ex)
		{
			System.out.println("corpodadostables.java");
		}
		
		return listaNodes;
	}

	public void editarGasto(GastoPNode node)
	{		
		try
		{
			int posTabela = calcularHashCode( node.getKey() );
			sobrePorRegisto(node, posTabela);
			JOptionPane.showMessageDialog(null, "Gasto Editada com Sucesso.");

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao editar Gasto.");
		}		
	}

	
	public void eliminarGasto(GastoPNode node)
	{
		GastoDadosTable hashCadaver = new GastoDadosTable("GASTOS.DAT",100);
		
		try
		{
			hashCadaver.openFile();
			node.getModel().setStatusRegisto(false);

			int posTabela = calcularHashCode( node.getKey() );

			hashCadaver.stream.seek(getFilePosition(posTabela));
			node.write(hashCadaver.stream);
			JOptionPane.showMessageDialog(null, "Gasto eliminado com sucesso!");  // Mensagem de sucesso			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao eliminar a Gasto.");
		}		
	}

	//adiciona na tabela e depois no ficheiro
	public void adicionarNovoGasto(GastoPNode node)
	{
		try
		{
			stream.seek(0);
			
			int nTableSize = stream.readInt();
			int nElements = stream.readInt();					
	
			
			// Trata do ouverFlow da tabela a 80%
			if ( (nElements + 1) / nTableSize >= 0.8 )
				ouverFlowFile( nTableSize, nElements );
		}
		catch (Exception ex)
		{
			ShowMessage.displayMessage("Falha ao adicionar um Gasto " + fileName, "ERROR", true);	
		}

		 //calcula a posicao na tabela
		 int posTabela = calcularHashCode( node.getKey() );
	
		 //se nao tiver nenhum elemento nesta posicao adiciona
		 if ( getNode( posTabela ) == null)
		 		 
			 adicionarNoFicheiro(node, posTabela); 
		
		 //se tiver alguem e ele quiser sobrepor 
		 else if (getNode(posTabela).getKey().equalsIgnoreCase(node.getKey()))
		 {
			if (JOptionPane.showConfirmDialog(null, "Este Registo ja existe Quer Sobrepor") == JOptionPane.YES_OPTION)
				
				sobrePorRegisto(node, posTabela);
			else
				return;			 
		 }		 
		 else
			 //se houver colisao, colocar na lista de colisoes
			 adicionarNaListaColisoes(node, posTabela);
	}


	// sobrepoem 1 registo
	public void sobrePorRegisto(GastoPNode node, int posTabela)
	{	
		GastoPNode no = (GastoPNode)getNode(posTabela);	
		
		node.setNext(no.getNext());
		
		node.setPrev(no.getPrev());	
		
		adicionarNoFicheiro(node, posTabela);
		
	}
	//segredo do projecto
	//adiciona na lista de colisoes
	public void adicionarNaListaColisoes(GastoPNode node, int lastColision)
	{		
		GastoPNode no = (GastoPNode)getNode(lastColision);
		
		 //se nao houver ninguem na lista, adicionar
		if (no.getNext() == null && lastColision != tableSize - 1)
		{			
            no.setNext(node);
			node.setPrev(no);
		
			adicionarNoFicheiro(node, tableSize - 1);				
		}
		else
		{
			GastoPNode tmp = no.getNext();
		
			//procura 1 espaco vazio apartir do fim
			for (int i = tableSize - 1; i >= 0; --i)
			{
				if (tmp.getNext() == null) //se houver lugar vago adicionar
				{
					tmp.setNext(node);
					node.setPrev(tmp);
					//adiciona no fim da tabela se houver espaco
					adicionarNoFicheiro(node, i);
					return;
				}
				else
					tmp = tmp.getNext();				
			}	
		}
	}		


	//calcula onde o fichiro deve se posicionar
	// para escrever o proximo registo
 	public long getFilePosition(int tablePosition)
 	{
		return (2 * 4 + tablePosition * GastoPNode.sizeof());
 	}


	public SaveWriteReadInteface getEmptyNode()
	{
		return new GastoPNode();
	}

	public static int getNextID()
	{
		//UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		//return hashCadaver.getNextAutoId() + 1;
		
		return 1;
	}
	//

	public static String[][] listarGastos()
	{
		GastoDadosTable hashCadaver = new GastoDadosTable("GASTOS.DAT",100);
		GastoPNode tmp = (GastoPNode)hashCadaver.getEmptyNode();
		
		String [][] dados=null;
		int index = 0;
		int contador =0;
		
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);

			for (int c = 0; c < hashCadaver.tableSize; ++c)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
				{						
					index++;
				}	
			}

			dados = new String[index][10];

			hashCadaver.openFile();
			hashCadaver.stream.seek(8);

			for (int c = 0; c < hashCadaver.tableSize; ++c)
			{
				tmp.read(hashCadaver.stream);
				
				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
				{
					dados[contador][0] = "" + tmp.getModel().getId();
					dados[contador][1] = tmp.getModel().getTipoGasto();
					dados[contador][2] = tmp.getModel().getCategoriaGasto();
					dados[contador][3] = "" + tmp.getModel().getValorGasto();
					dados[contador][4] = tmp.getModel().getDescricao();
					dados[contador][5] = tmp.getModel().getDataGasto();
					dados[contador][6] = tmp.getModel().getDataAgendamento();
					dados[contador][7] = tmp.getModel().getStatusManutencao();
					dados[contador][8] = tmp.getModel().getFornecedor();
					dados[contador][9] = tmp.getModel().getDataDeCadastro();
					contador++;
				}
			}

			return dados;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	

		return dados;
	}




   public static void showGastos()
	{
		GastoDadosTable hashCadaver = new GastoDadosTable("GASTOS.DAT",100);
		GastoPNode tmp = (GastoPNode)hashCadaver.getEmptyNode();
		
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals(""))
				{						
					System.out.println(tmp.getModel().toString());
				}	
			}		
						
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

	}

	public static void pesquisarGastos(String pesquisa)
	{
		GastoDadosTable hashCadaver = new GastoDadosTable("GASTOS.DAT",100);
		GastoPNode tmp = (GastoPNode)hashCadaver.getEmptyNode();

		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if (!tmp.getKey().equals("") && 
    (tmp.getKey().equalsIgnoreCase(tmp.getModel().getTipoGasto()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getDataGasto()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getCategoriaGasto()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getDataAgendamento()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getStatusManutencao()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getFornecedor()) ||
     tmp.getKey().equalsIgnoreCase("" + tmp.getModel().getValorGasto()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getDataDeCadastro()) ||
     tmp.getKey().equalsIgnoreCase(tmp.getModel().getDescricao()) ||
     tmp.getKey().equalsIgnoreCase("" + tmp.getModel().getStatusRegisto()))) 
{						
    JOptionPane.showMessageDialog(null, tmp.getModel().toString(), 
        "Gestão de Condomínio", JOptionPane.INFORMATION_MESSAGE);
}
	
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

	}


	public static GastoModelo pesquisarGastoPorId(String id)
	{
		GastoDadosTable hashCadaver = new GastoDadosTable("GASTOS.DAT",100);
		GastoPNode tmp = (GastoPNode)hashCadaver.getEmptyNode();
		
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
				{						
					if((""+tmp.getModel().getId()).equals(id))
						return tmp.getModel();
				}	
				
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

		return tmp.getModel();
	}
	//888888888888888888888888888888
	public void reHashFile(int nTableSize)
	{
		//implementar o reHash
	}

	

}