package to.lova.mdl;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(layout = UserDetail.class, value = "profile")
public class UserProfile extends VerticalLayout implements BeforeEnterObserver {

    private final Binder<User> binder = new Binder<>();

    private final TextField nameField = new TextField("Name");

    private final EmailField emailField = new EmailField("Email");

    UserProfile() {
        binder.forField(nameField).bind(User::getName, User::setName);
        binder.forField(emailField).bind(User::getEmail, User::setEmail);
        add(nameField, emailField);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getRouteParameters().get("userId").map(UserProvider.USERS::get).ifPresent(binder::readBean);
    }
}
