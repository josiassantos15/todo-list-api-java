package com.josiassantos.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.josiassantos.todolist.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var servletPath = request.getServletPath();

        if (!servletPath.startsWith("/tasks/")) {
            filterChain.doFilter(request, response);
        } else {

            var auth = request.getHeader("Authorization");
            var authEncoded = auth.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);


            String[] credentials = authString.split(":");
            var username = credentials[0];
            var password = credentials[1];

            var user = userRepository.findUserByUsername(username);
            if (user == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Sem autorização.");
            } else {
                var verified = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (!verified.verified) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Sem autorização.");
                } else {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                }
            }
        }
    }
}
