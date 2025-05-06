package task3;

import java.util.List;
import java.util.UUID;

public record Client (UUID id,
                      String name,
                      Integer age,
                      List<Phone> phones) {
}
