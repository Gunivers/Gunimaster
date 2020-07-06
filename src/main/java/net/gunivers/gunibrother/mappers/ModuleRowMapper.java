package net.gunivers.gunibrother.mappers;

import net.gunivers.gunibrother.models.module.Module;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class ModuleRowMapper implements RowMapper<Module> {
    @Override
    public Module mapRow(ResultSet rs, int i) throws SQLException {
        return Module.builder()
                .uuid(rs.getObject("uuid", UUID.class))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .version(rs.getString("version"))
                .url(rs.getString("url"))
                .build();
    }
}
