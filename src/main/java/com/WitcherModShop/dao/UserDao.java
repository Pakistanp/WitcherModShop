package com.WitcherModShop.dao;

import com.WitcherModShop.model.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
    DAOUser save(DAOUser u);
}
