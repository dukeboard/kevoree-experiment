package org.kevoree.library.tools.dpa;

import org.kevoree.ContainerRoot;
import org.kevoree.NamedElement;
import org.kevoree.tools.marShell.ast.Script;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 04/06/13
 * Time: 11:02
- */
public interface DPA {

    java.util.List<java.util.Map<String, NamedElement>> applyPointcut(ContainerRoot myModel);

    Script getASTScript(java.util.Map<java.lang.String,org.kevoree.NamedElement> p);

}
