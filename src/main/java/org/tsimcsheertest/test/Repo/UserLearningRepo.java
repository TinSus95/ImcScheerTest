package org.tsimcsheertest.test.Repo;

import org.springframework.data.repository.Repository;
import org.tsimcsheertest.test.Models.UserLearning;

import java.util.Optional;

public interface UserLearningRepo extends Repository<UserLearning, Long> {
    public Optional<UserLearning> findByUserIdAndComponentId(Long user_id, String learning_id);
}
