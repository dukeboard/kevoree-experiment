package fr.inria.hocl.core.hoclc2j;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 13:02
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class CodeGeneratorHelper {

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

	public static String upperFirstLetter( String str ) {
			if( str.length() > 0 ) {
				return Character.toUpperCase( str.charAt( 0 ) ) + str.substring( 1 );
			} else {
				return str;
			}
		}
}
