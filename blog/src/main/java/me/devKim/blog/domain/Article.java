package me.devKim.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity //엔티티로 지정
@Getter //Getter 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 만들어 줌. (protected 접근제어)
public class Article {

    @Id //id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)//컬럼: 'title' / Not null
    private String title;

    @Column(name = "content", nullable = false)//컬럼: 'content' / Not null
    private String content;

    @CreatedDate //엔티티가 생성 될 때 생성시간 저장.
    @Column(name = "created_at") //컬럼: 'created_at'
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티가 수정될 때 수정시간 저장.
    @Column(name = "updated_at") //컬럼: 'updated_at'
    private LocalDateTime updatedAt;


    @Builder //빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //블로그 글 수정 기능
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
