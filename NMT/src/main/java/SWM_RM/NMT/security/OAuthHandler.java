package SWM_RM.NMT.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class OAuthHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String token = jwtTokenProvider.createAccessToken(authentication);

        System.out.println("token value "+token);
        Cookie myCookie = new Cookie("nmt-token", token);
        myCookie.setMaxAge(300);
        myCookie.setPath("/");
        response.addCookie(myCookie);
        System.out.println("login success, token value : "+token);
        String targetUrl = UriComponentsBuilder.fromUriString("/")
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
