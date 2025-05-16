package to.lova.mdl;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(layout = UserDetail.class, value = "preferences")
public class UserPreferences extends VerticalLayout {

    UserPreferences() {
        add(new Checkbox("Enable Notifications", true));
        add(new Checkbox("Subscribe to Newsletter", false));
    }

}
