package fr.inria.hocl.core.hoclc2j;


import java.util.*;

/**
 * Descriptor of a molecule
 * 
 */
public class BasicMolecule extends LinkedList<Atom> implements Molecule {

	private static final long serialVersionUID = 1048532482827897L;

	// private final int nestLevel;
	private final String varName;

	// private final List<Symbols> symbolsList;
	private final SymbolsTable symbolsTable;

	private static int moleculeVarCounter = 0;


	public BasicMolecule( int nestLevel, SymbolsTable symbolsTable ) {
		// this.nestLevel = nestLevel;
		varName = "mol" + moleculeVarCounter;
		moleculeVarCounter++;
		this.symbolsTable = (SymbolsTable) symbolsTable.clone();
		/*
		 * symbolsList = new LinkedList<Symbols>(); ListIterator<Symbols> it =
		 * symbolsTable.listIterator(); while(it.hasNext()){ it.next(); } Symbols
		 * symb; while(it.hasPrevious()){ symb = it.previous(); if(!(symb instanceof
		 * ReactionRulePat)){ symbolsList.add(symb); } }
		 */

	}


	public Set<Symbols> getFreeVars() {
		Set<Symbols> freeVars = new HashSet<Symbols>();
		Iterator<Atom> it = iterator();
		Atom atom;
		while( it.hasNext() ) {
			atom = it.next();
			freeVars.addAll( atom.getFreeVars() );
		}
		return freeVars;
	}


	public void addToLinkedList( List<String> list, String s ) {
		int sign = 0;

		if( list.size() == 0 ) {
			list.add( s );
		}

		else {
			for (String aList : list) {
				if (aList.equals(s)) {
					sign = 1;
					break;
				}
			}
			if( sign == 0 ) {
				list.add( s );
			}
		}
	}


