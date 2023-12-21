package com.in28minutes.rest.webservices.restfulwebservices.user;


import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Predicate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        // return service.findAll();
        return userRepository.findAll();
    }

    // createUser
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        // User savedUser = service.save(user);
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
        // return new ResponseEntity<User>(savedUser, HttpStatus.BAD_REQUEST);
    }

    // retrieveUser by id
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        // User user = service.findOne(id);
        /*if (Objects.isNull(user))
            throw new UserNotFoundException("id:" + id);*/
        // return user;
        // User user = service.findOne(id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found : [id] + " + id));
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUser(id));
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    // deleteUserById
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }


    // ***************************************

    // retrieve all posts for a user
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found : [id] + " + id));
        return user.getPosts();
    }

    // create a post for a user
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found by Id [] " + id));
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedPost);
    }

    // http://localhost:8080/jpa/users/10001/posts/20005
    // retrieve one post for a user
    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post retrieveOnePostForUser(@PathVariable Integer userId, @PathVariable Integer postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found by Id [] " + userId));
        Predicate<? super Post> predicate = post -> post.getId().equals(postId);
        return user.getPosts().stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("post not found by Id [] " + postId));
    }

    /*@PostMapping("/users")
    public void createUser(@RequestBody User user) {
        service.save(user);
        // return ResponseEntity.created(null).build();
    }*/
}