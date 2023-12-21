package com.in28minutes.spring.basic.componentscan;

import com.in28minutes.spring.basic.spring5steps.scope.JdbcConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ComponentDAO {

    @Autowired
    private ComponentJdbcConnection componentJdbcConnection;

    public ComponentJdbcConnection getComponentJdbcConnection() {
        return componentJdbcConnection;
    }

    public void setComponentJdbcConnection(ComponentJdbcConnection componentJdbcConnection) {
        this.componentJdbcConnection = componentJdbcConnection;
    }
}
