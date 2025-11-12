package org.tsimcsheertest.test.Repo;

import org.springframework.data.repository.Repository;
import org.tsimcsheertest.test.Models.User;

import java.util.Optional;

public interface UserRepo extends Repository<User, Long>{
    public Optional<User> findByUsernameAndPassword(String username, String password);
}
