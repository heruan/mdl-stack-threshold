package to.lova.mdl;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

public class UserCard extends HorizontalLayout {

    private final Avatar avatar = new Avatar();

    private final Span name = new Span();

    private final Span email = new Span();

    private final Div content = new Div();

    public UserCard(User user) {
        avatar.setName(user.getName());
        avatar.setImage(user.getImage());
        name.setText(user.getName());
        email.setText(user.getEmail());
        email.addClassNames(TextColor.SECONDARY, FontSize.SMALL);
        content.addClassNames(Display.FLEX, FlexDirection.COLUMN);
        content.add(name, email);
        add(avatar, content);
    }
}
