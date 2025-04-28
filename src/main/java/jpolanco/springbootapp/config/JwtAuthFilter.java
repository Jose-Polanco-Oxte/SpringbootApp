package jpolanco.springbootapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpolanco.springbootapp.user.infrastructure.adapters.output.mysql.UserRepositoryMySQL;
import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.Token;
import jpolanco.springbootapp.user.infrastructure.adapters.output.repository.JpaTokenRepository;
import jpolanco.springbootapp.user.infrastructure.services.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JpaTokenRepository jpaTokenRepository;
    private final UserRepositoryMySQL userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println(request.getServletPath());
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwtToken);
        System.out.println(userEmail);
        System.out.println(jwtToken);
        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println("User email is null or authentication is not null");
            return;
        }
        final Token token = jpaTokenRepository.findByToken(jwtToken).orElse(null);
        if (token == null) {
            System.out.println("Token not found");
        }
        if (token == null || token.isRevoked() || token.isExpired()) {
            System.out.println("Token is revoked or expired");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Token is valid");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        System.out.println("uSERnAME: " + userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        final var maybeUser = userRepository.findByEmail(userDetails.getUsername());
        if (maybeUser.isEmpty()) {
            System.out.println("User not found");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(maybeUser.get().getEmail());
        final boolean isTokenValid = jwtService.isTokenValid(jwtToken, maybeUser.get().getEmail());
        if (!isTokenValid) {
            return;
        }
        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        System.out.println(authToken.getPrincipal());
        System.out.println(authToken.getCredentials());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
