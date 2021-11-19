package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqian477
 * @date 2021/11/15 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionBean implements Serializable {

    private static final long serialVersionUID = 6513573445811030320L;

    private List<String> ss;

}
