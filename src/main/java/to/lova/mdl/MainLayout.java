package to.lova.mdl;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.masterdetaillayout.MasterDetailLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;

public class MainLayout extends MasterDetailLayout {

    private final VerticalLayout drawer = new VerticalLayout();

    private final SideNav sideNav = new SideNav();

    private final SideNavItem homeNavItem = new SideNavItem("Home", HomeView.class, LineAwesomeIcon.TACHOMETER_ALT_SOLID.create());

    private final SideNavItem idmNavItem =
            new SideNavItem("Identity Management", UserList.class, LineAwesomeIcon.USERS_SOLID.create());

    MainLayout() {
        addClassName("side-nav");
        idmNavItem.setMatchNested(true);
        sideNav.addItem(homeNavItem);
        sideNav.addItem(idmNavItem);
        drawer.addClassNames(AlignItems.STRETCH);
        drawer.add(new H4("MDL"), sideNav);
        setMaster(drawer);
        setMasterMinSize("256px");
        setDetailMinSize("256px");
        setOverlayMode(OverlayMode.STACK);
    }
}
