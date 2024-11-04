package jdbc.demo.mapper;

import jdbc.demo.DTO.Item;
import jdbc.demo.DTO.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

        Item item = new Item();
        User user = new User();

        item.setTitle(rs.getString("title"));
        item.setPrice(rs.getFloat("price"));

        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));

        item.setUser(user);

        return item;
    }
}
