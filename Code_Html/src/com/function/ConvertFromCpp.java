package com.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dataStructure.Attribute;

public class ConvertFromCpp {

	private static String[] array = { "and", "and_eq", "and_eq", "asm", "auto",
			"bitand", "bitor", "bool", "bool", "break", "case", "catch",
			"char", "class", "compl", "const", "const_cast", "continue",
			"default", "delete", "do", "double", "dynamic_cast", "else",
			"enum", "explicit", "export", "extern", "false", "false", "float",
			"for", "friend", "goto", "if", "inline", "int", "long", "mutable",
			"namespace", "new", "not", "not_eq", "operator", "or", "or_eq",
			"private", "protected", "public", "register", "reinterpret_cast",
			"return", "short", "signed", "struct", "sizeof", "static",
			"static_cast", "throw", "switch", "template", "this", "typeid",
			"true", "try", "typedef", "using", "typename", "union", "unsigned",
			"virtual", "void", "volatile", "wchar_t", "while", "xor", "xor_eq",
			"void", "void", "void", "void", "void",

	// "abstract","class","assert","boolean","break","byte",
	// "catch","case","char","const","continue","default","do",
	// "double","else","enum","extends","final","finally","float",
	// "for","goto","if","implements","import","instanceof","int",
	// "interface","long","native","new","package","private","protected",
	// "public","return","strictfp","short","static","super","switch",
	// "synchronized","this","throw","throws","transient","try","void",
	// "volatile","while"
	};

