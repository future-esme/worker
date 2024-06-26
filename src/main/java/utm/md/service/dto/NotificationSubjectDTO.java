package utm.md.service.dto;

public class NotificationSubjectDTO implements NotificationDTO {
    private String receiver;
    private String content;

    private String subject;

    public NotificationSubjectDTO() {
    }

    public NotificationSubjectDTO(String receiver, String content, String subject) {
        this.receiver = receiver;
        this.content = content;
        this.subject = subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "NotificationSubjectDTO{" +
            "subject='" + subject + '\'' +
            '}';
    }
}
