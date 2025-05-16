package to.lova.mdl;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.ItemIndexProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Service
public class UserProvider extends AbstractBackEndDataProvider<User, Void> implements ItemIndexProvider<User, Void> {

    static final Faker FAKER = new Faker();

    static final Map<String, User> USERS = Stream.generate(() -> {
                var user = new User();
                var uuid = FAKER.internet().uuid();
                user.setId(uuid);
                user.setName(FAKER.name().fullName());
                user.setEmail(FAKER.internet().emailAddress());
                user.setImage("https://api.dicebear.com/7.x/avataaars/svg?" +
                        "backgroundColor=338bff&" +
                        "seed=" + uuid);
                return user;
            })
            .limit(100)
            .collect(Collectors.toMap(User::getId, Function.identity()));

    @Override
    protected Stream<User> fetchFromBackEnd(Query<User, Void> query) {
        return USERS.values().stream().skip(query.getOffset()).limit(query.getLimit());
    }

    @Override
    protected int sizeInBackEnd(Query<User, Void> query) {
        return USERS.size();
    }

    @Override
    public Integer apply(User user, Query<User, Void> query) {
        return fetchFromBackEnd(query).toList().indexOf(user);
    }
}
