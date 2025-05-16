package to.lova.mdl;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.masterdetaillayout.MasterDetailLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;

@Route(layout = MainLayout.class, value = "")
@RoutePrefix("idm")
@ParentLayout(MainLayout.class)
public class IdentityManagementSection extends MasterDetailLayout {

    private final VerticalLayout master = new VerticalLayout();

    private final HorizontalLayout header = new HorizontalLayout();

    private final SideNav sideNav = new SideNav();

    private final SideNavItem usersNavItem = new SideNavItem("Users", UserList.class);

    IdentityManagementSection() {
        addClassName("side-nav");
        master.addClassNames(AlignItems.STRETCH);
        header.addClassNames(AlignItems.CENTER);
        header.addToStart(new H4("Identity Management"));
        master.addClassNames(Flex.AUTO, Display.FLEX, FlexDirection.COLUMN);
        master.add(header, sideNav);
        var userCountBadge = new Span(String.valueOf(UserProvider.USERS.size()));
        userCountBadge.getElement().getThemeList().add("badge pill");
        usersNavItem.setSuffixComponent(userCountBadge);
        usersNavItem.setMatchNested(true);
        sideNav.addItem(usersNavItem);
        setMaster(master);
        setMasterMinSize("256px");
        setDetailMinSize("425px");
        setOverlayMode(OverlayMode.STACK);
    }
}
