package wordcount.first;
import java.io.*;
import java.util.*;

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
	static int codeline_num;//��������
	static int annoline_num;//ע������
	static int emptyline_num;//������
	
	//��ʼ�ļ�·������
	static String inputFilePath = "";//�����ļ�·��
	static String outputFilePath = "result.txt";//����ļ�·��
	
	//����������
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
				case "-c": //�ַ���������
					c = true;
					break;
				case "-w": //���ʲ�������
					w = true;
					break;
				case "-l": //������������
					l = true;
					break;
				case "-o": //�����������
					o = true;
					i++;
					outputFilePath = args[i];
					break;
				case "-s": //�ݹ��ļ���
					s = true;
					break;
				case "-a": //���Ӵ���
					a = true;
					break;
				case "-e": //ͣ�ñ�
					e = true;
					break;
				default: //����·����������
					inputFilePath = args[i];
					break;
				}
			}
			if(inputFilePath == "") //���ļ�����ʱ����
			{
				System.out.println("û���ļ�����������");
				return;
			}
		}
	}
	
	//���ַ�
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
	
	//����
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
	
	//������
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
	
	//��������
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
	
	//д�ļ�
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
			if(c) //���ַ���������ʱ
			{
				System.out.println(readCharactersFromFile(inputFilePath));
				String charContent = inputFilePath + ",�ַ���:" + char_num + "\r\n";
				outputFileContent += charContent;	
			}	
			if(l) //���в�������ʱ
			{
				System.out.println(readLinesFromFile(inputFilePath));
				String lineContent = inputFilePath + ",����:" + line_num + "\r\n";
				outputFileContent += lineContent;	
			}
			if(w) //�����ʲ�������ʱ
			{
				System.out.println(readWordsFromFile(inputFilePath));
				String wordContent = inputFilePath + ",������:" + word_num + "\r\n";
				outputFileContent += wordContent;	
			}
			if(a) 
			{
				readComplexLinesFromFile(inputFilePath);
				System.out.println(codeline_num);
				String complexLineContent = inputFilePath + ",������/����/ע����:" + codeline_num + "/" + emptyline_num + "/" + annoline_num + "\r\n";
				outputFileContent += complexLineContent;	
			}
			writeFile(outputFilePath, outputFileContent);
		} catch (FileNotFoundException e2) 
		{
			e2.printStackTrace();
		}
			
	}
}
