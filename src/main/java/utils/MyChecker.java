package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyChecker {
	public static boolean isStringValid(String str) {
		try {
			if(str.isBlank()) {
				return false;
			}else if(str.length() < 4) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isEmailValid(String email) {
		try {
			String emailRegex = "[A-Za-z0-9+_.-]+@[a-z]+\\.[a-z]{2,3}$";
			Pattern pattern = Pattern.compile(emailRegex);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isMobileNoValid(String mobNo) {
		try {
			String mobNoRegex = "[6-9][0-9]{9}";
			Pattern pattern = Pattern.compile(mobNoRegex);
			Matcher matcher = pattern.matcher(mobNo);
			return matcher.matches();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isPasswordValid(String password) {
		try {
			String passRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).{6,}$";
			Pattern pattern = Pattern.compile(passRegex);
			Matcher matcher = pattern.matcher(password);
			return matcher.matches();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isValidAge(String dob) {
		 try {
	            LocalDate today = LocalDate.now();
	            LocalDate birthday = LocalDate.parse(dob);

	            Period period = Period.between(birthday, today);

	            if(period.getYears()<18) {
	            	return false;
	            }
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	public static boolean isActive(String date) {
		if (date.isEmpty() || date.trim().equals("")) {
			return false;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date d = null;
			Date d1 = null;
			String today = (java.time.LocalDate.now()).toString();
			try {
				d = sdf.parse(date);
				d1 = sdf.parse(today);

				if (d1.compareTo(d) < 0) {
					// not expired
					return true;
				} else if (d.compareTo(d1) == 0) {
					// both date are same
					return true;

				} else {
					// expired
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

}
