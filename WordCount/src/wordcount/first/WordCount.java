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
	static String outputFilePath = "";//输出文件路径
	
	
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
					return;
				case "-w":
					c = true;
					return;
				case "-l":
					c = true;
					return;
				case "-o":
					c = true;
					i++;
					outputFilePath = args[i];
					return;
				default:
					inputFilePath = args[i];
					return;
				}
			}
			if(inputFilePath == "") 
			{
				System.out.println("没有文件名，请输入");
				return;
			}
		}
	}
	
	public static void main(String args[]) throws ParseException 
	{
			
	}
}
