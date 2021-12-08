package models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
//@NoArgsConstructor
@Getter
@Setter
@ToString
//@Data
@NoArgsConstructor
public class Book {
	
	private int bid;
    private String name;
    private int pages;   

}
