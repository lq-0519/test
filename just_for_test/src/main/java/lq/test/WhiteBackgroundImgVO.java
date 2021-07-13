package lq.test;


import java.io.Serializable;

/**
 * 白底图信息vo
 *
 * @author liqian477
 * @date 2021/7/8 10:35
 */
public class WhiteBackgroundImgVO implements Serializable {

    private static final long serialVersionUID = -715740017279646351L;
    /**
     * 白底图jfs的url
     */
    private String whiteBackgroundImg;

    /**
     * bannerId, 用于编辑白底图时做传参
     */
    private String bannerId;

    public String getWhiteBackgroundImg() {
        return whiteBackgroundImg;
    }

    public void setWhiteBackgroundImg(String whiteBackgroundImg) {
        this.whiteBackgroundImg = whiteBackgroundImg;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    @Override
    public String toString() {
        return "WhiteBackgroundImgVO{" +
                "whiteBackgroundImg='" + whiteBackgroundImg + '\'' +
                ", bannerId='" + bannerId + '\'' +
                '}';
    }
}
