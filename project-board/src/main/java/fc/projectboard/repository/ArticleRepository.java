package fc.projectboard.repository;

import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import fc.projectboard.domain.Article;
import fc.projectboard.domain.QArticle;
import fc.projectboard.domain.QArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {//기본 검색기능 추가해줌{

    Page<Article> findByTitle(String title, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {

        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);//
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);//
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);//
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);//
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);//
    }
}