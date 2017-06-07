package wdsr.exercise5.models;

public class ClassTab {
	private String name;
	private int id;
	private int fkey_faculty;
	
	public ClassTab(String name, int fkey_faculty) {
		super();
		this.name = name;
		this.fkey_faculty = fkey_faculty;
	}
	
	public ClassTab(int id, String name, int fkey_faculty) {
		super();
		this.id = id;
		this.name = name;
		this.fkey_faculty = fkey_faculty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFkey_faculty() {
		return fkey_faculty;
	}

	public void setFkey_faculty(int fkey_faculty) {
		this.fkey_faculty = fkey_faculty;
	}
}
