package company.HibernateAndORM.HomeWork_Six.src.main.java.factory;

import org.hibernate.Session;

public interface DatabaseSessionFactory {
    Session openSession();
}
