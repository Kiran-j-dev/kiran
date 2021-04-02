package com.example.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileProgram {

	public static void main(String[] args) throws IOException {
	        ArrayList<String> lines = new ArrayList<String>();
	        
	        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/kiran/Desktop/checking.txt"));
	        
	        String currentLine = reader.readLine();
	        Map<String,String> map = new LinkedHashMap<>();
	        while (currentLine != null) 
            {
                lines.add(currentLine);
                String a[] = currentLine.split(",");
                 for(int i=0;i<a.length;i++){
                	 String res[] = a[i].split("-");
                	map.put(res[0],res[1]);
                
                 } 
               currentLine = reader.readLine();
                
            }
	        
	        Collections.sort(lines);
	        
	        for (String line : lines)
            {
	        	  System.out.println(line);
            }
	        
	        reader.close();

	}

}
