package com.svn.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.svn.conf.ErrorVal;

public class FindAllFile {

	private List<String> filePathList = new ArrayList<String>();
	private List<File> files = new ArrayList<File>();

	public void checkFilePaths4(String[] filepath) throws Exception {
		if (filepath == null | filepath.length == 0){
			throw new Exception(ErrorVal.Path_no_having);
		}
		try{
			for (int i = 0; i < filepath.length; i++) {
				File file = new File(filepath[i]);
				if (!file.exists())
	                throw new Exception(ErrorVal.Path_no_having);
		        if (!file.isDirectory()) {
		        	files.add(file);
		        	filePathList .add(file.getPath());
		        }else if (file.isDirectory()) {
		        	files.add(file);
		        	filePathList .add(file.getPath());
	                String[] filelist = file.list();
	                for (int j = 0; j < filelist.length; j++) {
	                	File readfile = new File(filepath[i] + "\\" + filelist[j]);
	                    if (!readfile.isDirectory()) {
	                    	files.add(readfile);
	                    	filePathList .add(readfile.getPath());
	                    }else if (readfile.isDirectory()) {
	                    	files.add(readfile);
	                    	filePathList .add(readfile.getPath());
	                    	String[] subFile = readfile.list();
	                    	String [] subFilePath = new String[subFile.length];
	                    	for (int k = 0; k < subFilePath.length; k++) {
	                    		subFilePath[k] = readfile.getPath()+"\\"+subFile[k];
							}
	                    	 checkFilePaths4(subFilePath);
	                    }
	                 }
		         }
			}
			
		} catch (FileNotFoundException e) {
         System.out.println("readfile()   Exception:" + e.getMessage());
		}
	}
	
	public File [] resultToFiles(String[] filepath) throws Exception{
		int k = 0;
		this.checkFilePaths4(filepath);
		File [] fileArray = new  File[files.size()];
		for (File file : files) {
			fileArray[k] = file;
			k++;
		}
		return fileArray;
	}
}
