package com.alterra.graphql.service;

import com.alterra.graphql.model.dao.AuthorDao;
import com.alterra.graphql.model.dao.PostDao;
import com.alterra.graphql.model.dto.PostDto;
import com.alterra.graphql.repository.AuthorRepository;
import com.alterra.graphql.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    public List<PostDto> getAllPost() {
        log.info("Start executing get all post.");
        try {
            log.info("");
            List<PostDao> daoList = postRepository.findAll();
            List<PostDto> dtoList = new ArrayList<>();
            for (PostDao post : daoList) {
                dtoList.add(mapper.map(post, PostDto.class));
            }
            return dtoList;
        } catch (Exception e) {
            log.error("Happened error when get all post. Error: {}", e.getMessage());
            log.trace("Get error when get all post. ", e);
            throw e;
        }
    }

    public PostDto writePost(PostDto req) {
        log.info("Start executing write new post.");
        log.debug("Executing write post with request: {}", req);
        try{
            Optional<AuthorDao> author = authorRepository.findById(req.getAuthorId());
        PostDao postDao = PostDao.builder()
                .author(author.get())
                .title(req.getTitle())
                .text(req.getText())
                .category(req.getCategory())
                .build();
                postRepository.save(postDao);

            return mapper.map(postDao, PostDto.class);
        } catch (Exception e) {
            log.error("Happened error when write post. Error: {}", e.getMessage());
            log.trace("Get error when write post. ", e);
            throw e;
        }
    }

    public PostDto deletePost(Long id) {
        log.info("Executing delete post id: {}", id);
       try{
           Optional<PostDao> daoList = postRepository.findById(id);
           if(daoList.isEmpty()){
               log.info("Post {} not found", id);
               throw new IllegalArgumentException("Post not found");
           }
           log.info("Executing delete post success");
           postRepository.deleteById(id);
           return null;
       } catch (Exception e) {
           log.error("Happened error when delete post. Error: {}", e.getMessage());
           log.trace("Get error when delete post. ", e);
           throw e;
       }


    }
}
