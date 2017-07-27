package com.svn.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制台输出log信息
 * @author MoTing
 * @date 2017年6月30日
 */
public class ConsoleLog {

	protected boolean log = false;
    /**
     * 错误编码
     */
    private String[] errorCode = { "E155010", "E200005", "E204899", "E200009", "E155015","E155004" };
    /**
     * 编码解释
     */
    private String[] errorInfo = { "找不到文件或文件夹,其所在目录未被纳入版本控制的", "文件没有被纳入版本控制", "无法访问的目录", "文件没有被纳入版本控制", "svn冲突！","文件被锁定,可能是因为上次操作意外中断导致,请执行cleanup" };

    /**
     * 输出log到console
     * 
     * @param msg
     *            输出信息
     */
    protected void log(String msg, String... content) {
        if (log) {
            StackTraceElement stack[] = (new Throwable()).getStackTrace();
            String time = new SimpleDateFormat("yyyy年MM月dd日/HH:mm:ss").format(new Date());
            System.out.println(new StringBuffer("[").append(time).append("]").append(stack[1].getClassName()).append(".").append(stack[1].getMethodName()).append("  line:")
                    .append(stack[1].getLineNumber()));
            System.out.println(new StringBuffer("[").append(time).append("]").append(msg));
        }
    }

    protected void log(Exception e) {
        StringBuffer sbf = new StringBuffer("【SVN-ERROR】");
        for (int i = 0; i < errorCode.length; i++) {
            if (e.getMessage().indexOf(errorCode[i]) != -1)
                this.log(sbf.append(errorInfo[i]).toString());
        }
    }

}
