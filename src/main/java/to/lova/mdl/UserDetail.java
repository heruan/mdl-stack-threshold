package to.lova.mdl;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.masterdetaillayout.MasterDetailLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@Route(layout = UserList.class, value = "")
@RoutePrefix(":userId")
@ParentLayout(UserList.class)
public class UserDetail extends MasterDetailLayout implements BeforeEnterObserver {

    private final Div master = new Div();

    private final HorizontalLayout header = new HorizontalLayout();

    private final H4 title = new H4();

    private final RouterLink backButton = new RouterLink(UserList.class);

    private final Button saveButton = new Button("Save");

    private final VerticalLayout form = new VerticalLayout();

    private final TextField nameField = new TextField("Name");

    private final EmailField emailField = new EmailField("Email");

    private final Binder<User> binder = new Binder<>();

    UserDetail() {
        backButton.add(LineAwesomeIcon.ANGLE_LEFT_SOLID.create());
        saveButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        header.addClassNames(Padding.MEDIUM);
        header.addToStart(backButton);
        header.addToMiddle(title);
        header.addToEnd(saveButton);
        form.add(nameField, emailField);
        master.addClassNames(Flex.AUTO);
        master.add(header, form);
        setMaster(master);
        binder.forField(new ReadOnlyHasValue<>(title::setText)).bindReadOnly(User::getName);
        binder.forField(nameField).bind(User::getName, User::setName);
        binder.forField(emailField).bind(User::getEmail, User::setEmail);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getRouteParameters().get("userId").map(UserProvider.USERS::get).ifPresent(binder::readBean);
    }
}
