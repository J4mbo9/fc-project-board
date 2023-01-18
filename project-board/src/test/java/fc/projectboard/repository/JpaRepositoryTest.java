package fc.projectboard.repository;

import fc.projectboard.config.JpaConfig;
import fc.projectboard.domain.Article;
import fc.projectboard.domain.ArticleComment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)//직접 만든거라 못읽어서 넣어줌
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentsRepository articleCommentsRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentsRepository articleCommentsRepository) {

        this.articleRepository = articleRepository;
        this.articleCommentsRepository = articleCommentsRepository;
    }
    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        //given
        //when

        List<Article> articles = articleRepository.findAll();
        //then
        Assertions.assertThat(articles)
                .isNotNull()
                .hasSize(100);
    }
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        //given

        long previousCount = articleRepository.count();
        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));
        //then
        Assertions.assertThat(articleRepository.count())
                .isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){

        //given

        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);
        //then
        Assertions.assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){

        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long count = articleRepository.count();

        //when
        articleRepository.delete(article);
        //then
        Assertions.assertThat(articleRepository.count()).isEqualTo(count-1);
    }
}