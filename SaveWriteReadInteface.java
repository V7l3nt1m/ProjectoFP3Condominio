

import java.io.*;


public interface SaveWriteReadInteface extends Serializable
{
	public void write(RandomAccessFile stream) throws IOException;
	public void read(RandomAccessFile stream) throws IOException;
	public void save();
	public String getKey();
	public boolean isEmptyNode();	
}
