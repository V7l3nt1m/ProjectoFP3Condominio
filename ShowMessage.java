

import javax.swing.*;

public class ShowMessage
{
	private static final boolean ERROR = true;
	private static final boolean INFORMATION = false;
	
	public static void displayMessage(String msg, String msgTitle, boolean msgType)
	{
		if (msgType == ERROR)
			JOptionPane.showMessageDialog(null, msg, msgTitle, 
				JOptionPane.ERROR_MESSAGE);
				
		if (msgType == INFORMATION)
			JOptionPane.showMessageDialog(null, msg, msgTitle, 
				JOptionPane.INFORMATION_MESSAGE);
	}
}