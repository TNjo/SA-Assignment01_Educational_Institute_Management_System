package teacher_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class TeacherActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceRegistration publishServiceRegistration;
	
    public void start(BundleContext context) throws Exception {
		
		System.out.println("Teacher Producer Start");
	
		TeacherService teacherService = new TeacherServiceImpl();
		
		publishServiceRegistration = context.registerService(TeacherService.class.getName(), teacherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Teacher Publisher Stop");
		publishServiceRegistration.unregister();
	}
}
