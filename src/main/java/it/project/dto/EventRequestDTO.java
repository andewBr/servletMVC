package it.project.dto;

public class EventRequestDTO {
    private UserDTO user;
    private FileDTO file;

    public EventRequestDTO() {
    }

    public EventRequestDTO(UserDTO user, FileDTO file) {
        this.user = user;
        this.file = file;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public FileDTO getFile() {
        return file;
    }

    public void setFile(FileDTO file) {
        this.file = file;
    }


    @Override
    public String toString() {
        return "EventRequestDTO{" +
                "user=" + user +
                ", file=" + file +
                '}';
    }
}
