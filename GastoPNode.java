/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: GastoPNode.java
Data: 10/02/2025
--------------------------------------*/

import java.io.IOException;
import java.io.RandomAccessFile;
  
public class GastoPNode extends GastoDadosTable implements SaveWriteReadInteface
  {
    private GastoPNode next, prev;
    private GastoModelo model;
    //protected int numeroAluno;
    
	public GastoPNode(GastoModelo model)
    {
		super("GASTOS.DAT", 100);
		this.model = model;
		next = prev = null;
    } 
    public GastoPNode()
    { 
		    model = new GastoModelo();	
    }
    
    public String getKey()
    {
		return model.getDescricao();
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

    public GastoModelo getModel()
    {
		return model;
    }
    public void setPrev(GastoPNode prev)
    {
		this.prev = prev;
    }

    public void setNext(GastoPNode next)
    {
		this.next = next;
    }

    public GastoPNode getNext()
    {
		return next;
    }
    public GastoPNode getPrev()
    {
		return prev;
    }
	
    public void save()
    {
		    adicionarNovoGasto(this);
    }

    public void eliminar()
    {
        eliminarGasto(this);
    }

     public void editar()
    {
        editarGasto(this);
    }
   
    public static long sizeof()
    {		
		GastoPNode node = new GastoPNode();
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
