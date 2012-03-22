package fr.inria.hocl.example.libraryJavaS7;


public class Record {

	Record( int stuId, int bookId, String bookName, String stuName ) {
		this.book_id = bookId;
		this.book_name = bookName;
		this.stu_id = stuId;
		this.stu_name = stuName;
		this.record_state = "NOT YET";
	}


	Record( String bookName, String stuName ) {
		this.book_name = bookName;
		this.stu_name = stuName;
	}


	public int getStuId() {
		return this.stu_id;
	}


	public int getBookId() {
		return this.book_id;
	}


	public String getStuName() {
		return this.stu_name;
	}


	public String getBookName() {
		return this.book_name;
	}


	public String getRecordStata() {
		return this.record_state;
	}


	public boolean returnBook() {
		this.record_state = "RETURNED";
		return true;
	}


	public String toString() {
		String s = "\nStudent: \"" + this.stu_name + "\" has borrowed a book: \""
				+ this.book_name + "\".";
		if( this.record_state == "RETURNED" ) {
			s += "Now this book has been returned.\n";
		}
		return s;
	}

	private int stu_id;

	private int book_id;

	private String stu_name;

	private String book_name;

	private String record_state;
}
