package org.kevoree.library.hocl;

import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.Group;
import org.kevoree.annotation.GroupType;
import org.kevoree.annotation.Library;
import org.kevoree.framework.Constants;
import org.kevoree.framework.KevoreePropertyHelper;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.library.nanohttp.NanoRestGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Option;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 12/04/12
 * Time: 13:57
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@Library(name = "HOCL")
@GroupType
public class HoclNanoRestGroup extends NanoRestGroup {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void triggerModelUpdate () {
		Group group = getModelElement();
		for (ContainerNode subNode : group.getSubNodesForJ()) {
			if (!subNode.getName().equals(this.getNodeName())) {
				internalPush(getModelService().getLastModel(), subNode.getName(), this.getNodeName());
			}
		}
	}

	private void internalPush (ContainerRoot model, String targetNodeName, String sender) {
		List<String> ips = KevoreePropertyHelper.getStringNetworkProperties(model, targetNodeName, Constants.KEVOREE_PLATFORM_REMOTE_NODE_IP());
		Option<Integer> portOption = KevoreePropertyHelper.getIntPropertyForGroup(model, this.getName(), "port", true, targetNodeName);
		int PORT = 8000;
		if (portOption.isDefined()) {
			PORT = portOption.get();
		}
		boolean sent = false;
		for (String ip : ips) {
			logger.debug("try to send model on url=>" + "http://" + ip + ":" + PORT + "/model/current?nodesrc=" + sender);
			if (sendModel(model, "http://" + ip + ":" + PORT + "/model/current?nodesrc=" + sender)) {
				sent = true;
				break;
			}
		}
		if (!sent) {
			logger.debug("try to send model on url=>" + "http://127.0.0.1:" + PORT + "/model/current?nodesrc=" + sender);
			if (!sendModel(model, "http://127.0.0.1:" + PORT + "/model/current?nodesrc=" + sender)) {
				logger.debug("Unable to push a model on " + targetNodeName);
			}
		}
	}

	private boolean sendModel (ContainerRoot model, String urlPath) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			KevoreeXmiHelper.saveStream(outStream, model);
			outStream.flush();
			URL url = new URL(urlPath);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(outStream.toString());
			wr.flush();
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = rd.readLine();
			while (line != null) {
				line = rd.readLine();
			}
			wr.close();
			rd.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
