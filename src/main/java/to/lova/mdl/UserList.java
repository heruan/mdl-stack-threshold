package to.lova.mdl;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.masterdetaillayout.MasterDetailLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@Route(layout = IdentityManagementSection.class, value = "")
@RoutePrefix("users")
@ParentLayout(IdentityManagementSection.class)
public class UserList extends MasterDetailLayout implements BeforeEnterObserver {

    private final Div master = new Div();

    private final HorizontalLayout header = new HorizontalLayout();

    private final Button addButton = new Button();

    private final Grid<User> grid = new Grid<>();

    UserList(UserProvider userProvider) {
        addButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_TERTIARY_INLINE);
        addButton.setIcon(LineAwesomeIcon.PLUS_SOLID.create());
        header.addClassNames(Padding.MEDIUM);
        header.addToStart(new H3("Users"));
        header.addToEnd(addButton);
        master.addClassNames(Flex.AUTO, Display.FLEX, FlexDirection.COLUMN);
        master.add(header, grid);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setDataProvider(userProvider);
        grid.addComponentColumn(UserCard::new);
        grid.addSelectionListener(event ->
                event.getFirstSelectedItem().ifPresentOrElse(this::navigateToUserDetail, this::navigateToUserList));
        grid.getLazyDataView().setItemIndexProvider(userProvider);
        setMaster(master);
        setDetailSize("400px");
        setMasterMinSize("400px");
        setOverlayMode(OverlayMode.STACK);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getRouteParameters()
                .get("userId")
                .map(UserProvider.USERS::get)
                .ifPresentOrElse(grid::select, grid::deselectAll);
    }

    private void navigateToUserDetail(User user) {
        UI.getCurrent().navigate(UserDetail.class, new RouteParam("userId", user.getId()));
    }

    private void navigateToUserList() {
        UI.getCurrent().navigate(UserList.class);
    }
}
