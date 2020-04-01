package net.gunivers.gunibrother.dao;

import net.gunivers.gunibrother.extractors.ModuleSetExtractor;
import net.gunivers.gunibrother.mappers.ModuleRowMapper;
import net.gunivers.gunibrother.models.module.Module;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModuleDaoImpl implements Dao<Module> {

    private NamedParameterJdbcTemplate template;

    public ModuleDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Module get(long id) {
        return template.query("select * from module where " + id + " == module.id", new ModuleSetExtractor());
    }

    @Override
    public List<Module> getAll() {
        return template.query("select * from module", new ModuleRowMapper());
    }

    @Override
    public void save(Module module) {

    }

    @Override
    public void update(Module module, String[] params) {

    }

    @Override
    public void delete(Module module) {

    }
}
