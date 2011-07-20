package org.kevoree.experiment.smartbuilding;
import java.util.Hashtable
import org.kevoree.api.service.core.handler.KevoreeModelHandlerService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.util.tracker.ServiceTracker
import org.kevoree.framework.KevoreeActor
import org.kevoree.framework._
import org.kevoree.framework.KevoreeComponent
class LBroadCActivator extends org.kevoree.framework.osgi.KevoreeChannelFragmentActivator {
def callFactory() : org.kevoree.framework.KevoreeChannelFragment = { LBroadCFactory.createChannel() } }
