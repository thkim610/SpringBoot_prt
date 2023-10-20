package me.devKim.blog.dto;

import lombok.Getter;
import me.devKim.blog.domain.Article;

/**
 * 뷰에게 데이터를 전달하기 위한 객체(DTO)
 */
@Getter
public class ArticleListViewResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
