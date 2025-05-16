package to.lova.mdl;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(layout = UserDetail.class, value = "roles")
public class UserRoles extends Grid<UserRoles.Role> {

    UserRoles() {
        addThemeVariants(GridVariant.LUMO_NO_BORDER);
        addColumn(Role::name);
        addComponentColumn(role -> {
            var badge = new Span("Inherited");
            badge.getElement().getThemeList().add("badge pill");
            return badge;
        });
        setItems(new Role("Manager"), new Role("Auditor"), new Role("Publisher"));
    }

    record Role(String name) {}
}
