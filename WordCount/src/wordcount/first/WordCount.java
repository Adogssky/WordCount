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
	static String outputFilePath = "";//����ļ�·��
	
	
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
				System.out.println("û���ļ�����������");
				return;
			}
		}
	}
	
	public static void main(String args[]) throws ParseException 
	{
			
	}
}
