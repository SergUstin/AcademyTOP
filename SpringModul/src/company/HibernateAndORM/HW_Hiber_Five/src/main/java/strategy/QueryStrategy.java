package company.HibernateAndORM.HW_Hiber_Five.src.main.java.strategy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public interface QueryStrategy {

    void execute(Session session);
}
