package Sanda.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Book {
    private UUID id;
    private String bookName;
    private int pageNumbers;
    private Date releaseDate;
    private List<Topic> topics;
    private Author author;
}
