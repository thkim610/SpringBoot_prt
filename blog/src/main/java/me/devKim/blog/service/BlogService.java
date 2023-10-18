package me.devKim.blog.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.AddArticleRequest;
import me.devKim.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor //final 키워드가 붙거나 @Not null이 붙은 필드의 생성자 추가.
@Service //빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }


}
