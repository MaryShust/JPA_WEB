package beans;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class MBeanManager {
    private static final Map<Class<?>, ObjectName> beans = new HashMap<>();

    private static final MissRatio missRatioMBean = new MissRatio();
    private static final Attempts attemptsMBean = new Attempts();

    public static MissRatio getMissRatioMBean() {
        return missRatioMBean;
    }

    public static Attempts getAttemptsMBean() {
        return attemptsMBean;
    }

    public static void registerBean(Object bean, String name) {
        try {
            register(bean, name);
        } catch (InstanceAlreadyExistsException ex) {
            unregisterBean(bean);
            try {
                register(bean, name);
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void register(Object bean, String name) throws InstanceAlreadyExistsException {
        try {
            String domain = bean.getClass().getPackageName();
            ObjectName objectName = new ObjectName(String.format("%s:type=%s", domain, name));
            ManagementFactory.getPlatformMBeanServer().registerMBean(bean, objectName);
            beans.put(bean.getClass(), objectName);
        } catch (MBeanRegistrationException | NotCompliantMBeanException |
                 MalformedObjectNameException ex) {
            ex.printStackTrace();
        }
    }

    public static void unregisterBean(Object bean) {
        if (!beans.containsKey(bean.getClass())) {
            throw new IllegalArgumentException("Specified bean is not registered.");
        }

        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(beans.get(bean.getClass()));
        } catch (InstanceNotFoundException | MBeanRegistrationException ex) {
            ex.printStackTrace();
        }
    }
}
