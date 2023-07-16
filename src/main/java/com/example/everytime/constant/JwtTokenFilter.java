package com.example.everytime.constant;

import com.example.everytime.entity.User;
import com.example.everytime.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationToken == null || !authorizationToken.startsWith("Bearer ")) {
            filterChain.doFilter(request,response); // 권한 부여중에 다음 필터로 이동
            return;
        }
        String token;
        try {
            token = authorizationToken.split(" ")[1];
        } catch (Exception e) {
            log.error("{} 에러가 발생하여 token 추출에 실패했습니다.", e);
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰이 만료되었는지 체크
        if(JwtTokenFilter.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userId = extractClaims(token, secretKey).get("userId").toString();
        User user = userService.getUserByUserId(userId);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), null, List.of(new SimpleGrantedAuthority(user.getRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 해당 계정 승인 정보 담기
        filterChain.doFilter(request, response);
    }

    private static Claims extractClaims(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public static boolean isExpired(String token, String secretKey) {
        Date expiredDate = extractClaims(token, secretKey).getExpiration();
        return expiredDate.before(new Date());
    }
}
