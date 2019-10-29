package src;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSONReader.java - A class to handle loading/reading JSON files to the system
 * @author Kevin Prince
 *
 */
public class JSONReader {
	private static final String USERS_FILE_NAME = "users.json";
	private static final String BOOKS_FILE_NAME = "books.json";
	private static final String MAGAZINES_FILE_NAME = "magazines.json";
	private static final String DVDS_FILE_NAME = "dvds.json";
	private static final String AUDIOBOOKS_FILE_NAME = "audiobooks.json";
	private static final String EBOOKS_FILE_NAME = "ebooks.json";
	
	public void loadDatabase() {
		loadUsers();
		loadBooks();
		loadAudioBooks();
		loadDVDs();
		loadEbooks();
		loadMagazines();
		System.out.println("Data loaded!");
	}
	
	public void loadUsers() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.USERS_FILE_NAME;
			FileReader userReader = new FileReader(fileDest);
			//JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(userReader);
			JSONArray usersJSON = (JSONArray)jsonData.get("users");
			for(int i=0;i < usersJSON.size();i++) {
				JSONObject userJSON = (JSONObject)usersJSON.get(i);
				String name = (String)userJSON.get("name");
				String dateOfBirth = (String)userJSON.get("dateOfBirth");
				String address= (String)userJSON.get("address");
				String email = (String)userJSON.get("email");
				String phoneNumber = (String)userJSON.get("number");
				String username = (String)userJSON.get("username");
				String password = (String)userJSON.get("password");
				int id = ((Long)userJSON.get("id")).intValue();
				User temp = null;
				if(((String)userJSON.get("accountType")).equals("Librarian"))
					temp = LibrarySystem.getInstance().createAccount(4, name, dateOfBirth, address, email, phoneNumber, username, password, id);
				if(((String)userJSON.get("accountType")).equals("Teacher"))
					temp = LibrarySystem.getInstance().createAccount(3, name, dateOfBirth, address, email, phoneNumber, username, password, id);
				if(((String)userJSON.get("accountType")).equals("Average"))
					temp = LibrarySystem.getInstance().createAccount(2, name, dateOfBirth, address, email, phoneNumber, username, password, id);
				if(((String)userJSON.get("accountType")).equals("Child"))
					temp = LibrarySystem.getInstance().createAccount(1, name, dateOfBirth, address, email, phoneNumber, username, password, id);
				temp.setClosed(((Boolean)userJSON.get("isClosed")).booleanValue());
				JSONArray feeArray = (JSONArray) new JSONParser().parse((String) userJSON.get("fines"));
				ArrayList<Fee> feeList = new ArrayList<Fee>();
				for(Object l:feeArray.toArray()) {
					int tempfee = ((Long)l).intValue();
					for(Fee f:LibrarySystem.getInstance().fees) {
						if(f.returnId() == tempfee)
							feeList.add(f);
					}
				}
				temp.setFines(feeList);
				JSONArray notifications = (JSONArray) new JSONParser().parse((String) userJSON.get("notifications"));
				ArrayList<String> notificationsList = new ArrayList<String>();
				for(Object l:notifications.toArray()) {
					String notice = (String)l;
					notificationsList.add(notice);
				}
				temp.setNotifications(notificationsList);
				JSONArray children = (JSONArray) new JSONParser().parse((String) userJSON.get("children"));
				ArrayList<User> childrenList = new ArrayList<User>();
				for(Object l:children.toArray()) {
					int tempchild = ((Long)l).intValue();
					for(User u:LibrarySystem.getInstance().users)
						if(u.getId() == tempchild)
							childrenList.add(u);
				}
				temp.setChildren(childrenList);
				JSONArray wishlist = (JSONArray) new JSONParser().parse((String) userJSON.get("wishlist"));
				ArrayList<Media> wishlistList = new ArrayList<Media>();
				for(Object l:wishlist.toArray()) {
					int tempwish = ((Long)l).intValue();
					for(Media f:LibrarySystem.getInstance().inventory) {
						if(f.returnId() == tempwish)
							wishlistList.add(f);
					}
				}
				temp.setWishlist(wishlistList);
				JSONArray checkedout = (JSONArray) new JSONParser().parse((String) userJSON.get("checkedOutMedia"));
				ArrayList<Media> checkoutList = new ArrayList<Media>();
				for(Object l:checkedout.toArray()) {
					int tempcheck = ((Long)l).intValue();
					for(Media f:LibrarySystem.getInstance().inventory) {
						if(f.returnId() == tempcheck) {
							checkoutList.add(f);
						}
					}
				}
				temp.setCheckedOutMedia(checkoutList);
				JSONArray heldmedia = (JSONArray) new JSONParser().parse((String) userJSON.get("heldMedia"));
				ArrayList<Media> heldList = new ArrayList<Media>();
				for(Object l:heldmedia.toArray()) {
					int temphelp = ((Long)l).intValue();
					for(Media f:LibrarySystem.getInstance().inventory) {
						if(f.returnId() == temphelp)
							heldList.add(f);
					}
				}
				temp.setHeldMedia(heldList);
				
				//temp.setNotifications((ArrayList<String>)userJSON.get("notifications"));
				
				//temp.setChildren((ArrayList<User>)userJSON.get("children"));
				//temp.setCheckedOutMedia((ArrayList<Media>)userJSON.get("checkedOutMedia"));
				//temp.setHeldMedia((ArrayList<Media>)userJSON.get("heldMedia"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadBooks() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.BOOKS_FILE_NAME;
			FileReader booksReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(booksReader);
			JSONArray booksJSON = (JSONArray)jsonData.get("books");
			for(int i=0;i < booksJSON.size();i++) {
				JSONObject bookJSON = (JSONObject)booksJSON.get(i);
				String title = (String)bookJSON.get("title");
				String genre = (String)bookJSON.get("genre");
				String description = (String)bookJSON.get("description");
				String year = (String)bookJSON.get("year");
				boolean newRelease = ((Boolean)bookJSON.get("newRelease")).booleanValue();
				int copies = ((Integer)bookJSON.get("copies")).intValue();
				String author = (String)bookJSON.get("author");
				int ISBN = ((Integer)bookJSON.get("ISBN")).intValue();
				String publisher = (String)bookJSON.get("publisher");
				LibrarySystem.getInstance().inventory.add(new Book(title,genre,description,year,newRelease,copies,author,ISBN,publisher));
				Book temp = new Book(title,genre,description,year,newRelease,copies,author,ISBN,publisher);
				temp.setCheckedOut(((Boolean)bookJSON.get("checkedOut")).booleanValue());
				temp.setLastBorrowDate((Date)bookJSON.get("lastBorrowDate"));
				temp.setLastDueDate((Date)bookJSON.get("lastDueDate"));
				temp.setWaitlist((ArrayList<User>)bookJSON.get("waitlist"));
				temp.setRenewCount(((Integer)bookJSON.get("renewCount")).intValue());
				LibrarySystem.getInstance().inventory.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadAudioBooks() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.AUDIOBOOKS_FILE_NAME;
			FileReader audiobooksReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(audiobooksReader);
			JSONArray audiobooksJSON = (JSONArray)jsonData.get("audiobooks");
			for(int i=0;i < audiobooksJSON.size();i++) {
				JSONObject audiobookJSON = (JSONObject)audiobooksJSON.get(i);
				String title = (String)audiobookJSON.get("title");
				String genre = (String)audiobookJSON.get("genre");
				String description = (String)audiobookJSON.get("description");
				String year = (String)audiobookJSON.get("year");
				boolean newRelease = ((Boolean)audiobookJSON.get("newRelease")).booleanValue();
				int copies = ((Integer)audiobookJSON.get("copies")).intValue();
				String author = (String)audiobookJSON.get("author");
				String narrator = (String)audiobookJSON.get("narrator");
				AudioBook temp = new AudioBook(title,genre,description,year,newRelease,copies,author,narrator);
				temp.setCheckedOut(((Boolean)audiobookJSON.get("checkedOut")).booleanValue());
				temp.setLastBorrowDate((Date)audiobookJSON.get("lastBorrowDate"));
				temp.setLastDueDate((Date)audiobookJSON.get("lastDueDate"));
				temp.setWaitlist((ArrayList<User>)audiobookJSON.get("waitlist"));
				temp.setRenewCount(((Integer)audiobookJSON.get("renewCount")).intValue());
				LibrarySystem.getInstance().inventory.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadDVDs() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.DVDS_FILE_NAME;
			FileReader dvdsReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(dvdsReader);
			JSONArray dvdsJSON = (JSONArray)jsonData.get("dvds");
			for(int i=0;i < dvdsJSON.size();i++) {
				JSONObject dvdJSON = (JSONObject)dvdsJSON.get(i);
				String title = (String)dvdJSON.get("title");
				String genre = (String)dvdJSON.get("genre");
				String description = (String)dvdJSON.get("description");
				String year = (String)dvdJSON.get("year");
				boolean newRelease = ((Boolean)dvdJSON.get("newRelease")).booleanValue();
				int copies = ((Integer)dvdJSON.get("copies")).intValue();
				String director = (String)dvdJSON.get("director");
				DVD temp = new DVD(title,genre,description,year,newRelease,copies,director);
				temp.setCheckedOut(((Boolean)dvdJSON.get("checkedOut")).booleanValue());
				temp.setLastBorrowDate((Date)dvdJSON.get("lastBorrowDate"));
				temp.setLastDueDate((Date)dvdJSON.get("lastDueDate"));
				temp.setWaitlist((ArrayList<User>)dvdJSON.get("waitlist"));
				temp.setRenewCount(((Integer)dvdJSON.get("renewCount")).intValue());
				LibrarySystem.getInstance().inventory.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadEbooks() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.EBOOKS_FILE_NAME;
			FileReader ebooksReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(ebooksReader);
			JSONArray ebooksJSON = (JSONArray)jsonData.get("ebooks");
			for(int i=0;i < ebooksJSON.size();i++) {
				JSONObject ebookJSON = (JSONObject)ebooksJSON.get(i);
				String title = (String)ebookJSON.get("title");
				String genre = (String)ebookJSON.get("genre");
				String description = (String)ebookJSON.get("description");
				String year = (String)ebookJSON.get("year");
				boolean newRelease = ((Boolean)ebookJSON.get("newRelease")).booleanValue();
				int copies = ((Integer)ebookJSON.get("copies")).intValue();
				String author = (String)ebookJSON.get("athor");
				EBook temp = new EBook(title,genre,description,year,newRelease,copies,author);
				temp.setCheckedOut(((Boolean)ebookJSON.get("checkedOut")).booleanValue());
				temp.setLastBorrowDate((Date)ebookJSON.get("lastBorrowDate"));
				temp.setLastDueDate((Date)ebookJSON.get("lastDueDate"));
				temp.setWaitlist((ArrayList<User>)ebookJSON.get("waitlist"));
				temp.setRenewCount(((Integer)ebookJSON.get("renewCount")).intValue());
				LibrarySystem.getInstance().inventory.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadMagazines() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.MAGAZINES_FILE_NAME;
			FileReader magazinesReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(magazinesReader);
			JSONArray magazinesJSON = (JSONArray)jsonData.get("magazines");
			for(int i=0;i < magazinesJSON.size();i++) {
				JSONObject magazineJSON = (JSONObject)magazinesJSON.get(i);
				String title = (String)magazineJSON.get("title");
				String genre = (String)magazineJSON.get("genre");
				String description = (String)magazineJSON.get("description");
				String year = (String)magazineJSON.get("year");
				boolean newRelease = ((Boolean)magazineJSON.get("newRelease")).booleanValue();
				int copies = ((Integer)magazineJSON.get("copies")).intValue();
				String author = (String)magazineJSON.get("author");
				int volume = ((Integer)magazineJSON.get("volume")).intValue();
				int issue = ((Integer)magazineJSON.get("issue")).intValue();
				Magazine temp = new Magazine(title,genre,description,year,newRelease,copies,author,volume,issue);
				temp.setCheckedOut(((Boolean)magazineJSON.get("checkedOut")).booleanValue());
				temp.setLastBorrowDate((Date)magazineJSON.get("lastBorrowDate"));
				temp.setLastDueDate((Date)magazineJSON.get("lastDueDate"));
				temp.setRenewCount(((Integer)magazineJSON.get("renewCount")).intValue());
				
				temp.setWaitlist((ArrayList<User>)magazineJSON.get("waitlist"));
				//TODO: Set up waitlist array JSON writing.
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public void writeUsers() {
		try {
			String fileDest = "src/src/JSONs/" + JSONReader.USERS_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray users = new JSONArray();
			for(User u:LibrarySystem.getInstance().users) {
				JSONObject userdetail = new JSONObject();
				userdetail.put("name", u.getName());
				userdetail.put("dateOfBirth", u.getDateOfBirth());
				userdetail.put("address", u.getAddress());
				userdetail.put("email", u.getEmail());
				userdetail.put("number", u.getPhoneNumber());
				userdetail.put("username", u.getUsername());
				userdetail.put("password", u.getPassword());
				userdetail.put("id", u.getId());
				userdetail.put("accountType", u.getAccountType());
				JSONArray fines = new JSONArray();
				for(Fee f: u.getFines())
					fines.add(f.returnId());
				userdetail.put("fines", fines.toJSONString());
				JSONArray wishlist = new JSONArray();
				for(Media m: u.getWishlist())
					wishlist.add(m.returnId());
				userdetail.put("wishlist", wishlist.toJSONString());
				JSONArray notifications = new JSONArray();
				for(String n: u.getNotifications())
					notifications.add(n);
				userdetail.put("notifications",notifications.toJSONString());
				userdetail.put("isClosed",u.isClosed());
				JSONArray children = new JSONArray();
				for(User c: u.getChildren())
					children.add(c.getId());
				userdetail.put("children", children.toJSONString());
				JSONArray checkedout = new JSONArray();
				for(Media m: u.getCheckedOutMedia())
					checkedout.add(m.returnId());
				userdetail.put("checkedOutMedia",checkedout.toJSONString());
				JSONArray heldmedia = new JSONArray();
				for(Media m: u.getHeldMedia())
					heldmedia.add(m.returnId());
				userdetail.put("heldMedia",heldmedia.toJSONString());
				users.add(userdetail);
			}
			JSONObject allUsers = new JSONObject();
			allUsers.put("users",users);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allUsers);
			writer.write(JSON);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Book> returnInventoryBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		for(Media m:LibrarySystem.getInstance().inventory) {
			
		}
		return books;
	}
}
