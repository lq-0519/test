package lq.test;

import java.io.Serializable;
import java.util.List;

/**
 * 白底图query对象
 *
 * @author liqian477
 * @date 2021/7/8 20:27
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "unused"})
public class WhiteBackgroundRpcQuery implements Serializable {

    private static final long serialVersionUID = 3272211619779174118L;
    /**
     * template_id, 调用玲珑接口必传值
     */
    private String template_id;

    /**
     * 图片的jfs地址
     */
    private List<JfsUrl> images;

    private boolean need_matting;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public List<JfsUrl> getImages() {
        return images;
    }

    public void setImages(List<JfsUrl> images) {
        this.images = images;
    }

    public boolean isNeed_matting() {
        return need_matting;
    }

    public void setNeed_matting(boolean need_matting) {
        this.need_matting = need_matting;
    }

    @Override
    public String toString() {
        return "WhiteBackgroundRpcQuery{" +
                "template_id='" + template_id + '\'' +
                ", images=" + images +
                ", need_matting=" + need_matting +
                '}';
    }

    @SuppressWarnings("unused")
    public static class JfsUrl implements Serializable {

        private static final long serialVersionUID = 4062630417383775454L;

        /**
         * 商品主图地址
         */
        public String jfs_url;

        /**
         * 无参构造
         */
        public JfsUrl() {
        }

        /**
         * 全参构造
         */
        public JfsUrl(String jfs_url) {
            this.jfs_url = jfs_url;
        }

        public String getJfs_url() {
            return jfs_url;
        }

        public void setJfs_url(String jfs_url) {
            this.jfs_url = jfs_url;
        }

        @Override
        public String toString() {
            return "JfsUrl{" +
                    "jfs_url='" + jfs_url + '\'' +
                    '}';
        }
    }
}
