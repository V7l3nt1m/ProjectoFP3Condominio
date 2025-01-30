
import java.io.*;
import SwingComponents.ObjectGeneric;

public abstract class AbstractHashTableCoalashed extends ObjectGeneric
{
	protected String fileName;
	protected RandomAccessFile stream, tmpStream;
	protected int tableSize;
	protected int nElementos;
	
	public AbstractHashTableCoalashed()
		{	}
	
	public AbstractHashTableCoalashed(String fileName, int tableSize)
	{
		this.fileName = fileName;
		this.tableSize = tableSize;
		nElementos = 0;
		
		if (existe(fileName) == true)
			openFile();
		else
		{
			openFile();
			try
			{
				inicializarFicheiro(tableSize, nElementos, stream);
			}
			catch (IOException ex)
			{
				ShowMessage.displayMessage("Falha ao tentar escrever no ficheiro " + fileName,
				"ERROR", true);	
			}
		}		
	}
	
	//abrir o ficheiro	
	public void openFile()
	{
		try
		{
			stream = new RandomAccessFile( fileName, "rw" );
		}
		catch (IOException io)
		{			
			ShowMessage.displayMessage("Falha no acesso ao ficheiro " + fileName,
			"ERROR", true);						
		}
	}
	
	//abrir o ficheiro	
	public void openTempFile()
	{
		try
		{
			tmpStream = new RandomAccessFile( "tmp" + fileName, "rw" );
		}
		catch (IOException io)
		{			
			ShowMessage.displayMessage("Falha no acesso ao ficheiro " + "tmp" + fileName, "ERROR", true);						
		}
	}
	
	
	//inicializar o ficheiro com o tamanho da tabela e o factor de carga
	public void inicializarFicheiro(int tableSize, int nElementos, RandomAccessFile stream) throws IOException
	{
		stream.writeInt(tableSize);
		stream.writeInt(nElementos);
		
		SaveWriteReadInteface node = getEmptyNode();
		
		for (int i = 0; i < tableSize; ++i)
		{
			node.write(stream);
			//node.increCodigoAluno();
		}			
			
		stream.close();
	}
	
	public int getNElements()
	{
		return nElementos;
	}
	
	//permite saber qais os valores actuais da tabela e do nElementos
	public int getCurrentNElements()
	{
		openFile(); //criar o canal	
		try
		{			
			//posiciona - se na posicao do factor de carga
			stream.seek(4);			
			
			nElementos = stream.readInt();					
		}
		
		catch (IOException ex)
		{
			ShowMessage.displayMessage("Falha na leitura do numero de elementos do ficheiro " + fileName,
			"ERROR", true);	
		}
		
		return nElementos;
	}
	
	//chamado cada vez que se introduz um novo registo no ficheiro
	public void actualizarNElementos()
	{		
		long currentPosition;
		
		try
		{
			//guarda a posicao corrente
			currentPosition = stream.getFilePointer();
			
			//posiciona - se na posicao do factor de carga
			stream.seek(4);			
			
			nElementos++;
			
			//escreve o novo factor de carga
			stream.writeInt(nElementos);
			
			//volta a posicao onde estava
			stream.seek(currentPosition);
			
			//fecha o canal
			stream.close();
		}
		
		catch (IOException ex)
		{
			ShowMessage.displayMessage("Falha na escrita do ficheiro " + fileName,
			"ERROR", true);	
		}
	}
	
	public void ouverFlowFile(int nTableSize, int nElements)
	{
		try
		{
			System.out.println("Esta no ouverFlow");
			openTempFile(); //criar o canal
			
			inicializarFicheiro(nTableSize * 2, nElements, tmpStream);
			
			reHashFile(nTableSize);
			
			stream.close();
			tmpStream.close();
			
			File tmp = new File("tmp"+fileName);
			tmp.renameTo(new File(fileName));
			
			tableSize = tableSize * 2;
		}
		catch (IOException ex)
		{
			ShowMessage.displayMessage("Falha ao tentar escrever no ficheiro " + "tmp" + fileName,
			"ERROR", true);	
		}		
	}
	
