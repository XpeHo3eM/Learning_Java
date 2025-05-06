package Collections.task4;

import java.util.*;

public class SearchServiceImpl implements SearchService {
    @Override
    public List<User> searchForFriendsInWidth(User me, String name) {
        Queue<User> queue = new LinkedList<>();
        queue.add(me);

        return findNameInWidth(name, queue, new HashSet<>());
    }

    @Override
    public List<User> searchForFriendsInDepth(User me, String name) {
        List<User> stack = new Stack<>();
        stack.add(me);

        return findNameInDepth(name, stack, new HashSet<>());
    }

    private List<User> findNameInDepth(String name, List<User> stack, Set<User> checked) {
        if (stack.isEmpty()) {
            return Collections.emptyList();
        }

        User current = ((Stack<User>)stack).pop();
        checked.add(current);

        for (User friend : current.getFriends()) {
            if (checked.contains(friend)) {
                continue;
            }

            stack.add(friend);
        }

        List<User> res = new LinkedList<>();

        if (current.getName().equals(name)) {
            res.add(current);
        }

        res.addAll(findNameInDepth(name, stack, checked));

        return res.stream()
                .distinct()
                .toList();
    }

    private List<User> findNameInWidth(String name, Queue<User> queue, Set<User> checked) {
        if (queue.isEmpty()) {
            return Collections.emptyList();
        }

        User current = queue.poll();
        checked.add(current);

        for (User friend : current.getFriends()) {
            if (checked.contains(friend)) {
                continue;
            }

            queue.add(friend);
        }

        List<User> res = new LinkedList<>();

        if (current.getName().equals(name)) {
            res.add(current);
        }

        res.addAll(findNameInWidth(name, queue, checked));

        return res.stream()
                .distinct()
                .toList();
    }
}
