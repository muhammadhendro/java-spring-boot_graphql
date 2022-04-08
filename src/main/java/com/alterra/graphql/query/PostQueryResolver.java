package com.alterra.graphql.query;

import com.alterra.graphql.model.dto.AuthorDto;
import com.alterra.graphql.model.dto.PostDto;
import com.alterra.graphql.service.AuthorService;
import com.alterra.graphql.service.PostService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    public List<PostDto> getAllPost() {
        return postService.getAllPost();
    }

    public List<AuthorDto> getAllAuthor() {
        return  authorService.getAllAuthor();
    }
}
