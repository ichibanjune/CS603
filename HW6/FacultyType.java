/** Abstract superclass for all faculty working in the department
 *  Has one abstract method - promote - which must be overridden in subclasses.
 *  Defines the title instance variable for all faculty members
 */

//LEAVE THIS FILE UNCHANGED - do not submit

public abstract class FacultyType {
	private String title;
	
	public FacultyType(String title){
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return " Title: " + this.title;
	}
	
	/** ABSTRACT must be overridden in concrete subclasses
	 * Returns a boolean indicating if the faculty member
	 * has been promoted.
	 */
	public abstract boolean promote();
}