	public LinkedList<String> getElementTypes() {
		LinkedList<String> types = new LinkedList<String>();

		types.add( "Tuple-'element1:element2:...:elementn'" );
		types.add( "Solution-'A container like: <>'" );

		// String type is added by default (if String type is natively in the HOCL program, the next line is silent)
		addToLinkedList( types, "String-Java Object" );
		

		String code, decl;
		Iterator<Atom> it = this.iterator();
		Atom atom;
		int position = 0;

		code = "Molecule " + varName + " = new Molecule();";

		while( it.hasNext() ) {
			atom = it.next();
			decl = atom.generateDeclarationInit( position, symbolsTable );
			code = code + "\n" + ( decl.length() > 1 ? decl + "\n" : "" );
			if( atom.getType().equals( "tuple" ) ) {
				for( int i = 0; i < ( ( Tuple ) atom ).contents.size(); i++ ) {
					LinkedList<String> t = ( ( Tuple ) atom ).contents.get( i ).getElementTypes();
					if( t != null ) {
						for (String aT : t) {
							addToLinkedList(types, aT);
						}
					}
				}
			} else if( atom.getType().equals( "external" ) ) {
				String type = ( ( External ) atom ).getJavaType();

				if( atom.toString().length() > 3
						&& atom.toString().substring( 0, 3 )
								.equals( "new" ) ) {
					String[] s = atom.toString().split( "\\(" );
					String[] t = s[0].split( " " );
					type = t[1];
				}

				if( !type.equals( "IOSender" ) )
					addToLinkedList( types, type + "-Java Object" );
			} else if( atom.getType().equals( "moleculeVar" ) ) {
				addToLinkedList( types, atom.generateReference() + "-MoleculeVar" );
			} else if( atom.getType().equals( "basicSolution" ) ) {
				LinkedList<String> s = atom.getElementTypes();
				for (String value : s) {
					addToLinkedList(types, value);
				}
			} else if( atom.getType().equals( "reactionRuleVar" ) ) {
				// *********************
				// Because we can not add reaction rules at present
				// *********************
				addToLinkedList( types, ( ( ReactionRuleVar ) atom ).getVarName()
						+ "-Reaction Rule" );
			} else if( atom.getType().equals( "reactionRule" ) ) {
				// *********************
				// Because we can not add reaction rules at present
				// *********************
				addToLinkedList( types, ( ( ReactionRule ) atom ).getRuleName()
						+ "-Reaction Rule" );
			} else {
				addToLinkedList( types, atom.getType() + "-else" );
			}
			position++;
		}

		return types;
	}


//	public String generateAddElementCode() {
//		List<String> types = getElementTypes();
//		String myCode = "";
//
//		String generateFunction = "";
//		String generateTuple = "";
//		String generateSolution = "";
//
//		generateTuple += "\tpublic Tuple generateTupleElement() throws IOException{\n"
//		    + "\t\tint num;\n"
//		    + "\t\tString input;\n\n"
//		    + "\t\tSystem.out.println(\"How many elements in the tuple?\\n\");\n"
//		    + "\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
//		    + "\t\tnum = Integer.decode(input);\n\n"
//		    + "\t\tTuple t = new Tuple (num);\n"
//		    + "\t\tfor (int i=0;i<num;i++){\n"
//		    + "\t\t\tSystem.out.print(\"Enter the type for the \");\n"
//		    + "\t\t\tint j = i % 10;\n"
//		    + "\t\t\tif (j == 0)\n"
//		    + "\t\t\t\tSystem.out.println((i+1) + \"st element. \\n\");\n"
//		    + "\t\t\telse if (j == 1)\n"
//		    + "\t\t\t\tSystem.out.println((i+1) + \"nd element. \\n\");\n"
//		    + "\t\t\telse if (j == 2)\n"
//		    + "\t\t\t\tSystem.out.println((i+1) + \"rd element. \\n\");\n"
//		    + "\t\t\telse \n\t\t\t\t System.out.println((i+1) + \"th element. \\n\");\n"
//		    + "\t\t\tSystem.out.println(\"Types:\");\n"
//		    + "\t\t\tSystem.out.println(this.displayTypes());\n"
//		    + "\t\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n\n"
//		    + "\t\t\twhile(input.length()==0){\n"
//		    + "\t\t\t\tSystem.out.println(\"Enter the type of the element.\");\n"
//		    + "\t\t\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
//		    + "\t\t\t}\n"
//		    + "\t\t\t int choice = Integer.decode(input);\n"
//		    + "\t\t\t input = this.getiAllType(choice-1);\n";
//
//
//		generateSolution += "\tpublic Solution generateSolutionElement() throws IOException{\n"
//		    + "\t\tint sign = 0;\n"
//		    + "\t\tString input;\n"
//		    + "\t\tSolution sol = new Solution ();\n"
//		    + "\t\tMolecule mol = new Molecule();\n"
//		    + "\t\twhile(sign == 0){\n"
//		    + "\t\t\tSystem.out.println(\"Do you want to add an element in this solution? (y/n)\");\n"
//		    + "\t\t\tString stop = new BufferedReader(new InputStreamReader(System.in)).readLine();\n\n"
//		    + "\t\t\tif (stop.equals(\"yes\")||stop.equals(\"y\")||stop.equals(\"YES\")||stop.equals(\"Y\")){\n"
//		    + "\t\t\t\tSystem.out.println(\"Enter the type of the element.\\n\");\n"
//		    + "\t\t\t\tSystem.out.println(\"Types:\");\n"
//		    + "\t\t\t\tSystem.out.println(this.displayTypes());\n"
//		    + "\t\t\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n\n"
//		    + "\t\t\t\twhile(input.length()==0){\n"
//		    + "\t\t\t\t\tSystem.out.println(\"Enter the type of the element.\");\n"
//		    + "\t\t\t\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
//		    + "\t\t\t\t}\n"
//		    + "\t\t\t\tint choice = Integer.decode(input);\n"
//		    + "\t\t\t\tinput = this.getiAllType(choice-1);\n";
//
//		myCode += "\tpublic Molecule addElement(){\n"
//				+ "\t\tString input = \"\";\n"
//				+ "\t\tExternalObject obj;\n"
//				+ "\t\tReactionRule r;\n"
//				+ "\t\tMolecule mol = new Molecule();\n"
//				+ "\t\tint choice;\n\n"
//				+ "\t\tSystem.out.println(\"Create the element that you want to add/remove.\\n\");\n"
//				+ "\t\tSystem.out.println(\"What kind of element you want to create? (input the number) \\n \");\n"
//				+ "\t\tSystem.out.println(\"Supported Elements:\");\n"
//				+ "\t\tSystem.out.println(this.displayTypes());\n\n";
//
//		/*
//		 * this is the version that can return all the element types before
//		 * compilation
//		 *
//		 * for (int i=0;i<types.size();i++){ myCode += "\t\tSystem.out.println(\""+
//		 * (i+1) + ": "+types.get(i)+"\");\n"; }
//		 */
//
//		myCode += "\t\ttry {\n"
//				+ "\t\t\tinput += new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
//				+ "\t\t} catch (IOException e1) {\n" + "\t\t\te1.printStackTrace();\n"
//				+ "\t\t}\n\n"
//
//				+ "\t\ttry{\n\n" + "\t\t\tchoice = Integer.decode(input);\n"
//				+ "\t\t\tinput = this.getiAllType(choice-1);\n"
//				+ "\t\t\tif(input.length()==0)\n"
//				+ "\t\t\t\tSystem.out.println(\"Null pointer!\");\n";
//
//		for( int i = 0; i < types.size(); i++ ) {
//
//			String s = types.get( i );
//			String[] l = s.split( "\\-" );
//
//			if( l[0].startsWith( "_HOCL" ) )
//				continue;
//
//			myCode += "\t\t\telse if (input.equals(\"" + l[0] + "\")){\n";
//
//			// temp is l[0] but with capitalized first letter
//			String temp = l[0].substring( 0, 1 ).toUpperCase() + l[0].substring( 1 );
//
//			myCode += "\t\t\t\tmol = new Molecule();\n";
//
//			if( l[1].equals( "Reaction Rule" ) ) {
//
//				myCode += "\t\t\t\tr = new " + temp + "()" + ";\n"
//						+ "\t\t\t\tmol.add(r);\n";
//
//				// myCode += "\t\t\t\tReactionRule r = generate"+temp+"Element()"+";\n"
//				// + "\t\t\t\tmol.add(r);\n";
//
//				// generateFunction += "//\tpublic" + temp + " generate" + temp
//				// 		+ "Element(){;\n" + "\t\t//return new " + temp + "();\n\n"
//				// 		+ "\t//}\n";
//				if (i == 0) {
//				    generateTuple += "\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\tt.set(i, new " + temp + "());\n"
//					+ "\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol = new Molecule();\n" + "\t\t\t\t\tmol.add(new "
//					+ temp + "());\n" + "\t\t\t\t\tsol.addMolecule(mol);\n"
//					+ "\t\t\t\t}\n\n";
//
//				} else {
//				    generateTuple += "\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\tt.set(i, new " + temp + "());\n"
//					+ "\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol = new Molecule();\n" + "\t\t\t\t\tmol.add(new "
//					+ temp + "());\n" + "\t\t\t\t\tsol.addMolecule(mol);\n"
//					+ "\t\t\t\t}\n\n";
//
//				}
//			} else if( l[1].equals( "Java Object" ) ) {
//				myCode += "\t\t\t\tobj = new ExternalObject(generate" + l[0]
//						+ "Element());\n" + "\t\t\t\tmol.add(obj);\n";
//				if( l[0].equalsIgnoreCase( "object" ) ) {
//					generateFunction += "\tpublic " + l[0] + " generate" + l[0]
//							+ "Element() throws IOException{;\n" + "\t\treturn new " + l[0]
//							+ "();\n\n" + "\t}\n";
//				} else {
//					generateFunction += "\tpublic "
//							+ l[0]
//							+ " generate"
//							+ l[0]
//							+ "Element() throws IOException{;\n"
//							+ "\t\tSystem.out.println(\"Enter your " + l[0] + ":\\n\");\n"
//							+ "\t\tString input = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
//							+ "\t\treturn new " + l[0] + "(input);\n\n" + "\t}\n";
//				}
//
//				if ( i==0 ) {
//				    generateTuple += "\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\tt.set(i, new ExternalObject (generate" + l[0]
//					+ "Element()));\n" + "\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol = new Molecule();\n"
//					+ "\t\t\t\t\tmol.add(new ExternalObject (generate" + l[0]
//					+ "Element())) ;\n" + "\t\t\t\t\tsol.addMolecule(mol);\n"
//					+ "\t\t\t\t}\n\n";
//				} else {
//				    generateTuple += "\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\tt.set(i, new ExternalObject (generate" + l[0]
//					+ "Element()));\n" + "\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol = new Molecule();\n"
//					+ "\t\t\t\t\tmol.add(new ExternalObject (generate" + l[0]
//					+ "Element())) ;\n" + "\t\t\t\t\tsol.addMolecule(mol);\n"
//					+ "\t\t\t\t}\n\n";
//				}
//
//
//			} else if( l[1].equals( "'element1:element2:...:elementn'" ) ) {
//				myCode += "\t\t\t\tmol.add(generateTupleElement());\n\n";
//
//				if ( i==0 ) {
//				    generateTuple += "\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\tt.set(i, generate" + l[0] + "Element());\n"
//					+ "\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol = new Molecule();\n"
//					+ "\t\t\t\t\tmol.add(generateTupleElement());\n"
//					+ "\t\t\t\t\tsol.addMolecule(mol);\n" + "\t\t\t\t}\n\n";
//				} else {
//				 generateTuple += "\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\tt.set(i, generate" + l[0] + "Element());\n"
//					+ "\t\t\t}\n\n";
//				 generateSolution += "\t\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//				     + "\t\t\t\t\tmol = new Molecule();\n"
//				     + "\t\t\t\t\tmol.add(generateTupleElement());\n"
//				     + "\t\t\t\t\tsol.addMolecule(mol);\n" + "\t\t\t\t}\n\n";
//				}
//
//			} else if( l[1].equals( "'A container like: <>'" ) ) {
//				myCode += "\t\t\t\tmol.add(generateSolutionElement());\n\n";
//
//				if ( i==0) {
//				    generateTuple += "\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tt.set(i, generate" + l[0] + "Element());\n"
//					+ "\t\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\tif(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol.add(generateSolutionElement());\n"
//					+ "\t\t\t\t\tsol.addMolecule(mol);\n\n" + "\t\t\t\t}\n\n";
//				} else {
//				    generateTuple += "\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tt.set(i, generate" + l[0] + "Element());\n"
//					+ "\t\t\t\t}\n\n";
//				    generateSolution += "\t\t\t\telse if(input.equals(\"" + l[0] + "\")){\n"
//					+ "\t\t\t\t\tmol.add(generateSolutionElement());\n"
//					+ "\t\t\t\t\tsol.addMolecule(mol);\n\n" + "\t\t\t\t}\n\n";
//				}
//
//			} else {
//				myCode += "\t\t\t\t//" + l[1] + ";\n";
//			}
//
//			myCode += "\t\t\t\t}\n\n";
//		}
//		myCode += "\t\t\t\telse{\n"
//				+ "\t\t\t\t\tPackage pack = this.getClass().getPackage();\n"
//				+ "\t\t\t\t\tString packageName = pack.getName();\n"
//				+ "\t\t\t\t\tString ts = input.substring(0, 1).toUpperCase()+input.substring(1);\n"
//				+ "\t\t\t\t\tReactionRule m = (ReactionRule) Class.forName(packageName+\".\"+ts).newInstance();\n "
//				+ "\t\t\t\t\tmol.add(m);\n\n"
//				+ "\t\t\t\t\tSystem.out.println(\"The new rule has been added!\");\n"
//				+ "\t\t\t}\n\n"
//				+ "\t\treturn mol;\n"
//				+ "\t\t}\n\n"
//				+ "\t\tcatch  (Exception e) {\n\n"
//				+ "\t\t\tSystem.out.println(\"Input Error! Please input *NUMBER* while not string!\");"
//				+ "\t\t\treturn mol;"
//				+ "\t\t}\n\n"
//				+ "\t}\n"
//
//				+ "\tpublic Molecule addElementSubSolution(String line) throws IOException{\n"
//				+ "\t\tMolecule mol = new Molecule();\n"
//				+ "\t\tString[] s = line.split(\"\\\\ \");\n"
//				+ "\t\tif(s.length!=2){\n"
//				+ "\t\t\tString errorLog=	\"Input command with wrong format.\\n\"\n"
//				+ "\t\t\t+	\"Use 'put' command in the following ways:\\n\"\n"
//				+ "\t\t\t+	\"1: 'put'; (just put an element in the container;)\\n\"\n"
//				+ "\t\t\t+	\"2: 'put path' ex.: put ./SubSolition1/SubSolution1-1 (put an element in the indicated subsolution.)\\n\";\n"
//				+ "\t\t\tSystem.out.println(errorLog);\n"
//				+ "\t\t}\n\n"
//				+
//
//				"\t\telse{\n"
//				+
//
//				"\t\t\t//System.out.println(\"The length of input command is: \" + s.length +\"\\n\");\n"
//				+ "\t\t\t//System.out.println(\"S0 is: \" + s[0] +\"\\n\");\n"
//				+ "\t\t\t//System.out.println(\"S1 is: \" + s[1] +\"\\n\");\n"
//				+ "\t\t\tString[] path = s[1].split(\"\\\\/\");\n"
//				+ "\t\t\tint size = path.length;\n"
//				+ "\t\t\tTuple[] tuple = new Tuple[size-1];\n\n"
//				+
//
//				"\t\t\ttuple[size-2] = new Tuple(3);\n"
//				+ "\t\t\ttuple[size-2].set(0, new ExternalObject(\"INSERT\"));\n"
//				+ "\t\t\ttuple[size-2].set(1, new ExternalObject(path[size-1]));\n"
//				+ "\t\t\ttuple[size-2].set(2, (this.generateSolutionElement()));\n\n"
//				+
//
//				"\t\t\tfor (int i = size-3;i>=0;i--){\n"
//				+ "\t\t\t\ttuple[i] = new Tuple(3);\n"
//				+ "\t\t\t\ttuple[i].set(0, new ExternalObject(\"INSERT\"));\n"
//				+ "\t\t\t\ttuple[i].set(1, new ExternalObject(path[i+1]));\n"
//				+
//
//				"\t\t\t\tSolution so = new Solution();\n"
//				+ "\t\t\t\tMolecule mo = new Molecule();\n"
//				+ "\t\t\t\tmo.add(tuple[i+1]);\n"
//				+ "\t\t\t\tso.addMolecule(mo);\n"
//				+
//
//				"\t\t\t\ttuple[i].set(2, so);\n"
//				+
//
//				"\t\t\t}\n\n"
//				+
//
//				"\t\t\tmol.add(tuple[0]);\n"
//				+
//
//				"\t\t}\n"
//				+
//
//				"\t\treturn mol;\n"
//				+ "\t}\n\n"
//				+
//
//				"\tpublic Molecule addRemoveTuple() throws IOException{\n"
//				+ "\t\tSystem.out.println(\"We are going to construct a solution for all the elements that you want to remove.\");\n"
//				+ "\t\tTuple t = new Tuple(2);\n"
//				+ "\t\tt.set(0, new ExternalObject(\"REMOVE\"));\n"
//				+ "\t\tt.set(1, this.generateSolutionElement());\n"
//				+ "\t\tMolecule mo = new Molecule();\n"
//				+ "\t\tmo.add(t);\n"
//				+ "\t\treturn mo;\n"
//				+ "\t}\n\n"
//				+
//
//				"\tpublic Molecule addInheritRemoveTuple(String line) throws IOException{\n"
//				+ "\t\t\t\tMolecule m = new Molecule();\n"
//				+ "\t\tString[] s = line.split(\"\\\\ \");\n"
//				+
//
//				"\t\tif(s.length!=2){\n"
//				+ "\t\t\tString errorLog=	\"Input command with wrong format.\\n\"\n"
//				+ "\t\t\t+	\"Use 'get' command in the following ways:\\n\"\n"
//				+ "\t\t\t+	\"1: 'get'; (just get an element in the container;)\\n\"\n"
//				+ "\t\t\t+	\"2: 'get path' ex.: get ./SubSolition1/SubSolution1-1 (get the elements in the indicated subsolution.)\\n\";\n"
//				+ "\t\t\tSystem.out.println(errorLog);\n"
//				+ "\t\t}\n\n"
//				+
//
//				"\t\telse{\n"
//				+
//
//				"\t\t\tif(!s[1].contains(\"/\"))\n"
//				+ "\t\t\t\tSystem.out.println(\"You have given a wrong solution path.\\n\");\n"
//				+
//
//				"\t\t\telse{\n"
//				+
//
//				"\t\t\t\tSystem.out.println(\"We are going to construct a solution for all the elements that you want to remove.\");\n"
//				+ "\t\t\t\tString[] path = s[1].split(\"\\\\/\");\n"
//				+ "\t\t\t\tint size = path.length;\n"
//				+ "\t\t\t\tTuple[] tuple = new Tuple[size-1];\n\n" +
//
//				"\t\t\t\ttuple[size-2] = new Tuple(3);\n"
//				+ "\t\t\t\ttuple[size-2].set(0, new ExternalObject(\"REMOVE\"));\n"
//				+ "\t\t\t\ttuple[size-2].set(1, new ExternalObject(path[size-1]));\n"
//				+ "\t\t\t\tSolution so = new Solution();\n"
//				+ "\t\t\t\tMolecule mo = new Molecule();\n"
//				+ "\t\t\t\tTuple tt = new Tuple(2);\n"
//				+ "\t\t\t\ttt.set(0, new ExternalObject(\"REMOVE\"));\n"
//				+ "\t\t\t\ttt.set(1,this.generateSolutionElement());\n"
//				+ "\t\t\t\tmo.add(tt);\n" + "\t\t\t\tso.addMolecule(mo);\n"
//				+ "\t\t\t\ttuple[size-2].set(2, so);\n" +
//
//				"\t\t\t\tfor (int i = size-3;i>=0;i--){\n"
//				+ "\t\t\t\t\ttuple[i] = new Tuple(3);\n"
//				+ "\t\t\t\t\ttuple[i].set(0, new ExternalObject(\"REMOVE\"));\n"
//				+ "\t\t\t\t\ttuple[i].set(1, new ExternalObject(path[i+1]));\n" +
//
//				"\t\t\t\t\tso = new Solution();\n" + "\t\t\t\t\tmo = new Molecule();\n"
//				+ "\t\t\t\t\tmo.add(tuple[i+1]);\n" + "\t\t\t\t\tso.addMolecule(mo);\n"
//				+
//
//				"\t\t\t\t\ttuple[i].set(2, so);\n" +
//
//				"\t\t\t\t}\n\n" +
//
//				"\t\t\t\tm.add(tuple[0]);\n" +
//
//				"\t\t\t}\n" + "\t\t}\n" + "\t\treturn m;\n" + "\t}\n\n";
//
//		generateTuple += "\t\t}\n" + "\t\treturn t;\n" + "\t}\n\n";
//
//		generateSolution += "\t\t\t\telse{\n"
//				+ "\t\t\t\t\ttry{\n"
//				+ "\t\t\t\t\t\tString ts = input.substring(0, 1).toUpperCase()+input.substring(1);\n"
//				+ "\t\t\t\t\t\tReactionRule m = (ReactionRule) Class.forName(input).newInstance();\n"
//				+ "\t\t\t\t\t\tmol.add(m);\n"
//				+ "\t\t\t\t\t\tsol.addMolecule(mol);\n"
//				+ "\t\t\t\t\t}catch(Exception e){\n"
//				+ "\t\t\t\t\t\tSystem.out.println(\"Error: (Test1_gen.generateSolutionElement()): \"+e+\".\");\n"
//				+ "\t\t\t\t\t}\n" + "\t\t\t\t}\n" + "\t\t\t}\n\n"
//				+ "\t\t\telse{sign=1;}\n\n" + "\t\t}\n\n" + "\t\treturn sol;\n" + "\t}";
//
//		return myCode + "\n\n" + generateFunction + "\n\n" + generateTuple + "\n\n"
//				+ generateSolution;
//
//	}


