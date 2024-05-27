package uberbackend.uberprojectauthservice.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uberbackend.uberprojectauthservice.services.JwtService;
import uberbackend.uberprojectauthservice.services.UserDetailsServiceImpl;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if(request.getCookies()!=null){
            for(Cookie cookie:request.getCookies()){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }
        }
        if(token==null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        }
        System.out.println("Incoming token : "+ token);
        String email = jwtService.extractEmail(token);
        System.out.println("Incoming email : "+ email);
        if(email!=null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtService.validateToken(token,userDetails.getUsername())){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
