


import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.spi.DateFormatProvider;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.DateFormatter;

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
	private static final String FEES_FILE_NAME = "fees.json";
	private static final String JSON_FOLDER_DEST = "src/JSONs/";
	
	
	private ArrayList<Integer[]> waitlists = new ArrayList<Integer[]>();
	
	public void loadDatabase() {
		loadBooks();
		loadAudioBooks();
		loadDVDs();
		loadEBooks();
		loadMagazines();
		loadUsers();
		loadFees();
		loadWaitLists();
		System.out.println("Data loaded!");
	}
	
	public void writeDatabase() {
		writeUsers();
		writeBooks();
		writeAudioBooks();
		writeDVDs();
		writeEBooks();
		writeMagazines();
		writeFees();
		System.out.println("Data saved!");
	}
	
	private void loadUsers() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.USERS_FILE_NAME;			//Create the file reader
			FileReader userReader = new FileReader(fileDest);
			//JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(userReader);		//Get the JSON object from the file
			JSONArray usersJSON = (JSONArray)jsonData.get("users");						//Get the array from the object
			for(int i=0;i < usersJSON.size();i++) {										//For each object in the array...
				JSONObject userJSON = (JSONObject)usersJSON.get(i);						//Create a new object for this object within the array
				String name = (String)userJSON.get("name");
				String dateOfBirth = (String)userJSON.get("dateOfBirth");
				String address= (String)userJSON.get("address");
				String email = (String)userJSON.get("email");
				String phoneNumber = (String)userJSON.get("number");
				String username = (String)userJSON.get("username");
				String password = (String)userJSON.get("password");
				int id = ((Long)userJSON.get("id")).intValue();
				User temp = null;														//Create a null user, and instantiate it using the method in the system.
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
				ArrayList<Fee> feeList = new ArrayList<Fee>();							//Set the fee array using the array within the object.
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void loadBooks() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.BOOKS_FILE_NAME;
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
				boolean newString = (Boolean) bookJSON.get("newRelease");
				boolean newRelease = newString;
				int copies = ((Long)bookJSON.get("copies")).intValue();
				
				//Book specific variables:
				String author = (String)bookJSON.get("author");
				String ISBN = ((String)bookJSON.get("ISBN"));
				String publisher = (String)bookJSON.get("publisher");
				
				Book loadedBook = new Book(title,genre,description,year,newRelease,copies,author,ISBN,publisher);
				
				loadMediaData(loadedBook,bookJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadAudioBooks() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.AUDIOBOOKS_FILE_NAME;
			FileReader AudioBooksReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(AudioBooksReader);
			JSONArray AudioBooksJSON = (JSONArray)jsonData.get("audiobooks");
			for(int i=0;i < AudioBooksJSON.size();i++) {
				JSONObject AudioBookJSON = (JSONObject)AudioBooksJSON.get(i);
				String title = (String)AudioBookJSON.get("title");
				String genre = (String)AudioBookJSON.get("genre");
				String description = (String)AudioBookJSON.get("description");
				String year = (String)AudioBookJSON.get("year");
				boolean newRelease = ((Boolean)AudioBookJSON.get("newRelease")).booleanValue();
				int copies = ((Long)AudioBookJSON.get("copies")).intValue();
				
				//AudioBook specific variables:
				String author = (String)AudioBookJSON.get("author");
				String narrator = (String)AudioBookJSON.get("narrator");
				
				AudioBook loadedAudioBook = new AudioBook(title,genre,description,year,newRelease,copies,author,narrator);

				loadMediaData(loadedAudioBook,AudioBookJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadDVDs() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.DVDS_FILE_NAME;
			FileReader DVDsReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(DVDsReader);
			JSONArray DVDsJSON = (JSONArray)jsonData.get("dvds");
			for(int i=0;i < DVDsJSON.size();i++) {
				JSONObject DVDJSON = (JSONObject)DVDsJSON.get(i);
				String title = (String)DVDJSON.get("title");
				String genre = (String)DVDJSON.get("genre");
				String description = (String)DVDJSON.get("description");
				String year = (String)DVDJSON.get("year");
				boolean newRelease = ((Boolean)DVDJSON.get("newRelease")).booleanValue();
				int copies = ((Long)DVDJSON.get("copies")).intValue();
				
				//DVD specific variables:
				String director = (String)DVDJSON.get("director");
				//TODO: Add actor parsing once the actor class is complete.
				
				DVD loadedDVD = new DVD(title,genre,description,year,newRelease,copies,director);

				loadMediaData(loadedDVD,DVDJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadEBooks() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.EBOOKS_FILE_NAME;
			FileReader EbooksReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(EbooksReader);
			JSONArray EbooksJSON = (JSONArray)jsonData.get("ebooks");
			for(int i=0;i < EbooksJSON.size();i++) {
				JSONObject EBookJSON = (JSONObject)EbooksJSON.get(i);
				String title = (String)EBookJSON.get("title");
				String genre = (String)EBookJSON.get("genre");
				String description = (String)EBookJSON.get("description");
				String year = (String)EBookJSON.get("year");
				boolean newRelease = ((Boolean)EBookJSON.get("newRelease")).booleanValue();
				int copies = ((Long)EBookJSON.get("copies")).intValue();
				
				//EBook specific variables:
				String author = (String)EBookJSON.get("author");
				
				EBook loadedEBook = new EBook(title,genre,description,year,newRelease,copies,author);
				
				loadMediaData(loadedEBook,EBookJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadMagazines() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.MAGAZINES_FILE_NAME;
			FileReader MagazinesReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(MagazinesReader);
			JSONArray MagazinesJSON = (JSONArray)jsonData.get("magazines");
			for(int i=0;i < MagazinesJSON.size();i++) {
				JSONObject MagazineJSON = (JSONObject)MagazinesJSON.get(i);
				String title = (String)MagazineJSON.get("title");
				String genre = (String)MagazineJSON.get("genre");
				String description = (String)MagazineJSON.get("description");
				String year = (String)MagazineJSON.get("year");
				boolean newRelease = ((Boolean)MagazineJSON.get("newRelease")).booleanValue();
				int copies = ((Long)MagazineJSON.get("copies")).intValue();
				
				//Magazine specific variables:
				String author = (String)MagazineJSON.get("author");
				int volume = ((Long)MagazineJSON.get("volume")).intValue();
				int issue = ((Long)MagazineJSON.get("issue")).intValue();
				
				Magazine loadedMagazine = new Magazine(title,genre,description,year,newRelease,copies,author,volume,issue);

				loadMediaData(loadedMagazine,MagazineJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void loadFees() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.FEES_FILE_NAME;
			FileReader FeesReader = new FileReader(fileDest);
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject)new JSONParser().parse(FeesReader);
			JSONArray FeesJSON = (JSONArray)jsonData.get("fees");
			for(int i=0;i < FeesJSON.size();i++) {
				JSONObject FeeJSON = (JSONObject)FeesJSON.get(i);
				double total = ((Double)FeeJSON.get("total")).doubleValue();
				int tempid = ((Long)FeeJSON.get("mediaID")).intValue();
				Media temp = null;
				for(Media m:LibrarySystem.getInstance().inventory)
					if(m.id == tempid)
						temp = m;
				Fee tempF = new Fee(temp);
				tempF.setTotal(total);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private void writeUsers() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.USERS_FILE_NAME;
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
	
	private void writeAudioBooks() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.AUDIOBOOKS_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray abooks = new JSONArray();
			for(AudioBook a:this.returnInventoryAudioBooks()) {
				JSONObject abookdetail = new JSONObject();
				abookdetail = addMediaDetails(abookdetail,a);

				//Audiobook-Specific stuff
				abookdetail.put("author",a.getAuthor());
				abookdetail.put("narrator",a.getNarrator());
				
				abooks.add(abookdetail);
				}
			JSONObject allAudiobooks = new JSONObject();
			allAudiobooks.put("audiobooks",abooks);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allAudiobooks);
			writer.write(JSON);
			writer.flush();
			writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void writeBooks() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.BOOKS_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray books = new JSONArray();
			for(Book a:this.returnInventoryBooks()) {
				JSONObject bookdetail = new JSONObject();
				bookdetail = addMediaDetails(bookdetail,a);

				//Book-Specific stuff
				bookdetail.put("author",a.getAuthor());
				bookdetail.put("ISBN",a.getISBN());
				bookdetail.put("publisher",a.getPublisher());
				
				books.add(bookdetail);
				}
			JSONObject allBooks = new JSONObject();
			allBooks.put("books",books);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allBooks);
			writer.write(JSON);
			writer.flush();
			writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void writeDVDs() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.DVDS_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray DVDs = new JSONArray();
			for(DVD a:this.returnInventoryDVDs()) {
				JSONObject DVDdetail = new JSONObject();
				DVDdetail = addMediaDetails(DVDdetail,a);

				//DVD-Specific stuff
				//DVDdetail.put("actors",a.getActors());
				DVDdetail.put("director",a.getDirector());
				
				DVDs.add(DVDdetail);
				}
			JSONObject allDVDs = new JSONObject();
			allDVDs.put("dvds",DVDs);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allDVDs);
			writer.write(JSON);
			writer.flush();
			writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void writeEBooks() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.EBOOKS_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray EBooks = new JSONArray();
			for(EBook a:this.returnInventoryEBooks()) {
				JSONObject EBookdetail = new JSONObject();
				EBookdetail = addMediaDetails(EBookdetail,a);

				//EBook-Specific stuff
				EBookdetail.put("author",a.getAuthor());
				
				EBooks.add(EBookdetail);
				}
			JSONObject allEBooks = new JSONObject();
			allEBooks.put("ebooks",EBooks);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allEBooks);
			writer.write(JSON);
			writer.flush();
			writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void writeMagazines() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.MAGAZINES_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray Magazines = new JSONArray();
			for(Magazine a:this.returnInventoryMagazines()) {
				JSONObject Magazinedetail = new JSONObject();
				Magazinedetail = addMediaDetails(Magazinedetail,a);

				//Magazine-Specific stuff
				Magazinedetail.put("author",a.getAuthor());
				Magazinedetail.put("volume",a.getVolume());
				Magazinedetail.put("issue",a.getIssue());
				
				Magazines.add(Magazinedetail);
				}
			JSONObject allMagazines = new JSONObject();
			allMagazines.put("magazines",Magazines);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allMagazines);
			writer.write(JSON);
			writer.flush();
			writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void writeFees() {
		try {
			String fileDest = JSON_FOLDER_DEST + JSONReader.FEES_FILE_NAME;
			FileWriter writer = new FileWriter(fileDest);
			JSONArray fees = new JSONArray();
			for(Fee a:LibrarySystem.getInstance().fees) {
				JSONObject feedetail = new JSONObject();
				feedetail.put("mediaID",a.getMedia().getId());
				feedetail.put("total",a.getTotal());
				fees.add(feedetail);
				}
			JSONObject allFees = new JSONObject();
			allFees.put("fees",fees);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = gson.toJson(allFees);
			writer.write(JSON);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Common detail method for all media
	private JSONObject addMediaDetails(JSONObject mediadetail, Media a) {
		mediadetail.put("title",a.getTitle());
		mediadetail.put("genre",a.getGenre());
		mediadetail.put("description",a.getDescription());
		mediadetail.put("year",a.getYearOfRelease());
		mediadetail.put("newRelease",a.isNewRelease());
		mediadetail.put("copies",a.getCopies());
		
		mediadetail.put("checkedOut",a.isCheckedOut());
		if(a.getLastBorrowDate() != null) {
			mediadetail.put("lastBorrowDate",DateFormat.getInstance().format(a.getLastBorrowDate()));
			mediadetail.put("lastDueDate",DateFormat.getInstance().format(a.getLastDueDate()));
		} else {
			mediadetail.put("lastBorrowDate","N/A");
			mediadetail.put("lastDueDate","N/A");
		}
		mediadetail.put("renewCount",a.getRenewCount());
		JSONArray waitlist = new JSONArray();
		for(User u:a.getWaitlist())
			waitlist.add(u.getId());
		mediadetail.put("waitlist",waitlist.toJSONString());
		return mediadetail;
	}
	
	//Private methods to access only one type of media.
	private ArrayList<Book> returnInventoryBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m instanceof Book)
				books.add((Book) m);
		}
		return books;
	}
	private ArrayList<AudioBook> returnInventoryAudioBooks() {
		ArrayList<AudioBook> AudioBooks = new ArrayList<AudioBook>();
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m instanceof AudioBook)
				AudioBooks.add((AudioBook) m);
		}
		return AudioBooks;
	}
	private ArrayList<DVD> returnInventoryDVDs() {
		ArrayList<DVD> DVDs = new ArrayList<DVD>();
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m instanceof DVD)
				DVDs.add((DVD) m);
		}
		return DVDs;
	}
	private ArrayList<EBook> returnInventoryEBooks() {
		ArrayList<EBook> EBooks = new ArrayList<EBook>();
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m instanceof EBook)
				EBooks.add((EBook) m);
		}
		return EBooks;
	}
	private ArrayList<Magazine> returnInventoryMagazines() {
		ArrayList<Magazine> Magazines = new ArrayList<Magazine>();
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m instanceof Magazine)
				Magazines.add((Magazine) m);
		}
		return Magazines;
	}
	//Loads waitlist.  Due to load order, waitlists must be loaded after users.
	private void loadWaitLists() {
		for(int i = 1;i < LibrarySystem.getInstance().inventory.size();i++) {
			if(waitlists.get(i).length != 0)
			for(int s:waitlists.get(i)) {
				ArrayList<User> waitlisttemp = new ArrayList<User>();
				for(User u:LibrarySystem.getInstance().users)
					if(u.getId() == s)
						waitlisttemp.add(u);
				LibrarySystem.getInstance().inventory.get(i).setWaitlist(waitlisttemp);
			}
		}
	}
	
	//Load common data for all media.
	private void loadMediaData(Media loadedBook,JSONObject bookJSON) {
		try { 
		loadedBook.setCheckedOut(((Boolean)bookJSON.get("checkedOut")).booleanValue());
		
		loadedBook.setRenewCount((((Long)bookJSON.get("renewCount")).intValue()));
		if(!((String)bookJSON.get("lastBorrowDate")).equals("N/A")) {
			loadedBook.setLastBorrowDate((java.util.Date)(DateFormat.getInstance().parse((String) bookJSON.get("lastBorrowDate"))));
			loadedBook.setLastDueDate((java.util.Date)(DateFormat.getInstance().parse((String) bookJSON.get("lastDueDate"))));
		}
		
		JSONArray waitlist = (JSONArray) new JSONParser().parse((String) bookJSON.get("waitlist"));
		if(waitlist.size() != 0) {
			Integer[] waitlistarray = new Integer[waitlist.size()];
			for(int i = 0;i < waitlist.size();i++) {
				waitlistarray[i] = (Integer)waitlist.get(i);
			}
			waitlists.add(waitlistarray);
		} else {
			waitlists.add(new Integer[0]);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
