package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private static ThreadLocal session = new ThreadLocal();

    private HibernateUtil() {}
    
    static {
    	 Configuration config = new Configuration().configure();
    	 StandardServiceRegistryBuilder rebuilder = new StandardServiceRegistryBuilder()
    	 			.applySettings(config.getProperties());
    	 ServiceRegistry serviceRegistry = rebuilder.build();
    	 sessionFactory = config.buildSessionFactory(serviceRegistry);
    }

    public static Session getThreadLocalSession() {
    	Session s = (Session)session.get();
    	if (s==null) {
    		s = sessionFactory.openSession();
    		session.set(s);
    	}
    	return s;
    }
	
    public static void closeSession() {
    	Session s = (Session)session.get();
    	if (s!=null) {
    		s.close();
    		session.set(null);
    	}
    }
}
