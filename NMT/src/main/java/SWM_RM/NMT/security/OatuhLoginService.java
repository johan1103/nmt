package SWM_RM.NMT.security;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class OatuhLoginService extends DefaultOAuth2UserService{
    private final UserRepository userRepository;
    @Builder
    @Getter
    private static class OauthUser{
        private String nickName;
        private String oauthKey;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> userAttributes = oAuth2User.getAttributes();
        String provider = userRequest.getClientRegistration().getRegistrationId();
        OauthUser oauthUser = getNickNameAndOAuthKeyFromAttributes(userAttributes,provider);
        Long userId = createOrFindUser(oauthUser.getOauthKey(), oauthUser.getNickName());
        return CustomOAuthUser.create(userId);
    }

    @Transactional
    protected Long createOrFindUser(String key,String nickName){
        List<User> user = userRepository.findUserByKey(key);
        if(user.isEmpty()){
            return userRepository.createUser(User.create(nickName, key)).getId();
        }else{
            return user.get(user.size()-1).getId();
        }
    }

    private OauthUser getNickNameAndOAuthKeyFromAttributes(Map<String, Object> userAttributes,String provider){
        String nickName=null;
        String oauthKey=null;
        if(provider.equals("github")) {
            nickName = (String) userAttributes.get("login");
            oauthKey= String.valueOf(userAttributes.get("id"));
            oauthKey+=('&'+provider);
        }else if(provider.equals("kakao")) {
            Map<String,Object> kakaoAttributes=(Map<String,Object>)userAttributes.get("kakao_account");
            nickName = (String) ((Map<String,Object>)kakaoAttributes.get("profile")).get("nickname");
            oauthKey =  String.valueOf(userAttributes.get("id"));
            oauthKey+=('&'+provider);
        }
        return OauthUser.builder().nickName(nickName).oauthKey(oauthKey).build();
    }
}