	/*
	 * public static void main(String[] args) throws Exception{ Attribute at=new
	 * Attribute();
	 * 
	 * File s = new File("E:/b/carlos/amaudio.h");
	 * System.out.println(s.getAbsolutePath()); File outD = new
	 * File("E:/b/carlos/test"); convertFromCpp(s.getAbsolutePath(),outD, at);
	 * 
	 * //convertFromCpp( s,outD,Attribute at) }
	 */
	// public static void convertFromCpp(File sourceFile,File
	// outputDirectory,Attribute at)
	public static void convertFromCpp(String fileName, File outD, Attribute at) {
		
		File checkedDirectory = new File((com.uiComponent.MainFrame.outputDirectory.getAbsolutePath()+"/html_output"));
		if(!checkedDirectory.exists()){
			File SourceOutputDirectory = new File(checkedDirectory.getAbsolutePath());;
			File cppSource = new File(checkedDirectory.getAbsolutePath()+"/CppSource");
			File javaSource = new File(checkedDirectory.getAbsolutePath()+"/JavaSource");
			SourceOutputDirectory.mkdirs();
			 cppSource.mkdirs();
			 javaSource.mkdirs();
		}

		int index = 0;
		int index1 = 0;
		for (int d = 0; d < fileName.length(); d++) {
			if (fileName.charAt(d) == '.' || fileName.charAt(d) == '\\') {
				if (fileName.charAt(d) == '.') {
					index = d - 1;
					break;
				}
				if (fileName.charAt(d) == '\\') {
					index1 = d + 1;
				}
			}
		}

		// for(int d=0;d<fileName.length();d++){
		// index++;
		// if(fileName.charAt(d)=='.')
		// {
		// break;
		// }
		// }

		// String name=fileName.substring(0, index);
		String name = "";
		for (int i = index1; i <= index; i++) {
			name += fileName.charAt(i);
		}
		// System.out.println(name);
		File file = new File(fileName);
		Scanner input;
		List<List<String>> list = new ArrayList<List<String>>();

		try {
			input = new Scanner(file);
			while (input.hasNext()) {
				String s = input.nextLine();
				List<String> ss = stringToStrings(s);
				list.add(ss);

			}

			input.close();

			/*
			 * for(int i=0;i<list.size();i++){ List<String> k=list.get(i);
			 * for(int j=0;j<k.size();j++){ System.out.print(k.get(j));
			 * 
			 * } System.out.println(); }
			 */

			File ff = new File(outD.getAbsolutePath() + "/" + name + ".html"); // //////////////
																				// "/"+"main"
			PrintWriter output = new PrintWriter(ff);
			output.println("<html>");
			output.println("<head>");
			output.println("<fontbase family=" + '"' + at.getFontStyle() + '"'
					+ ">");
			output.println("<meta http-equiv=" + '"' + "Content-Type" + '"'
					+ "content=" + '"' + "text/html;charset=GB18030" + '"'
					+ ">");
			output.println("</head>");
			output.println("<body>");
			output.println("<font size=" + '"' + at.getFontSize() + '"'
					+ "color=" + '"' + at.getBaseColor() + '"' + "face=" + '"'
					+ at.getFontStyle() + '"' + ">");

			int flag = 0;
			int flagf = 0;
			for (int k = 0; k < list.size(); k++) {
				List<String> ll = list.get(k);
				output.print("<font>" + k + "&nbsp&nbsp&nbsp" + "</font>");
				for (int t = 0; t < ll.size(); t++) {
					String st = ll.get(t);
					flag = getStatus(st, flagf);
					// System.out.println(st);
					// System.out.println(flagf);
					// System.out.println(flag);
					if (flagf == -2) { // System.out.println(ll.get(t));
						while (t < ll.size()) {
							output.print("<font color=" + '"'
									+ at.getCommentColor() + '"' + ">");
							output.print(ll.get(t));
							output.print("</font>");
							t++;

						}
						flagf = 0;
						break;

					}
					flagf = flag;

					if (flag == 1) {
						output.print("<b><font color=" + '"'
								+ at.getKeywordColor() + '"' + ">");
						output.print(st);
						output.print("</font></b>");
						flagf = 0;
					} else if (flag == 2 || flag == 3) {
						output.print("<font color=" + '"' + at.getStringColor()
								+ '"' + ">");
						output.print(st);
						output.print("</font>");
						if (flag == 3) {
							flagf = 0;
						}

					} else if (flag == 4) {

						output.print("");

					} else if (flag == 5) {
						output.print("/");
						output.print(st);
					} else if (flag == 6) {
						output.print("<font color=" + '"'
								+ at.getCommentColor() + '"' + ">");
						output.print("//");
						output.print("</font>");
					} else if (flag == 7) {
						output.print("<font color=" + '"'
								+ at.getCommentColor() + '"' + ">");
						output.print(st);
						output.print("</font>");
					} else if (flag == 9) {
						output.print("<font color=" + '"'
								+ at.getCommentColor() + '"' + ">");
						output.print("/*");
						output.print("</font>");
					} else if (flag == 9 || flag == 10 || flag == 11) {
						output.print("<font color=" + '"'
								+ at.getCommentColor() + '"' + ">");
						output.print(st);
						output.print("</font>");
					} else if (flag == 12) {
						output.print("<font color=" + '"'
								+ at.getCommentColor() + '"' + ">");
						output.print(st);
						output.print("</font>");
						flagf = 0;
					} else if (flag == 13 || flag == 14 || flag == 15) {
						output.print("<font color=" + '"' + at.getStringColor()
								+ '"' + ">");
						output.print(st);
						output.print("</font>");
						if (flag == 15) {
							flagf = 0;
						}
					} else {
						if (st.equals("\t"))
							output.print("<font>" + "&nbsp&nbsp&nbsp"
									+ "</font>");
						else if (st.equals("" + '#')) {
							output.print("<font color=" + '"'
									+ at.getCommentColor() + '"' + ">");
							output.print(st);
							output.print("</font>");
						} else
							output.print("<font>" + st + "</font>");
					}

				}
				if (flag == 7) {
					flagf = 0;
				}
				// System.out.println(flagf);
				output.print("\n");
				output.println("<br>");

			}
			output.println("</font>");
			output.println("</body>");
			output.println("</html>");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public static int getStatus(String st, int flag) {

		int ff = 0;
		if (st.equals("" + '#')) {
			return ff = -2;
		}
		if (flag == 0) {
			for (int y = 0; y < array.length - 1; y++) {
				if (st.equals(array[y])) {
					ff = 1;
					break;
				}
			}
		}
		if (st.equals("" + '"') && flag == 0) {
			ff = 2;
		}
		if (!st.equals("" + '"') && flag == 2) {
			ff = 2;
		}
		if (st.equals("" + '"') && flag == 2) {
			ff = 3;
		}
		if (st.equals("/") && flag == 0) {
			ff = 4;
		}
		if ((!st.equals("*")) && (!st.equals("/")) && st.equals(" ")
				&& (flag == 4)) {
			ff = 5;
		}
		if (!st.equals(" ") && flag == 4) {
			ff = 4;
		}
		if (st.equals("/") && flag == 4) {
			ff = 6;
		}
		if (flag == 6 || flag == 7) {
			ff = 7;
		}
		if (st.equals("*") && flag == 4) {
			ff = 9;
		}
		if ((flag == 9 || flag == 10) && !st.equals("*")) {
			ff = 10;
		}
		if (flag == 10 && st.equals("*")) {
			ff = 11;
		}
		if (!st.equals("/") && st.equals("") && flag == 11) {
			ff = 11;
		}
		if (!st.equals("") && !st.equals("/") && flag == 11) {
			ff = 10;
		}
		if (flag == 11 && st.equals("/")) {
			ff = 12;
		}
		if (flag == 0 && st.equals("'")) {
			ff = 13;
		}
		if (flag == 13) {
			ff = 14;
		}
		if (flag == 14 && st.equals("'")) {
			ff = 15;
		}
		if (flag == 14 && !st.equals("'")) {
			ff = 0;
		}

		return ff;

	}

	private static List<String> stringToStrings(String s) {
		// TODO 自动生成的方法存根
		List<String> ss = new ArrayList<String>();
		String bf = new String("");
		String chh = new String("");
		for (int i = 0; i < s.length(); i++) {

			char ch = s.charAt(i);
			// System.out.print(ch);
			if (ch == '\t' || ch == ' ' || ch == '(' || ch == ')' || ch == '.'
					|| ch == '<' || ch == '>' || ch == '{' || ch == '}'
					|| ch == '[' || ch == ']' || ch == ',' || ch == '"'
					|| ch == '%' || ch == '/' || ch == '&' || ch == '|'
					|| ch == '*' || ch == '-' || ch == '+' || ch == '='
					|| ch == ';' || ch == '#') {

				ss.add(bf);
				bf = "";

				chh += ch;

				ss.add(chh);
				chh = "";

			} else {

				bf += ch;
			}
		}
		ss.add(bf);
		// System.out.println();
		return ss;
	}
}
