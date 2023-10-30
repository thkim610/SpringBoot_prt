package me.devKim.blog.service;

import lombok.RequiredArgsConstructor;
import me.devKim.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
//UserDetailsService : 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    //사용자 이름(email)으로 사용자 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException(email));//값이 정확하지 않으면 예외 처리
    }
}
