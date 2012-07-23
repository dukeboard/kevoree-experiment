package org.kevoree.hocl.maven.compile;

import fr.inria.hocl.core.hoclc2j.*;

import java.io.*;
import java.util.Calendar;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 15/03/12
 * Time: 13:34
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class HoclJavaGenerator {
	public static final String HOCLC2J_VERSION = "0.1->0.2", FILE_HEADER = "/*\n"
			+ "* DO NOT EDIT\n"
			+ "* this class has been generated by fr.inria.hocl.core.hoclc2j version"
			+ HOCLC2J_VERSION + "\n" + "* on " + Calendar.getInstance().getTime()
			+ "\n" + "*/\n" + "\n";

	private final static String javaSuffix = ".java";

	public Program parse (File sourceFile) throws FileNotFoundException, ParseException {
		InputStream inputStream = new FileInputStream(sourceFile);
		HoclParser parser = new HoclParser(inputStream);
		parser.ReInit(inputStream);
		return parser.Start();
	}


	public void optimize (Program prgm) {
		prgm.setReadOnlyReactives();
		// recycleObjects(); // c.f. recycle sub-solutions and sub-tuples from
		// reactives into the result e.g. rep (x:y):z by x:y
		// setNonInertPattern(); // c.f. the hiddden property
		// reorderVariables();
	}


	public void generate (Program prgm, String className, String targetOutputDirPath) throws IOException {
		String pathSeparator = System.getProperty("file.separator");

		prgm.setProgramName(className);
		String code = FILE_HEADER + prgm.generateCode() + "\n";

		String[] classCode = prgm.generateRuleClass();
		// String test = "";
		for (String aClassCode : classCode) {
			// test +="start: \n"+classCode[i]+"***************\n";

			if (aClassCode.startsWith("public class")) {
				// int endIndex = classCode[i].indexOf("extends");
				// String ruleName = classCode[i].substring(13,endIndex);
				String[] s1 = aClassCode.split("extends");
				String[] s2 = s1[0].split(" ");
				String ruleName = s2[s2.length - 1];
				writeFile(targetOutputDirPath + pathSeparator + ruleName + ".java", prgm
						.getHeader()
						+ "\n"
						+ "import fr.inria.hocl.api.*;\n"
						+ "import fr.inria.hocl.iterator.*;\n"
						+ "import fr.inria.hocl.strategy.*;\nimport java.io.*;\n\n"
						+ aClassCode);
			}
		}

		writeFile(targetOutputDirPath + pathSeparator + className + javaSuffix, code);
	}


	public static String indentCode (String code) {
		String s;
		s = code.replaceAll("\n", "\n\t");
		s = "\t" + s;
		return s;
	}


	public static String indentCode (int nb, String code) {
		String s = code;
		for (int i = 1; i <= nb; i++) {
			s = indentCode(s);
		}
		return s;
	}


	private void writeFile (String filename, String contents) throws IOException {

		FileOutputStream genFile = new FileOutputStream(filename);
		DataOutputStream dataFile = new DataOutputStream(genFile);
		dataFile.writeBytes(contents); // writeChars, writeUTF, writeBytes
		dataFile.close();
	}


	/**
	 * Check that the argument is a descriptor of an external object If it is not,
	 * generate a parse exception "Waiting for a Java object at line x column y".
	 *
	 * @param descAtom
	 * @throws fr.inria.hocl.core.hoclc2j.ParseException
	 */
	/*public static void checkExternal (Atom descAtom) throws ParseException {
		if (descAtom instanceof AtomVarPat) {
			if (!((AtomVarPat) descAtom).isExternal()) {
				throw new ParseException("Waiting for a Java object. " + descAtom);
			}
		} else {
			if (!(descAtom instanceof External)) {
				throw new ParseException("Waiting for a Java object. " + descAtom);
			}
		}
	}*/
}