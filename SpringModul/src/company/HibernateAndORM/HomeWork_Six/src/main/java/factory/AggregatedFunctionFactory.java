package company.HibernateAndORM.HomeWork_Six.src.main.java.factory;

public class AggregatedFunctionFactory implements QuerySelectionFactory {
    @Override
    public QuerySelection createQuery() {
        return new AggregatedFunction();
    }
}
