package fc.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link fc.projectboard.domain.Article} entity
 */
public record ArticleUpdateDto(
        String title,
        String content,
        String hashtag
) {
    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}