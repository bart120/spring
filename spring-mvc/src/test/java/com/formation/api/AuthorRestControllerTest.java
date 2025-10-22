package com.formation.api;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.formation.api.controllers.AuthorRestController;
import com.formation.api.controllers.BookRestController;
import com.formation.api.exceptions.ApiExceptionHandler;
import com.formation.models.Author;
import com.formation.repo.AuthorRepository;
import com.formation.repo.BookRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorRestControllerTest {
    private MockMvc mvc;
    private AuthorRepository authorRepo;

    @BeforeEach()
    void setup() {
        authorRepo = Mockito.mock(AuthorRepository.class);

        var controller = new AuthorRestController(authorRepo);
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                // .apply(SecurityMockMvcConfigurers.springSecurity())
                .setControllerAdvice(new ApiExceptionHandler())
                .build();

        var author = new Author();
        author.setName("Vincent");

        Mockito.when(authorRepo.findById(10L)).thenReturn(Optional.of(author));

        Mockito.when(authorRepo.save(any())).thenAnswer(inv -> {
            Author a = inv.getArgument(0);
            if (a.getId() == null)
                a.setId(10L);
            return a;
        });

    }

    @Test
    void get_one_auth_but_returns_json() throws Exception {
        mvc.perform(get("/api/author/10")/* .with(httpBasic("user","pwd")) */)
                .andExpect(status().isOk());
        // .andExpect(jsonPath("$.name").value("Vincent"));
    }

}
