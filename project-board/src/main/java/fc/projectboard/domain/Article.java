package fc.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article extends AuditingField{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Setter @Column(nullable = false)
    private String title; // 게시글 제목
    @Setter @Column(nullable = false,length = 5555)
    private String content; // 게시글 내용
    @Setter
    private String hashtag;// 해시태그

    @OrderBy("createdAt DESC")//정렬 기준 시간순
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)// 기본이름을 두엔티티 이름을 합치기때문에 방지하려고 넣어줌.
    @ToString.Exclude// 양방향 순환참조 방지
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() {
    }
    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
    public static Article of(UserAccount userAccount,String title, String content, String hastag) {
        return new Article(userAccount,title, content, hastag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null & id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
