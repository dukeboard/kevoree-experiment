package fr.inria.hocl.core.hoclc2j;


/**
 * fr.inria.hocl.core.hoclc2j = HOCL Compiler to Java
 *
 * It generates a Java file from an HOCL file. It generates also a run file that
 * uses the HOCL interpreter fr.inria.hocl.core.hocli to run the program. It may
 * also (cf command line switches) generate a test file that tests the result
 * computed.
 *
 * Command line switches: -v : set trace (for debugging) -nv : quiet, no output
 * (even errors) -test : generate the test file (for debugging) -- : read from
 * standard input fileName : read from file fileName
 *
 */
public class Hoclc2j {

	/*public static final String HOCLC2J_VERSION = "0.1->0.2", FILE_HEADER = "*//*\n"
			+ "* DO NOT EDIT\n"
			+ "* this class has been generated by fr.inria.hocl.core.hoclc2j version"
			+ HOCLC2J_VERSION + "\n" + "* on " + Calendar.getInstance().getTime()
			+ "\n" + "*//*\n" + "\n";

	private HoclParser parser;

	private String className;

	private String projectName;

	private String pathToHoclFile;

	public static final Debug debug = new Debug();

	private String hoclFileName = null;

	private boolean generateTest = false; // cf option "-test"

	private static final String genSuffixTag = "_gen",
	// expSuffixTag = "_expectedResult",
			runPrefixTag = "Run", testPrefixTag = "Test", javaSuffix = ".java";


	// hoclSuffix = ".hocl";

	public static void main( String args[] ) {
		Hoclc2j compiler = new Hoclc2j( args );
		compiler.run();
	}


	public Hoclc2j( String args[] ) {
		analyzeArguments( args );
		InputStream inputStream = getInputStream();
		parser = new HoclParser( inputStream );
		parser.ReInit( inputStream );
	}


	public void run() {
		Program prgm;
		prgm = parse();
		optimize( prgm );
		generate( prgm );
	}


	private Program parse() {
		Program prgm = null;

		try {
			prgm = parser.Start();
		} catch( ParseException e ) {
			stopError( "Encountered errors during parse.\n" + "Line "
					+ parser.jj_input_stream.getBeginLine() + ", " + "Column "
					+ ( parser.jj_input_stream.getBeginColumn() - 1 ) + ": "
					+ e.getMessage() );
		} catch( Exception e ) {
			e.printStackTrace();
			stopError( e.getMessage() );
		}
		return prgm;
	}


	private void optimize( Program prgm ) {
		prgm.setReadOnlyReactives();
		// recycleObjects(); // c.f. recycle sub-solutions and sub-tuples from
		// reactives into the result e.g. rep (x:y):z by x:y
		// setNonInertPattern(); // c.f. the hiddden property
		// reorderVariables();
	}


	private void generate( Program prgm ) {
		String pathSeparator = System.getProperty( "file.separator" );

		prgm.setProgramName( className );
		String code = FILE_HEADER + prgm.generateCode() + "\n";

		String[] classCode = prgm.generateRuleClass();
		// String test = "";
		for( int i = 0; i < classCode.length; i++ ) {
			// test +="start: \n"+classCode[i]+"***************\n";

			if( classCode[i].startsWith( "public class" ) ) {
				// int endIndex = classCode[i].indexOf("extends");
				// String ruleName = classCode[i].substring(13,endIndex);
				String[] s1 = classCode[i].split( "extends" );
				String[] s2 = s1[0].split( " " );
				String ruleName = s2[s2.length - 1];
				writeFile( pathToHoclFile + pathSeparator + ruleName + ".java", prgm
						.getHeader()
						+ "\n"
						+ "import fr.inria.hocl.core.hocli.*;\nimport java.io.*;\n\n"
						+ classCode[i] );
			}
		}

		*//**
		 * PUBLIC RELEASE 1 NOTE: For the current time being, server/client multiset
		 * exchange is disabled. TODO: get it ready for PR2!
		 *//*
		// writeFile(pathToHoclFile + pathSeparator +upperFirstLetter(projectName)+
		// "TEST"+".txt", test);
		// code +=
		// "\n//+++++++++++++++\n\n"+"//"+prgm.sol.getContents().toString()+"\n//+++++++++++++++++\n";
		// writeFile(pathToHoclFile + pathSeparator + "SrvInterface"+".java",
		// genSrvInterfaceClass(className,prgm.header));
		*//*
		 * writeFile(pathToHoclFile + pathSeparator +upperFirstLetter(projectName)+
		 * "Srv"+".java", genSrvClass(className,prgm.header));
		 *//*

		// writeFile(pathToHoclFile + pathSeparator + "CltInterface"+".java",
		// genCltInterfaceClass(className,prgm.header));
		*//*
		 * writeFile(pathToHoclFile + pathSeparator +upperFirstLetter(projectName)+
		 * "Clt"+".java", genCltClass(className,prgm.header));
		 *//*

		writeFile( pathToHoclFile + pathSeparator + className + javaSuffix, code );
		writeFile( pathToHoclFile + pathSeparator + runPrefixTag + className
				+ javaSuffix, genRunClass( className, prgm.header ) );

		if( generateTest ) {
			// generate the test file if the file expectedResult.hocl exists
			if( new File( pathToHoclFile + pathSeparator + "expectedResult.hocl" )
					.exists() ) {
				writeFile( pathToHoclFile + pathSeparator + testPrefixTag + className
						+ javaSuffix, genTestClass( className, prgm.header ) );
			} else {
				stopError( "File 'expectedResult.hocl' not found.\n" );
			}
		}
	}


	private void analyzeArguments( String args[] ) {

		if( args == null ) {
			this.hoclFileName = "";
			return;
		}

		int size = args.length;

		for( int i = 0; i < size; i++ ) {
			if( args[i].equals( "-v" ) ) {
				debug.setVerboseLevel( Debug.Level.TRACE );
			} else if( args[i].equals( "-nv" ) ) {
				debug.setVerboseLevel( Debug.Level.NONE );
			} else if( args[i].equals( "-test" ) ) {
				generateTest = true;
			} else if( args[i].equals( "--" ) ) {
				hoclFileName = "";
			} else {
				if( hoclFileName != null && hoclFileName.length() > 0 ) {
					stopError( "Two HOCL files specified: " + hoclFileName + " and "
							+ args[i] );
				} else {
					hoclFileName = args[i];
				}
			}
		}
	}


	private InputStream getInputStream() {
		if( this.hoclFileName == null || this.hoclFileName.length() == 0 ) {

			debug.addLog( "Reading from standard input . . .", Debug.Level.TRACE );

			className = "_HOCL_StdIn";
			pathToHoclFile = System.getProperty( "user.dir" );
			return System.in;
		} else {

			debug.addLog( "Reading from file " + hoclFileName + " . . .",
					Debug.Level.TRACE );

			try {
				File inputFile = new File( hoclFileName );
				// FIXME: inputFile.isFile()
				className = upperFirstLetter( fileBasename( inputFile.getName() ) )
						+ genSuffixTag;
				projectName = fileBasename( inputFile.getName() );

				pathToHoclFile = inputFile.getAbsoluteFile().getParent();
				return new FileInputStream( inputFile );
			} catch( FileNotFoundException e ) {
				stopError( "File " + hoclFileName + " not found." );
				return null;
			}
		}
	}


	private String fileBasename( String filename ) {
		try {
			return filename.substring( 0, filename.indexOf( '.' ) );
		} catch( Exception e ) {
			return filename;
		}
	}


	public static String upperFirstLetter( String str ) {
		if( str.length() > 0 ) {
			return Character.toUpperCase( str.charAt( 0 ) ) + str.substring( 1 );
		} else {
			return str;
		}
	}


	public static String indentCode( String code ) {
		String s;
		s = code.replaceAll( "\n", "\n\t" );
		s = "\t" + s;
		return s;
	}


	public static String indentCode( int nb, String code ) {
		String s = code;
		for( int i = 1; i <= nb; i++ ) {
			s = indentCode( s );
		}
		return s;
	}


	private void writeFile( String filename, String contents ) {
		try {
			FileOutputStream genFile = new FileOutputStream( filename );
			DataOutputStream dataFile = new DataOutputStream( genFile );
			dataFile.writeBytes( contents ); // writeChars, writeUTF, writeBytes
			dataFile.close();
		} catch( IOException e ) {
			stopError( e.getMessage() );
		}
	}


	private String genSrvInterfaceClass( String className, String header ) {
		String s = FILE_HEADER
				+ ( header.length() > 0 ? header + "\n" : "" )
				+ "import java.rmi.*;\n"
				+ "import fr.inria.hocl.core.hocli.*;\n\n\n"
				+ "*//**\n"
				+ " * Remote Interface for multiset I/O\n"
				+ " *//*"
				+ "public interface "
				+ "SrvInterface"
				+ " extends Remote {\n"
				+ "    *//**\n"
				+ "     * Remotely invocable method.\n"
				+ "     * @exception RemoteException if the remote invocation fails.\n"
				+ "     *//*\n"
				+ "     public void remoteOperations(Molecule m) throws RemoteException;\n"
				+ "     public void remoteCheckState(String clientClass) throws RemoteException;\n"
				+ "     public void remoteRemove(Molecule m) throws RemoteException;\n"
				+ "}\n";
		return s;
	}


	private String genCltInterfaceClass( String className, String header ) {
		String s = FILE_HEADER + ( header.length() > 0 ? header + "\n" : "" )
				+ "import java.rmi.*;\n" + "import fr.inria.hocl.core.hocli.*;\n\n\n"
				+ "*//**\n" + " * Remote Interface for multiset I/O\n" + " *//*"
				+ "public interface " + "CltInterface extends Remote {\n" + "    *//**\n"
				+ "     * Remotely invocable method.\n"
				+ "     * @exception RemoteException if the remote invocation fails.\n"
				+ "     *//*\n"
				+ "     public void printMultiset(String s) throws RemoteException;\n"
				+ "}\n";
		return s;
	}


	private String genSrvClass( String className, String header ) {
		String s = FILE_HEADER
				+ ( header.length() > 0 ? header + "\n" : "" )
				+ "import java.rmi.*;\n"
				+ "import java.rmi.registry.Registry;\n"
				+ "import java.rmi.registry.LocateRegistry;\n"
				+ "import java.io.*;\n"
				+ "import java.rmi.server.*;\n"
				+ "import fr.inria.hocl.core.hocli.*;\n\n\n"
				+

				"*//**\n"
				+ "* Remote Class for multiset remote I/O\n"
				+ "*//*\n"
				+ "public class "
				+ upperFirstLetter( this.projectName )
				+ "Srv extends UnicastRemoteObject implements "
				+ "GeneralSrvInterface {\n"
				+ "    private Solution sol;\n\n"
				+ "    *//**\n"
				+ "     * Construct a remote object\n"
				+ "     * @param msg the message of the remote object\n"
				+ "     * @exception RemoteException if the object handle cannot be constructed.\n"
				+ "     *//*\n"
				+ "    public "
				+ upperFirstLetter( this.projectName )
				+ "Srv (Solution s) throws RemoteException {\n"
				+ "        sol = s;\n"
				+ "    }\n"
				+ "   *//**\n"
				+ "    * Implementation of the remotely invocable method.\n"
				+ "    * @return the message of the remote object\n"
				+ "    * @exception RemoteException if the remote invocation fails.\n"
				+ "    *//*\n"
				+ "   public void remoteOperations(Molecule m) throws RemoteException {\n"
				+ "\t\tSystem.out.println(\"THis is remote operation Function:\");\n"
				+ "\t\tSystem.out.println(\"Get the input molecule from a remote client:\");\n"
				+ "\t\tSystem.out.println(m);\n"
				+ "\t\tsol.addMolecule(m);\n"
				+ "\t\tsol.printsol();\n"
				+ "   }\n"
				+

				"   public void remoteRemove(Molecule m) throws RemoteException {\n"
				+ "\t\tSystem.out.println(\"THis is remote operation Function:\");\n"
				+ "\t\tSystem.out.println(\"Get the input molecule from a remote client:\");\n"
				+ "\t\tSystem.out.println(m);\n"
				+ "\t\tsol.removeFromContainer(m);\n"
				+ "\t\tsol.printsol();\n"
				+ "   }\n"
				+

				"   public void remoteCheckState(String hostName, String clientClass) throws RemoteException {\n"
				+ "\t\ttry{\n"
				+ "\t\tSystem.out.println(\"Print the solution state to the remote client.\");\n"
				+ "\t\tRegistry registry = LocateRegistry.getRegistry(hostName);\n"
				+ "\t\tGeneralCltInterface remoteioclt = (GeneralCltInterface) registry.lookup (clientClass);\n"
				+ "\t\tString s = this.sol.prepareprintsol();\n"
				+ "\t\t//System.out.println(s);\n\n"
				+ "\t\tremoteioclt.printMultiset(s);\n" + "\t\t}catch (Exception e){\n"
				+ "\t\t\tSystem.out.println(e);\n" + "\t\t}\n\n"
				+ "\t\t\tSystem.out.print(\">\");\n" + "   }\n" + "}\n";
		return s;
	}


	private String genCltClass( String className, String header ) {
		String s = FILE_HEADER
				+ ( header.length() > 0 ? header + "\n" : "" )
				+ "import java.rmi.*;\n"
				+ "import java.io.*;\n"
				+ "import java.rmi.server.*;\n"
				+ "import fr.inria.hocl.core.hocli.*;\n\n\n"
				+

				"*//**\n"
				+ "* Remote Class for multiset remote I/O\n"
				+ "*//*\n"
				+ "public class "
				+ upperFirstLetter( this.projectName )
				+ "Clt extends UnicastRemoteObject implements GeneralCltInterface {\n"
				+ "    private Solution sol;\n\n"
				+ "    *//**\n"
				+ "     * Construct a remote object\n"
				+ "     * @param msg the message of the remote object\n"
				+ "     * @exception RemoteException if the object handle cannot be constructed.\n"
				+ "     *//*\n" + "    public " + upperFirstLetter( this.projectName )
				+ "Clt (Solution s) throws RemoteException {\n" + "        sol = s;\n"
				+ "    }\n" + "   *//**\n"
				+ "    * Implementation of the remotely invocable method.\n"
				+ "    * @return the message of the remote object\n"
				+ "    * @exception RemoteException if the remote invocation fails.\n"
				+ "    *//*\n"
				+ "   public void printMultiset(String s) throws RemoteException {\n"
				+ "\t\tSystem.out.println(\"The remote multi-set is :\");\n"
				+ "\t\t//s.printsol();\n" + "\t\tSystem.out.println(s);\n" + "   }\n"
				+ "}\n";
		return s;
	}


	private String genRunClass( String className, String header ) {
		String s = FILE_HEADER
				+ ( header.length() > 0 ? header + "\n" : "" )
				+ "import java.io.*;\n"
				+ "import fr.inria.hocl.core.hocli.*;\n"
				+ "import fr.inria.hocl.core.hoclc2j.Hoclc2j;\n"
				+ "import java.rmi.Naming;\n"
				+ "import java.rmi.RMISecurityManager;\n"
				+ "import java.net.InetAddress;\n"
				+

				"import java.rmi.registry.LocateRegistry;\n"
				+ "import java.rmi.registry.Registry;\n"
				+ "import java.rmi.server.UnicastRemoteObject;\n\n"
				+

				"public class Run"
				+ className
				+ " {\n\n"
				+

				"\tpublic static Molecule addElement(String line, Solution sol) throws Exception{\n"
				+ "\t\tMolecule mol = new Molecule();\n"
				+

				"\t\t\t\t\tif (line.equals(\"put\") || line.equals(\"p\")) {\n"
				+ "\t\t\t\t\t\tmol = sol.addElement();\n"
				+ "\t\t\t\t\t\t//sol.setNonInert();\n"
				+ "\t\t\t\t\t\t//sol.reduce();\n"
				+ "\t\t\t\t\t}\n\n"
				+

				"\t\t\t\t\telse{\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"We are going to construct a solution to contain all the elements that you want to add.\");\n"
				+ "\t\t\t\t\t\tmol = (("
				+ className
				+ ")sol).addElementSubSolution(line);"
				+ "\t\t\t\t\t}\n"
				+

				"\t\treturn mol;\n"
				+

				"\t}\n\n"
				+

				"\tpublic static boolean deleteDir(File dir) {\n"
				+ "\t\tif (dir.isDirectory()) {\n"
				+ "\t\t\tString[] children = dir.list();\n\n"
				+ "\t\t\tfor (int i=0; i<children.length; i++) {\n\n"
				+ "\t\t\t\tboolean success = deleteDir(new File(dir, children[i]));\n\n"
				+ "\t\t\t\tif (!success) {\n\n"
				+ "\t\t\t\t\treturn false;\n\n"
				+ "\t\t\t\t}\n\n"
				+ "\t\t\t}\n\n"
				+ "\t\t}\n\n"
				+ "\t\treturn dir.delete();\n"
				+ "\t}\n\n"
				+

				// THIS IS THE MAIN METHOD!
				"\tpublic static void main(String args[]) throws Exception{\n"
				+ "\t\tboolean quitshell = false;\n"
				+ "\t\tHocli.init(args);\n"
				+ "\t\tSolution sol = new "
				+ className
				+ "();\n"
				+ "\t\tSystem.out.println(\"HOCL shell\");\n"
				+ "\t\tSystem.out.println(\"Copyright INRIA, 2009\");\n"
				+ "\t\twhile (!quitshell) {\n"
				+ "\t\t\ttry{\n"
				+ "\t\t\t\tSystem.out.print(\"hocl> \");\n"
				+ "\t\t\t\tString line = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
				+ "\t\t\t\tif( line == null ) {\n"
				+ "\t\t\t\t\tquitshell = true;\n"
				+ "\t\t\t\t\tSystem.out.println();\n"
				+

				// +++++++++++++++++++++++++++++++++++++++++++++++++
				// put an Element;
				// +++++++++++++++++++++++++++++++++++++++++++++++++

				"\t\t\t\t} else if (line.startsWith(\"put\")||line.startsWith(\"p\")||line.startsWith(\"PUT\")||line.startsWith(\"P\")){\n"
				+ "\t\t\t\t\tsol.addMolecule(addElement(line,sol));\n"
				+ "\t\t\t\t}\n\n"
				+
				// +++++++++++++++++++++++++++++++++++++++++++++++++
				// Get an Element;
				// +++++++++++++++++++++++++++++++++++++++++++++++++

		    "\t\t\t\telse if (line.startsWith(\"get\")||line.startsWith(\"g\")||line.startsWith(\"GET\")||line.startsWith(\"G\")){\n"
		    + "\t\t\t\t\tif (line.equals(\"get\") || line.equals(\"g\")) {\n"
		    + "\t\t\t\t\t\t// First scanning for display\n"
		    + "\t\t\t\t\t\tSimpleIterator<Atom> it = sol.newIterator();\n"
		    + "\t\t\t\t\t\tAtom atom = null;\n"
		    + "\t\t\t\t\t\tint cpt_at = 1;\n"
		    + "\t\t\t\t\t\twhile( it.hasNext() ) {\n"
		    + "\t\t\t\t\t\t\tatom = it.next();\n"
		    + "\t\t\t\t\t\t\tSystem.out.println( cpt_at + \": \" + atom );\n"
		    + "\t\t\t\t\t\t\tcpt_at++;\n"
		    + "\t\t\t\t\t\t}\n"
		    + "\t\t\t\t\t\tSystem.out.println( cpt_at + \": \" + it.next() );\n"
		    + "\t\t\t\t\t\t// Second scanning for removing itself\n"
		    + "\t\t\t\t\t\tSystem.out.println(\"Enter the number of the element to be removed:\");\n"
		    + "\t\t\t\t\t\tString input = new BufferedReader( new InputStreamReader( System.in ) ).readLine();\n"
		    + "\t\t\t\t\t\tint atomNb = ( new Integer( input ) ).intValue();\n"
		    + "\t\t\t\t\t\tit = sol.newIterator();\n"
		    + "\t\t\t\t\t\tcpt_at = 1;\n"
		    + "\t\t\t\t\t\twhile( cpt_at <= atomNb ) {\n"
		    + "\t\t\t\t\t\t\tatom = it.next();\n"
		    + "\t\t\t\t\t\t\tcpt_at++;\n"
		    + "\t\t\t\t\t\t}\n"
		    + "\t\t\t\t\tsol.removeAtomFromContainer(atom);\n"
		    + "\t\t\t\t\t}\n\n"

		    + "\t\t\t\t\telse {\n"
				+ "\t\t\t\t\t\tMolecule mo = new Molecule();\n"
				+ "\t\t\t\t\t\tmo = (("
				+ className
				+ ")sol).addInheritRemoveTuple(line);\n"
				+
				// "\t\t\t\t\t\t\t(("+className+")sol).addMolecule(m);\n"+
				"\t\t\t\t\t\tsol.addMolecule(mo);\n"
				+ "\t\t\t\t\t\t//sol.setNonInert();\n"
				+ "\t\t\t\t\t\t//sol.reduce();\n"
				+ "\t\t\t\t\t}\n\n"
				+ "\t\t\t\t}\n"
				+

				"\t\t\t\telse{\n\n"
				+

				// +++++++++++++++++++++++++++++++++++++++++++++++++
				// Quit the Procedure;
				// +++++++++++++++++++++++++++++++++++++++++++++++++
				"\t\t\t\t\tif (line.equals(\"quit\") || line.equals(\"q\"))\n"
				+ "\t\t\t\t\t\tquitshell=true;\n\n"
				+

				// +++++++++++++++++++++++++++++++++++++++++++++++++
				// Run the Chemical Reactions;
				// +++++++++++++++++++++++++++++++++++++++++++++++++
				"\t\t\t\t\telse if (line.equals(\"run\") || line.equals(\"r\")){\n"
				+ "\t\t\t\t\t\tsol.setNonInert();\n"
				+ "\t\t\t\t\t\tsol.reduce();\n"
				+ "\t\t\t\t\t}\n\n"
				+

				*//*******************************************************
				 * TODO: For now, this part is commented out (for PR1) until the
				 * server/client stuff has been worked out
				 *
				 * //+++++++++++++++++++++++++++++++++++++++++++++++++ //Server:
				 * Response to Remote Operation;
				 * //+++++++++++++++++++++++++++++++++++++++++++++++++
				 * "\t\t\t\t\telse if (line.equals(\"server\") || line.equals(\"srv\")) {\n"
				 * + "\t\t\t\t\t\tif (System.getSecurityManager() == null) {\n"+
				 * "\t\t\t\t\t\t\tSystem.setSecurityManager(new RMISecurityManager());\n"
				 * + "\t\t\t\t\t\t}\n"+ "\t\t\t\t\t\ttry {\n" +
				 * "\t\t\t\t\t\t\tString name = \""
				 * +upperFirstLetter(this.projectName)+"Srv\";\n" +
				 * "\t\t\t\t\t\t\tGeneralSrvInterface Server = new "
				 * +upperFirstLetter(this.projectName)+"Srv(sol);\n" +
				 * "\t\t\t\t\t\t\tRegistry registry = LocateRegistry.getRegistry();\n" +
				 * "\t\t\t\t\t\t\tregistry.rebind(name, Server);\n" +
				 * "\t\t\t\t\t\t\tname = \""
				 * +upperFirstLetter(this.projectName)+"Clt\";\n"+
				 * "\t\t\t\t\t\t\tGeneralCltInterface Client = new "
				 * +upperFirstLetter(this.projectName)+"Clt(sol);\n"+
				 * "\t\t\t\t\t\t\tregistry = LocateRegistry.getRegistry();\n"+
				 * "\t\t\t\t\t\t\tregistry.rebind(name, Client);\n"+
				 *
				 *
				 * "\t\t\t\t\t\t\tSystem.out.println (\"Remote I/O multiset is enabled.\");\n"
				 * + "\t\t\t\t\t\t} catch (Exception e) {\n" +
				 * "\t\t\t\t\t\t\tSystem.out.println (\"Remote I/O multiset failed: \" + e);\n"
				 * + "\t\t\t\t\t\t}\n"+ "\t\t\t\t\t}\n\n" +
				 *
				 * //+++++++++++++++++++++++++++++++++++++++++++++++++ //CLIENT: MAKE
				 * REMOTE OPERATION; //+++++++++++++++++++++++++++++++++++++++++++++++++"\t\t\t\t\telse if (line.startsWith(\"client\") || line.startsWith(\"clt\")) {\n"
				 * +
				 *
				 * "\t\t\t\t\t\ttry {\n" + "\t\t\t\t\t\t\tint out = 0;\n"+
				 * "\t\t\t\t\t\t\tString ipaddr = \"\";\n"+
				 * "\t\t\t\t\t\t\tString serverClass = \"\";\n"+
				 *
				 * //CONNECT TO LOCAL SERVER
				 * "\t\t\t\t\t\t\tif (line.equals(\"client\") || line.equals(\"clt\")) {\n"
				 * + "\t\t\t\t\t\t\t\tipaddr = \"localhost\";\n"+
				 * "\t\t\t\t\t\t\t\tserverClass = \""
				 * +upperFirstLetter(this.projectName)+"\";\n"+ "\t\t\t\t\t\t\t}\n\n"+
				 *
				 * //CONNECT TO A REMOTE SERVER "\t\t\t\t\t\t\telse {\n" +
				 * "\t\t\t\t\t\t\t\tString[] cmd = line.split(\"\\\\ \");\n"+
				 * "\t\t\t\t\t\t\t\tif(cmd.length!=3 && cmd.length!=2){\n"+
				 * "\t\t\t\t\t\t\t\t\tString errorLog=	\"Input command with wrong format.\\n\"\n"
				 * +
				 * "\t\t\t\t\t\t\t\t\t+\"Use 'client' command in the following ways:\\n\"\n"
				 * +
				 * "\t\t\t\t\t\t\t\t\t+	\"1: 'client'; (Connect to local server;)\\n\"\n"
				 * + "\t\t\t\t\t\t\t\t\t+	\"2: 'client 192.168.0.0 serverClass'\\n\";\n"
				 * + "\t\t\t\t\t\t\t\t\tSystem.out.println(errorLog);\n"+
				 * "\t\t\t\t\t\t\t\t}\n"+ "\t\t\t\t\t\t\t\telse if (cmd.length==3){\n"+
				 * "\t\t\t\t\t\t\t\t\tipaddr = cmd[1];\n"+
				 * "\t\t\t\t\t\t\t\t\t\tserverClass = cmd[2];\n"+ "\t\t\t\t\t\t\t\t}\n"+
				 * "\t\t\t\t\t\t\t\telse if (cmd.length==2){\n"+
				 * "\t\t\t\t\t\t\t\t\tipaddr = \"localhost\";\n"+
				 * "\t\t\t\t\t\t\t\t\tserverClass = cmd[1];\n"+ "\t\t\t\t\t\t\t\t}\n"+
				 * "\t\t\t\t\t\t\t}\n\n"+
				 *
				 * "\t\t\t\t\t\t\tString name = serverClass+\"Srv\";\n"+
				 * "\t\t\t\t\t\t\tRegistry registry = LocateRegistry.getRegistry(ipaddr);\n"
				 * +"\t\t\t\t\t\t\tGeneralSrvInterface remoteiosrv = (GeneralSrvInterface) registry.lookup(name);\n"
				 * +
				 *
				 *
				 * "\t\t\t\t\t\t\tSystem.out.print(\">>This is the client shell.\\n\");\n"
				 * + "\t\t\t\t\t\t\twhile (out ==0){\n"+"\t\t\t\t\t\t\t\tSystem.out.print(\">>Input your command for the remote I/O operation:\\n>>\");\n"
				 * +"\t\t\t\t\t\t\t\tString Comstr = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
				 * +
				 *
				 * //PUT AN ELEMENT;"\t\t\t\t\t\t\t\t\tif (Comstr.startsWith(\"put\")||Comstr.startsWith(\"p\")||Comstr.startsWith(\"PUT\")||Comstr.startsWith(\"P\")){\n"
				 * + //PUT AN ELEMENT IN THE CONTAINER;"\t\t\t\t\t\t\t\t\tif (Comstr.equals(\"put\")||Comstr.equals(\"p\")||Comstr.equals(\"PUT\")||Comstr.equals(\"p\")){\n"
				 * + "\t\t\t\t\t\t\t\t\t\tMolecule m = addElement(Comstr, sol);\n"+
				 * "\t\t\t\t\t\t\t\t\t\tremoteiosrv.remoteOperations(m);\n"+"\t\t\t\t\t\t\t\t\t\tSystem.out.println(\"The element \"+m+\" has been added into the remote multi-set.\");\n"
				 * + "\t\t\t\t\t\t\t\t\t}\n\n"+ //PUT AN ELEMENT IN THE SUBSOLUTION;
				 * "\t\t\t\t\t\t\t\t\telse{\n"+
				 * "\t\t\t\t\t\t\t\t\t\tMolecule m = (("+className
				 * +")sol).addElementSubSolution(Comstr);\n"+
				 * "\t\t\t\t\t\t\t\t\t\tremoteiosrv.remoteOperations(m);\n"+"\t\t\t\t\t\t\t\t\t\tSystem.out.println(\"The element \"+m+\" has been added into the remote multi-set.\");\n"
				 * + "\t\t\t\t\t\t\t\t\t}\n\n"+ "\t\t\t\t\t\t\t\t}\n\n"+
				 *
				 * //GET AN ELEMENT;"\t\t\t\t\t\t\t\telse if (Comstr.startsWith(\"get\")||Comstr.startsWith(\"g\")||Comstr.startsWith(\"GET\")||Comstr.startsWith(\"G\")){\n"
				 * + //GET AN ELEMENT IN THE CONTAINER;"\t\t\t\t\t\t\t\t\tif (Comstr.equals(\"get\")||Comstr.equals(\"g\")||Comstr.equals(\"GET\")||Comstr.equals(\"G\")){\n"
				 * + "\t\t\t\t\t\t\t\t\t\tMolecule m1 = new Molecule();\n"+
				 * "\t\t\t\t\t\t\t\t\t\tm1 = sol.addElement();\n"+
				 * "\t\t\t\t\t\t\t\t\t\tremoteiosrv.remoteRemove(m1);\n"+"\t\t\t\t\t\t\t\t\t\tSystem.out.println(\"The element \"+m1+\" has been removed from the remote multi-set.\");\n"
				 * +
				 *
				 * //"\t\t\t\t\t\t\t\t\t\tMolecule m = (("+className+
				 * ")sol).addRemoveTuple();\n"+
				 * //"\t\t\t\t\t\t\t\t\t\tremoteiosrv.remoteOperations(m);\n"+
				 * "\t\t\t\t\t\t\t\t\t}\n\n"+ //GET AN ELEMENT IN THE SUBSOLUTION;
				 * "\t\t\t\t\t\t\t\t\telse{\n"+
				 * "\t\t\t\t\t\t\t\t\t\tMolecule m = (("+className
				 * +")sol).addInheritRemoveTuple(Comstr);\n"+
				 * "\t\t\t\t\t\t\t\t\t\tremoteiosrv.remoteOperations(m);\n"+
				 * "\t\t\t\t\t\t\t\t\t}\n\n"+ "\t\t\t\t\t\t\t\t}\n\n"+
				 *
				 * //DISPLAY THE CONTENT OF THE REMOTE MULTI-SET
				 * "\t\t\t\t\t\t\t\telse if (Comstr.equals(\"display\")||Comstr.equals(\"dp\")){\n"
				 * +"\t\t\t\t\t\t\t\t\t//System.out.println(\"***********\\nCALL REMOTE CHECK STATE METHOD\\n************\");\n"
				 * +
				 * "\t\t\t\t\t\t\t\t\tInetAddress addr = InetAddress.getLocalHost();\n"+
				 * "\t\t\t\t\t\t\t\t\tString ip=addr.getHostAddress();"+
				 * "\t\t\t\t\t\t\t\t\tremoteiosrv.remoteCheckState(ip, \""
				 * +upperFirstLetter(this.projectName)+"Clt\");\n"+
				 * "\t\t\t\t\t\t\t\t}\n"+
				 *
				 * //QUIT THE CLIENT SHELL
				 * "\t\t\t\t\t\t\t\telse if (Comstr.equals(\"q\")||Comstr.equals(\"quit\")){\n"
				 * + "\t\t\t\t\t\t\t\t\t\tout = 1;\n"+
				 * "\t\t\t\t\t\t\t\t\t\tSystem.out.println(\">>Quit the client shell.\");\n"
				 * + "\t\t\t\t\t\t\t\t}\n"+
				 *
				 * "\t\t\t\t\t\t\t}\n"+
				 *
				 * "\t\t\t\t\t\t} catch (Exception e) {\n"+
				 * "\t\t\t\t\t\t\t\tSystem.out.println (\"RemoteIOclient exception: \" + e);\n"
				 * + "\t\t\t\t\t\t}\n\n"+
				 *
				 * "\t\t\t\t\t}\n\n"+
				 *
				 * *//*************************************** TODO: End of client/server
				 * commented out part
				 *//*

				"\t\t\t\t\telse if (line.equals(\"debug\") || line.equals(\"d\")) {\n"
				+ "\t\t\t\t\t\tSystem.out.print(\"Which level (0-9)? \");\n"
				+ "\t\t\t\t\t\tline = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
				+ "\t\t\t\t\t\tHocli.setVerboseLevel(Integer.decode(line));\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"Debug level set to \" + line);\n"
				+ "\t\t\t\t\t\t}\n\n"
				+

				"\t\t\t\t\telse if (line.equals(\"strategy\") || line.equals(\"s\")) {\n"
				+ "\t\t\t\t\t\twhile( true ) {\n"
				+ "\t\t\t\t\t\t\tSystem.out.print(\"Which strategy (FR / UFR / RAND / KSS) ? \");\n"
				+ "\t\t\t\t\t\t\tline = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
				+ "\t\t\t\t\t\t\tif( line == null || line.isEmpty() ) {\n"
				+ "\t\t\t\t\t\t\t\tSystem.out.println( \"You must enter a strategy name!\" );\n"
				+ "\t\t\t\t\t\t\t\tcontinue;\n"
				+ "\t\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\t\tline = line.trim();\n"
				+ "\t\t\t\t\t\t\tif( line.equalsIgnoreCase( \"fr\" ) )\n"
				+ "\t\t\t\t\t\t\t\tbreak;\n"
				+ "\t\t\t\t\t\t\tif( line.equalsIgnoreCase( \"ufr\" ) )\n"
				+ "\t\t\t\t\t\t\t\tbreak;\n"
				+ "\t\t\t\t\t\t\tif( line.equalsIgnoreCase( \"rand\" ) )\n"
				+ "\t\t\t\t\t\t\t\tbreak;\n"
				+ "\t\t\t\t\t\t\tif( line.equalsIgnoreCase( \"kss\" ) )\n"
				+ "\t\t\t\t\t\t\t\tbreak;\n"
				+ "\t\t\t\t\t\t\tSystem.out.println( line + \" is not a valid strategy!\" );\n"
				+ "\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\tHocli.setStrategy(line);\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"Strategy set to \" + line);\n"
				+ "\t\t\t\t\t\t}\n\n"
				+

				"\t\t\t\t\telse if (line.equals(\"display\") || line.equals(\"dp\")) {\n"
				+ "\t\t\t\t\t\tsol.printsol();\n"
				+ "\t\t\t\t\t}\n\n"
				+

				"\t\t\t\t\telse if (line.equals(\"check\") || line.equals(\"cht\")) {\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"All the supported types of elements:\\n\");\n"
				+ "\t\t\t\t\t\tSystem.out.println((("
				+ className
				+ ") sol).displayTypes());\n"
				+ "\t\t\t\t\t}\n"
				+

				"\t\t\t\t\telse if (line.equals(\"help\") || line.equals(\"h\")) {\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(q) quit - exit the HOCL shell\");\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(r) run - run the HOCL program\");\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(d) debug - set debug level\");\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(s) strategy - select strategy\");\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(p) put - add a molecule to the multi-set\");\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(g) get - get a molecule from the multi-set\");\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"(dp) display - display solution\");\n"
				+
				*//**
				 * TODO: PUBLIC RELEASE 1 NOTE: we should not, for the time being, show
				 * these commands as available
				 *//*
				// "\t\t\t\t\t\tSystem.out.println(\"(srv) server - enabling remote I/O multiset\");\n"
				// +
				// "\t\t\t\t\t\tSystem.out.println(\"(clt) client - enabling remote I/O multiset\");\n"
				// +
				"\t\t\t\t\t\tSystem.out.println(\"(cht) check - check all the supported types of elements\");\n"
				+ "\t\t\t\t\t}\n"
				+

				"\t\t\t\t\t else if (line.equals(\"newrule\") || line.equals(\"nr\")){\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"Please give your rule name:\\n\");\n"
				+ "\t\t\t\t\t\tString newRuleName = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"Please give your rule definition:\\n\");\n"
				+ "\t\t\t\t\t\tString newRuleDef = new BufferedReader(new InputStreamReader(System.in)).readLine();\n"
				+
				// "\t\t\t\t\t\tString newProgram = \"package test;\\n\"+ \n" +
				"\t\t\t\t\t\tString newProgram = \n"
				+ "\t\t\t\t\t\t\t\"let \"+newRuleName+ \" =\\n\"+\n"
				+ "\t\t\t\t\t\t\tnewRuleDef+\n"
				+ "\t\t\t\t\t\t\t\"\\nin\\n\"+\n"
				+ "\t\t\t\t\t\t\t\"<\"+newRuleName+\">\\n\";\n"
				+ "\t\t\t\t\t\ttry{\n"
				+ "\t\t\t\t\t\t\tFile ruledir = new File(\"./rules/\");\n\n"
				+ "\t\t\t\t\t\t\tif(!(new File(\"./rules/\").isDirectory())){\n"
				+ "\t\t\t\t\t\t\t\truledir.mkdir();\n"
				+ "\t\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\t\tFileOutputStream genFile = new FileOutputStream(\"./rules/"
				+ hoclFileName
				+ "\");\n"
				+ "\t\t\t\t\t\t\tDataOutputStream dataFile = new DataOutputStream(genFile);\n"
				+ "\t\t\t\t\t\t\tdataFile.writeBytes(newProgram);\n"
				+ "\t\t\t\t\t\t\tdataFile.close();\n"
				+ "\t\t\t\t\t\t\ttry{\n"
				+ "\t\t\t\t\t\t\t\tString inputFiles[] = new String[1];\n"
				+ "\t\t\t\t\t\t\t\tinputFiles[0] = \"./rules/"
				+ hoclFileName
				+ "\";\n"
				+ "\t\t\t\t\t\t\t\tHoclc2j compiler = new Hoclc2j(inputFiles);\n"
				+ "\t\t\t\t\t\t\t\tcompiler.run();\n"
				+ "\t\t\t\t\t\t\t\tString ruleClassName = Character.toUpperCase(newRuleName.charAt(0)) + newRuleName.substring(1);\n"
				+ "\t\t\t\t\t\t\t\tFile file = new File(\"./rules/\"+ruleClassName+\".java\");\n"
				+ "\t\t\t\t\t\t\t\tFile dir = new File(\"./\");\n"
				+ "\t\t\t\t\t\t\t\tif(file.renameTo(new File(dir, file.getName()))){\n"
				+ "\t\t\t\t\t\t\t\t\tif(deleteDir(ruledir)){\n"
				+ "\t\t\t\t\t\t\t\t\t\ttry{\n"
				+ "\t\t\t\t\t\t\t\t\t\t\tRuntime   r   =   Runtime.getRuntime();\n"
				+ "\t\t\t\t\t\t\t\t\t\t\tString compile = \"javac -cp ../../../bin -d ../../../bin \"+ruleClassName+\".java\";\n"
				+
				// "\t\t\t\t\t\t\t\t\t\t\tString cmd = \"cd /Users/cwang/Documents/workspace/hocl/src/fr.inria.hocl.example/test\";\n"
				// +
				// "\t\t\t\t\t\t\t\t\t\t\tr.exec(cmd);\n" +
				"\t\t\t\t\t\t\t\t\t\t\tr.exec(compile);\n"
				+ "\t\t\t\t\t\t\t\t\t\t\t(("
				+ className
				+ ")sol).addType(ruleClassName);\n"
				+ "\t\t\t\t\t\t\t\t\t\t\tSystem.out.println(\"The new rule have been created!\\n\");\n"
				+ "\t\t\t\t\t\t\t\t\t\t}catch(Exception e){\n"
				+ "\t\t\t\t\t\t\t\t\t\t\tSystem.out.println(\"You might have the following error(s) during compiling new rules:\\n\" +e);\n"
				+ "\t\t\t\t\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\t\t}catch(Exception e){\n"
				+ "\t\t\t\t\t\t\t\tSystem.out.println(\"You might have the following error(s) during your definition of rules:\\n\" +e);\n"
				+ "\t\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t\t}catch(Exception e){\n"
				+ "\t\t\t\t\t\t\tSystem.out.println(\"Exception during creating new HOCL files:\\n\" +e);\n"
				+ "\t\t\t\t\t\t}\n"
				+ "\t\t\t\t\t}\n"
				+

				"\t\t\t\t\telse{\n"
				+ "\t\t\t\t\t\tSystem.out.println(\"Command '\"+line+\"' does not exist.\\nInput 'help' (h) to check all the commands.\");"
				+ "\t\t\t\t\t}\n" + "\t\t\t\t}\n\n" +

				"\t\t\t}\n" + "\t\t\tcatch (IOException e) {\n"
				+ "\t\t\t\t//e.printStackTrace();\n"
				+ "\t\t\t\tSystem.out.println(\"e\");\n" + "\t\t\t}\n" + "\t\t}\n"
				+ "\t}\n" + "} // class Run" + className + "\n";
		return s;
	}


	private String genTestClass( String className, String packDecl ) {
		String s = FILE_HEADER + ( packDecl.length() > 0 ? packDecl + "\n" : "" )
				+ "import fr.inria.hocl.core.hocli.*;\n\n" + "public class Test"
				+ className + " {\n\n"
				+ "	public static void main(String args[]) throws Exception{\n"
				+ "		Hocli.init(args);\n" + "		Solution sol = new " + className
				+ "();\n" + "		sol.reduce();\n"
				+ "		Solution res = new ExpectedResult_gen();\n"
				+ "		boolean success = sol.equals(res);\n" + "		if(!success){\n"
				+ "			System.out.println(\"Error: Unexpected result!\");\n"
				+ "			System.exit(1);\n" + "		}\n" + "	}\n" + "} // class Test"
				+ className + "\n";
		return s;
	}


	public static void stopError( String message ) {
		debug.addLog( "FATAL ERROR: " + message, Debug.Level.ERROR );
		System.exit( 254 );
	}*/


	/**
	 * Check that the argument is a descriptor of an external object If it is not,
	 * generate a parse exception "Waiting for a Java object at line x column y".
	 *
	 * @param descAtom
	 */
	public static void checkExternal( Atom descAtom ) throws ParseException {
		if( descAtom instanceof AtomVarPat) {
			if( !( (AtomVarPat) descAtom ).isExternal() ) {
				throw new ParseException( "Waiting for a Java object. " + descAtom );
			}
		} else {
			if( !( descAtom instanceof External ) ) {
				throw new ParseException( "Waiting for a Java object. " + descAtom );
			}
		}
	}

}