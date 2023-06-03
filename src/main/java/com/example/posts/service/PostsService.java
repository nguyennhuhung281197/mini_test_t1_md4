package com.example.posts.service;


import com.example.posts.model.Posts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostsService implements IPosts {

    private static final List<Posts> postsList;

    static {
        postsList = new ArrayList<>();
        postsList.add(new Posts(1, "Conan", "Thám Tử", "2023-6-3"));
        postsList.add(new Posts(2, "Ngôn Tình", "Tình Cảm", "2023-6-3"));
        postsList.add(new Posts(3, "Naruto", "Võ Thuật", "2023-6-3"));
        postsList.add(new Posts(4, "OnePrice", "Thám Hiểm", "2023-6-3"));
        postsList.add(new Posts(5, "Baki", "Võ Thuật", "2023-6-3"));

    }

    @Override
    public List<Posts> findAll() {
        return new ArrayList<>(postsList);
    }

    @Override
    public void save(Posts posts) {
        postsList.add(posts);
    }

    @Override
    public Posts findById(int id) {
        return postsList.stream().filter(posts -> posts.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void update(int id, Posts posts) {
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getId() == id) {
                postsList.set(i, posts);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getId() == id) {
                postsList.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Posts> search(String name) {
        return postsList.stream().filter(posts -> posts.getTitle().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
}
