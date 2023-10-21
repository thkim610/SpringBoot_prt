package me.devKim.blog.controller;

import lombok.RequiredArgsConstructor;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.ArticleListViewResponse;
import me.devKim.blog.dto.ArticleViewResponse;
import me.devKim.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    //"/articles" GET 요청
    // 블로그 글 목록 리스트를 모델에 담아서 전달
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); //모델에 블로그 글리스트 저장.
        return "articleList"; // articleList.html 뷰 이름 반환. (resource/templates/articleList.html를 찾음.)
    }

    //"/articles/{id} GET 요청
    // 블로그 글을 모델에 담아서 전달
    @GetMapping("/articles/{id}")
    public String getArticle(Model model, @PathVariable Long id){ //url요청으로 넘어온 인자(id)를 받음.
        Article article = blogService.findById(id); //넘어온 인자 값으로 블로그 글 조회
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
