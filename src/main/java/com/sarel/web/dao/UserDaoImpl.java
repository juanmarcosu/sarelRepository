package com.sarel.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sarel.web.model.State;
import com.sarel.web.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void save(User user) {
        persist(user);
    }
	
	public User findById(int id) {
		return getByKey(id);
	}

	public User findBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (User) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsersByRol(String rol){
		String hql = "Select distinct user FROM User user join user.userProfiles p where user.state = '"+State.ACTIVE.getName()+"' and p.type = '"+rol+"'";
		Query query = createQuery(hql);
		return (List<User>) query.list();
	}
	
}
