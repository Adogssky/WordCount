package wordcount.first;
import java.io.*;
import java.util.*;

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
	static int codeline_num;//代码行数
	static int annoline_num;//注释行数
	static int emptyline_num;//空行数
	
	//初始文件路径变量
	static String inputFilePath = "";//输入文件路径
	static String outputFilePath = "result.txt";//输出文件路径
	
	//参数处理部分
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
				case "-c": //字符参数处理
					c = true;
					break;
				case "-w": //单词参数处理
					w = true;
					break;
				case "-l": //行数参数处理
					l = true;
					break;
				case "-o": //输出参数处理
					o = true;
					i++;
					outputFilePath = args[i];
					break;
				case "-s": //递归文件夹
					s = true;
					break;
				case "-a": //复杂处理
					a = true;
					break;
				case "-e": //停用表
					e = true;
					break;
				default: //输入路径参数处理
					inputFilePath = args[i];
					break;
				}
			}
			if(inputFilePath == "") //无文件输入时报错
			{
				System.out.println("没有文件名，请输入");
				return;
			}
		}
	}
	
	//读字符
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
	
	//读行
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
	
	//读单词
	public static int readWordsFromFile(String inputFilePath) throws IOException
	{
		File file = new File(inputFilePath);  
        BufferedReader reader = null;   
        reader = new BufferedReader(new FileReader(file));  
        String tempString = null;   
        while ((tempString = reader.readLine()) != null)
        {    
        	word_num += tempString.split("( |,)+").length;
        }  
        reader.close();
        return word_num; 
	}
	
	//读复杂行
	public static void readComplexLinesFromFile(String inputFilePath) throws IOException 
	{
		int emptyFlag = 0;
		File file = new File(inputFilePath);  
        BufferedReader reader = null;   
        reader = new BufferedReader(new FileReader(file));  
        String tempString = null;   
        while ((tempString = reader.readLine()) != null)
        {    
        	char[] array = tempString.toCharArray();
        	for(int i =0 ; i < array.length; i++) 
        	{
        		if(array[i] != ' ')
        		{
        			emptyFlag = 1;
        			if((array[i] == '/' && array[i+1] == '/') || (array[i] == '/' && array[i+1] == '*') || (array[array.length - 2] == '*' && array[array.length -1] == '/'))
        			{
        				annoline_num++;
        				break;
        			}
        			else
        			{
        				codeline_num++;
        				break;
        			}
        		}
        			
        	}
        	if(emptyFlag == 0)
        		emptyline_num++;
        	emptyFlag = 0;
        }  
            reader.close();	

	}
	
	//写文件
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
            e.printStackTrace();  
        }		
	}
	
	public static void main(String args[]) throws IOException 
	{
		try {
			String outputFileContent = "";
			parProcess(args);
			System.out.println(inputFilePath);
			if(c) //读字符参数输入时
			{
				System.out.println(readCharactersFromFile(inputFilePath));
				String charContent = inputFilePath + ",字符数:" + char_num + "\r\n";
				outputFileContent += charContent;	
			}	
			if(l) //读行参数输入时
			{
				System.out.println(readLinesFromFile(inputFilePath));
				String lineContent = inputFilePath + ",行数:" + line_num + "\r\n";
				outputFileContent += lineContent;	
			}
			if(w) //读单词参数输入时
			{
				System.out.println(readWordsFromFile(inputFilePath));
				String wordContent = inputFilePath + ",单词数:" + word_num + "\r\n";
				outputFileContent += wordContent;	
			}
			if(a) 
			{
				readComplexLinesFromFile(inputFilePath);
				System.out.println(codeline_num);
				String complexLineContent = inputFilePath + ",代码行/空行/注释行:" + codeline_num + "/" + emptyline_num + "/" + annoline_num + "\r\n";
				outputFileContent += complexLineContent;	
			}
			writeFile(outputFilePath, outputFileContent);
		} catch (FileNotFoundException e2) 
		{
			e2.printStackTrace();
		}
			
	}
}
