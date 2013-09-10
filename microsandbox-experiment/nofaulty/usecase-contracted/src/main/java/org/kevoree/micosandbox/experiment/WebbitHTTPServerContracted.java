package org.kevoree.micosandbox.experiment;

import org.kevoree.annotation.ComponentType;
import org.kevoree.library.javase.http.webbit.WebbitHTTPServer;
import org.kevoree.microsandbox.api.contract.CPUContracted;
import org.kevoree.microsandbox.api.contract.MemoryContracted;
import org.kevoree.microsandbox.api.contract.NetworkContracted;
import org.kevoree.microsandbox.api.contract.ThroughputContracted;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 05/09/13
 * Time: 11:10
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@ComponentType
public class WebbitHTTPServerContracted extends WebbitHTTPServer implements CPUContracted,MemoryContracted,NetworkContracted,ThroughputContracted {
}