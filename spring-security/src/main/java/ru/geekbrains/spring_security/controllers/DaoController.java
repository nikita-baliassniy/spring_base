package ru.geekbrains.spring_security.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring_security.entities.User;
import ru.geekbrains.spring_security.services.UserService;

import java.security.Principal;

/**
 * Немножко изменил адреса и роли:
 * Для всех запросов к дао нужно быть авторизированным,
 * а для получения score по любому айди нужно быть админом
 */

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
public class DaoController {
    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new RuntimeException("unable to find user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }

    @GetMapping("/dao/score")
    public String daoScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new RuntimeException("unable to find user by username: " + principal.getName()));
        return "Your current score: " + user.getScore().getScore();
    }

    @GetMapping("/dao/score/inc")
    public String daoScoreInc(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new RuntimeException("unable to find user by username: " + principal.getName()));
        userService.incrementScore(user.getUsername());
        return "Your score was incremented!";
    }

    @GetMapping("/dao/score/dec")
    public String daoScoreDec(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new RuntimeException("unable to find user by username: " + principal.getName()));
        userService.decrementScore(user.getUsername());
        return "Your score was decremented!";
    }

    @GetMapping("/dao/score/get/{id}")
    public String daoScoreById(@PathVariable Long id) {
        User userToFound = userService.findById(id).orElseThrow(() ->
                new RuntimeException("unable to find user by id: " + id));
        return "User " + id + " score: " + userToFound.getScore().getScore();
    }
}