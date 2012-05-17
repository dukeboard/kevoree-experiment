package policyTools.transformations;
import policy.*;
import policy.impl.*;
import grapho.*;
import grapho.impl.NodeImpl;
public class Policy2GraphO{
	private Policy policy;
	public Policy2GraphO(Policy x) {
		policy = x;
	}
	public GraphO transformation() {
		GraphoFactory factory = GraphoFactory.eINSTANCE;
		GraphO g = factory.createGraphO();
		// adding simple nodes
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
			if (e instanceof RoleImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
			if (e instanceof PermissionImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
			
			if (e instanceof ObjectImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
		}

		// adding relationships
		for (PolicyElement e : policy.getElements()) {
			// user->assignedRoles
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getRoles()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + r.getName());
					ed.setColor("black");
					ed.setStyle("solid");
					ed.setConstraintRank(true);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, r.getName()));
					g.getElements().add(ed);
				}
			}

			// user->user
			if (e instanceof UserImpl) {
				for (User u : ((User) e).getDelegatees()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + u.getName());
					ed.setColor("black");
					ed.setStyle("dashed");
					ed.setConstraintRank(false);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, u.getName()));
					g.getElements().add(ed);
				}
			}

			// role->assignedPermissions
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + p.getName());
					ed.setColor("black");
					ed.setStyle("solid");
					ed.setConstraintRank(true);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, p.getName()));
					g.getElements().add(ed);
				}
			}

			// SSOD role->role
			if (e instanceof RoleImpl) {
				for (Role r : ((Role) e).getSsod()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + r.getName());
					ed.setColor("red");
					ed.setStyle("dashed");
					ed.setConstraintRank(false);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, r.getName()));
					g.getElements().add(ed);
				}
			}

			// permission->operation->object
			if (e instanceof PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					Node n = factory.createNode();
					n.setColor("black");
					n.setLabel(o.getName());
					n.setName(e.getName() + o.getName());
					n.setShape("ellipse");
					n.setStyle("solid");
					g.getElements().add(n);

					Edge ed = factory.createEdge();
					ed.setName(e.getName() + o.getName());
					ed.setColor("black");
					ed.setStyle("solid");
					ed.setConstraintRank(true);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(n);
					g.getElements().add(ed);
				}
			}

			//permission operation -> object
			if (e instanceof PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					for(policy.Object ob : o.getObjects()){
						Edge edge = factory.createEdge();
						edge.setName(e.getName() + ob.getName());
						edge.setColor("black");
						edge.setStyle("solid");
						edge.setConstraintRank(true);
						edge.setNodeA(getNodeByName(g, e.getName() + o.getName()));
						edge.setNodeB(getNodeByName(g,ob.getName()));
						g.getElements().add(edge);
						
					}
				}
			}

		}
	    return g;
	}
	
	
	public Node getNodeByName(GraphO g, String nodeName) {
		Node res = null;
		for (GraphElement e : g.getElements()) {
			if (e instanceof NodeImpl && e.getName().equals(nodeName)) {
				res = (Node) e;
			}
		}
		return res;
	}
}