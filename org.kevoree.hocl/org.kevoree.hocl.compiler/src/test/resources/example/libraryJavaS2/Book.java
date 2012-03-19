package fr.inria.hocl.example.libraryJavaS2;


public class Book {

	Book( String aName ) {
		this.book_name = aName;
		this.book_id = next_bookid;
		next_bookid++;
	}


	public String getBookName() {
		return this.book_name;
	}


	public int getBookId() {
		return this.book_id;
	}


	public String toString() {
		return "\nTHis is a book.ID:" + this.book_id + ", Name:" + this.book_name
				+ "\n";
	}

	private static int next_bookid;

	private String book_name;

	private int book_id;

}
