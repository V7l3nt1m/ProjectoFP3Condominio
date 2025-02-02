/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: UnidadePNode.java
Data: 30/01/2025
--------------------------------------*/

import java.io.IOException;
import java.io.RandomAccessFile;
  
public class UnidadePNode extends UnidadeDadosTable implements SaveWriteReadInteface
  {
    private UnidadePNode next, prev;
    private UnidadeModelo model;
    //protected int numeroAluno;
    
	public UnidadePNode(UnidadeModelo model)
    {
		super("UNIDADES.DAT", 100);
		this.model = model;
		next = prev = null;
    } 
    public UnidadePNode()
    { 
		    model = new UnidadeModelo();	
    }
    
    public String getKey()
    {
		    return model.getNumeroUni();
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

    public UnidadeModelo getModel()
    {
		return model;
    }
    public void setPrev(UnidadePNode prev)
    {
		this.prev = prev;
    }

    public void setNext(UnidadePNode next)
    {
		this.next = next;
    }

    public UnidadePNode getNext()
    {
		return next;
    }
    public UnidadePNode getPrev()
    {
		return prev;
    }
	
    public void save()
    {
		    adicionarNovaUnidade(this);
    }

    public void eliminar()
    {
        eliminarUnidade(this);
    }

     public void editar()
    {
        editarUnidade(this);
    }
   
    public static long sizeof()
    {		
		UnidadePNode node = new UnidadePNode();
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