	/*
	 * public String generateAddElementCode(){
	 * 
	 * String code, decl; String myCode=""; Iterator<Atom> it = this.iterator();
	 * Atom atom; int position = 0;
	 * 
	 * List<String> types = new LinkedList<String>();
	 * 
	 * code = "Molecule " + varName + " = new Molecule();";
	 * 
	 * while(it.hasNext()){ atom = it.next(); decl =
	 * atom.generateDeclarationInit(position,symbolsTable);
	 * 
	 * code = code + "\n" + (decl.length() > 1 ? decl + "\n" : "");
	 * 
	 * if (atom.getType().equals("tuple")){ addToLinkedList(types,"tuple-Tuple");
	 * } else if (atom.getType().equals("external")){ String type =
	 * ((External)atom).getJavaType();
	 * 
	 * if (((External)atom).toString().length()>3 &&
	 * ((External)atom).toString().substring(0, 3).equals("new")){ String[]
	 * s=((External)atom).toString().split("\\("); String[] t=s[0].split("\\ ");
	 * type=t[1]; } addToLinkedList(types,type+"-Java Object"); } else if
	 * (atom.getType().equals("moleculeVar")){
	 * addToLinkedList(types,atom.generateReference()+"-MoleculeVar"); } else if
	 * (atom.getType().equals("basicSolution")){
	 * addToLinkedList(types,((BasicSolution
	 * )atom).generateReference()+"-BasicSolution"); } else if
	 * (atom.getType().equals("reactionRuleVar")){
	 * addToLinkedList(types,((ReactionRuleVar
	 * )atom).getVarName()+"-Reaction Rule"); } else if
	 * (atom.getType().equals("reactionRule")){
	 * addToLinkedList(types,((ReactionRule)atom).getRuleName()+"-Reaction Rule");
	 * } else{ addToLinkedList(types,atom.getType()+"-else"); } position++; }
	 * 
	 * myCode += "\tpublic void addElement()  throws IOException{\n" +
	 * "\t\tString input;\n" + "ExternalObject obj;\n" + "\t\tMolecule mol;\n" +
	 * "\t\tint choice;\n\n" +
	 * "\t\tSystem.out.println(\"What kind of element you want to add? (input the number)\\n \");\n"
	 * + "\t\tSystem.out.println(\"Supported Elements:\\n\");\n";
	 * 
	 * for (int i=0;i<types.size();i++){ myCode += "\t\tSystem.out.println(\""+
	 * (i+1) + ": "+types.get(i)+"\\n\");\n"; }
	 * 
	 * myCode +=
	 * "\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
	 * + "\t\tchoice = Integer.decode(input);\n\n" +
	 * "\t\tSystem.out.println(\"Enter your parameters, saperated by ',':\\n\");\n"
	 * +
	 * "\t\tinput = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
	 * + "\t\tswitch (choice){\n\n";
	 * 
	 * for (int i=0;i<types.size();i++){ myCode += "\t\t\tcase "+(i+1)+":\n";
	 * 
	 * String s = types.get(i); String [] l=s.split("\\-");
	 * 
	 * myCode += "\t\t\tmol = new Molecule();\n";
	 * 
	 * if (l[1].equals("Reaction Rule")){ String temp = l[0].substring(0,
	 * 1).toUpperCase()+l[0].substring(1);
	 * 
	 * myCode += "\t\t\t//ReactionRule r = new "+temp+"();\n" +
	 * "\t\t\t//mol.add(r);\n" + "\t\t\t//this.addMolecule(mol);\n\n"; } else if
	 * (l[1].equals("Java Object")){ myCode +=
	 * "\t\t\tobj = new ExternalObject(new "+l[0]+"(input));\n" +
	 * "\t\t\tmol.add(obj);\n" + "\t\t\tthis.addMolecule(mol);\n\n"; } else if
	 * (l[1].equals("Tuple")){ myCode +=
	 * "\t\t\tSystem.out.println(\"ADD A NEW TUPLE\");\n"; } else{}
	 * 
	 * myCode += "\t\t\tbreak;\n\n"; }
	 * 
	 * myCode += "\t\t}\n"+"\t}\n";
	 * 
	 * 
	 * return myCode; }
	 */

