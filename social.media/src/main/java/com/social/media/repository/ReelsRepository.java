package com.social.media.repository;

import com.social.media.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {
   public List<Reels> findByUserId(Integer userId);

}
