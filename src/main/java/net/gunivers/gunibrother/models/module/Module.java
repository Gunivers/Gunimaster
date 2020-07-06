package net.gunivers.gunibrother.models.module;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@Getter
@Setter
public class Module {
    private UUID uuid;
    private String name;
    private String description;
    private String version;
    private String url;
    private List<Module> dependencies;
}
