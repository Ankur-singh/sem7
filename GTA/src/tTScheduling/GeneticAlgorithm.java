package tTScheduling;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormatSymbols;
import java.util.*;

public class GeneticAlgorithm {	
	
	private static TimeTable GlobalBestTimetable;
	private static int min=1000;
	private static ArrayList<String>weekDayNames=new ArrayList<>();
	private static ArrayList<String>lectureTimings=new ArrayList<>();
	
	public static void populationAccepter(ArrayList<TimeTable> timeTableCollection) throws IOException{
		// randomly got population from the initialization class
		Iterator<TimeTable> timetableIterator=timeTableCollection.iterator();		
		for (Iterator<TimeTable> iterator = timeTableCollection.iterator(); iterator.hasNext();) {
			TimeTable tt = iterator.next();
				fitness(tt);			
		}		
		createWeek();
		createLectureTime();
		selection(timeTableCollection);		
	}
	
	private static void createWeek(){
		String[] weekDaysName=new DateFormatSymbols().getWeekdays();
		for (int i = 1; i < weekDaysName.length; i++) {
	        System.out.println("weekday = " + weekDaysName[i]);
	    	if (!(i == Calendar.SUNDAY)) {
	    		weekDayNames.add(weekDaysName[i]);
	    	}
	    }
	 }
	
	private static void createLectureTime(){
		for(int i=9; i<16; i++){
			//if(i!=12){
				lectureTimings.add(i+":00"+" TO "+(i+1)+":00");
			//}			
		}
	}

	public static void selection(ArrayList<TimeTable> timetables) throws IOException{
		int iterations=50;
		int i=1;
		ArrayList<TimeTable> mutants=new ArrayList<>();
		Iterator<TimeTable> ttItr=timetables.iterator();
		while(ttItr.hasNext()){
			fitness(ttItr.next());
		}
		while(iterations!=0){
			Iterator<TimeTable> timetableIterator=timetables.iterator();
		
			while (timetableIterator.hasNext()) {
				TimeTable tt = timetableIterator.next();
				int score=tt.getFittness();		
				if(score<min){
					min=score;
					GlobalBestTimetable=tt;
					//display();
					writeToExcelFile();
				}			
			}
		
			if(min==0){
				System.exit(0);
			}
			else{
				System.out.println("Iteration :"+i);
				i++;
				iterations--;			
				for (Iterator<TimeTable> iterator = timetables.iterator(); iterator.hasNext();) {
					TimeTable timetable1 = iterator.next();
					TimeTable childTimetable=crossOver(timetable1);	
					TimeTable mutant=Mutation(childTimetable);
					mutants.add(mutant);
				}			
				
				timetables.clear();			
				for (int j = 0; j < mutants.size(); j++) {
					fitness(mutants.get(j));
					timetables.add(mutants.get(j));
				}
				mutants.clear();
			}	
		}		
		//display();
	}
	
	public static void fitness(TimeTable timetable){		
		ArrayList<ClassRoom> rooms=timetable.getRoom();
		Iterator<ClassRoom> roomIterator1 = rooms.iterator();		
		while(roomIterator1.hasNext()){			
			int score=0;
			ClassRoom room1 = roomIterator1.next(); 
			Iterator<ClassRoom> roomIterator2 = rooms.iterator();
			while(roomIterator2.hasNext()){		
				ClassRoom room2 = roomIterator2.next();
				if(room2!=room1){
					ArrayList<Day> weekdays1 = room1.getWeek().getWeekDays();
					ArrayList<Day> weekdays2 = room2.getWeek().getWeekDays();
					Iterator<Day> daysIterator1=weekdays1.iterator();
					Iterator<Day> daysIterator2=weekdays2.iterator();
					while(daysIterator1.hasNext() && daysIterator2.hasNext()){
						Day day1 = daysIterator1.next();
						Day day2 = daysIterator2.next();
						ArrayList<TimeSlot> timeslots1 = day1.getTimeSlot();
						ArrayList<TimeSlot> timeslots2 = day2.getTimeSlot();
						Iterator<TimeSlot> timeslotIterator1= timeslots1.iterator();
						Iterator<TimeSlot> timeslotIterator2= timeslots2.iterator();
						while(timeslotIterator1.hasNext() && timeslotIterator2.hasNext()){
							TimeSlot lecture1=timeslotIterator1.next();
							TimeSlot lecture2=timeslotIterator2.next();							
							if(lecture1.getLecture()!=null  &&  lecture2.getLecture()!=null){
							String professorName1=lecture1.getLecture().getProfessor().getProfessorName();
							String professorName2=lecture2.getLecture().getProfessor().getProfessorName();							
							String stgrp1=lecture1.getLecture().getStudentGroup().getName();
							String stgrp2=lecture2.getLecture().getStudentGroup().getName();							
							if(stgrp1.equals(stgrp2) || professorName1.equals(professorName2)){
								score=score+1;
							}
								ArrayList<Combination> stcomb1 = lecture1.getLecture().getStudentGroup().getCombination();
								Iterator<Combination> stcombItr = stcomb1.iterator();
								while(stcombItr.hasNext()){
									if(lecture2.getLecture().getStudentGroup().getCombination().contains(stcombItr.next())){
										score = score+1;
										break;
									}
								}
							
							}
						}
					}
				}
			}
			timetable.setFittness(score);			
			}
		System.out.println("Score..................................."+timetable.getFittness());
	}

