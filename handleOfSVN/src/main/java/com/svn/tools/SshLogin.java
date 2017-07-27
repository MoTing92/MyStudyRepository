package com.svn.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SshLogin {

	public static void main(String[] args) {
		String hostname = "192.168.214.89";
		String username = "moting";
		String password = "mt199309025";
		sshLogin(hostname,username,password);
	}
	
	public static void sshLogin(String hostname,String username,String password){
		try{
			Connection conn = new Connection(hostname);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username, password);
			if (isAuthenticated == false)
				throw new IOException("认证失败，用户名或密码错误");
			Session sess = conn.openSession();
			sess.execCommand("uname -a && date && uptime && who");
			System.out.println("Here is some information about the remote host:");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			while (true){
				String line = br.readLine();
				if (line == null)
					break;
			 	System.out.println(line);
			}
			System.out.println("ExitCode: " + sess.getExitStatus());
			sess.close();
			conn.close();
			br.close();
		}catch (IOException e){
			e.printStackTrace(System.err);
			System.exit(2);
		}
    }
}
