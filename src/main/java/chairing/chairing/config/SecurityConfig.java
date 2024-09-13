package chairing.chairing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import chairing.chairing.domain.user.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 권한에 따른 경로 접근 설정
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                // Enum을 사용하여 권한에 따른 경로 접근 설정
                .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name())  // ROLE_ADMIN
                .requestMatchers("/guardian/**").hasRole(UserRole.GUARDIAN.name())  // ROLE_GUARDIAN
                .requestMatchers("/normal/**").hasRole(UserRole.NORMAL.name())  // ROLE_NORMAL
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated())  // 그 외의 모든 요청은 인증 필요
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/h2-console/**"))  // H2 콘솔에 대한 CSRF 비활성화
            .headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))  // H2 콘솔 사용을 위한 헤더 설정
            .formLogin((formLogin) -> formLogin
                .loginPage("/user/login")  // 로그인 페이지 경로 설정
                .defaultSuccessUrl("/", true)  // 로그인 성공 시 기본적으로 홈으로 리다이렉트
                .permitAll())  // 로그인 페이지는 누구나 접근 허용
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/user/login?logout")
                .permitAll());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