	public String generateDeclarationInit() {
		String code, decl;
		Iterator<Atom> it = this.iterator();
		Atom atom;
		int position = 0;

		code = "Molecule " + varName + " = new Molecule();";

		while( it.hasNext() ) {
			atom = it.next();
			decl = atom.generateDeclarationInit( position, symbolsTable );

			code = code + "\n" + ( decl.length() > 1 ? decl + "\n" : "" );

			if( atom.getType().equals( "tuple" ) ) {
				code += "tuple = " + atom.generateReference() + ";\n" + varName
						+ ".add(" + "tuple" + ");\n" + "this.addType(\"Tuple\");\n\n";
			} else if( atom.getType().equals( "external" ) ) {
				code += "object = "
						+ atom.generateReference()
						+ ";\n"
						+ varName
						+ ".add("
						+ "object"
						+ ");\n"
						+ "string = object.getObject().getClass().toString().split(\"\\\\.\");\n"
						+ "this.addType(string[string.length-1]);\n\n";
			} else if( atom.getType().equals( "moleculeVar" ) ) {
				code += varName + ".add(" + atom.generateReference() + ");";
			} else if( atom.getType().equals( "basicSolution" ) ) {
				code += varName + ".add(" + atom.generateReference() + ");";
			} else if( atom.getType().equals( "reactionRuleVar" ) ) {
				code += "rule = " + atom.generateReference() + ";\n" + varName
						+ ".add(" + "rule" + ");\n" + "this.addType(rule.getName());\n\n";
			} else if( atom.getType().equals( "reactionRule" ) ) {
				code += "rule = " + atom.generateReference() + ";\n" + varName
						+ ".add(" + "rule" + ");\n" + "this.addType(rule.getName());\n\n";
			} else {
				code += "//++++++++++++++++++++\n" + "//atom.getType();\n"
						+ "//++++++++++++++++++++\n";
				/*
				 * code +="r = " + atom.generateReference() +";\n" + varName + ".add("+
				 * "r" +");\n" + "this.addType(r.getName());\n\n";
				 */
			}

			// + varName + ".add("+ atom.generateReference() + "HAHAHA);";

			/*
			 * code = code + "\n" + (decl.length() > 1 ? decl + "\n\n" : "");
			 * 
			 * String s =atom.generateReference(); String sub = s.substring(0, 18);
			 * 
			 * if (sub.equals("new ExternalObject")){ code += "obj = " + s +";\n" +
			 * varName + ".add("+ "obj" +");\n" +
			 * "this.addType(obj.getObject().getClass().toString());\n\n"; }
			 * 
			 * else{
			 * 
			 * code += "r = " + s + ";\n" + varName + ".add("+
			 * atom.generateReference() +");\n" + "this.addType(r.getName());\n\n"; }
			 * 
			 * //+ "obj = " + atom.generateReference() +";\n" //+ varName + ".add("+
			 * "obj" +");\n" //+
			 * "this.allTypes.add(obj.getObject().getClass().toString());";
			 */
			position++;
		}
		return code;
	}


