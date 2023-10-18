package me.devKim.blog.controller;

import lombok.RequiredArgsConstructor;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.AddArticleRequest;
import me.devKim.blog.dto.AddArticleResponse;
import me.devKim.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;


    @PostMapping("/api/articles")//HTTP 메서드가 POST일 때, 전달받은 URL과 동일하면 메서드로 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){// @RequestBody로 요청 본문 값에 매핑
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
}
