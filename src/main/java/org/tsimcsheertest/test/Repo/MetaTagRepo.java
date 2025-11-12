package org.tsimcsheertest.test.Repo;

import org.springframework.data.repository.Repository;
import org.tsimcsheertest.test.Models.MetaTag;
import org.tsimcsheertest.test.Models.UserLearningKey;

public interface MetaTagRepo extends Repository<MetaTag, UserLearningKey> {
}
