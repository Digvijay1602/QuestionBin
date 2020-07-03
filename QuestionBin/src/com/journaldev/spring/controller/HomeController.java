package com.journaldev.spring.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.User;




@Controller
public class HomeController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home()
	{
		return "home";
		
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login()
	{
		return "login";
		
	}
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)  
	public String uploading(User user,Locale locale, Model model,HttpSession session){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		String path=session.getServletContext().getRealPath("/WEB-INF/Log/"); 
		File Idloc=new File(path+""+"LoginId.txt");
		System.out.println(Idloc);
		
		FileInputStream fileStem = null;
		try {
			fileStem = new FileInputStream(Idloc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BufferedInputStream buffReder=new BufferedInputStream(fileStem);  
		
		int a;
		StringBuilder  fileStringBilder=new StringBuilder();
		
		try {
			while((a=buffReder.read())!=-1){ 
				fileStringBilder.append((char)a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] splitlogFile = fileStringBilder.toString().split("loginlogin");
		List<String> logiIdList=Arrays.asList(splitlogFile);
		//System.out.println(logiIdList);
		
		for (int i = 0; i < logiIdList.size(); i++) {
			
			String struserId=user.getUserId().trim();
			String userPwd=user.getPassword().trim();
			
			if (logiIdList.get(i).trim().equalsIgnoreCase(struserId) && userPwd=="sangamone@1") {
			
				return "uploading";
			}
				
		}
		return "errorpage";
	}

	
	@RequestMapping(value="/uploaded",method=RequestMethod.POST)  
	public ModelAndView upload(@RequestParam CommonsMultipartFile file,HttpSession session){  
	        String path=session.getServletContext().getRealPath("/WEB-INF/UPLOADED_FILE/");  
	        String filename=file.getOriginalFilename();  
	          
	        System.out.println(path+""+filename);  
	        try{  
	        byte barr[]=file.getBytes();  
	          
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(path+"/"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	        
	          
	        }catch(Exception e){System.out.println(e);}  
	        return new ModelAndView("upload",filename,path+"/"+filename); 
	       
	    }  
	
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView user(Locale locale, Model model,HttpSession session) {
		//System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		
		String path=session.getServletContext().getRealPath("/WEB-INF/UPLOADED_FILE/"); 
		
		
		File questionloc=new File(path);
		File[] filelist=questionloc.listFiles();
		List<String> List=new ArrayList<String>();
		for (int i = 0; i < filelist.length; i++) {
			File file = filelist[i];
			List.add(file.getName());		
		}
		
		
		String subject = "";
		for (String s :List)
		{
			subject += s + ",";
		}
		subject =  subject.replace(".txt","");
		List<String> subjectList = new ArrayList<String>(Arrays.asList(subject.split(",")));
		
		
		ModelAndView mod=	new ModelAndView();
		mod.setViewName("user");
		mod.addObject("subjectList",subjectList);
		return mod;
	}

	
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public ModelAndView test(@Validated User user, Model model,Locale locale,HttpSession session) {
		
		System.out.println("User Page Requested");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		session.setAttribute("LoggedinUsername", user.getUserName());
		session.setAttribute("LoggedinSubjectType", user.getSubjectType());
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("subjectType", user.getSubjectType());
		
		 String path=session.getServletContext().getRealPath("/WEB-INF/UPLOADED_FILE/");  

			//System.out.println(path+"\\"+user.getSubjectType().trim()+".txt");
		
		File questionloc=new File(path+""+user.getSubjectType().trim()+".txt");
				FileInputStream fileInStem = null;
		try {
			fileInStem = new FileInputStream(questionloc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BufferedInputStream buffReader=new BufferedInputStream(fileInStem);  
		
		int a;
		StringBuilder  fileStringBuilder=new StringBuilder();
		
		try {
			while((a=buffReader.read())!=-1){ 
				fileStringBuilder.append((char)a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] splitFile = fileStringBuilder.toString().split("::");
		List<String> questionList=Arrays.asList(splitFile);
		Collections.shuffle(questionList);
		
		
		List<String> answList=new ArrayList<String>();
		List<String> quesList=new ArrayList<String>();
		String[] questionArray=null;
		for (int i=0; i<10; i++){
			questionArray=questionList.get(i).split("AnswerAnswer");			
			//System.out.println(questionArray[0].trim());
			quesList.add(questionArray[0].trim()); 
			answList.add(questionArray[1].trim());
		}
		
		
		List<String> queList=new ArrayList<String>();
		List<String> optionList=new ArrayList<String>();
		 String[] questionsArray=null;
		for (int i=0; i<quesList.size(); i++) {
			int k=i+1;
			questionsArray=quesList.get(i).split("OptionOption");
			queList.add((k)+"."+questionsArray[0].trim()); 
			optionList.add(questionsArray[1].trim());
		}
		session.setAttribute("TotalQuestionList", queList);
		session.setAttribute("TotalQuestionOptionList", optionList);
		session.setAttribute("TotalAnswerList", answList);
		
		
		ModelAndView mv=	new ModelAndView();
		mv.setViewName("test");
		mv.addObject("queList",queList);
		mv.addObject("optionList",optionList);
		return mv ;
		
	}
	
	
	@RequestMapping(value="/result",method=RequestMethod.POST)  
	@SuppressWarnings("unchecked")
	public ModelAndView result(@Validated User user, Model model,Locale locale,HttpSession session) {
		
		System.out.println("User Page Requested");
		System.out.println(user.getCorrectAnswer().trim());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		model.addAttribute("userName", session.getAttribute("LoggedinUsername"));
		model.addAttribute("subjectType",session.getAttribute("LoggedinSubjectType"));
		
		int score = 0;
		int positivemarks=10;
		int negativemarks=-5;
		int correct=0;
		int wrong=0;
		List<String> wrongAnswerList=new ArrayList<String>();
		
		List<String> queList=new ArrayList<String>();
		queList.addAll((Collection<? extends String>) session.getAttribute("TotalQuestionList"));
		
		List<String> optionList=new ArrayList<String>();
		optionList.addAll((Collection<? extends String>) session.getAttribute("TotalQuestionOptionList"));

		List<String> answList=new ArrayList<String>();
		answList.addAll((Collection<? extends String>) session.getAttribute("TotalAnswerList"));
		
		for (int i = 0; i < queList.size(); i++) {
			System.out.println("Each Question="+queList.get(i));
			System.out.println(optionList.get(i));
			System.out.println("Answer="+answList.get(i));
			
									
			
				String[] InputAnswerArray=user.getCorrectAnswer().trim().split(",");
				
				if ( answList.get(i).equalsIgnoreCase(InputAnswerArray[i]))
				{
					correct++;
					score= score+positivemarks;
					
				}	
				else{
					wrongAnswerList.add(queList.get(i));
					wrongAnswerList.add(optionList.get(i));
					wrongAnswerList.add("--> "+"Correct Answer:"+answList.get(i));
					wrongAnswerList.add(" ");
					wrong++;
					score += negativemarks;
					
				}				    
		}
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("result");
		mv.addObject("correct",correct);
		mv.addObject("wrong",wrong);
		mv.addObject("score",score);
		mv.addObject("wrongAnswerList",wrongAnswerList);
		return mv;
	}
	
}
