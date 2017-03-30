package mainpackage;

import ont.MySensorOntology;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;
import org.universAAL.middleware.owl.Ontology;
import org.universAAL.middleware.owl.OntologyManagement;

import context.SensorPublisher;
import context.SensorStateSubscriber;


public class Activator implements BundleActivator {
	public static ModuleContext mContext = null;

	private Ontology mySensorOnt = new MySensorOntology();
	private SensorPublisher pub;
	private SensorStateSubscriber sub;
	

	public void start(final BundleContext bcontext) throws Exception {
		mContext = uAALBundleContainer.THE_CONTAINER.registerModule(new Object[] {bcontext});
		OntologyManagement.getInstance().register(mContext, mySensorOnt);
		System.out.println("Hello World!");
		sub = new SensorStateSubscriber(mContext);
		pub = new SensorPublisher(mContext);
		pub.publishContextEvent();		
	}

	public void stop(BundleContext arg0) throws Exception {
		OntologyManagement.getInstance().unregister(mContext, mySensorOnt);
		pub.close();
		sub.close();
	}

}
