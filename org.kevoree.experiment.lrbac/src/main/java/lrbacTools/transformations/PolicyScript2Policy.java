package lrbacTools.transformations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import patternbuilders.lrbac.*;
import patternmatchers.lrbac.*;
import signatures.lrbac.*;
import lrbac.*;
import lrbacTools.editor.PolicyEditor;

public class PolicyScript2Policy {

	private String script;
	private ArrayList<String> primitives;
	
	
	public PolicyScript2Policy(String txt) {
		script = txt.replaceAll("\n", " ");
		script = script.substring(script.indexOf("PolicyScript{") + 13,
				script.indexOf("}"));
		script = script.replace(" ", "");
		primitives = new ArrayList<String>();
		initPrimitives();
	}

	public void initPrimitives() {
		for (Method m : PolicyEditor.class.getMethods()){
			primitives.add(m.getName());
		}
	}

	public Policy transformation() {
		Policy policy = LrbacFactory.eINSTANCE.createPolicy();
		PolicyEditor policyEditor = new PolicyEditor(policy);
		parseExpressions(policyEditor);
		return policy;
	}
	
	public void transformation(Policy policy) {
		PolicyEditor policyEditor = new PolicyEditor(policy);
		parseExpressions(policyEditor);
	}

	public void parseExpressions(PolicyEditor policyEditor) {
		for (String expr : script.split(";")) {
			String exprPrim = expr.substring(0,expr.indexOf("("));
			for (String prim : primitives) {
				if (exprPrim.equals(prim)) {
					triggerMethod(policyEditor, parseExpressionArguments(expr),
							prim);
				}
			}
		}
	}

	public java.lang.Object[] parseExpressionArguments(String expr) {
		String txt = expr.substring(expr.indexOf("(") + 1, expr.indexOf(")"));
		java.lang.Object[] args = new java.lang.Object[txt.split(",").length];
		int cpt = 0;
		for (String arg : txt.split(",")) {
			args[cpt] = arg;
			cpt=cpt+1;
		}
		return args;
	}

	public void triggerMethod(java.lang.Object o, java.lang.Object[] args, String nomMethode) {
		Class[] paramTypes = null;
		if (args != null) {
			paramTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				paramTypes[i] = args[i].getClass();
			}
		}
		Method m = null;
		try {
			m = o.getClass().getMethod(nomMethode, paramTypes);
			m.setAccessible(true);
			m.invoke(o, args);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(m.getName());
			for (int i = 0; i < args.length; i++) {
				System.out.println(paramTypes[i].getSimpleName());
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
