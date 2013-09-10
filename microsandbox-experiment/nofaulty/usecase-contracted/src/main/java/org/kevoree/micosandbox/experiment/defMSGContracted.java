package org.kevoree.micosandbox.experiment;

import org.kevoree.annotation.ChannelType;
import org.kevoree.library.defaultChannels.defMSG;
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
@ChannelType
public class defMSGContracted extends defMSG implements CPUContracted,MemoryContracted,NetworkContracted,ThroughputContracted {
}
