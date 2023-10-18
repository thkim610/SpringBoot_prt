package me.devKim.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.devKim.blog.domain.Article;
import me.devKim.blog.dto.AddArticleRequest;
import me.devKim.blog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 기능이 잘 수행되는지 체크하는 테스트 케이스를 작성.
 */
@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; //직렬화(자바 내부 -> 외부 데이터(JSON)), 역직렬화(JSON->자바 내부)를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    //글 작성 테스트
    @DisplayName("addArticle: 블로그 글 추가에 성공")
    @Test
    public void addArticle() throws Exception{
        //given :블로그 글 추가를 위한 요청 객체를 만든다.
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        //객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when :블로그 글 추가 API에 요청을 보낸다. 요청타입 : JSON
        //설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody));

        //then :응답코드가 201 create 인지 확인. 블로그를 전체 조회하여 크기가 1인지 확인하고 db 값과 비교.
        result.andExpect(status().isCreated()); //응답코드가 201 create인지 확인.

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1); //크기가 1인지 검증
        assertThat(articles.get(0).getTitle()).isEqualTo(title); //title이 맞는지 확인.
        assertThat(articles.get(0).getContent()).isEqualTo(content); //content가 맞는지 확인.


    }

    //글 목록 조회 테스트
    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공")
    @Test
    public void findAllArticles() throws Exception{
        //given :블로그 글을 저장한다.
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        //save 메서드로 글 저장 (빌트 패턴으로 저장)
        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when :블로그 글 목록 조회 API를 호출한다.
        //설정한 내용을 바탕으로 요청 전송
        final ResultActions result = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then :응답코드가 200 ok 인지 확인. 반환 받은 값 중에 0번쨰 요소들이 저장된 값들과 맞는지 비교.
        result.andExpect(status().isOk()) //응답코드가 200 ok인지 확인.
                .andExpect(jsonPath("$[0].content").value(content))//content가 맞는지 확인.
                .andExpect(jsonPath("$[0].title").value(title));//title이 맞는지 확인.

    }

    //블로그 글 조회 테스트
    @DisplayName("findArticle: 블로그 글 조회에 성공")
    @Test
    public void findArticle() throws Exception{
        //given :블로그 글을 저장한다.
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        //save 메서드로 글 저장 (빌트 패턴으로 저장)
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when :저장한 블로그 글의 id 값으로 API를 호출한다.
        final ResultActions result = mockMvc.perform(get(url, savedArticle.getId()));


        //then :응답코드가 200 ok 인지 확인. 반환 받은 값들이 저장된 값들과 맞는지 비교.
        result.andExpect(status().isOk()) //응답코드가 200 ok인지 확인.
                .andExpect(jsonPath("$.content").value(content))//content가 맞는지 확인.
                .andExpect(jsonPath("$.title").value(title));//title이 맞는지 확인.

    }

}