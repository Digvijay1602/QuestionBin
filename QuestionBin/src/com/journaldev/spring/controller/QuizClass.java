package com.journaldev.spring.controller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class QuizClass  
{ 

	public static void main(String[] args) 
			throws IOException 
	{ 

		try{
			System.out.println("                QUIZ TEST");
			System.out.println("               ----------- ");
			System.out.println("Choose Type of Quiz 1.Physics 2.GeneralKnowledge  3.Mathematics  ");
			Scanner filenamescnner=new Scanner(System.in);
			String filename=filenamescnner.nextLine();
			File questionloc=null;
			
			if(filename.equals("1")){
				questionloc=new File("C:\\Users\\NAVEEN\\Documents\\Physics.txt");
			}else if(filename.equals("2")){
				questionloc=new File("C:\\Users\\NAVEEN\\Documents\\GeneralKnowledge.txt");
			}else if(filename.equals("3")){
				questionloc=new File("C:\\Users\\NAVEEN\\Documents\\Mathematics.txt");
			}
			
			FileInputStream fileInStem=new FileInputStream(questionloc);
			
			BufferedInputStream buffReader=new BufferedInputStream(fileInStem);  
			
			int a;
			int score = 0;
			int positivemarks=10;
			int negativemarks=-5;
			int correct=0;
			int wrong=0;
			StringBuilder  fileStringBuilder=new StringBuilder();
			
			while((a=buffReader.read())!=-1){ 
				fileStringBuilder.append((char)a);
			}
			String[] splitFile = fileStringBuilder.toString().split("::");
			
			List<String> questionList=Arrays.asList(splitFile);
			Collections.shuffle(questionList);
						
			List<String> quesList=new ArrayList<String>();
			List<String> answList=new ArrayList<String>();
			List<String> wrongAnswerList=new ArrayList<String>();
			
			String[] questionArray=null;
			
			for (int i=0; i<questionList.size(); i++){
				questionArray=questionList.get(i).split("AnswerAnswer");
				quesList.add(questionArray[0].trim()); 
				answList.add(questionArray[1].trim());
			}
			
			Scanner sc=new Scanner(System.in);
			
			
			System.out.println("Total number of question="+questionList.size() +"    "+"Total marks="+ questionList.size()*10);
			System.out.println("Rule:- Choose the correct option from A,B,C and D");
			System.out.println("    ");
			System.out.println("What is your name?");
			String name=sc.nextLine();
			System.out.println("    ");
			System.out.println(name+" Now,choose one option from each question");
			System.out.println("    ");
			
			for (int i = 0; i < quesList.size(); i++) {		
				int k=i+1;						
				System.out.println((k)+". "+quesList.get(i));
				String str=answList.toString().replaceAll(",", "");
				char[] charAnswer = str.substring(1, str.length()-1).replaceAll(" ", "").toCharArray();
				
				while(true)
				{
					
					char answer = sc.nextLine().charAt(0);
					
					answer  = Character.toUpperCase(answer);
					
					if ( charAnswer[i] == answer)
					{
						correct++;
						score= score+positivemarks;
						break;
					}	
					else{
						wrongAnswerList.add((k)+"."+quesList.get(i));
						wrongAnswerList.add("--> "+" Answer:"+answList.get(i));
						wrongAnswerList.add(" ");
						wrong++;
						score += negativemarks;
						break;
					}					
				}
			}
			
			System.out.println("                       ");
            System.out.println("Correct Answer ="+correct+"   "+"Wrong Answer ="+wrong);
            System.out.println("                       ");
			System.out.println(name+"  You Score="+score+"/"+ quesList.size()*10);
			System.out.println("                       ");
			System.out.println("Your Wrong question and answer:-");
			System.out.println("                       ");
			for (int i = 0; i < wrongAnswerList.size(); i++) {
				System.out.println(wrongAnswerList.get(i));	
				}
			buffReader.close();   
			fileInStem.close(); 
            sc.close();
            filenamescnner.close();
            
		}catch(Exception e){System.out.println(e);}    
	}    
}  
