package org.kevoree.micosandbox.experiment;

import org.kevoree.annotation.GroupType;
import org.kevoree.library.BasicGroup;
import org.kevoree.microsandbox.api.contract.CPUContracted;
import org.kevoree.microsandbox.api.contract.MemoryContracted;
import org.kevoree.microsandbox.api.contract.NetworkContracted;
import org.kevoree.microsandbox.api.contract.ThroughputContracted;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 05/09/13
 * Time: 11:12
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@GroupType
public class BasicGroupContracted extends BasicGroup implements CPUContracted,MemoryContracted,NetworkContracted,ThroughputContracted {
}
