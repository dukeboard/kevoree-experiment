package transformations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import rbac.Policy;
import rbac.RbacFactory;
import rbacTools.editor.PolicyEditor;

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
			System.out.println(m.getName());
			primitives.add(m.getName());
		}
	}

	public Policy transformation() {
		Policy policy = RbacFactory.eINSTANCE.createPolicy();
		PolicyEditor policyEditor = new PolicyEditor(policy);
		parseExpressions(policyEditor);
		System.out.println(policy.getElements().size());
		return policy;
	}
	
	public void transformation(Policy policy) {
		PolicyEditor policyEditor = new PolicyEditor(policy);
		parseExpressions(policyEditor);
		System.out.println(policy.getElements().size());
	}

	public void parseExpressions(PolicyEditor policyEditor) {
		for (String expr : script.split(";")) {
			for (String prim : primitives) {
				if (expr.contains(prim)) {
					triggerMethod(policyEditor, parseExpressionArguments(expr),
							prim);
				}
			}
		}
	}

	public Object[] parseExpressionArguments(String expr) {
		String txt = expr.substring(expr.indexOf("(") + 1, expr.indexOf(")"));
		Object[] args = new Object[txt.split(",").length];
		int cpt = 0;
		for (String arg : txt.split(",")) {
			args[cpt] = arg;
		}
		return args;
	}

	public void triggerMethod(Object o, Object[] args, String nomMethode) {
		Class[] paramTypes = null;
		if (args != null) {
			paramTypes = new Class[args.length];
			for (int i = 0; i < args.length; ++i) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
