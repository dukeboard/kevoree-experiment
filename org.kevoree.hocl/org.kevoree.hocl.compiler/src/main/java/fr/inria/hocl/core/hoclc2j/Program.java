package fr.inria.hocl.core.hoclc2j;


/**
 * Descriptor of a program
 */
public class Program {

	Solution sol;

	String header; // package declarations and imports

	String prgmClassName;

	String pkgName;


	public Program (Solution sol, String header, String packageName) {
		this.sol = sol;
		this.header = header;
		this.pkgName = packageName;
	}


	public String getPackageName () {
		return this.pkgName;
	}


	public String getHeader () {
		return this.header;
	}


	public void setReadOnlyReactives () {
		sol.findReadOnlyReactives();
	}


	public String[] generateRuleClass () {
		String code = sol.generateClassCode();

		/*
		 * for (int i = 0; i<tmpRule.length;i++){ String[] s =
		 * tmpRule[i].split("extends ReactionRule"); tmpRule[i] = s[0] +
		 * "extends ReactionRule" + " implements Serializable"; for (int
		 * j=1;j<s.length;j++){ tmpRule[i] += s[j]; } }
		 */

		return code.split("ThisIsBetweenTheClasses");
	}


	public String generateCode () {
		return header + "\n"

				+ "import fr.inria.hocl.api.*;\n"
				+ "import fr.inria.hocl.iterator.*;\n"
				+ "import fr.inria.hocl.strategy.*;\n"

				+ "import java.util.LinkedList;\n"

				+ "import java.util.List;\n"

				+ "import java.io.*;\n"

				+ "\n\npublic class " + prgmClassName
				+ " extends Solution {\n\n"

				// defined in Molecule class
				// + "\tprivate List<String> allTypes;\n\n"

				+ "\tpublic " + prgmClassName
				+ "(){\n\n"

				// useless
				// + "\t\tthis.allTypes = new LinkedList<String>();\n\n"

				+ "\t\tExternalObject object;\n"

				+ "\t\tReactionRule rule;\n"

				+ "\t\tString[] string;\n"

				+ "\t\tTuple tuple;\n\n"

				+ CodeGeneratorHelper.indentCode(2, sol.generateContentsDeclaration()) + "\n"

// 				+ "\t\tIOSender sender = new IOSender();\n"

// 				+ "\t\tobject = new ExternalObject(sender);\n"

// 				+ "\t\t" + sol.generateContentsReference() + ".add(object);\n\n"

				+ "\t\tthis.addMolecule(" + sol.generateContentsReference() + ");\n"

				+ "\t\tthis.addType(\"Solution\");\n"

				+ "\t\tthis.addType(\"Integer\");\n"

				+ "\t\tthis.addType(\"String\");\n"

				+ "\t\tthis.addType(\"Tuple\");\n\n"

				+ "\t}\n\n"

				+ this.sol.generateAddElement() + "\n"

				// Add element types in the LinkedList

				+ "\tpublic void addType(String s){\n"

				+ "\t\tint sign=0;\n\n"

				+ "\t\tif(this.getAllTypeSize()==0){\n"

				+ "\t\t\tthis.addToAllTypes(s);\n"

				+ "\t\t}\n\n"

				+ "\t\telse{\n"

				+ "\t\t\tfor (int i=0;i<this.getAllTypeSize();i++){\n"

				+ "\t\t\t\tif (this.getiAllType(i).equals(s)){\n"

				+ "\t\t\t\t\tsign=1;\n"

				+ "\t\t\t\t\tbreak;\n"

				+ "\t\t\t\t}\n"

				+ "\t\t\t}\n"

				+ "\t\t\tif (sign==0){\n"

				+ "\t\t\t\tthis.addToAllTypes(s);\n"

				+ "\t\t\t}\n"

				+ "\t\t}\n"

				+ "\t}\n\n"

				+ "\tpublic String displayTypes (){\n\n"
				+ "\t\tString types = \"\";\n\n"

				+ "\t\tif(this.getAllTypeSize()==0){\n"

				+ "\t\t\ttypes = \"No element in the multi-set.\\n\";\n"

				+ "\t\t}\n"

				+ "\t\telse{\n"

				+ "\t\t\tfor(int i=0;i<this.getAllTypeSize();i++){\n"

				+ "\t\t\tif (!this.getiAllType(i).equals(\"IOSender\"))\n"

				+ "\t\t\t\ttypes += i+1 +\": \"+this.getiAllType(i)+\";\\n\";\n"

				+ "\t\t\t}\n"

				+ "\t\t}\n"

				+ "\t\treturn types;\n"

				+ "\t}\n"

				+ "} // class " + prgmClassName + "\n";
	}


	public void setProgramName (String name) {
		prgmClassName = CodeGeneratorHelper.upperFirstLetter(name);
	}
}
