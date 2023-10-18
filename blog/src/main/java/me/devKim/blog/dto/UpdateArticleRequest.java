package me.devKim.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 블로그 글 수정 요청을 받을 DTO
 */
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 인자를 가지고 만드는 생성자
@Getter //게터 생성
public class UpdateArticleRequest {
    private String title;
    private String content;
}
