package to.lova.mdl;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(layout = MainLayout.class, value = "")
public class HomeView extends VerticalLayout {

    HomeView() {
        add(new H1("Home"));
    }
}
