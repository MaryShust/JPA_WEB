package beans;

import jakarta.servlet.ServletContextListener;
import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class MBeanRegistry implements ServletContextListener {
    private static final Map<Class<?>, ObjectName> beans = new HashMap<>();


    public static void registerBean(Object bean, String name) {
        try {
            String domain = bean.getClass().getPackageName();
            String type = bean.getClass().getSimpleName();
            System.out.println("MBeanRegistry registerBean domain = " + domain + "; type = " + type + ";");

            ObjectName objectName = new ObjectName(String.format("%s:type=%s", domain, name));

            System.out.println("MBeanRegistry registerBean objectName = " + objectName + ";");
            ManagementFactory.getPlatformMBeanServer().registerMBean(bean, objectName);
//            beans.put(bean.getClass(), objectName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException |
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

    public static void updStat(MissRatioMBean hitRatioMBean) {
//        // Создайте метрики
//        totalAttempts = Counter.build().namespace(NAMESPACE).name(TOTAL_ATTEMPTS).help("Total attempts").register();
//        totalHits = Counter.build().namespace(NAMESPACE).name(TOTAL_HITS).help("Total hits").register();
//        hitRatio = Gauge.build().namespace(NAMESPACE).name(HIT_RATIO).help("Hit ratio").register();
//
//        try {
//            HTTPServer server = new HTTPServer(8000);
//        } catch (IOException e) {
//            System.err.println("FAIL START SERER: " + e.getMessage());
//        }
//
//        // Обновляйте метрики каждую секунду
//        while (true) {
//            totalAttempts.inc(hitRatioMBean.getTotalAttempts());
//            totalHits.inc(hitRatioMBean.getTotalHits());
//            hitRatio.set(hitRatioMBean.getHitRatio());
//            System.out.println("UPD 1 = " + hitRatioMBean.getTotalAttempts() + "; 2 = " + hitRatioMBean.getTotalHits() + "; 3 = "+ hitRatioMBean.getHitRatio() + ";");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.err.println("Ошибка при ожидании: " + e.getMessage());
//            }
//        }
    }

}