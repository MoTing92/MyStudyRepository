package com.svn.impl.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svn.conf.ErrorVal;
import com.svn.inf.service.ISvnCommon;
import com.svn.tools.ConsoleLog;
import com.svn.tools.FindAllFile;

public class SvnCommonImpl extends ConsoleLog implements ISvnCommon{

	public List<List<File>> bindFile(File[] files) {
		List<List<File>> fileList = new ArrayList<List<File>>(1);
        fileList.add(new ArrayList<File>());
        fileList.add(new ArrayList<File>());
        for (File f : files) {
            if (f.isDirectory())
                // 0是文件目录,先提交文件目录让再提交文件
                fileList.get(0).add(f);
            else
                fileList.get(1).add(f);
        }
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).size() == 0)
                fileList.remove(i);
        }
        super.log("分离文件和文件夹");
        return fileList;
	}

	public File[] sortF_S(File[] files) {
		 Arrays.sort(files);
		 super.log("文件排序：父级-->子级");
	     return files;
	}

	public File[] sortS_F(File[] files) {
		files = sortF_S(files);
        File[] f = new File[files.length];
        for (int i = 0; i < files.length;) {
            f[i] = files[files.length - (++i)];
        }
        super.log("文件排序：子级-->父级");
        return f;
	}

	public File[] checkFilePaths(String[] paths) throws Exception {
		if (paths == null | paths.length == 0){
			throw new Exception(ErrorVal.Path_no_having);
		}
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            if (!(files[i] = new File(paths[i])).exists())
                throw new Exception(ErrorVal.Path_no_having);
        }
        super.log("检查文件并将其添加到容器中");
        return files;
	}
	
	public File[] checkFilePaths2(String[] paths) throws Exception {
        FindAllFile findAllfile = new FindAllFile();
		return findAllfile.resultToFiles(paths); 
	}
	
}
