package me.devKim.blog.repository;

import me.devKim.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa레포지토리 상속 -> <엔티티(Article), 엔티티(Long=id)>
 * jpa레포지토리의 다양한 메서드를 사용가능.
 */
public interface BlogRepository extends JpaRepository<Article, Long> {
}
