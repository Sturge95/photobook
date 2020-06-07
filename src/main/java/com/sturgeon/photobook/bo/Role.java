package com.sturgeon.photobook.bo;

import javax.persistence.*;

@Entity
@Table(schema = "content", name = "roles")
public class Role {
    @Id
    @GeneratedValue(generator = "content.role_id" , strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_id", schema = "content",  sequenceName = "content.role_id" , allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private auth_role name;

    public enum auth_role {
        USER,
        ADMIN,
        CREATOR
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public auth_role getName() {
        return name;
    }

    public void setName(auth_role name) {
        this.name = name;
    }


}
