package com.alterra.graphql.mutation;

import com.alterra.graphql.model.dto.AuthorDto;
import com.alterra.graphql.model.dto.PostDto;
import com.alterra.graphql.service.AuthorService;
import com.alterra.graphql.service.PostService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;


    public PostDto writePost(PostDto req) {
        log.info("write post Mutation resolver with input form {}", req);
        return postService.writePost(req);
    }

    public PostDto writePostParam(String title, String text, String category, Long authorId) {
        log.info("write post Mutation resolver with parameter. Title: {}", title);
        PostDto request = PostDto.builder()
                .title(title)
                .text(text)
                .category(category)
                .authorId(authorId)
                .build();
        return postService.writePost(request);
    }

    public AuthorDto addAuthor(AuthorDto request) {
        log.info("Execute add author mutation req: {}", request);
        return authorService.addAuthor(request);
    }

    public PostDto deletePost(Long id) {
        log.info("delete post Mutation resolver with parameter. Id: {}", id);
        return postService.deletePost(id);
    }

    public AuthorDto updateAuthor(AuthorDto request, Long id) {
        log.info("Execute update author mutation");
        return authorService.updateAuthor(request, id);
    }

}
