package com.vitalyh.restfulwebservices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping ("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping ("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id " + id + " not found");

        // Link to all users
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if(user==null)
            throw new UserNotFoundException("id " + id + " not found");
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid  @RequestBody User user){
        User savedUser = service.save(user);

        // Return correct status code and location of created user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class UserNotFoundException extends RuntimeException {
        UserNotFoundException(String message) {
            super(message);
        }
    }
}
