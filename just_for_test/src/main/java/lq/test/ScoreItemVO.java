package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/11/02 17:48
 */
@Data
@AllArgsConstructor
public class ScoreItemVO implements Serializable {

    private static final long serialVersionUID = 7989470683825051036L;

    private String scoreName;
    private int scoreValue;
}
