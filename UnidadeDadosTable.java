/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: UnidadeDadosTable.java
Data: 30/01/2025
--------------------------------------*/

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class UnidadeDadosTable extends AbstractHashTableCoalashed
{
    public UnidadeDadosTable(String filename, int tableSize)
    {
        super(filename, tableSize);
    }

    public UnidadeDadosTable()
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
		UnidadePNode node = (UnidadePNode)getEmptyNode();

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

     public UnidadePNode getNode(String key) throws NullPointerException
	{		
		//calcula a posicao de entrada na tabela apartir da chave key
		int tablePosition = calcularHashCode( key );
	
		UnidadePNode tmp = (UnidadePNode)getNode( tablePosition );
	
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
		UnidadeDadosTable table = new UnidadeDadosTable("UNIDADES.DAT", 100);
		
		UnidadePNode tmp = table.getNode(key);
		
		return !tmp.isEmptyNode();
	}

	
	 
	public static Vector getAllNodes()
	{
		Vector listaNodes = new Vector();
		
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT", 100);
		
		UnidadePNode tmp = new UnidadePNode();
			
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
					System.out.println( tmp.getModel().getNumeroUni() );
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

	public void editarUnidade(UnidadePNode node)
	{		
		try
		{
			int posTabela = calcularHashCode( node.getKey() );
			sobrePorRegisto(node, posTabela);
			JOptionPane.showMessageDialog(null, "Unidade Editada com Sucesso.");

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao editar Unidade.");
		}		
	}

	
	public void eliminarUnidade(UnidadePNode node)
	{
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		
		try
		{
			hashCadaver.openFile();
			node.getModel().setStatusRegisto(false);

			int posTabela = calcularHashCode( node.getKey() );

			hashCadaver.stream.seek(getFilePosition(posTabela));
			node.write(hashCadaver.stream);
			JOptionPane.showMessageDialog(null, "Unidade eliminada com sucesso!");  // Mensagem de sucesso			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao eliminar a unidade.");
		}		
	}

	//adiciona na tabela e depois no ficheiro
	public void adicionarNovaUnidade(UnidadePNode node)
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
			ShowMessage.displayMessage("Falha ao adicionar uma Unidade " + fileName, "ERROR", true);	
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
	public void sobrePorRegisto(UnidadePNode node, int posTabela)
	{	
		UnidadePNode no = (UnidadePNode)getNode(posTabela);	
		
		node.setNext(no.getNext());
		
		node.setPrev(no.getPrev());	
		
		adicionarNoFicheiro(node, posTabela);
		
	}
	//segredo do projecto
	//adiciona na lista de colisoes
	public void adicionarNaListaColisoes(UnidadePNode node, int lastColision)
	{		
		UnidadePNode no = (UnidadePNode)getNode(lastColision);
		
		 //se nao houver ninguem na lista, adicionar
		if (no.getNext() == null && lastColision != tableSize - 1)
		{			
            no.setNext(node);
			node.setPrev(no);
		
			adicionarNoFicheiro(node, tableSize - 1);				
		}
		else
		{
			UnidadePNode tmp = no.getNext();
		
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
		return (2 * 4 + tablePosition * UnidadePNode.sizeof());
 	}


	public SaveWriteReadInteface getEmptyNode()
	{
		return new UnidadePNode();
	}

	public static int getNextID()
	{
		//UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		//return hashCadaver.getNextAutoId() + 1;
		
		return 1;
	}
	//

	public static String[][] listarUnidades()
	{
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		UnidadePNode tmp = (UnidadePNode)hashCadaver.getEmptyNode();
		
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
					dados[contador][0] = "" + tmp.getModel().getId();                     // Acessando o getter do 'model' para 'getId()'
					dados[contador][1] = tmp.getModel().getTipoUnidade();                    // Acessando o getter do 'model' para 'getTipoUnidade()'
					dados[contador][2] = "" + tmp.getModel().getNumeroUni();                 // Acessando o getter do 'model' para 'getNumeroUni()'
					dados[contador][3] = tmp.getModel().getBloco();                          // Acessando o getter do 'model' para 'getBloco()'
					dados[contador][4] = "" + tmp.getModel().getAndares();                   // Acessando o getter do 'model' para 'getAndares()'
					dados[contador][5] = "" + tmp.getModel().getArea();                      // Acessando o getter do 'model' para 'getArea()'
					dados[contador][6] = "" + tmp.getModel().getNumQuartos();                // Acessando o getter do 'model' para 'getNumQuartos()'
					dados[contador][7] = "" + tmp.getModel().getGaragemCapaci();             // Acessando o getter do 'model' para 'getGaragemCapaci()'
					if(tmp.getModel().getStatusUnidade() == true)
						dados[contador][8] = "disponivel";             // Acessando o getter do 'model' para 'getStatusUnidade()'
					else
						dados[contador][8] = "indisponivel";            // Acessando o getter do 'model' para 'getStatusUnidade()'
					dados[contador][9] = tmp.getModel().getDataCadastro();                  // Acessando o getter do 'model' para 'getDataCadastro()'

					contador++;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	

		return dados;
	}

	public static UnidadeModelo pesquisarUnidadePorNumeroUni(String numeroUni)
	{
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		UnidadePNode tmp = (UnidadePNode)hashCadaver.getEmptyNode();
		
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true && tmp.getModel().getNumeroUni().equalsIgnoreCase(numeroUni) || numeroUni.equalsIgnoreCase(""+tmp.getModel().getId()))
				{						
					return tmp.getModel();
				}	
				
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

		return null;
	}

	public static void pesquisarUnidades(String pesquisa)
	{
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		UnidadePNode tmp = (UnidadePNode)hashCadaver.getEmptyNode();

		boolean resposta = (pesquisa == "disponivel" ? true : false);

		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if (!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true) 
{						
    if (pesquisa.equalsIgnoreCase(tmp.getModel().getTipoUnidade()) ||
                    pesquisa.equalsIgnoreCase(tmp.getModel().getNumeroUni()) ||
                    pesquisa.equalsIgnoreCase(tmp.getModel().getBloco()) ||
                    pesquisa.equalsIgnoreCase("" + tmp.getModel().getAndares()) ||
                    pesquisa.equalsIgnoreCase("" + tmp.getModel().getArea()) ||
                    pesquisa.equalsIgnoreCase("" + tmp.getModel().getNumQuartos()) ||
                    pesquisa.equalsIgnoreCase("" + tmp.getModel().getGaragemCapaci()) ||
                    pesquisa.equalsIgnoreCase(tmp.getModel().getImagem()) ||
                    pesquisa.equalsIgnoreCase(tmp.getModel().getDataCadastro()) ||
                    resposta == tmp.getModel().getStatusUnidade()) {
					
                    JOptionPane.showMessageDialog(null, tmp.getModel().toString(),
                        "Gestão de Condomínio", JOptionPane.INFORMATION_MESSAGE);
                }
}
	
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

	}

	public static UnidadeModelo pesquisarUnidadePorId(String id)
	{
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		UnidadePNode tmp = (UnidadePNode)hashCadaver.getEmptyNode();
		
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

	public static void pesquisarUnidadePorBlocoNumero(String pesquisa)
	{
		UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		UnidadePNode tmp = (UnidadePNode)hashCadaver.getEmptyNode();
		
		String output = "";
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);
				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
				{	
					if (tmp.getModel().getNumeroUni().startsWith(pesquisa) || tmp.getModel().getBloco().equalsIgnoreCase( pesquisa ) )
					{
						output += tmp.toString();
						output += "---------------------------------------";
						JOptionPane.showMessageDialog(null, tmp.toString(), 
						"Gestao de Condominio", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}


	//888888888888888888888888888888
	public void reHashFile(int nTableSize)
	{
		//implementar o reHash
	}

	

}