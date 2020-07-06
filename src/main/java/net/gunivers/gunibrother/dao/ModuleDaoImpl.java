package net.gunivers.gunibrother.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.gunivers.gunibrother.extractors.ModuleSetExtractor;
import net.gunivers.gunibrother.mappers.ModuleRowMapper;
import net.gunivers.gunibrother.models.module.Module;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ModuleDaoImpl implements Dao<Module> {

    private NamedParameterJdbcTemplate namedParamTemplate;
    private JdbcTemplate template;
    private ObjectMapper oMapper = new ObjectMapper();

    public ModuleDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate template) {
        this.namedParamTemplate = namedParameterJdbcTemplate;
        this.template = template;
    }

    @Override
    public Module get(UUID uuid) {
        return namedParamTemplate.query("select * from module m where '" + uuid + "' == m.uuid limit 1", new ModuleSetExtractor());
    }

    @Override
    public List<Module> getAll() {
        return namedParamTemplate.query("select * from module", new ModuleRowMapper());
    }

    public List<Module> getDependencies(UUID uuid) {
        return namedParamTemplate.query("select m.*\n" +
                "from module m, \"module-dependencies\" d\n" +
                "where m.uuid = d.dependency\n" +
                "  and d.dependent = '" + uuid  + "'", new ModuleRowMapper());
    }

    @Override
    public void save(Module module) {
        Map<String,Module> moduleMap = oMapper.convertValue(module, Map.class);
        namedParamTemplate.update("insert into module(name,description,version,url) values (:name,:description,:version,:url)", moduleMap);
    }

    public void saveDependency(UUID dependent, UUID dependency) {
        template.update("insert into \"module-dependencies\"(dependent, dependency) value (?,?)", dependent, dependency);
    }

    @Override
    public void update(Module module, String[] params) {
        Map<String,Module> moduleMap = oMapper.convertValue(module, Map.class);
        namedParamTemplate.update("update module set name = :name, description = :description, version = :version, url = :url where uuid = :uuid", moduleMap);
    }

    @Override
    public void delete(UUID uuid) {
        template.update("delete from module where uuid = ?", uuid);
        template.update("delete from \"module-dependencies\" where dependent = ?", uuid);
        template.update("delete from \"module-dependencies\" where dependency = ?", uuid);
    }

    public void deleteDependency(UUID dependent, UUID dependency) {
        template.update("delete from \"module-dependencies\" where dependent = ? and dependency = ?", dependent, dependency);
    }
}
