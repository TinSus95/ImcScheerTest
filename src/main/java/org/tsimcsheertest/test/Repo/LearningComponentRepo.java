package org.tsimcsheertest.test.Repo;

import org.springframework.data.repository.Repository;
import org.tsimcsheertest.test.Models.LearningComponent;

public interface LearningComponentRepo extends Repository<LearningComponent, Long> {
    public LearningComponent findById(Long id);
}
