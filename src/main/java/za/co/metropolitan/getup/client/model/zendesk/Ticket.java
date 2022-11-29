package za.co.metropolitan.getup.client.model.zendesk;
import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {
        private static final long serialVersionUID = 1L;
        private String type;
        private String subject;
        private String description;
        private Comment comment;
        private Requester requester;
        private String priority;
        private Boolean is_public;
        protected String status;
        private boolean hasIncidents;
        private List<String> tags;
        private List<CustomFieldValue> custom_fields;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Comment getComment() {
            return comment;
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Requester getRequester() {
            return requester;
        }

        public void setRequester(Requester requester) {
            this.requester = requester;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public Boolean getIs_public() {
            return is_public;
        }

        public void setIs_public(Boolean is_public) {
            this.is_public = is_public;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isHasIncidents() {
            return hasIncidents;
        }

        public void setHasIncidents(boolean hasIncidents) {
            this.hasIncidents = hasIncidents;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<CustomFieldValue> getCustom_fields() {
            return custom_fields;
        }

        public void setCustom_fields(List<CustomFieldValue> custom_fields) {
            this.custom_fields = custom_fields;
        }
}
