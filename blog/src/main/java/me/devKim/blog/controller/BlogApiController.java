package me.devKim.blog.controller;

import lombok.RequiredArgsConstructor;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.AddArticleRequest;
import me.devKim.blog.dto.AddArticleResponse;
import me.devKim.blog.dto.UpdateArticleRequest;
import me.devKim.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;


    @PostMapping("/api/articles")//HTTP 메서드가 POST일 때, 전달받은 URL과 동일하면 메서드로 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){// @RequestBody로 요청 본문 값에 매핑
        System.out.println("컨트롤러 찍힘");
        Article savedArticle = blogService.save(request);
    // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송함. (응답코드:201)
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(savedArticle);
    }

    @GetMapping("/api/articles")//HTTP 메서드가 GET일 때, 전달받은 URL과 동일하면 메서드로 매핑
    public ResponseEntity<List<AddArticleResponse>> findAllArticles(){
        /*
        blogService.findAll() : db에서 모든 게시글 정보를 가져옵니다.
        .stream() : 반환된 게시글 목록에 대해 스트림을 생성.
        .map(AddArticleResponse::new) : 각 게시글 정보를 각각 AddArticleResponse 객체로 변환 (title, content)
        .toList() : 각 AddArticleResponse 객체를 리스트로 변환하여 반환.
         */
        List<AddArticleResponse> articles = blogService.findAll().stream()
                .map(AddArticleResponse::new)
                .toList();
        //HTTP 응답코드를 200(ok)로 설정하고, 응답의 본문에 글 목록을 담아 반환.
        return ResponseEntity.ok().body(articles);

    }

    //블로그 글 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<AddArticleResponse> findArticle(@PathVariable long id){  // url에서 {id}에 해당하는 값이 들어옴.
        //@PathVariable: url에서 값을 가져오는 애너테이션.
        Article article = blogService.findById(id);

        //HTTP 응답코드를 200(ok)로 설정하고, 응답의 본문에 해당 id의 글 정보를 담아 반환.
        return ResponseEntity.ok().body(new AddArticleResponse(article));
    }

    //블로그 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    //블로그 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id, request);

        // 요청한 자원이 성공적으로 수정되었으면 수정된 블로그 글 정보를 응답 객체에 담아 전송함. (응답코드:200)
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedArticle);
    }
}
