package lq.test;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/8/3 17:43
 */
public class VenderColType implements Serializable {
    private static final long serialVersionUID = 1874149234570644372L;
    private Long venderId;
    private Integer colType;

    public VenderColType() {
    }

    public VenderColType(Long venderId, Integer colType) {
        this.venderId = venderId;
        this.colType = colType;
    }

    public Long getVenderId() {
        return this.venderId;
    }

    public void setVenderId(Long venderId) {
        this.venderId = venderId;
    }

    public Integer getColType() {
        return this.colType;
    }

    public void setColType(Integer colType) {
        this.colType = colType;
    }

    @Override
    public String toString() {
        return "VenderColType{venderId=" + this.venderId + ", colType=" + this.colType + '}';
    }
}
