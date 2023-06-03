package com.example.posts.service;
import com.example.posts.model.Posts;

import java.util.List;

public interface IPosts {
    List<Posts> findAll();

    void save(Posts posts);

    Posts findById(int id);

    void update(int id, Posts posts);

    void delete(int id);

    List<Posts> search(String name);

}
