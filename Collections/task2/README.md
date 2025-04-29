### Задача №2

Дан массив(список) из 1 млн. не отсортированных постов. Требуется найти 10 самых залайканых постов. Необходимо реализовать наиболее оптимальное решение по скорости и памяти.

Реализовать метод getTop10

```
public static List<Post> getTop10(List<Post> posts){
       // implement
}

public class Post{
    private String text;
    private Integer likesCount;
}
```