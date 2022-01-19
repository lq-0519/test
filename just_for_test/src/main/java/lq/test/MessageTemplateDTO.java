package lq.test;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liqian477
 * @date 2022/01/18 15:36
 */
public class MessageTemplateDTO implements Serializable {

    private static final long serialVersionUID = -8386786195309063627L;
    private Long id;
    private String templateCode;
    private String messageTitle;
    private String messageContent;
    private Integer touchItem;
    private Integer status;
    private Date created;
    private Date modified;
    private String link;
    private Integer systemSource;
    private String jmMobileUrl;

    public MessageTemplateDTO() {
    }

    public MessageTemplateDTO(String templateCode, String link) {
        this.templateCode = templateCode;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getTouchItem() {
        return touchItem;
    }

    public void setTouchItem(Integer touchItem) {
        this.touchItem = touchItem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSystemSource() {
        return systemSource;
    }

    public void setSystemSource(Integer systemSource) {
        this.systemSource = systemSource;
    }

    public String getJmMobileUrl() {
        return jmMobileUrl;
    }

    public void setJmMobileUrl(String jmMobileUrl) {
        this.jmMobileUrl = jmMobileUrl;
    }

    @Override
    public String toString() {
        return "MessageTemplateDTO{" +
                "id=" + id +
                ", templateCode='" + templateCode + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", touchItem=" + touchItem +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                ", link='" + link + '\'' +
                ", systemSource=" + systemSource +
                ", jmMobileUrl='" + jmMobileUrl + '\'' +
                '}';
    }
}

