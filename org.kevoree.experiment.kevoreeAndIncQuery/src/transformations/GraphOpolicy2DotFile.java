package transformations;

import grapho.Edge;
import grapho.GraphElement;
import grapho.GraphO;
import grapho.GraphoFactory;
import grapho.GraphoPackage;
import grapho.Node;
import grapho.impl.EdgeImpl;
import grapho.impl.NodeImpl;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import utils.graphStructure.dotThings.DotDisplayer;
import utils.writer.FileWriterO;


public class GraphOpolicy2DotFile {
	
	private ResourceSet resourceSetMetamodel;
	private Resource resourceModel;
	private GraphO grapho;
	
	public GraphOpolicy2DotFile(GraphO g){
		grapho =g;
	}
	
	public void loadGraphOModel(String path) {
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(GraphoPackage.eNS_URI,
				GraphoPackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		// LOAD THE MODEL
		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI(path));
		try {
			resourceModel.load(null);
		} catch (IOException e) {
			System.out.println("error during the model loading step");
			e.printStackTrace();
		}
		// INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL
		grapho = (GraphO) resourceModel.getContents().get(0);
		for (GraphElement e : grapho.getElements()) {
			System.out.println(e.getName());
		}
	}
	
	public File transformation(String path) {
		String output = "";
		output = output + "digraph G { \n";	

		for (GraphElement e : grapho.getElements()) {
			if (e instanceof NodeImpl){
				output = output + e.getName()+ " [color=" + ((Node)e).getColor() + " shape=" + ((Node)e).getShape()+" style=" + ((Node)e).getStyle() + " label=" +((Node)e).getLabel()+"  ];\n";
			}
		}
		for (GraphElement e : grapho.getElements()) {
			if (e instanceof EdgeImpl){
				output = output +  ((Edge)e).getNodeA().getName() + " -> " + ((Edge)e).getNodeB().getName() + "[constraint=" + ((Edge)e).isConstraintRank() +  " color=" + ((Edge)e).getColor()+" style=" + ((Edge)e).getStyle() + "]" + ";\n";
			}
		}
		output = output + "}";
		
		FileWriterO fw = new FileWriterO();
		File f = fw.writeStringOnFile(output, path);
		return f;
	}
	
	
	
}
