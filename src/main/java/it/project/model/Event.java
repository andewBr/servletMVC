package it.project.model;

import javax.persistence.*;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private FileModel fileModel;

    public Event() {
    }

    public Event(User user, FileModel fileModel) {
        this.user = user;
        this.fileModel = fileModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FileModel getFile() {
        return fileModel;
    }

    public void setFile(FileModel fileModel) {
        this.fileModel = fileModel;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", user=" + user +
                ", fileModel=" + fileModel +
                '}';
    }
}
