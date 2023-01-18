package fc.projectboard.repository;

import fc.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentsRepository extends JpaRepository<ArticleComment,Long> {
}
