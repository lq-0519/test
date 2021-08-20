package lq.test;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -4720644832658092169L;
    private List<T> items;
}
