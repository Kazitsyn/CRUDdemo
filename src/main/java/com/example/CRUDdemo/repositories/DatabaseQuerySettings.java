package com.example.CRUDdemo.repositories;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "database.query")
@Component
public class DatabaseQuerySettings {
    private String select;
    private String delete;
    private String update;
    private String insert;
}
