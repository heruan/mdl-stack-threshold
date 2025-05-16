package to.lova.mdl;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;

public class MainLayout extends AppLayout {

    private final VerticalLayout drawer = new VerticalLayout();

    private final SideNav sideNav = new SideNav();

    private final SideNavItem homeNavItem = new SideNavItem("Home", HomeView.class);

    private final SideNavItem idmNavItem = new SideNavItem("Identity Management", UserList.class);

    MainLayout() {
        idmNavItem.setMatchNested(true);
        sideNav.addItem(homeNavItem);
        sideNav.addItem(idmNavItem);
        drawer.addClassNames(AlignItems.STRETCH);
        drawer.add(new H3("MDL"), sideNav);
        addToDrawer(drawer);
    }
}
