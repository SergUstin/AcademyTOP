package company.HibernateAndORM.HomeWork_Six.src.main.java.factory;

public class JoinQueryFactory implements QuerySelectionFactory {
    @Override
    public QuerySelection createQuery() {
        return new JoinQuery();
    }
}
