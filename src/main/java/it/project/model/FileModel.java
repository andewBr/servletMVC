package it.project.model;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "filePath", nullable = false)
    private String filePath;

    public FileModel() {
    }

    public FileModel(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

//    public FileModel(Integer id, String name, String filePath) {
//        this.id = id;
//        this.name = name;
//        this.filePath = filePath;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

