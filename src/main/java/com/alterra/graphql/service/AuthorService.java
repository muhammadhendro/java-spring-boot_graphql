package com.alterra.graphql.service;

import com.alterra.graphql.model.dao.AuthorDao;
import com.alterra.graphql.model.dto.AuthorDto;
import com.alterra.graphql.repository.AuthorRepository;
import com.alterra.graphql.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    public AuthorDto addAuthor(AuthorDto request) {
        log.info("Start executing add author.");
        log.debug("Executing add author with request: {}", request);

        try {
            AuthorDao authorDao = mapper.map(request, AuthorDao.class);
            authorRepository.save(authorDao);
            return mapper.map(authorDao, AuthorDto.class);
        } catch (Exception e) {
            log.error("Happened error when add author. Error: {}", e.getMessage());
            log.trace("Get error when add new author. ", e);
            throw e;
        }
    }

    public List<AuthorDto> getAllAuthor() {
        try {
            List<AuthorDao> daoList = authorRepository.findAll();
            List<AuthorDto> dtoList = new ArrayList<>();
            for(AuthorDao author : daoList) {
                dtoList.add(mapper.map(author, AuthorDto.class));
            }
            return dtoList;

        } catch (Exception e) {
            throw e;
        }
    }

    public AuthorDto updateAuthor(AuthorDto request, Long id) {
        log.info("Start executing update author.");
        log.debug("Executing update author with request: {}", request);

        try {
            Optional<AuthorDao> author = authorRepository.findById(id);
            if (author.isEmpty()) throw new IllegalArgumentException("Author not found");

            mapper.map(request, author.get());
            author.get().setId(id);
            authorRepository.save(author.get());

            return mapper.map(author.get(), AuthorDto.class);
        } catch (Exception e) {
            log.error("Happened error when update author. Error: {}", e.getMessage());
            log.trace("Get error when update author. ", e);
            throw e;
        }
    }
}
