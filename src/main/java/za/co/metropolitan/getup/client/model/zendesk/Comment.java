package za.co.metropolitan.getup.client.model.zendesk;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String body;
    private String htmlBody;
    private Long authorId;
    private List<String> uploads;
    private Date createdAt;
    private Boolean publicComment;

    public Comment() {
    }

    public Comment(String body) {
        this.body = body;
    }

    public Comment(String body, String... uploads) {
        this.body = body;
        this.uploads = uploads.length == 0 ? null : Arrays.asList(uploads);
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("html_body")
    public String getHtmlBody() {
        return this.htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public List<String> getUploads() {
        return this.uploads;
    }

    public void setUploads(List<String> uploads) {
        this.uploads = uploads;
    }

    @JsonProperty("author_id")
    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @JsonProperty("created_at")
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("public")
    public Boolean isPublic() {
        return this.publicComment;
    }

    public void setPublic(Boolean isPublic) {
        this.publicComment = isPublic;
    }

    public String toString() {
        return "Comment{id=" + this.id + ", body='" + this.body + '\'' + ", authorId=" + this.authorId +", createdAt=" + this.createdAt + ", uploads=" + this.uploads + '}';
    }
}





