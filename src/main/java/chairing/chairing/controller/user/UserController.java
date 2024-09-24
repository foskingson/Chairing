package chairing.chairing.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chairing.chairing.config.JwtUtil;
import chairing.chairing.domain.user.User;
import chairing.chairing.dto.user.LoginRequest;
import chairing.chairing.dto.user.UserCreateRequest;
import chairing.chairing.service.user.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입 처리
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserCreateRequest userCreateRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation errors occurred");
        }

        try {
            userService.create(userCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed");
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login page placeholder");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 로그인 인증 처리
            Authentication authentication = userService.authenticate(loginRequest);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // JWT 토큰 생성
            String token = jwtUtil.generateToken(userDetails.getUsername());
    
            // 여기서 올바르게 JWT 토큰을 반환해야 함
            System.out.println("Login successful, returning token: " + token);  // 디버깅용 로그 추가
            return ResponseEntity.ok(Collections.singletonMap("token", token));  // 응답에 토큰 포함
        } catch (Exception e) {
            // 예외 발생 시 UNAUTHORIZED 반환
            System.out.println("Login failed due to: " + e.getMessage());  // 예외 메시지 출력
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // 로그아웃 처리 (JWT는 상태 저장 없이, 클라이언트에서 삭제 처리)
            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided, logout failed");
    }
        
    @GetMapping("/current")
    public User getCurrentUser(@RequestHeader("Authorization") String token) {
        // Bearer 토큰에서 JWT 부분만 분리
        String jwt = token.substring(7); // "Bearer " 이후 부분 추출
        User user = jwtUtil.getUserFromToken(jwt); // JWT에서 사용자 정보 추출
        return user; // 현재 사용자 정보 반환
    }

    @GetMapping("/authStatus")
    public ResponseEntity<?> getAuthStatus(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            
            try {
                // JWT가 유효한지 확인
                if (jwtUtil.validateToken(token)) {
                    String username = jwtUtil.extractUsername(token);
                    return ResponseEntity.ok("User authenticated: " + username);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT Token");
                }
            } catch (MalformedJwtException e) {
                // 잘못된 JWT 형식에 대한 처리
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Malformed JWT Token");
            } catch (ExpiredJwtException e) {
                // 만료된 JWT에 대한 처리
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired JWT Token");
            } catch (JwtException e) {
                // 기타 JWT 관련 에러에 대한 처리
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT Token error");
            }
        }

        // Authorization 헤더가 없는 경우
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing");
    }

}