	private static TimeTable Mutation(TimeTable parentTimetable) {		
		TimeTable mutantTimeTable=parentTimetable;
		int rnd1,rnd2;
		Random randomGenerator = new Random();
		ArrayList<ClassRoom> presentClassroom=mutantTimeTable.getRoom();
		for (Iterator<ClassRoom> iterator = presentClassroom.iterator(); iterator.hasNext();) {
			ClassRoom classRoom = iterator.next();			
			rnd1=randomGenerator.nextInt(5);
				rnd2=-1;
				while(rnd1!=rnd2){
					rnd2=randomGenerator.nextInt(5);
				}
				ArrayList<Day> weekDays = classRoom.getWeek().getWeekDays();
				Day day1=weekDays.get(rnd1);
				Day day2=weekDays.get(rnd2);
				
				
				ArrayList<TimeSlot> timeSlotsOfday1=day1.getTimeSlot();
				ArrayList<TimeSlot> timeSlotsOfday2=day2.getTimeSlot();
				
				day1.setTimeSlot(timeSlotsOfday2);
				day2.setTimeSlot(timeSlotsOfday1);
				
				break;
						
		}		
		return mutantTimeTable;	
	}

	private static TimeTable crossOver(TimeTable fatherTimeTable){
		// let us say that we give father the priority to stay as the checker!
		// in the outer loop		
		Random randomGenerator = new Random();
		Iterator<ClassRoom> parentTimeTableClassRooms=fatherTimeTable.getRoom().iterator();		
		while(parentTimeTableClassRooms.hasNext()) {
			ClassRoom room = parentTimeTableClassRooms.next();
			if(!room.isLaboratory()){
				ArrayList<Day> days = room.getWeek().getWeekDays();
				int i=0;
				while(i<3){
					int rnd=randomGenerator.nextInt(5);
					Day day = days.get(rnd);
					Collections.shuffle(day.getTimeSlot());
					i++;
				}			
			}
			
		}
		return fatherTimeTable;
	}
	
	private static void writeToExcelFile() throws IOException{
		FileWriter writer = new FileWriter("timetable.csv");
		//PrintWriter pw = new PrintWriter(writer);
		int i=0;
		writer.append("\n\nMinimum : "+min);
		writer.append("\n\nScore : "+GlobalBestTimetable.getFittness());
		writer.append("\n\n (Subject#Professor#Student Group)");
			ArrayList<ClassRoom> allrooms = GlobalBestTimetable.getRoom();
			Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
			while(allroomsIterator.hasNext()){
				ClassRoom room = allroomsIterator.next();
				writer.append("\n\nRoom Number: "+room.getRoomNo());
				ArrayList<Day> weekdays = room.getWeek().getWeekDays();
				Iterator<Day> daysIterator=weekdays.iterator();
				Iterator<String> lectTimeItr = lectureTimings.iterator();
				writer.append("\n\nTimings: ,");
				while(lectTimeItr.hasNext()){
					writer.append(lectTimeItr.next()+",");
				}
				i=0;
				writer.append("\nDays\n");
				while(daysIterator.hasNext()){
					Day day = daysIterator.next();
					writer.append(/*Day: */""+weekDayNames.get(i)+",");
					ArrayList<TimeSlot> timeslots = day.getTimeSlot();
					i++;
					for(int k=0; k<timeslots.size();k++){
						if(k==3){
							writer.append("BREAK,");
						}
							TimeSlot lecture = timeslots.get(k);
							if(lecture.getLecture()!=null){
								writer.append("("+lecture.getLecture().getSubject()+"#"+lecture.getLecture().getProfessor().getProfessorName()+"#"+lecture.getLecture().getStudentGroup().getName().split("/")[0]+")"+",");
							}
						
							else{
								writer.append("FREE LECTURE,");
							}
						}
					writer.append("\n");
				}
				writer.append("\n");
			}
			writer.flush();
		    writer.close();
	}
	

	
	private static void display() {
		int i=0,j=0;
		System.out.println("Minimum : "+min);
			System.out.println("\nScore : "+GlobalBestTimetable.getFittness());
			ArrayList<ClassRoom> allrooms = GlobalBestTimetable.getRoom();
			Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
			while(allroomsIterator.hasNext()){
				ClassRoom room = allroomsIterator.next();
				System.out.println("\nRoom: "+room.getRoomNo());
				ArrayList<Day> weekdays = room.getWeek().getWeekDays();
				Iterator<Day> daysIterator=weekdays.iterator();
				Iterator<String> lectTimeItr = lectureTimings.iterator();
				System.out.print("\nTimings:    ");
				while(lectTimeItr.hasNext()){
					System.out.print(" "+lectTimeItr.next()+" ");
				}
				i=0;
				System.out.print("\n");
				while(daysIterator.hasNext()){
					Day day = daysIterator.next();
					System.out.print("Day: "+weekDayNames.get(i));
					ArrayList<TimeSlot> timeslots = day.getTimeSlot();
					i++;
					for(int k=0; k<timeslots.size();k++){
						if(k==3){
							System.out.print("       BREAK       ");
						}
							TimeSlot lecture = timeslots.get(k);
							if(lecture.getLecture()!=null){
								System.out.print("  ("+lecture.getLecture().getSubject()+"#"+lecture.getLecture().getProfessor().getProfessorName()+"#"+lecture.getLecture().getStudentGroup().getName().split("/")[0]+")");
							}
						
							else{
								System.out.print(" FREE LECTURE ");
							}
						}
					System.out.print("\n");
				}
				System.out.print("\n");
			}
	}
}
