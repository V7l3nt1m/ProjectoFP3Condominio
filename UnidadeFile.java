/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: UnidadeFile.java
Data: 20/01/2025
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class UnidadeFile extends ObjectsFile
{
	
	public UnidadeFile()
	{
		super("UnidadeFile.dat", new UnidadeModelo() );
	}
	
}