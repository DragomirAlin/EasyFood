package com.easyfood.files.persistence;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    private String username;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data, String username) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.username = username;
    }

}