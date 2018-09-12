package dao;

import bean.Administrator;

public interface AdministratorDao {
    Administrator SelectAdminByUsernameAndPassword(Administrator administrator);

    int AddAdministrator(Administrator administrator);

    int UpdatePassword(Administrator administrator);
}
