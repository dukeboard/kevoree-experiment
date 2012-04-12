package addressBook.enforcement;

public class RbacRule {
	
	private String user,role,permission,operation,object;	
	public RbacRule(String u,String r,String p,String op,String ob){
		user=u; role =r;permission=p;operation=op;object =ob;
	}
	public String toString(){
		String res = user+":"+role+":"+permission+":"+operation+":"+":"+object;
		return res;
	}
	
}
