package test;

import java.lang.reflect.*;
//import java.lang.Double;
//import com.dao.s.*;

public class a {

	public static void printConstructors(Class cla) {
		Constructor[] constructors = cla.getConstructors();
		for (Constructor c : constructors) {
			String name = c.getName();
			String modifiers = Modifier.toString(c.getModifiers());
			if (modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.print(name + "(");

			Class[] paramTypes = c.getParameterTypes();
			for (int j = 0; j < paramTypes.length; j++) {
				if (j > 0)
					System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}

	public static void printMethods(Class cla) {
		Method[] methods = cla.getMethods();
		for (Method m : methods) {
			String name = m.getName();
			String modifiers = Modifier.toString(m.getModifiers());
			Class rt = m.getReturnType();
			String returnType = rt.getName();
			if (modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.print(returnType + " ");
			System.out.print(name + "(");			

			Class[] paramTypes = m.getParameterTypes();
			for (int j = 0; j < paramTypes.length; j++) {
				if (j > 0)
					System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}

	public static void printFields(Class cla) {
		Field[] fields = cla.getDeclaredFields();
		for (Field f : fields) {
			String name = f.getName();
			String modifiers = Modifier.toString(f.getModifiers());
			if (modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.println(name + ";");
		}
	}

	public static void main(String args[]) {
		Integer i = new Integer(5);
		Field f;
		Modifier m = new Modifier();
		try {
			/*
			 * Class cla = Class.forName("java.lang.Double"); Class superCla =
			 * cla.getSuperclass(); String modifiers =
			 * Modifier.toString(cla.getModifiers());
			 * System.out.println(Modifier.isPrivate(cla.getModifiers()));
			 */

			Class clax = Class.forName("com.dao.s.SUser");
			printFields(clax);
			printConstructors(clax);
			printMethods(clax);
			System.out.println(Modifier.isAbstract(0));
			
			Object o = clax.newInstance();
			Class[] paramTypes = new Class[1];
			paramTypes[0] = Class.forName("java.lang.String");
			Method met = clax.getMethod("setName", String.class);
			met.invoke(o, "azx");
			met = clax.getMethod("toString", String.class);
			String s = (String) met.invoke(o, " ");
			System.out.println(s);			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
