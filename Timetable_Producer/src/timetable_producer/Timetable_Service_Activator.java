package timetable_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Timetable_Service_Activator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;


	public void start(BundleContext context) throws Exception {
		
		System.out.println("Timetable Service Publisher Start");
		TimetableService publisherService = new DailyTimetable();
		
		publishServiceRegistration = context.registerService(TimetableService.class.getName(), publisherService, null);
		
			
			
		
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Puvlisher Stop");
		publishServiceRegistration.unregister();
		
		
	}

}