	public void adicionarNoFicheiro(SaveWriteReadInteface node, int posicaoTabela)
	{
		/*
			1 - openFile(); //criar o canal
			2 - posicionar - se na posicao (posicaoTabela)
			3 - escrever o node
		*/
		
		
		try
		{
			openFile(); //criar o canal
			
			long posFile = getFilePosition(posicaoTabela);		
			
			//node.setCodigoAluno(posicaoTabela);
System.err.println("Nome-> " + node.getKey() + "PosTable -> " + posicaoTabela + "PosFile" +  posFile);
			incrementnElementos();
									
			stream.seek(posFile);
			
			node.write(stream);			
			
			//actualizarNElementos();
	
			stream.close(); //fecha o canal
		}
		
		catch (Exception ex)
		{
			ShowMessage.displayMessage("Falha ao adicionar 1 novo aluno no ficheiro " + fileName, "ERROR", true);	
		}	
	}
	
	public void adicionarNoFicheiroTmp(SaveWriteReadInteface node, int posicaoTabela)
	{
		/*
			1 - openFile(); //criar o canal
			2 - posicionar - se na posicao (posicaoTabela)
			3 - escrever o node
		*/
		
		try
		{
			openTempFile(); //criar o canal
			
			long posFile = getFilePosition(posicaoTabela);		
			
			//node.setCodigoAluno(posicaoTabela);
									
			tmpStream.seek(posFile);
			node.write(tmpStream);			
	
			tmpStream.close(); //fecha o canal
		}
		
		catch (Exception ex)
		{
			ShowMessage.displayMessage("Falha ao adicionar 1 novo aluno no ficheiro tmp" + fileName, "ERROR", true);	
		}	
	}
	
	//verifica se o ficheiro ja existe
	public static boolean existe(String f)
	{
		File stream = new File(f);
		return ((stream.exists() == true) && (stream.isFile() == true));
	}
	
	//devolve a dimensao da tabela
	public int getTableSize()
	{
		return tableSize;
	}
	
	//devolve o factor de carga
	public int getnElementos()
	{
		return nElementos;
	}
	
	//incrementa o factor de carga
	public int incrementnElementos()
	{		
		try
		{		
			//posiciona - se na posicao do factor de carga
			stream.seek(4);					
			nElementos = stream.readInt();
			
			nElementos++;
			
			stream.seek(4);
			//escreve o novo factor de carga
			stream.writeInt(nElementos);			
		}
		
		catch (IOException ex)
		{
			ShowMessage.displayMessage("Falha na escrita do ficheiro " + fileName,
			"ERROR", true);	
		}
		
		return nElementos;
	}
	
	public String toString()
	{
		String str = "{ ";
		
		openFile();
		
		SaveWriteReadInteface tmp = getEmptyNode();
		
		try
		{		
			//posiciona - se na posicao do factor de carga
			stream.seek(8);		
		
			for (int i = 0; i < tableSize; ++i)
			{
				tmp.read( stream );
				
				if ( !tmp.isEmptyNode() && !( tmp.getKey().equalsIgnoreCase("") ) )
				{
					str += "Registo -> " + i + "\n";
					str += tmp.toString() + "\n";	
				}
					
			}			
		}
		
		catch (IOException ex)
		{
			ShowMessage.displayMessage("Falha na leitura do ficheiro " + fileName,
			"ERROR", true);	
		}
		
		return str + " }";
	}
	
	public abstract SaveWriteReadInteface getEmptyNode();
	public abstract long getFilePosition( int p );
	public abstract SaveWriteReadInteface getNode( int pos );
	public abstract int calcularHashCodeReHashing(String key);
	public abstract void reHashFile(int nTableSize);
}