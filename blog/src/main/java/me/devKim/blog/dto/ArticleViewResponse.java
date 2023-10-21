package me.devKim.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devKim.blog.domain.Article;

import java.time.LocalDateTime;

/**
 * 뷰에게 데이터를 전달하기 위한 객체(DTO)
 */
@NoArgsConstructor
@Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
