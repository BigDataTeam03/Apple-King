package com.javaproject.util;

public class ParameterPrintOut {
	
	//Field
	
	//Constructor
	public ParameterPrintOut() {
		// TODO Auto-generated constructor stub
	}
	// Method
	
	
	public void pp(String title, String[] parameterArray) {
		
		//
		System.out.println(">> "+ title + "parameter 를 프린트합니다. ");
		
		for(int i =0; i<parameterArray.length; i++) {
			
			System.out.println(i+"번째: \t " + parameterArray[i]);
		}
	
		
	}

}
