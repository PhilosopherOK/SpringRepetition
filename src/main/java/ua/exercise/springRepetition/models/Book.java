package ua.exercise.springRepetition.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*Create TABLE Book(
                     book_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                     person_id int REFERENCES person(person_id)on delete SET NULL,
                     title varchar NOT NULL,
                     author varchar NOT NULL,
                     yearOfWriting int NOT NULL CHECK ( yearOfWriting > 1900 )
)*/
public class Book {
    private int book_id;

    @NotEmpty(message = "title mush be empty")
    @Size(min = 3, max = 40, message = "Name should be more than 3, and dont max than 40")
    private String title;

    @NotEmpty(message = "author mush be empty")
    @Size(min = 3, max = 40, message = "Name should be more than 3, and dont max than 40")
    private String author;

    @NotNull(message = "year mush be not null")
    @Min(value = 1900,message = "must be after 1900")
    private int yearOfWriting;

    public Book() {
    }

    public Book(int book_id, String title, String author, int yearOfWriting) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }
}
