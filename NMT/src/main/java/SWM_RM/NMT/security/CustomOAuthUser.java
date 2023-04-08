package SWM_RM.NMT.security;

import SWM_RM.NMT.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class CustomOAuthUser implements OAuth2User{
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    protected CustomOAuthUser(Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities) {
        this.attributes = attributes;
        this.authorities = authorities;
    }
    public static CustomOAuthUser create(Long id) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id",id);
        return new CustomOAuthUser(attributes,authorities);
    }

    // UserDetail Override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // OAuth2User Override
    @Override
    public Map<String, Object> getAttributes(){
        return this.attributes;
    }
    @Override
    public String getName(){
        return String.valueOf(this.attributes.get("id"));
    }
}
