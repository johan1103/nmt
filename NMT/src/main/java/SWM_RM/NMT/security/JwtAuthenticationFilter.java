package SWM_RM.NMT.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = jwtTokenProvider
                .resolveCookieToken((HttpServletRequest) request,"nmt-token");
        //유효한 토큰인지 확인합니다.
        if(token!=null&&jwtTokenProvider.validateToken(token)){
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Cookie myCookie = new Cookie("nmt-token", token);
            myCookie.setMaxAge(300);
            myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
            HttpServletResponse httpServletResponse=(HttpServletResponse) response;
            httpServletResponse.addCookie(myCookie);
        }
        chain.doFilter(request,response);
    }
}

