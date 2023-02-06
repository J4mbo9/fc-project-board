package fc.projectboard.dto;

import fc.projectboard.domain.Article;
import fc.projectboard.domain.ArticleComment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link fc.projectboard.domain.ArticleComment} entity
 */
public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String content) {

    public static ArticleCommentDto of(Long id,
                                       Long articleId,
                                       UserAccountDto userAccountDto,
                                       LocalDateTime createdAt,
                                       String createdBy,
                                       LocalDateTime modifiedAt,
                                       String modifiedBy,
                                       String content) {

        return new ArticleCommentDto(id,articleId,userAccountDto,createdAt, createdBy, modifiedAt, modifiedBy, content);
    }

    public static ArticleCommentDto from(ArticleComment entity){

        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getContent()
        );
    }

    public ArticleComment toEntity(Article entity){
        return ArticleComment.of(
                entity,
                userAccountDto.toEntity(),
                content
        );

    }
}