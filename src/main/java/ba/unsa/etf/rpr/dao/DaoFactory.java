package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final MenuDao menuDao = MenuDaoSQLImpl.getInstance();
    private static final OrderDao orderDao = OrderDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final RegisterDao registerDao = RegisterDaoSQLImpl.getInstance();
    private DaoFactory(){
    }

    public static MenuDao menuDao(){
        return menuDao;
    }

    public static OrderDao orderDao(){
        return orderDao;
    }

    public static UserDao userDao(){
        return userDao;
    }

    public static RegisterDao registerDao() {
        return registerDao;
    }
}