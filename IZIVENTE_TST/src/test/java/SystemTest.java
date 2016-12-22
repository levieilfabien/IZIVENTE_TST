package test.java;

import org.junit.Test;

public class SystemTest {

	@Test
	public void systemTest() {
		//System.load("C:\\Program Files (x86)\\Java\\jdk1.7.0_80\\jre\\bin\\jacob-1.18-x64.dll");
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("jacob.dll.path"));
		
		System.load("C:\\Users\\levieilfa\\git\\Selenium\\TAG_TST\\src\\resources\\jacob-1.18-x86.dll");
		//System.load("C:\\work\\dev\\workspaces\\workspace1\\IZIVENTE_TST\\target\\classes\\resources\\jacob-1.18-x86.dll");
		//System.load("C:\\Windows\\System32\\jacob-1.18-x86.dll");
		
		
		//System.loadLibrary("jacob-1.18-x86.dll");
	}
}
