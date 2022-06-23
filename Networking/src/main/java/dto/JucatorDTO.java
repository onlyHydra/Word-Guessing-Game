package dto;

import java.io.Serializable;

public class JucatorDTO implements Serializable {

        private String alias;
        private Integer id;

    public JucatorDTO(String alias, Integer id) {
        this.alias = alias;
        this.id = id;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
