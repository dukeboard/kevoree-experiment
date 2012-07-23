package fr.inria.hocl.example.libraryJavaS5;


public class Book {

	Book( String aName ) {
		this.book_name = aName;
		this.book_id = id;
		this.available = "YES";
		id++;
	}


	public String getBookName() {
		return this.book_name;
	}


	public int getBookId() {
		return this.book_id;
	}


	public void setBookName( String aName ) {
		this.book_name = aName;
	}


	public String toString() {
		return "\nTHis is a book.ID:" + this.book_id + ", Name:" + this.book_name
				+ ", Available:" + this.available + ".";
	}


	public boolean borrowBook() {
		if( this.available == "YES" ) {
			this.available = "NO";
			return true;
		} else
			return false;
	}

	private static int id;

	private String book_name;

	private int book_id;

	private String available;

}
