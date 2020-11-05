package it.unicam.cs.ids.justmeet.backend.service;

import it.unicam.cs.ids.justmeet.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PostService")
public class PostService {

    @Autowired
    PostRepository postRepository;


}
