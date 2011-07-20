package org.kevoree.experiment.smartbuilding;
import java.util.Hashtable
import org.kevoree.api.service.core.handler.KevoreeModelHandlerService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.util.tracker.ServiceTracker
import org.kevoree.framework.KevoreeActor
import org.kevoree.framework.KevoreeComponent
import org.kevoree.framework._
class EmergencyLogActivator extends org.kevoree.framework.osgi.KevoreeComponentActivator {
def callFactory() : KevoreeComponent = { EmergencyLogFactory.createComponentActor() } }
