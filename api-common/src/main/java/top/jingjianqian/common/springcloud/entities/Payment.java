package top.jingjianqian.common.springcloud.entities;


import java.io.Serializable;

public class Payment  implements Serializable {

    private Long id;
    private String serial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
