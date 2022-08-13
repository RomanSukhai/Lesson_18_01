package ua.lviv.lgs.service;

import java.sql.SQLException;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User>{
	User getByUseremail(String userEmail) throws SQLException;
	User getbyUserPassword(String password) throws SQLException;
}
