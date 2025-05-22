package com.example.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users") // MongoDB collection name
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true) //to ensure username is unique
    @NonNull // to prevent null values
    private String username;
    @NonNull
    private String password;
    @DBRef //to reference JournalEntry documents by id
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;

}
