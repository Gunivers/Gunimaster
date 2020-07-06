package net.gunivers.gunibrother.extractors;

import net.gunivers.gunibrother.models.module.Module;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ModuleSetExtractor implements ResultSetExtractor<Module> {
    @Override
    public Module extractData(ResultSet rs) throws SQLException, DataAccessException {
        return Module.builder()
                .uuid(rs.getObject("uuid", UUID.class))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .version(rs.getString("version"))
                .url(rs.getString("url"))
                .build();
    }
}
