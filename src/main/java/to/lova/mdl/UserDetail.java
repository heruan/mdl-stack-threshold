package to.lova.mdl;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.masterdetaillayout.MasterDetailLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
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

    private final Binder<User> binder = new Binder<>();

    private final RouterLink profileLink = new RouterLink();

    private final RouterLink rolesLink = new RouterLink();

    private final RouterLink preferencesLink = new RouterLink();

    private final Tabs tabs = new Tabs();

    private final Tab profileTab = new Tab(profileLink);

    private final Tab rolesTab = new Tab(rolesLink);

    private final Tab preferencesTab = new Tab(preferencesLink);

    UserDetail() {
        backButton.add(LineAwesomeIcon.ANGLE_LEFT_SOLID.create());
        saveButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        header.addClassNames(Padding.MEDIUM, AlignItems.CENTER);
        header.addToStart(backButton);
        header.addToMiddle(title);
        header.addToEnd(saveButton);
        profileLink.setText("Profile");
        rolesLink.setText("Roles");
        preferencesLink.setText("Preferences");
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        tabs.add(profileTab, rolesTab, preferencesTab);
        master.addClassNames(Flex.AUTO);
        master.add(header, tabs);
        setMaster(master);
        setOrientation(Orientation.VERTICAL);
        binder.forField(new ReadOnlyHasValue<>(title::setText)).bindReadOnly(User::getName);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getRouteParameters().get("userId").map(UserProvider.USERS::get).ifPresent(this::readUser);
        var navigationTarget = event.getNavigationTarget();
        if (UserProfile.class.equals(navigationTarget)) {
            tabs.setSelectedTab(profileTab);
        } else if (UserRoles.class.equals(navigationTarget)) {
            tabs.setSelectedTab(rolesTab);
        } else if (UserPreferences.class.equals(navigationTarget)) {
            tabs.setSelectedTab(preferencesTab);
        }
    }

    private void readUser(User user) {
        var routeParams = new RouteParameters(new RouteParam("userId", user.getId()));
        profileLink.setRoute(UserProfile.class, routeParams);
        rolesLink.setRoute(UserRoles.class, routeParams);
        preferencesLink.setRoute(UserPreferences.class, routeParams);
        binder.readBean(user);
    }
}
