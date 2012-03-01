package org.kevoree.library.javase.modelProvider;

import java.util.ArrayList;
import java.util.HashMap;

import org.kevoree.framework.KevoreePropertyHelper;

public class KevoreeAnalyzer {

	
	public void analyze(org.kevoree.ContainerRoot cr) {
		HashMap<String, ArrayList<String>> archLocationEntities = new HashMap<String, ArrayList<String>>();
		for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
			for (org.kevoree.ComponentInstance c : n.getComponentsForJ()) {
				if (c.getTypeDefinition().getName().equals("MovableEntity")) {
					String loc = KevoreePropertyHelper
							.getPropertyForComponent(cr, c.getName(),
									"location").get().toString();
					if (!archLocationEntities.containsKey(loc)) {
						archLocationEntities.put(loc, new ArrayList<String>());
					}
					archLocationEntities.get(loc).add(c.getName());
				}
			}
		}
		String res = "";
		for (String s : archLocationEntities.keySet()) {
			res = res + s + " : ";
			for (String me : archLocationEntities.get(s)) {
				res = res + " " + me;
			}
			res = res + "\n";
		}
		System.out.println(res);
	}
	
	
}
