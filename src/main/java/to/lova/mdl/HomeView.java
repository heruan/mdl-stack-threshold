package to.lova.mdl;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;

@Route(layout = MainLayout.class, value = "")
public class HomeView extends VerticalLayout {

    HomeView() {
        add(new H4("Home"));
        add(new Span(Version.getFullVersion()));
    }
}
