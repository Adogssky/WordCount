package wordcount.first;
import org.apache.commons.cli.*;
import java.io.*;

public class WordCount 
{
	//初始参数
	static boolean c = false;//字符
	static boolean w = false;//单词
	static boolean l = false;//行数
	static boolean o = false;//输出到指定文件
	static boolean s = false;//递归
	static boolean a = false;//更复杂的数据
	static boolean e = false;//停用表
	
	//初始计数
	static int char_num = 0;//字符数
	static int word_num = 0;//单词数
	static int line_num = 0;//行数
	
	//初始文件路径变量
	static String inputFilePath = "";//输入文件路径
	static String outputFilePath = "result.txt";//输出文件路径
	
	
	public static void parProcess(String args[]) 
	{
		if(args.length == 0) 
		{
			System.out.println("无参数输入，请输入处理参数");
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
				System.out.println("没有文件名，请输入");
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
				String charContent = inputFilePath + ",字符数:" + char_num + "\r\n";
				outputFileContent += charContent;	
			}	
			if(l) 
			{
				System.out.println(readLinesFromFile(inputFilePath));
				String lineContent = inputFilePath + ",行数:" + line_num + "\r\n";
				outputFileContent += lineContent;	
			}
			if(w) 
			{
				System.out.println(readWordsFromFile(inputFilePath));
				String wordContent = inputFilePath + ",单词数:" + word_num + "\r\n";
				outputFileContent += wordContent;	
			}
			writeFile(outputFilePath, outputFileContent);
		} catch (FileNotFoundException e2) 
		{
			e2.printStackTrace();
		}
			
	}
}
