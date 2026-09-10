public class LongQueryFilter implements QueryFilter {
    @Override
    public boolean accept(String query) {
        return query.length() > 60;
    }
}