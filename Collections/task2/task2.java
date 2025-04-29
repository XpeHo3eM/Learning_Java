import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class task2 {
    public static List<Post> getTop10(List<Post> posts) {
        final int TOP_COUNT = 10;

        ArrayList<Post> top = new ArrayList<>(TOP_COUNT + 1);
        int minLikes = 0;

        for (Post post : posts) {
            if (post.likesCount() < minLikes) {
                continue;
            }

            top.add(post);
            top.sort(Comparator.comparingInt(Post::likesCount).reversed());

            if (top.size() > TOP_COUNT) {
                top.remove(TOP_COUNT);
            }
        }

        top.trimToSize();

        return top;
    }

    private static List<Post> generatePosts(int count) {
        List<Post> posts = new ArrayList<>(count);

        for (int i = 1; i <= count; ++i) {
            String postText = "Post #" + i;
            posts.add(new Post(postText, ThreadLocalRandom.current().nextInt(0, 100)));
        }

        return posts;
    }

    public static void main(String[] args) {
        List<Post> posts = generatePosts(30);

        getTop10(posts).forEach(System.out::println);
    }
}
