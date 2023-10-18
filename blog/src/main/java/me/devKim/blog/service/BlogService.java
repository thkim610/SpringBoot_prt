package me.devKim.blog.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.AddArticleRequest;
import me.devKim.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor //final 키워드가 붙거나 @Not null이 붙은 필드의 생성자 추가.
@Service //빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 작성
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    //블로그 글 목록 조회
    public List<Article> findAll(){
        return blogRepository.findAll(); //jpa레포에서 지원하는 findAll메서드 호출
    }


}
