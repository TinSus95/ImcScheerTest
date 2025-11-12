package org.tsimcsheertest.test.Repo;

import org.springframework.data.repository.Repository;
import org.tsimcsheertest.test.Models.LearningComponent;

public interface LearningComponentRepo extends Repository<LearningComponent, String> {
    public LearningComponent findById(String id);
}
