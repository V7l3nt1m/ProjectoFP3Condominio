/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: MoradorPNode.java
Data: 03/02/2025
--------------------------------------*/

import java.io.IOException;
import java.io.RandomAccessFile;
  
public class MoradorPNode extends MoradorDadosTable implements SaveWriteReadInteface
  {
    private MoradorPNode next, prev;
    private MoradorModelo model;
    //protected int numeroAluno;
    
	public MoradorPNode(MoradorModelo model)
    {
		super("MORADORES.DAT", 100);
		this.model = model;
		next = prev = null;
    } 
    public MoradorPNode()
    { 
		    model = new MoradorModelo();	
    }
    
    public String getKey()
    {
		    return model.getNumDoc();
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

    public MoradorModelo getModel()
    {
		return model;
    }
    public void setPrev(MoradorPNode prev)
    {
		this.prev = prev;
    }

    public void setNext(MoradorPNode next)
    {
		this.next = next;
    }

    public MoradorPNode getNext()
    {
		return next;
    }
    public MoradorPNode getPrev()
    {
		return prev;
    }
	
    public void save()
    {
		    adicionarNovoMorador(this);
    }

    public void eliminar()
    {
        eliminarMorador(this);
    }

     public void editar()
    {
        editarMorador(this);
    }
   
    public static long sizeof()
    {		
		MoradorPNode node = new MoradorPNode();
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
