package company.HibernateAndORM.HomeWork_Six.src.main.java.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDatabaseSessionFactory implements DatabaseSessionFactory {
    private String configurationFile;

    public HibernateDatabaseSessionFactory(String configurationFile) {
        this.configurationFile = configurationFile;
    }

    @Override
    public Session openSession() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(configurationFile)
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return sessionFactory.openSession();
    }
}
