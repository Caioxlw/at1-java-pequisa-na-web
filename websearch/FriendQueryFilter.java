public class FriendQueryFilter implements QueryFilter {
    @Override
    public boolean accept(String query) {
        return query.toLowerCase().contains("friend");
    }
}