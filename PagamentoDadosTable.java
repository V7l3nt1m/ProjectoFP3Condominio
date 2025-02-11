/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: PagamentoDadosTable.java
Data: 10/02/2025
--------------------------------------*/

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class PagamentoDadosTable extends AbstractHashTableCoalashed
{
    public PagamentoDadosTable(String filename, int tableSize)
    {
        super(filename, tableSize);
    }

    public PagamentoDadosTable()
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
		PagamentoPNode node = (PagamentoPNode)getEmptyNode();

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

     public PagamentoPNode getNode(String key) throws NullPointerException
	{		
		//calcula a posicao de entrada na tabela apartir da chave key
		int tablePosition = calcularHashCode( key );
	
		PagamentoPNode tmp = (PagamentoPNode)getNode( tablePosition );
	
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
		PagamentoDadosTable table = new PagamentoDadosTable("PAGAMENTOS.DAT", 100);
		
		PagamentoPNode tmp = table.getNode(key);
		
		return !tmp.isEmptyNode();
	}

	
	 
	public static Vector getAllNodes()
	{
		Vector listaNodes = new Vector();
		
		PagamentoDadosTable hashCadaver = new PagamentoDadosTable("PAGAMENTOS.DAT", 100);
		
		PagamentoPNode tmp = new PagamentoPNode();
			
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
					System.out.println( ""+tmp.getModel().getValorPagar() );
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

	public void editarPagamento(PagamentoPNode node)
	{		
		try
		{
			int posTabela = calcularHashCode( node.getKey() );
			sobrePorRegisto(node, posTabela);
			JOptionPane.showMessageDialog(null, "Pagamento Editada com Sucesso.");

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao editar Pagamento.");
		}		
	}

	
	public void eliminarPagamento(PagamentoPNode node)
	{
		PagamentoDadosTable hashCadaver = new PagamentoDadosTable("PAGAMENTOS.DAT",100);
		
		try
		{
			hashCadaver.openFile();
			node.getModel().setStatusRegisto(false);

			int posTabela = calcularHashCode( node.getKey() );

			hashCadaver.stream.seek(getFilePosition(posTabela));
			node.write(hashCadaver.stream);
			JOptionPane.showMessageDialog(null, "Pagamento eliminado com sucesso!");  // Mensagem de sucesso			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao eliminar a Pagamento.");
		}		
	}

	//adiciona na tabela e depois no ficheiro
	public void adicionarNovoPagamento(PagamentoPNode node)
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
			ShowMessage.displayMessage("Falha ao adicionar um Pagamento " + fileName, "ERROR", true);	
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
	public void sobrePorRegisto(PagamentoPNode node, int posTabela)
	{	
		PagamentoPNode no = (PagamentoPNode)getNode(posTabela);	
		
		node.setNext(no.getNext());
		
		node.setPrev(no.getPrev());	
		
		adicionarNoFicheiro(node, posTabela);
		
	}
	//segredo do projecto
	//adiciona na lista de colisoes
	public void adicionarNaListaColisoes(PagamentoPNode node, int lastColision)
	{		
		PagamentoPNode no = (PagamentoPNode)getNode(lastColision);
		
		 //se nao houver ninguem na lista, adicionar
		if (no.getNext() == null && lastColision != tableSize - 1)
		{			
            no.setNext(node);
			node.setPrev(no);
		
			adicionarNoFicheiro(node, tableSize - 1);				
		}
		else
		{
			PagamentoPNode tmp = no.getNext();
		
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
		return (2 * 4 + tablePosition * PagamentoPNode.sizeof());
 	}


	public SaveWriteReadInteface getEmptyNode()
	{
		return new PagamentoPNode();
	}

	public static int getNextID()
	{
		//UnidadeDadosTable hashCadaver = new UnidadeDadosTable("UNIDADES.DAT",100);
		//return hashCadaver.getNextAutoId() + 1;
		
		return 1;
	}
	//

	public static String[][] listarPagamentos()
	{
		PagamentoDadosTable hashCadaver = new PagamentoDadosTable("PAGAMENTOS.DAT",100);
		PagamentoPNode tmp = (PagamentoPNode)hashCadaver.getEmptyNode();
		
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

			dados = new String[index][9];

			hashCadaver.openFile();
			hashCadaver.stream.seek(8);

			for (int c = 0; c < hashCadaver.tableSize; ++c)
			{
				tmp.read(hashCadaver.stream);
				
				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true)
				{
					MoradorModelo modMor = MoradorDadosTable.pesquisarMoradorPorId(""+tmp.getModel().getIdMorador());
            		UnidadeModelo modUni = UnidadeDadosTable.pesquisarUnidadePorId(""+tmp.getModel().getIdUnidade());
					dados[contador][0] = "" + tmp.getModel().getId();
                    dados[contador][1] = modMor.getNumDoc();
					dados[contador][2] = modUni.getNumeroUni();
                    dados[contador][3] = "" + tmp.getModel().getTipoPagamento();
                    dados[contador][4] = "" + tmp.getModel().getValorPagar();
					dados[contador][5] = tmp.getModel().getDescricao();
                    dados[contador][6] = tmp.getModel().getStatusPagamento();
					dados[contador][7] = "" + tmp.getModel().getDataPagamento();
                    dados[contador][8] = "" + tmp.getModel().getDataCadastro();
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

   public static MoradorModelo pesquisarMoradorNumDocPorId(int id)
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

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true && tmp.getModel().getId() == id)
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

   public static UnidadeModelo pesquisarUnidadeNumUniPorId(int id)
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

				if(!tmp.getKey().equals("") && tmp.getModel().getStatusRegisto() == true && tmp.getModel().getId() == id)
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

   public static void showPagamentos()
	{
		PagamentoDadosTable hashCadaver = new PagamentoDadosTable("PAGAMENTOS.DAT",100);
		PagamentoPNode tmp = (PagamentoPNode)hashCadaver.getEmptyNode();
		
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

	public static void pesquisarPagamentos(String pesquisa)
	{
		PagamentoDadosTable hashCadaver = new PagamentoDadosTable("PAGAMENTOS.DAT",100);
		PagamentoPNode tmp = (PagamentoPNode)hashCadaver.getEmptyNode();

		UnidadeModelo modUni = UnidadeDadosTable.pesquisarUnidadePorNumeroUni(pesquisa);
		MoradorModelo modMorador = MoradorDadosTable.pesquisarMoradorIdPorNumDoc(pesquisa);


		try
		{
			hashCadaver.openFile();
			hashCadaver.stream.seek(8);
			
			for (int i = 0; i < hashCadaver.tableSize; ++i)
			{
				tmp.read(hashCadaver.stream);

				if(!tmp.getKey().equals("") && (tmp.getModel().getTipoPagamento().equalsIgnoreCase(pesquisa) || tmp.getModel().getStatusPagamento().equalsIgnoreCase(pesquisa) || pesquisa.equalsIgnoreCase(""+tmp.getModel().getValorPagar())))
				{						
					JOptionPane.showMessageDialog(null,tmp.getModel().toString(), 
						"Gestao de Condominio", JOptionPane.INFORMATION_MESSAGE);
				}	
				
				if(!tmp.getKey().equals("") && (tmp.getModel().getIdUnidade() == modUni.getId() || tmp.getModel().getIdMorador() == modMorador.getId()))
				{
					JOptionPane.showMessageDialog(null,tmp.getModel().toString(), 
						"Gestao de Condominio", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		

	}


	public static PagamentoModelo pesquisarPagamentoPorId(String id)
	{
		PagamentoDadosTable hashCadaver = new PagamentoDadosTable("PAGAMENTOS.DAT",100);
		PagamentoPNode tmp = (PagamentoPNode)hashCadaver.getEmptyNode();
		
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