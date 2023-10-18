package me.devKim.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devKim.blog.domain.Article;

/**
 * DTO : 계층끼리 데이터를 교화하기 위해 사용하는 객체
 *      단순하게 데이터를 옮기기 위해 사용하는 전달체
 */

@Getter //게터 생성
public class AddArticleResponse {

    private final String title;
    private final String content;

    //생성자를 사용해서 객체를 생성.
    public AddArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
