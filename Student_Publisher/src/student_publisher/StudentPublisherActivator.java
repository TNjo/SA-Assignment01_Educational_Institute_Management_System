package student_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class StudentPublisherActivator implements BundleActivator {
	
	ServiceRegistration publishServiceRegistration;


	
public void start(BundleContext context) throws Exception {
		
		System.out.println("Student Producer Start");
		StudentService studentService = new StuentServiceImpl();
		
		publishServiceRegistration = context.registerService(StudentService.class.getName(), studentService, null);
		
}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Student Publisher Stop");
		publishServiceRegistration.unregister();
			

	}

}