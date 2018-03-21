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
	static int codeline_num = 0;//��������
	static int annoline_num = 0;//ע������
	static int emptyline_num = 0;//������
	static int prestopword_num = 0; //ͣ�ñ�ǰ������
	static int poststopword_num = 0; //ͣ�ñ�󵥴���
	
	//��ʼ�ļ�·������
	static String inputFilePath = "";//�����ļ�·��
	static String outputFilePath = "result.txt";//����ļ�·��
	static String stopListFilePath = ""; //ͣ�ñ�·��
	
	//�ļ���
	 static ArrayList<String> dirctorys = new ArrayList<String>();;
	
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
					i++;
					stopListFilePath = args[i];
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
	
	//ͣ�ôʱ�
	public static int stopWordList(String inputFilePath, String stopListFilePath) throws IOException 
	{
		int stopword_num = 0;
		String[] stopList = null; //ͣ�ôʱ�
		String[] originalList = null; //ԭ���ʱ�
		File stopFile = new File(stopListFilePath); //ͣ���ļ� 
		File originalFile = new File(inputFilePath); //ԭ�ļ�
		//�ֱ���в���
        BufferedReader reader_0 = null;   
        BufferedReader reader_1 = null; 
        reader_0 = new BufferedReader(new FileReader(stopFile));  
        reader_1 = new BufferedReader(new FileReader(originalFile)); 
        String tempString_0 = null;  
        String tempString_1 = null; 
        //�Ƚϲ���
        while ((tempString_0 = reader_0.readLine()) != null)
        {    
        	stopList= tempString_0.split("( |,)+");
        	while ((tempString_1 = reader_1.readLine()) != null)
            {    
            	originalList = tempString_1.split("( |,)+");
            	prestopword_num += tempString_1.split("( |,)+").length;
            	for(int i = 0; i < originalList.length; i++) 
                {
                	for(int j = 0; j < stopList.length; j++) 
                	{
                		if(originalList[i].equals(stopList[j])) 
                		{
                			stopword_num++;
                			stopList[j] = " ";
                		}

                	}
                }
            }  
        }  
        reader_0.close();
        reader_1.close();
        poststopword_num = prestopword_num - stopword_num;
        System.out.println(stopword_num);
		return poststopword_num;
        
	}
	
//	public static ArrayList<String> recDirectory(String inputPath) 
//	{
//		File directory = new File(inputPath);    
//        if(directory.isDirectory()){  
//            File [] filelist = directory.listFiles();  
//            for(int i = 0; i < filelist.length; i ++){  
//                //�����ǰ���ļ��У�����ݹ�ɨ���ļ��� 
//                if(filelist[i].isDirectory()){  
//                	System.out.println("fuck");
//                    //�ݹ�ɨ��������ļ��� 
//                	recDirectory(filelist[i].getAbsolutePath());  
//                }  
//                //���ļ���  
//                else{  
//                	String[] array_0 = inputFilePath.split("");
//                	String[] array_1 = filelist[i].getName().split("");
//                	if(array_1[array_1.length - 1].equals(array_0[array_0.length - 1]))
//                		dirctorys.add(filelist[i].getAbsolutePath());
//                }  
//            }
//        }else 
//        {
//        	 throw new NullPointerException("fuck");
//        }
//		return dirctorys;
//	}
//	
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
	
	//����ļ����ݹ���
	public static String contentConstruct() throws IOException 
	{
		String outputFileContent = "";
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
		if(a) //�����в�������
		{
			readComplexLinesFromFile(inputFilePath);
			System.out.println(codeline_num);
			String complexLineContent = inputFilePath + ",������/����/ע����:" + codeline_num + "/" + emptyline_num + "/" + annoline_num + "\r\n";
			outputFileContent += complexLineContent;	
		}
		if(e) //ͣ�ñ�
		{
			System.out.println(stopWordList(inputFilePath, stopListFilePath));
			String postStopWordContent = inputFilePath + ",ͣ�ôʱ�󵥴���:" + poststopword_num + "\r\n";
			outputFileContent += postStopWordContent;
		}
//		if(s) //�ݹ�
//		{
//			recDirectory("./");
//			for(int i = 1; i< dirctorys.size();i++) 
//			{
//				inputFilePath = dirctorys.get(i);
//				contentConstruct();
//			}			
//		}
		return outputFileContent;
	}
	
	public static void main(String args[]) throws IOException 
	{
		try {
			parProcess(args);
			System.out.println(inputFilePath);
			writeFile(outputFilePath, contentConstruct());
		} catch (FileNotFoundException e2) 
		{
			e2.printStackTrace();
		}
			
	}
}
