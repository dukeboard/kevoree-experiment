package fr.inria.hocl.example.libraryJavaS6;


import java.util.LinkedList;
import java.util.List;

public class Student {

	Student( String aName ) {
		this.stu_name = aName;
		this.stu_id = id;
		this.borrowedBooks = new LinkedList<Book>();
		id++;
	}


	public String getStuName() {
		return this.stu_name;
	}


	public int getStuId() {
		return this.stu_id;
	}


	public void setStuName( String aName ) {
		this.stu_name = aName;
	}


	public String toString() {
		String info = "\nThis is a student. ID:" + this.stu_id + ", Name:"
				+ this.stu_name + "\nBorrowedBooks:";

		for( int i = 0; i < this.borrowedBooks.size(); i++ ) {
			info += ( i + 1 ) + ": " + this.borrowedBooks.get( i ).getBookName()
					+ ";";
		}

		info += "\n";
		return info;
	}


	public boolean borrowBook( Book aBook ) {
		this.borrowedBooks.add( aBook );
		return true;
	}


	public boolean returnBook( Book aBook ) {
		this.borrowedBooks.remove( aBook );
		return true;
	}

	private List<Book> borrowedBooks;

	private static int id;

	private String stu_name;

	private int stu_id;

}
