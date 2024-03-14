package finance_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class FinanceProducerActivator implements BundleActivator {

    ServiceRegistration publishServiceRegistration;

    public void start(BundleContext context) throws Exception {
        System.out.println("Finance Producer Start");

        FinanceService financeService = new FinanceServiceImpl();

        publishServiceRegistration = context.registerService(FinanceService.class.getName(), financeService, null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Publisher Stop");
        publishServiceRegistration.unregister();
    }

}
