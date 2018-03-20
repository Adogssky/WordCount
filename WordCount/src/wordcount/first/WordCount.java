package wordcount.first;
import org.apache.commons.cli.*;
import java.io.*;

public class WordCount 
{
	//��ʼ����
	static boolean c = false;//�ַ�
	static boolean w = false;//����
	static boolean l = false;//����
	static boolean o = false;//�����ָ���ļ�
	static boolean s = false;//�ݹ�
	static boolean a = false;//�����ӵ�����
	static boolean e = false;//ͣ�ñ�
	
	//��ʼ����
	static int char_num = 0;//�ַ���
	static int word_num = 0;//������
	static int line_num = 0;//����
	
	//��ʼ�ļ�·������
	static String inputFilePath = "";//�����ļ�·��
	static String outputFilePath = "result.txt";//����ļ�·��
	
	
	public static void parProcess(String args[]) 
	{
		if(args.length == 0) 
		{
			System.out.println("�޲������룬�����봦�����");
			return;
		}
		else 
		{
			for(int i = 0; i < args.length ; i++) 
			{
				switch(args[i])
				{
				case "-c":
					c = true;
					break;
				case "-w":
					w = true;
					break;
				case "-l":
					l = true;
					break;
				case "-o":
					o = true;
					i++;
					outputFilePath = args[i];
					break;
				default:
					inputFilePath = args[i];
					break;
				}
			}
			if(inputFilePath == "") 
			{
				System.out.println("û���ļ�����������");
				return;
			}
		}
	}
	
	public static int readCharactersFromFile(String inputFilePath) throws IOException 
	{
		File file = new File(inputFilePath);  
        BufferedReader reader = null;   
        reader = new BufferedReader(new FileReader(file));  
        String tempString = null;   
        while ((tempString = reader.readLine()) != null)
        {    
        	char_num += tempString.length();  
        }  
            reader.close();
			return char_num;

	}
	
	public static int readLinesFromFile(String inputFilePath) throws IOException
	{
		File file = new File(inputFilePath);  
        BufferedReader reader = null;   
        reader = new BufferedReader(new FileReader(file));  
        String tempString = null;   
        while ((tempString = reader.readLine()) != null)
        {    
        	line_num++;  
        }  
            reader.close();
			return line_num;
	}
	
	public static int readWordsFromFile(String inputFilePath) throws IOException
	{
		File file = new File(inputFilePath);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		int tempchar;
		while ((tempchar = reader.read()) != -1)
		{   
            if (((char) tempchar) != '\r') 
            {  
                word_num++;  
            }  
         }  
        reader.close();
		return word_num;  
	}
	
	public static void writeFile(String outputFilePath, String outputFileContent) throws IOException 
	{
        try {  
            File file = new File(outputFilePath);  
            if (!file.exists()) {  
	            file.createNewFile();  
	        } 
            FileWriter fw = new FileWriter(outputFilePath, true);  
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(outputFileContent);
            bw.close();
            fw.close(); 
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }		
	}
	

	public static void main(String args[]) throws IOException 
	{
		try {
			String outputFileContent = "";
			parProcess(args);
			System.out.println(inputFilePath);
			if(c) 
			{
				System.out.println(readCharactersFromFile(inputFilePath));
				String charContent = inputFilePath + ",�ַ���:" + char_num + "\r\n";
				outputFileContent += charContent;	
			}	
			if(l) 
			{
				System.out.println(readLinesFromFile(inputFilePath));
				String lineContent = inputFilePath + ",����:" + line_num + "\r\n";
				outputFileContent += lineContent;	
			}
			if(w) 
			{
				System.out.println(readWordsFromFile(inputFilePath));
				String wordContent = inputFilePath + ",������:" + word_num + "\r\n";
				outputFileContent += wordContent;	
			}
			writeFile(outputFilePath, outputFileContent);
		} catch (FileNotFoundException e2) 
		{
			e2.printStackTrace();
		}
			
	}
}
