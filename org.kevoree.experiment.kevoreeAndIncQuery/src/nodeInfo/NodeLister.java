package nodeInfo;

import java.io.IOException;

import kevoree.ContainerRoot;
import kevoree.KevoreePackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import patternbuilders.nodeInfo.PatternBuilderFornode;
import patternmatchers.nodeInfo.NodeMatcher;
import signatures.nodeInfo.NodeSignature;

public class NodeLister {

	public NodeLister() {

	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws IncQueryRuntimeException
	 */
	public static void main(String[] args) throws IOException,
			IncQueryRuntimeException {

		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				NodeMatcher.FACTORY.getPatternName(),
				new PatternBuilderFornode());

		ResourceSet rs = new ResourceSetImpl();
		rs.getPackageRegistry().put(KevoreePackage.eNS_URI,
				KevoreePackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());

		Resource resource = rs.createResource(URI
				.createFileURI("models/boukiki.xmi"));
				//.createFileURI("models/ContainerRoot.xmi"));
		resource.load(null);

		ContainerRoot rootElement = (ContainerRoot) resource.getContents().get(
				0);

		NodeMatcher matcher = NodeMatcher.FACTORY.getMatcher(rootElement);

		matcher.countMatches();
		System.out.println("number of nodes : "+matcher.countMatches());
		System.out.println();
		for(NodeSignature sig : matcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfX());
		}
		
		final DeltaMonitor<NodeSignature> mon = matcher.newDeltaMonitor(true);
		matcher.addCallbackAfterUpdates(new Runnable() {

			@Override
			public void run() {
				for (NodeSignature x : mon.matchFoundEvents) {
					System.out.println(x.getValueOfX());
				}
				mon.clear();

			}
		});

		for (NodeSignature x : mon.matchFoundEvents) {
			System.out.println(x.getValueOfX());
		}
	}

}
