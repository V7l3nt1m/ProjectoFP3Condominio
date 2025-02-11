/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: PagamentoPNode.java
Data: 10/02/2025
--------------------------------------*/

import java.io.IOException;
import java.io.RandomAccessFile;
  
public class PagamentoPNode extends PagamentoDadosTable implements SaveWriteReadInteface
  {
    private PagamentoPNode next, prev;
    private PagamentoModelo model;
    //protected int numeroAluno;
    
	public PagamentoPNode(PagamentoModelo model)
    {
		super("PAGAMENTOS.DAT", 100);
		this.model = model;
		next = prev = null;
    } 
    public PagamentoPNode()
    { 
		    model = new PagamentoModelo();	
    }
    
    public String getKey()
    {
		return ""+model.getDescricao();
    }
    
    public boolean isEmptyNode()
    {
		return ( getKey().equalsIgnoreCase("") == true );
    }

    public void write(RandomAccessFile stream) throws IOException
    {
		//stream.writeInt(codigoAluno);
		//stream.writeBoolean(eliminado);		
		    model.write(stream);
    }
    
    public void read(RandomAccessFile stream) throws IOException
    {
		//codigoAluno = stream.readInt();	
		//eliminado = stream.readBoolean();		
		    model.read(stream);
    }	

    public PagamentoModelo getModel()
    {
		return model;
    }
    public void setPrev(PagamentoPNode prev)
    {
		this.prev = prev;
    }

    public void setNext(PagamentoPNode next)
    {
		this.next = next;
    }

    public PagamentoPNode getNext()
    {
		return next;
    }
    public PagamentoPNode getPrev()
    {
		return prev;
    }
	
    public void save()
    {
		    adicionarNovoPagamento(this);
    }

    public void eliminar()
    {
        eliminarPagamento(this);
    }

     public void editar()
    {
        editarPagamento(this);
    }
   
    public static long sizeof()
    {		
		PagamentoPNode node = new PagamentoPNode();
		try
        { 
            return  node.model.sizeof();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
     }	
     public String toString()
     {
		    return model.toString();
     }




  }
