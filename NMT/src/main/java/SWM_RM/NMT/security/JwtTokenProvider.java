package SWM_RM.NMT.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey="kth";
    private long tokenValidTime=30*60*1000L;
    private final String AUTHORITIES_KEY = "role";

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        CustomOAuthUser user = (CustomOAuthUser) authentication.getPrincipal();
        String userId = user.getName();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .setSubject(userId)
                .claim(AUTHORITIES_KEY, role)
                .setIssuer("nmt")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .compact();
    }
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        CustomOAuthUser principal = CustomOAuthUser.create(Long.valueOf(claims.getSubject()));
        return new OAuth2AuthenticationToken(principal,authorities,"id");
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    public String resolveCookieToken(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key))
                    return cookie.getValue();
            }
        }
        return null;
    }
    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public Long getUserIdFromToken(String token){
        return Long.valueOf(getUserPk(token));
    }
}
