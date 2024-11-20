package model;

import db.DBConnector;
import dto.Course_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Filtered_Courses {
        private Course_DTO filteredCourseChoice;
        private String inputcourse;
        public Filtered_Courses(Course_DTO filteredCourseChoice, String inputcourse)
        {
            this.filteredCourseChoice = filteredCourseChoice;
            this.inputcourse = inputcourse;
            System.out.println("From search_filter constructor input course: "+this.inputcourse);
        }
        
        public boolean noData()
        {
            if(filteredCourseChoice.getCoursemode().equals("")  && filteredCourseChoice.getCoursecertification().equals("") && 
                    filteredCourseChoice.getCourseduration().equals("") && filteredCourseChoice.getCoursefee().equals("") && 
                    filteredCourseChoice.getCourseviewers().equals(""))
            {
                return true;
            }
            return false;
        }
        
        public ArrayList getCourses()
        {
            ArrayList <Course_DTO> filteredCourses = new ArrayList<>();
            filteredCourses.clear();
            String inputcourses[] = inputcourse.split("[ ]");
             Set<String> set = new HashSet<>(Arrays.asList(inputcourses));
            inputcourses = set.toArray(new String[0]);
            for(String x: inputcourses)
                System.out.print(x+" ");
             ArrayList <String> filteredCourseIdList = new ArrayList<>();
             filteredCourseIdList.clear();
             ArrayList <String> filteredVerifiedCourseIdList = new ArrayList<>();
             filteredVerifiedCourseIdList.clear();
            for(int j=0; j<inputcourses.length; j++)
            {
                if(inputcourses[j].length()>=3)
                {
                    String query = "SELECT course_id FROM course_table WHERE (course_skill_domain LIKE '%"+inputcourses[j].trim().toUpperCase()+"%' OR course_skill_domain LIKE '%"+inputcourses[j].trim().toUpperCase().substring(0,3)+"%'"
                                                + " OR course_title LIKE '%"+inputcourses[j].trim().toUpperCase()+"%' OR course_title LIKE '%"+inputcourses[j].trim().toUpperCase().substring(0,3)+"%')";
                    if(!filteredCourseChoice.getCoursemode().equals(""))
                    {
                        query = query + " AND (course_mode = '"+filteredCourseChoice.getCoursemode().trim().toUpperCase()+"')";
                    }
                    if(!filteredCourseChoice.getCoursecertification().equals(""))
                    {
                        query = query + " AND (course_certification = '"+filteredCourseChoice.getCoursecertification().trim()+"')";
                    }
                    if(!filteredCourseChoice.getCourseviewers().equals(""))
                    {
                        query = query + " AND (course_viewers = '"+filteredCourseChoice.getCourseviewers().trim().toUpperCase()+"')";
                    }
                    if(!filteredCourseChoice.getCoursefee().equals(""))
                    {
                        if(filteredCourseChoice.getCoursefee().equalsIgnoreCase("fee1"))
                        {
                            query = query + " AND (course_annual_fee <= 500)";
                        }
                        else if(filteredCourseChoice.getCoursefee().equalsIgnoreCase("fee2"))
                        {
                            query = query + " AND (course_annual_fee <= 1500 AND course_annual_fee > 500)";
                        }
                        else if(filteredCourseChoice.getCoursefee().equalsIgnoreCase("fee3"))
                        {
                            query = query + " AND (course_annual_fee <= 2500 AND course_annual_fee > 1500)";
                        }
                        else if(filteredCourseChoice.getCoursefee().equalsIgnoreCase("fee4"))
                        {
                            query = query + " AND (course_annual_fee <= 3500 AND course_annual_fee > 2500)";
                        }
                        else if(filteredCourseChoice.getCoursefee().equalsIgnoreCase("fee5"))
                        {
                            query = query + " (AND course_annual_fee > 3500)";
                        }
                    }
                    if(!filteredCourseChoice.getCourseduration().equals(""))
                    {
                        if(filteredCourseChoice.getCourseduration().equals("1"))
                        {
                            query = query + " AND (course_duration <= '1')";
                        }
                        else if(filteredCourseChoice.getCourseduration().equals("2"))
                        {
                            query = query + " AND (course_duration <= '2' AND course_duration>'1')";
                        }
                        else if(filteredCourseChoice.getCourseduration().equals("3"))
                        {
                            query = query + " AND (course_duration <= '3' AND course_duration>'2')";
                        }
                        else if(filteredCourseChoice.getCourseduration().equals("4"))
                        {
                            query = query + " AND (course_duration <= '4' AND course_duration>'3')";
                        }
                        else if(filteredCourseChoice.getCourseduration().equals("5"))
                        {
                            query = query + " AND (course_duration <= '5' AND course_duration>'4')";
                        }
                        else
                        {
                            query = query + "AND (course_duration>'5')";
                        }
                    }
                    System.out.println(query);
                    try
                    {
                        Statement st = DBConnector.getStatement();
                        ResultSet rs = st.executeQuery(query);
                        while(rs.next())
                        {
                            filteredCourseIdList.add(rs.getString(1));
                        }
                        HashSet <String> removeDuplicates2 = new HashSet<>(filteredCourseIdList);
                        filteredCourseIdList = new ArrayList<>(removeDuplicates2);
                        
                        for(int i=0; i<filteredCourseIdList.size(); i++)
                        {
                            rs = st.executeQuery("SELECT course_id FROM trainer_course_table WHERE course_id = '"+filteredCourseIdList.get(i)+"' AND verified = '1'");
                            if(rs.next())
                            {
                                filteredVerifiedCourseIdList.add(rs.getString(1));
                            }
                        }
                        HashSet <String> removeDuplicates = new HashSet<>(filteredVerifiedCourseIdList);
                        filteredVerifiedCourseIdList = new ArrayList<>(removeDuplicates);
                        for(int i=0; i<filteredVerifiedCourseIdList.size(); i++)
                        {
                            Course_DTO filteredCourseDetails = new Course_DTO();
                            filteredCourseDetails.setCourseid(filteredVerifiedCourseIdList.get(i));
                            Course_Details_Fetcher courseDetails = new Course_Details_Fetcher(filteredCourseDetails);
                            filteredCourses.add(courseDetails.getDetails());
                        }
                    }
                    catch(SQLException e)
                    {
                        System.out.println(e);
                    }
                }
            }  
            
            return filteredCourses;
        }
}
