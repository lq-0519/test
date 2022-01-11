package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqian477
 * @date 2021/11/02 20:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scene implements Serializable {
    private String questionnaireId;
    private List<Integer> sceneComeFromList;

    public int getName() {
        return 1;
    }

    public String getName() {
        return "1";
    }
}
