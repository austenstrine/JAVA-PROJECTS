package stats;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class FileStatistics2 
{
	public static void main(String[] args)
	{
		Path docx = Paths.get("Who am I.docx");
		Path rtf = Paths.get("Who am I.rtf");
		try {
			BasicFileAttributes docxAt = 
					Files.readAttributes(docx, BasicFileAttributes.class);
			BasicFileAttributes rtfAt = 
					Files.readAttributes(rtf, BasicFileAttributes.class);
			long size1 = rtfAt.size();
			long size2 = docxAt.size();
			if(size1 > size2)
			{
				System.out.println(rtf.getFileName() + " is larger than " + docx.getFileName());
			}
			else if(size1 < size2)
			{
				System.out.println(docx.getFileName() + " is larger than " + rtf.getFileName());
			}
			else
			{
				System.out.println(docx.getFileName() + " and " + rtf.getFileName() + " are equal in size.");
			}
			System.out.println(rtf.getFileName() + " is " + size1 + " bytes, where " + docx.getFileName() + " is " + size2 + " bytes."
					+ "\nThe ratio of " + rtf.getFileName() +" to "+ docx.getFileName() + " is about " + size2/size1 + " to 1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
