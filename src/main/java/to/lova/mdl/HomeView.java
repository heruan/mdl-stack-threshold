package to.lova.mdl;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(layout = MainLayout.class, value = "")
public class HomeView extends VerticalLayout {

    HomeView() {
        add(new H3("Home"));
    }
}
