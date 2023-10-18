package me.devKim.blog.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.AddArticleRequest;
import me.devKim.blog.dto.UpdateArticleRequest;
import me.devKim.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //블로그 글 조회
    public Article findById(long id){
        //jpa에서 제공하는 findByid메서드를 사용해 ID를 받아서 엔티티를 조회
        //id가 존재하지 않으면, IllegalArgumentException을 발생시킴.
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: "+ id));
    }

    //블로그 글 삭제
    public void delete(long id){
        //jpa에서 제공하는 deleteById메서드를 사용해 ID를 받아서 엔티티를 삭제
        blogRepository.deleteById(id);
    }

    //블로그 글 수정
    /*
    @Transactional : 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할을 한다.
     */
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        //jpa에서 제공하는 findByid메서드를 사용해 ID를 받아서 엔티티를 조회
        //id가 존재하지 않으면, IllegalArgumentException을 발생시킴.
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found : "+id));

        //브라우저로부터 받은 요청 정보로 블로그 글 수정 메서드 호출.
        article.update(request.getTitle(), request.getContent());

        return article;
    }


}
