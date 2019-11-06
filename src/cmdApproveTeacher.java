
public class cmdApproveTeacher implements Command {

	public void execute(String argument, User u) {
		if(u.getAccountType().equals("Librarian")) {
			User teacher = null;;
			for(User us:LibrarySystem.getInstance().users)
				if(us.getUsername().equals(argument))
					teacher = us;
			if(teacher != null) {
				if(teacher.getAccountType().equals("Teacher")) {
					((TeacherUser)teacher).setIsApproved(true);
					System.out.println("Success!  Teacher approved.");
				} else
					System.out.println("That user is not a teacher!");
			} else 
				System.out.println("Username not found.");
		} else 
			System.out.println("You must be a librarian to perform that action.");
	}	

	public boolean requiresUser() {
		return true;
	}

}
