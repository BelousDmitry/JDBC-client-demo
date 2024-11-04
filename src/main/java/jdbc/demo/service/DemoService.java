package jdbc.demo.service;


import jdbc.demo.DTO.Item;
import jdbc.demo.mapper.ItemRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService {

    private final JdbcClient jdbcClient;
    private final ItemRowMapper itemRowMapper;

    public DemoService(JdbcClient jdbcClient, ItemRowMapper itemRowMapper) {
        this.jdbcClient = jdbcClient;
        this.itemRowMapper = itemRowMapper;
    }


    public void saveItem(Item item) {
        this.jdbcClient.sql("""
                            INSERT INTO items(title, price, user_id)
                            VALUES (:title, :price, get_user_id_by_username(:username));
                        """)
                .param("title", item.getTitle())
                .param("price", item.getPrice())
                .param("username", item.getUser().getUsername())
                .update();
    }


    public void likeItem(int user_id, int item_id) {
        this.jdbcClient.sql("""
                            INSERT INTO liked_items(user_id, item_id)
                            VALUES (:user_id, :item_id);
                        """)
                .param("user_id", user_id)
                .param("item_id", item_id)
                .update();
    }


    public Item findItemById(int item_id) {
        return this.jdbcClient.sql("""
                            SELECT i.title, i,price, u.username, u.email
                            FROM items i
                            JOIN users u ON i.user_id = u.id
                            WHERE i.id = :item_id;
                        """)
                .param("item_id", item_id)
                .query(this.itemRowMapper)
                .single();
    }


    public Map<String, Object> findItemById(int item_id, int user_id) {
        return this.jdbcClient.sql("""
                            SELECT i.title, i.price, u.username, u.email,
                       
                            CASE
                                WHEN liked.item_id IS NOT NULL THEN true
                                ELSE false
                            END AS liked_status
                       
                            FROM items i
                            JOIN users u ON i.user_id = u.id
                            LEFT JOIN liked_items liked ON i.id = liked.item_id AND liked.user_id = :user_id

                            WHERE i.id = :item_id;
                        """)
                .param("item_id", item_id)
                .param("user_id", user_id)
                .query()
                .singleRow();
    }


}