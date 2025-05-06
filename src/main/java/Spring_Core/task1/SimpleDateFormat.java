package Spring_Core.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Configuration
@Scope("prototype")
public class SimpleDateFormat {
    private final Locale locale;

    @Autowired
    public SimpleDateFormat(Locale locale) {
        this.locale = locale;
    }

    public String today() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd LLLL yyyy", locale));
    }

    public String todayIso() {
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

    @Bean
    @Profile("ru")
    public static Locale localeRu() {
        return new Locale("ru", "RU");
    }

    @Bean
    @Profile("en")
    public static Locale localeEn() {
        return Locale.ENGLISH;
    }
}
