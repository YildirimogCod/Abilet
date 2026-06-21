package org.yildirimog.abilet.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yildirimog.abilet.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
