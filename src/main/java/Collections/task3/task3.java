package Collections.task3;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

public class task3 {
    static List<Client> clients = generateClients();

    private static List<Client> generateClients() {
        List<Client> clients = new LinkedList<>();

        clients.add(new Client(UUID.randomUUID(), "Василий", 33, List.of(new Phone("+7(123)123-13-12", PhoneType.MOBILE))));
        clients.add(new Client(UUID.randomUUID(), "Николай", 56, Collections.emptyList()));
        clients.add(new Client(UUID.randomUUID(), "Маша", 17, List.of(new Phone("+7(123)111-11-11", PhoneType.MOBILE),
                new Phone("+7(222)222-22-22", PhoneType.MOBILE))));
        clients.add(new Client(UUID.randomUUID(), "Василиса", 75, List.of(new Phone("+7(3212)22-22-11", PhoneType.STATIONARY))));
        clients.add(new Client(UUID.randomUUID(), "Тихон", 11, List.of(new Phone("+7(3212)22-22-11", PhoneType.STATIONARY))));
        clients.add(new Client(UUID.randomUUID(), "Маша", 11, List.of(new Phone("+7(3212)22-22-12", PhoneType.STATIONARY),
                new Phone("+7(234)155-34-21", PhoneType.MOBILE))));

        return clients;
    }

    public static void main(String[] args) {
        assertCalculateTotalAgeByName();
        assertGetUniqueNamesByAdding();
        assertExistsClientWithAgeMoreThan(30, true);
        assertExistsClientWithAgeMoreThan(120, false);
        assertGetClientsToMapIdName();
        assertGetClientsToMapAgeListClients();
        assertGetAllPhonesToStr();
        assertGetOldestClientWithStationaryPhone();
    }


    private static void assertCalculateTotalAgeByName() {
        final String name = "Маша";

        int totalAges = clients.stream()
                .filter(client -> client.name().equals(name))
                .mapToInt(Client::age)
                .sum();

        Assertions.assertEquals(28, totalAges, "Возраст не совпадает");
    }

    private static void assertGetUniqueNamesByAdding() {
        Set<String> names = new LinkedHashSet<>(clients.stream()
                .map(Client::name)
                .distinct()
                .toList());

        Set<String> expected = new LinkedHashSet<>(List.of("Василий", "Николай", "Маша", "Василиса", "Тихон"));

        Assertions.assertEquals(expected, names, "Порядок имен не совпадает");
    }

    private static void assertExistsClientWithAgeMoreThan(int age, boolean expected) {
        boolean exists = clients.stream()
                .map(Client::age)
                .anyMatch(a -> a > age);

        Assertions.assertEquals(expected, exists, "Найдены клиенты старше " + age);
    }

    private static void assertGetClientsToMapIdName() {
        Map<UUID, String> res = clients.stream()
                .collect(Collectors.toMap(Client::id, Client::name, (exists, replace) -> exists, LinkedHashMap::new));

        Map<UUID, String> expected = new LinkedHashMap<>();
        for (Client client : clients) {
            expected.put(client.id(), client.name());
        }

        Assertions.assertEquals(expected, res, "Некорректно преобразовано в Map<ID, NAME>");
    }

    private static void assertGetClientsToMapAgeListClients() {
        Map<Integer, List<Client>> res = clients.stream()
                .collect(Collectors.groupingBy(Client::age));

        Map<Integer, List<Client>> expected = new HashMap<>();
        for (Client client : clients) {
            if (expected.containsKey(client.age())) {
                List<Client> exists = expected.get(client.age());
                exists.add(client);

                expected.put(client.age(), exists);
            } else {
                List<Client> newList = new LinkedList<>();
                newList.add(client);

                expected.put(client.age(), newList);
            }
        }

        Assertions.assertEquals(expected, res, "Списки клиентов по возрастам не совпадают");
    }

    private static void assertGetAllPhonesToStr() {
        String res = clients.stream()
                .map(Client::phones)
                .flatMap(Collection::stream)
                .map(Record::toString)
                .collect(Collectors.joining(","));

        StringBuilder sb = new StringBuilder();
        for (Client client : clients) {
            for (Phone phone : client.phones()) {
                sb.append(phone.toString())
                        .append(",");
            }
        }
        String expected = sb.toString();
        expected = expected.substring(0, expected.length() - 1);

        Assertions.assertEquals(expected, res, "Строки телефонов не совпадают");
    }

    private static void assertGetOldestClientWithStationaryPhone() {
        Optional<Client> client = clients.stream()
                .filter(c -> c.phones().stream()
                        .map(Phone::type)
                        .anyMatch(phoneType -> phoneType == PhoneType.STATIONARY))
                .max(Comparator.comparingInt(Client::age));

        Assertions.assertDoesNotThrow(client::get, "Не обнаружен клиент со стационарным телефоном");

        Client oldestClient = null;
        if (client.isPresent()) {
            oldestClient = client.get();
        }

        Assertions.assertEquals(clients.get(3), oldestClient,
                "Найден не самый пожилой клиент со стационарным телефоном");
    }
}
