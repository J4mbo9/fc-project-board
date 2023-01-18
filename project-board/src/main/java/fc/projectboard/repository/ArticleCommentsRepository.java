package fc.projectboard.repository;

import fc.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentsRepository extends JpaRepository<ArticleComment,Long> {
}
