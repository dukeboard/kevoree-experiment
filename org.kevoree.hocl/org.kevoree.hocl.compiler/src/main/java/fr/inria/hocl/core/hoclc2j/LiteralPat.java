package fr.inria.hocl.core.hoclc2j;


public class LiteralPat extends AtomVarPat {

	private static int literalCounter = 0;


	// private final AtomVarPat atomVarPat;

	public LiteralPat( External external, Pattern pattern ) throws ParseException {
		super( "_HOCL_literal" + literalCounter, external.javaType, pattern );
		String varName = "_HOCL_literal" + literalCounter;
		// atomVarPat = new AtomVarPat(varName, external.javaType);
		literalCounter++;
		ReactionCondition reactionCondition = new ReactionCondition();
		reactionCondition.javaExpression = varName + ".equals("
				+ external.javaExpression + ")";
		reactionCondition.addVar( this );
		setReactionCondition( reactionCondition );
	}

}
