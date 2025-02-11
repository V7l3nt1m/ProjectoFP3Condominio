/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MoradorDadosTable.java
Data: 03/02/2025
--------------------------------------*/

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class MoradorDadosTable extends AbstractHashTableCoalashed
{
    public MoradorDadosTable(String filename, int tableSize)
    {
        super(filename, tableSize);
    }

    public MoradorDadosTable()
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
		MoradorPNode node = (MoradorPNode)getEmptyNode();

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

     public MoradorPNode getNode(String key) throws NullPointerException
	{		
		//calcula a posicao de entrada na tabela apartir da chave key
		int tablePosition = calcularHashCode( key );
	
		MoradorPNode tmp = (MoradorPNode)getNode( tablePosition );
	
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
		MoradorDadosTable table = new MoradorDadosTable("MORADORES.DAT", 100);
		
		MoradorPNode tmp = table.getNode(key);
		
		return !tmp.isEmptyNode();
	}

	
	 
	public static Vector getAllNodes()
	{
		Vector listaNodes = new Vector();
		
		MoradorDadosTable hashCadaver = new MoradorDadosTable("MORADORES.DAT", 100);
		
		MoradorPNode tmp = new MoradorPNode();
			
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
					System.out.println( tmp.getModel().getNumDoc() );
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

	public void editarMorador(MoradorPNode node)
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

	
	public void eliminarMorador(MoradorPNode node)
	{
		MoradorDadosTable hashCadaver = new MoradorDadosTable("MORADORES.DAT",100);
		
		try
		{
			hashCadaver.openFile();
			node.getModel().setStatusRegisto(false);

			int posTabela = calcularHashCode( node.getKey() );

			hashCadaver.stream.seek(getFilePosition(posTabela));
			node.write(hashCadaver.stream);
			JOptionPane.showMessageDialog(null, "Morador eliminado com sucesso!");  // Mensagem de sucesso			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao eliminar a morador.");
		}		
	}

	//adiciona na tabela e depois no ficheiro
	public void adicionarNovoMorador(MoradorPNode node)
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
			ShowMessage.displayMessage("Falha ao adicionar um Morador " + fileName, "ERROR", true);	
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
	public void sobrePorRegisto(MoradorPNode node, int posTabela)
	{	
		MoradorPNode no = (MoradorPNode)getNode(posTabela);	
		
		node.setNext(no.getNext());
		
		node.setPrev(no.getPrev());	
		
		adicionarNoFicheiro(node, posTabela);
		
	}
	//segredo do projecto
	//adiciona na lista de colisoes
	public void adicionarNaListaColisoes(MoradorPNode node, int lastColision)
	{		
		MoradorPNode no = (MoradorPNode)getNode(lastColision);
		
		 //se nao houver ninguem na lista, adicionar
		if (no.getNext() == null && lastColision != tableSize - 1)
		{			
            no.setNext(node);
			node.setPrev(no);
		
			adicionarNoFicheiro(node, tableSize - 1);				
		}
		else
		{
			MoradorPNode tmp = no.getNext();
		
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
		return (2 * 4 + tablePosition * MoradorPNode.sizeof());
 	}


	public SaveWriteReadInteface getEmptyNode()
	{
		return new MoradorPNode();
	}

	public static int getNextID()
	{
		//UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		//return hashCadaver.getNextAutoId() + 1;
		
		return 1;
	}
	//
	public static String[][] listarMoradores()
	{
		MoradorDadosTable hashCadaver = new MoradorDadosTable("MORADORES.DAT",100);
		MoradorPNode tmp = (MoradorPNode)hashCadaver.getEmptyNode();
		
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

			dados = new String[index][11];

			hashCadaver.openFile();
			hashCadaver.stream.seek(8);

			for (int c = 0; c < hashCadaver.tableSize; ++c)
			{
				tmp.read(hashCadaver.stream);
				
				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
				{
					dados[contador][0] = "" + tmp.getModel().getId();                     // Acessando o getter do 'model' para 'getId()'
					dados[contador][1] = tmp.getModel().getNome();                        // Acessando o getter do 'model' para 'getNome()'
					dados[contador][2] = "" + tmp.getModel().getTipoDocumento();          // Acessando o getter do 'model' para 'getTipoDocumento()'
					dados[contador][3] = tmp.getModel().getNumDoc();                      // Acessando o getter do 'model' para 'getNumDoc()'
					dados[contador][4] = "" + tmp.getModel().getTelefone();               // Acessando o getter do 'model' para 'getTelefone()'
					dados[contador][5] = "" + tmp.getModel().getEmail();    
					dados[contador][6] = "" + tmp.getModel().getUnidade();                // Acessando o getter do 'model' para 'getUnidade()'
					dados[contador][7] = "" + tmp.getModel().getNPorta();                 // Acessando o getter do 'model' para 'getNPorta()'
					dados[contador][8] = "" + (tmp.getModel().isResponsavel() ? "Sim" : "Não"); // Acessando o getter do 'model' para 'isResponsavel()'
					if(tmp.getModel().getMoradorResponsavelId() == -1)
						dados[contador][9] = "" + tmp.getModel().getMoradorResponsavelId();  // Acessando o getter do 'model' para 'getMoradorResponsavelId()'
					else
						dados[contador][9] = "" + pesquisarMoradorPorId(""+tmp.getModel().getMoradorResponsavelId()).getNumDoc();  // Acessando o getter do 'model' para 'getMoradorResponsavelId()'
									// Acessando o getter do 'model' para 'getDataCadastro()'
					dados[contador][10] = "" + tmp.getModel().getDataCadastro();           // Acessando o getter do 'model' para 'getDataCadastro()'

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

   public static MoradorModelo pesquisarMoradorIdPorNumDoc(String numDocumento)
   {
		MoradorDadosTable hashCadaver = new MoradorDadosTable("MORADORES.DAT",100);
		MoradorPNode tmp = (MoradorPNode)hashCadaver.getEmptyNode();
		
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true && tmp.getModel().getNumDoc().equalsIgnoreCase(numDocumento))
				{						
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

   public static void showMoradores()
	{
		MoradorDadosTable hashCadaver = new MoradorDadosTable("MORADORES.DAT",100);
		MoradorPNode tmp = (MoradorPNode)hashCadaver.getEmptyNode();
		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
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


	public static MoradorModelo pesquisarMoradorPorId(String id)
	{
		MoradorDadosTable hashCadaver = new MoradorDadosTable("MORADORES.DAT",100);
		MoradorPNode tmp = (MoradorPNode)hashCadaver.getEmptyNode();
		
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

    /*
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
*/

	//888888888888888888888888888888
	public void reHashFile(int nTableSize)
	{
		//implementar o reHash
	}

	

}