### Задача №4

Василий хочет продать автомобиль. У Василия много друзей и от кого-то когда-то он слышал, что какая-то Наташа хочет купить авто именно его марки. Василий проактивный, хочет побыстрее найти покупателя, однако, Наташа может быть другом одного или нескольких друзей Василия, но может быть и другом Василия через несколько рукопожатий (друг одного друга другого друга ...).

Помогите Василию найти всех Наташ и продать машину, напишите методы поиска по друзьям: поиск в ширину, и поиск в глубину, реализовав интерфейс SearchService

```
interface SearchService {
    List<User> searchForFriendsInWidth(User me, String name);
    List<User> searchForFriendsInDepth(User me, String name);
}
```

Входные параметры методов:
- пользователь, от которого начинаем поиск
- имя пользователя, которого хотим найти
  Методы возвращают список всех пользователей, у которых имя совпадает с запрашиваемым.

Класс пользователя:
```
public class User {
    private Long id;
    private String name;
    private List<User> friends;
    public User(String name) {
        this.name = name;
        this.id = new Random().nextLong();
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<User> getFriends() {
        return friends;
    }
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
```
Метод getFriends возвращает список объектов других пользователей - всех друзей пользователя. В этих объектах так же есть списки друзей, и у объектов из этих списков тоже есть списки друзей и так далее.

Условимся, что у каждого пользователя есть уникальный идентификатор, но имена у пользователей могут совпадать.

Для тестирования можно использовать:
```
public class SearchServiceTest {

    @Test
    public void test1() {
        User vasilii = new User("Василий");
        User arina = new User("Арина");
        User yulya = new User("Юля");
        User evgenii = new User("Евгений");
        User konstantin = new User("Константин");
        User vladimir = new User("Владимир");
        User mariya = new User("Мария");
        User dmitrii = new User("Дмитрий");
        User anatolii = new User("Анатолий");
        User gennadii = new User("Геннадий");
        User anna = new User("Анна");
        User mikhail = new User("Михаил");
        User sergei = new User("Сергей");
        User natasha = new User("Наташа");
        vasilii.setFriends(Arrays.asList(arina, yulya));
        arina.setFriends(Arrays.asList(vasilii, dmitrii));
        yulya.setFriends(Arrays.asList(vasilii, konstantin, evgenii));
        evgenii.setFriends(Arrays.asList(yulya, natasha));
        konstantin.setFriends(Arrays.asList(yulya, vladimir, mariya, natasha));
        vladimir.setFriends(Arrays.asList(konstantin, mariya));
        mariya.setFriends(Arrays.asList(konstantin, vladimir));
        dmitrii.setFriends(Arrays.asList(arina, anatolii, sergei));
        anatolii.setFriends(Arrays.asList(dmitrii, gennadii, mikhail));
        gennadii.setFriends(Arrays.asList(anatolii, anna));
        anna.setFriends(Arrays.asList(gennadii, mikhail));
        mikhail.setFriends(Arrays.asList(anatolii, sergei, anna, natasha));
        sergei.setFriends(Arrays.asList(dmitrii, mikhail));
        natasha.setFriends(Arrays.asList(konstantin, evgenii, mikhail));
 
        SearchService service = new SearchServiceImpl();

        List<User> foundFriends = service.searchForFriendsInDepth(vasilii, "Наташа");
        Assert.assertTrue(foundFriends.contains(natasha));
        foundFriends = service.searchForFriendsInWidth(vasilii, "Наташа");
        Assert.assertTrue(foundFriends.contains(natasha));
    }

    @Test
    public void test2() {
        User vasilii = new User("Василий");
        User arina = new User("Арина");
        User yulya = new User("Юля");
        User evgenii = new User("Евгений");
        User konstantin = new User("Константин");
        User vladimir = new User("Владимир");
        User mariya = new User("Мария");
        User dmitrii = new User("Дмитрий");
        User anatolii = new User("Анатолий");
        User gennadii = new User("Геннадий");
        User anna = new User("Анна");
        User mikhail = new User("Михаил");
        User sergei = new User("Сергей");
        User natasha = new User("Наташа");
        User natasha2 = new User("Наташа");
        vasilii.setFriends(Arrays.asList(arina, yulya));
        arina.setFriends(Arrays.asList(vasilii, dmitrii, natasha2));
        yulya.setFriends(Arrays.asList(vasilii, evgenii));
        evgenii.setFriends(Arrays.asList(yulya));
        konstantin.setFriends(Arrays.asList(vladimir, mariya));
        vladimir.setFriends(Arrays.asList(konstantin));
        mariya.setFriends(Arrays.asList(konstantin, vasilii));
        dmitrii.setFriends(Arrays.asList(arina, anatolii, natasha));
        anatolii.setFriends(Arrays.asList(dmitrii, gennadii));
        gennadii.setFriends(Arrays.asList(anatolii));
        anna.setFriends(Arrays.asList(mikhail));
        mikhail.setFriends(Arrays.asList(sergei, anna));
        sergei.setFriends(Arrays.asList(natasha, mikhail));
        natasha.setFriends(Arrays.asList(dmitrii, sergei));
        natasha2.setFriends(Arrays.asList(arina));

        SearchService service = new SearchServiceImpl();

        List<User> foundFriends = service.searchForFriendsInDepth(vasilii, "Наташа");
        Assert.assertTrue(foundFriends.contains(natasha));
        Assert.assertTrue(foundFriends.contains(natasha2));
        foundFriends = service.searchForFriendsInWidth(vasilii, "Наташа");
        Assert.assertTrue(foundFriends.contains(natasha));
        Assert.assertTrue(foundFriends.contains(natasha2));
    }

    @Test
    public void test3() {
        User vasilii = new User("Василий");
        User arina = new User("Арина");
        User mariya = new User("Мария");
        User vladimir = new User("Владимир");
        User evgenii = new User("Евгений");
        User yulya = new User("Юля");
        User konstantin = new User("Константин");
        User dmitrii = new User("Дмитрий");
        User natasha = new User("Наташа");
        vasilii.setFriends(Arrays.asList(arina, mariya, vladimir, evgenii));
        arina.setFriends(Arrays.asList(vasilii, dmitrii, vasilii));
        yulya.setFriends(Arrays.asList(konstantin, evgenii, vladimir, dmitrii));
        evgenii.setFriends(Arrays.asList(yulya, vasilii, dmitrii));
        konstantin.setFriends(Arrays.asList(vladimir, mariya, yulya, natasha));
        vladimir.setFriends(Arrays.asList(konstantin, vasilii, yulya));
        mariya.setFriends(Arrays.asList(konstantin, vasilii, natasha));
        dmitrii.setFriends(Arrays.asList(arina, evgenii, natasha, yulya));
        natasha.setFriends(Arrays.asList(dmitrii, arina, konstantin, mariya));
        SearchService service = new SearchServiceImpl();
        List<User> foundFriends = service.searchForFriendsInDepth(vasilii, "Наташа");
        Assert.assertTrue(foundFriends.contains(natasha));
        foundFriends = service.searchForFriendsInWidth(vasilii, "Наташа");
        Assert.assertTrue(foundFriends.contains(natasha));
    }
}
```