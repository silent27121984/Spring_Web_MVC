package ru.netology.repository;
import org.springframework.stereotype.Repository;
import ru.netology.model.Post;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
// Stub
public class PostRepository {
    private final Map<Long, Post> allPost;
    private final AtomicLong idCounter = new AtomicLong();

    public PostRepository() {
        this.allPost = new ConcurrentHashMap<>();
    }

    public Collection<Post> all() {
        return allPost.values();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(allPost.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long id = idCounter.incrementAndGet();
            post.setId(id);
            allPost.put(id, post);

        } else if (post.getId() != 0) {
            Long currentId = post.getId();
            allPost.put(currentId, post);
        }
        return post;
    }

    public void removeById(long id) {
        allPost.remove(id);
    }
}
