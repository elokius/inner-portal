package com.vergl.raid.repository;

import com.vergl.raid.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vergl on 29.01.2017.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByNumber(Integer number);
}
