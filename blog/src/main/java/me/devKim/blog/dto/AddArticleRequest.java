package me.devKim.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devKim.blog.domain.Article;

/**
 * DTO : 계층끼리 데이터를 교화하기 위해 사용하는 객체
 *      단순하게 데이터를 옮기기 위해 사용하는 전달체
 */
@NoArgsConstructor //기본 생성자 추가
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 추가
@Getter //게터 생성
public class AddArticleRequest {

    private String title;
    private String content;

    //빌더 패턴을 사용해 DTO를 엔티티로 만들어주는 메서드
    //생성자를 사용해서 객체를 생성.
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
