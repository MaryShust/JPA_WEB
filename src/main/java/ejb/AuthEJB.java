package ejb;

import db.AuthDao;
import jakarta.ejb.Stateless;
import model.Users;
import utils.Hash;

@Stateless
public class AuthEJB {

    AuthDao authDao = new AuthDao();

    public Long checkAuth(String login, String password) {
        Long id = authDao.hasInTable(login, password);
        if (id == null) {
            Users user = new Users();
            user.setLogin(login);
            user.setPassword(Hash.SHA(password));
            authDao.createUser(user);
            return user.getId();
        } else {
            return id;
        }
    }
}