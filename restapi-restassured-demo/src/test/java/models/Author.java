package models;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class Author {
	
	private int id;
    private String name;
    private int age;
    private String gender;
    private List<Book> books;    

}
