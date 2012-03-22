package fr.inria.hocl.example.libraryJavaS4;


public class Student {

	Student( String aName ) {
		this.stu_name = aName;
		this.stu_id = next_stuid;
		next_stuid++;
	}


	public String getStuName() {
		return this.stu_name;
	}


	public int getStuId() {
		return this.stu_id;
	}


	public String toString() {
		return "\nThis is a student. ID:" + this.stu_id + ", Name:" + this.stu_name
				+ "\n";
	}

	private static int next_stuid;

	private String stu_name;

	private int stu_id;
}
