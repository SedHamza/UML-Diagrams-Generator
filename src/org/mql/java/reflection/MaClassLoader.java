package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class MaClassLoader  {
	URLClassLoader myClassLoader;
	private Class<?> maClasse = null;
	
	public MaClassLoader(String binPath, String classqualifiedname) {
		 try {
	        	URL[] classPath = {new File(binPath).toURI().toURL()};
	            myClassLoader = new URLClassLoader(classPath, ClassLoader.getSystemClassLoader());
	            maClasse = myClassLoader.loadClass(classqualifiedname); 
	        }
	        catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public Class<?> getMaClass() {
		return maClasse;
	}
	
	public void closeClassLoader() {
		try {
		myClassLoader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