	public String generateAllVariablesDeclaration( Set<Symbols> argumentVars ) {
		String s = "";
		Set<Symbols> freeVars = this.getFreeVars();
		Iterator<Symbols> itVars;
		Symbols var;
		// remove first the symbols of rule or symbols of arguments which are
		// already declared
		itVars = freeVars.iterator();
		while( itVars.hasNext() ) {
			var = itVars.next();
			if( var instanceof ReactionRulePat ) {
				itVars.remove();
			}
			if( argumentVars.contains( var ) ) {
				itVars.remove();
			}
		}
		itVars = freeVars.iterator();
		while( itVars.hasNext() ) {
			var = itVars.next();
			/*
			 * String t = ""; Iterator<Symbols> itSymb = symbolsList.iterator();
			 * Symbols symb; int position = 0; while(itSymb.hasNext() && t.length() ==
			 * 0){ symb = itSymb.next(); t = symb.generateDeclaration(var,
			 * "permutation"); // + position + ")"); position++; }
			 */
			s = s + "\n" + symbolsTable.generateDeclaration( var, "permutation" );
		}
		return s;
	}


	public String generateReference() {
		return varName;
	}


	public String toString() {
		String s = "";
		Iterator<Atom> it = this.iterator();
		Atom atom;
		while( it.hasNext() ) {
			atom = it.next();
			s = s + ", " + atom;
		}
		if( s.length() > 1 ) {
			s = s.substring( 2 );
		}
		return s;
	}


	public void findReadOnlyReactives() {
		for (Atom o : this) {
			o.findReadOnlyReactives();
		}
	}

	public String generateAddElementCode () {
		return "";
	}

}
