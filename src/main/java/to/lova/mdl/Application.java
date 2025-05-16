package to.lova.mdl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

@Theme("app")
@SpringBootApplication
public class Application implements AppShellConfigurator {

    public static void main(String... args){
        SpringApplication.run(Application.class, args);
    }
}
