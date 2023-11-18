package Sanda.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Book {
    private String id, name;
    private int pageNumbers;
    private Date releaseDate;
    private List<Topic> topics;
    private Author author;
}
