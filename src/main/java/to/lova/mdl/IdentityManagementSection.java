package to.lova.mdl;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.masterdetaillayout.MasterDetailLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;

@Route(layout = MainLayout.class, value = "")
@RoutePrefix("idm")
@ParentLayout(MainLayout.class)
public class IdentityManagementSection extends MasterDetailLayout {

    private final VerticalLayout master = new VerticalLayout();

    private final SideNav sideNav = new SideNav();

    private final SideNavItem usersNavItem = new SideNavItem("Users", UserList.class);

    IdentityManagementSection() {
        master.addClassNames(AlignItems.STRETCH);
        master.add(new H3("Identity Management"), sideNav);
        usersNavItem.setMatchNested(true);
        sideNav.addItem(usersNavItem);
        setMaster(master);
        setMasterSize("256px");
        setOverlayMode(OverlayMode.STACK);
    }
}
