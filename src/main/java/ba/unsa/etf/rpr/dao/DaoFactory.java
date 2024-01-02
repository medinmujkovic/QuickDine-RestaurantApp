package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final MenuDao menuDao = MenuDaoSQLImpl.getInstance();
    private static final OrderDao orderDao = OrderDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final RolesDao rolesDao = RolesDaoSQLImpl.getInstance();
    private static final StatusDao statusDao = StatusDaoSQLImpl.getInstance();
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

    public static RolesDao rolesDao(){
        return rolesDao;
    }

    public static StatusDao statusDao(){
        return statusDao;
    }

}