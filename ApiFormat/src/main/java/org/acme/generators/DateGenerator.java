package org.acme.generators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateGenerator {
	public static String ddmmyyyygenerator() {
		LocalDate currentDate = LocalDate.now();
		Random random = new Random();
		int randomDays = random.nextInt(3 * 365) + 1;
		LocalDate pastDate = currentDate.minusDays(randomDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		String formattedDate = pastDate.format(formatter);

		return formattedDate;
    }
	
	public static String yyyymmddgenerator() {
		LocalDate currentDate = LocalDate.now();
		Random random = new Random();
		int randomDays = random.nextInt(3 * 365) + 1;
		LocalDate pastDate = currentDate.minusDays(randomDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formattedDate = pastDate.format(formatter);

		return formattedDate;
    }
	
	public static String yyyymmddthhmmssgenerator() {
		LocalDateTime currentDateTime = LocalDateTime.now();

	       
        Random random = new Random();
        int year = currentDateTime.getYear() - random.nextInt(3); 
        int month = random.nextInt(12) + 1; 
        int day = random.nextInt(28) + 1; 
        int hour = random.nextInt(24); 
        int minute = random.nextInt(60); 
        int second = random.nextInt(60); 
        
        String formattedMonth = String.format("%02d", month);
        String formattedDay = String.format("%02d", day);
        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);
        String formattedSecond = String.format("%02d", second);
        
        StringBuilder randomDate= new StringBuilder();
        
        StringBuilder randomDate2= new StringBuilder();
        
        randomDate.append(year);
        randomDate.append(formattedMonth);
        randomDate.append(formattedDay);
        randomDate.append("T");
        randomDate.append(formattedHour);
        randomDate.append(formattedMinute);
        randomDate.append(formattedSecond);
        
        randomDate2.append(year);
        randomDate2.append("-");
        randomDate2.append(formattedMonth);
        randomDate2.append("-");
        randomDate2.append(formattedDay);
        randomDate2.append(" ");
        randomDate2.append(formattedHour);
        randomDate2.append(":");
        randomDate2.append(formattedMinute);
        randomDate2.append(":");
        randomDate2.append(formattedSecond);
        
        
        return randomDate.toString()+randomDate2.toString();
    }
	
	public static String yyyymmddhhmmssgenerator() {
		LocalDateTime currentDateTime = LocalDateTime.now();

	       
        Random random = new Random();
        int year = currentDateTime.getYear() - random.nextInt(3); 
        int month = random.nextInt(12) + 1; 
        int day = random.nextInt(28) + 1; 
        int hour = random.nextInt(24); 
        int minute = random.nextInt(60); 
        int second = random.nextInt(60); 
        LocalDateTime randomDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return randomDateTime.format(formatter);
    }

    private static LocalDate generateRandomDateInRange(LocalDate lowerBound, LocalDate upperBound) {
        Random random = new Random();
        int minDay = (int) lowerBound.toEpochDay();
        int maxDay = (int) upperBound.toEpochDay();
        int randomDay = minDay + random.nextInt(maxDay - minDay + 1);
        return LocalDate.ofEpochDay(randomDay);
    }
}
