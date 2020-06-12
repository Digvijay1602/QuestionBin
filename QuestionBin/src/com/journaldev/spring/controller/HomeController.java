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
	
	
	@RequestMapping(value="/admin",method=RequestMethod.POST)  
	public ModelAndView admin(Locale locale, Model model){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		
		ModelAndView mod=	new ModelAndView();
		mod.setViewName("admin");
		String uploadmsg="upload";
		mod.addObject("uploadmsg",uploadmsg);
		return mod;
	}

	
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public ModelAndView test(@Validated User user, Model model,Locale locale,HttpSession session) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getUserName());
		
		 String path=session.getServletContext().getRealPath("/WEB-INF/UPLOADED_FILE/");  

			//System.out.println(path+"\\"+user.getSubjectType().trim()+".txt");
		
		File questionloc=new File(path+"\\"+user.getSubjectType().trim()+".txt");
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
		
		
		String[] questionArray=null;
		
		List<String> quesList=new ArrayList<String>();
		
		for (int i=0; i<questionList.size(); i++){
			questionArray=questionList.get(i).split("AnswerAnswer");
			
			System.out.println(questionArray[0].trim());
			
			quesList.add(questionArray[0].trim()); 
			answList.add(questionArray[1].trim());
		}
		ModelAndView mv=	new ModelAndView();
		mv.setViewName("test");
		mv.addObject("quesList",quesList);
		
		return mv ;
		
	}
	
	@RequestMapping(value="/uploaded",method=RequestMethod.POST)  
	public ModelAndView upload(@RequestParam CommonsMultipartFile file,HttpSession session){  
	        String path=session.getServletContext().getRealPath("/WEB-INF/UPLOADED_FILE/");  
	        String filename=file.getOriginalFilename();  
	          
	        //System.out.println(path+""+filename);  
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
}
