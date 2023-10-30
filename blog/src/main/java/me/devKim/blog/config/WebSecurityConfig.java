package me.devKim.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    //스프링 시큐리티 기능 비활성화 -> 모든기능을 사용하지 않음.
    @Bean
    public WebSecurityCustomizer configure(){
        return (web)-> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    //특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll() // "/login", "/signup", "/user"으로 오는 요청은 누구나 접근 가능.
                .anyRequest().authenticated() //위의 경로를 제외한 요청은 별도의 인가는 필요 없지만 인증이 성공된 상태여야 한다.
                .and()
                .formLogin()// 폼 기반의 로그인 설정
                .loginPage("/login")
                .defaultSuccessUrl("/articles") // 로그인이 완료되었을 때 이동할 경로
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/login")// 로그아웃이 완료되었을 때 이동할 경로
                .invalidateHttpSession(true) // 로그아웃 이후에 세션을 전체 삭제
                .and()
                .csrf().disable() //csrf 비활성화
                .build();
    }

    //인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService)throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) //사용자정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder) //비밀번호 암호화
                .and()
                .build();
    }

    //패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
