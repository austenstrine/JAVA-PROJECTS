package stats;

import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;
import static java.nio.file.StandardOpenOption.*;

public class FindSelectedFiles 
{

	public static void main(String[] args) 
	{
		JOptionPane.showMessageDialog(null, "The program will now search for 'autoexec.bat', 'SameFolder.java', 'FileStatistics.class', and 'Hello.doc' in the folder it exists in."
				+ "\nAn exception/message will be thrown if it does not exist.");
		Path autoexec = Paths.get("autoexec.bat");
		Path sameFolder = Paths.get("SameFolder.java");
		Path fileStatistics = Paths.get("FileStatistics.class");
		Path hello = Paths.get("Hello.doc");
		BufferedOutputStream out = null;
		try
		{
			out = new BufferedOutputStream(Files.newOutputStream(autoexec, WRITE));
			out.close();
			JOptionPane.showMessageDialog(null, "'autoexec.bat' exists.");
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.fillInStackTrace());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error! " + e.fillInStackTrace());
		}
		try
		{
			out = new BufferedOutputStream(Files.newOutputStream(sameFolder, WRITE));
			out.close();
			JOptionPane.showMessageDialog(null, "'SameFolder.java' exists.");
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.fillInStackTrace());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error! " + e.fillInStackTrace());
		}
		try
		{
			out = new BufferedOutputStream(Files.newOutputStream(fileStatistics, WRITE));
			out.close();
			JOptionPane.showMessageDialog(null, "'FileStatistics.class' exists.");
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.fillInStackTrace());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error! " + e.fillInStackTrace());
		}
		try
		{
			out = new BufferedOutputStream(Files.newOutputStream(hello, WRITE));
			out.close();
			JOptionPane.showMessageDialog(null, "'Hello.doc' exists.");
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.fillInStackTrace());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error! " + e.fillInStackTrace());
		}
	}

}
