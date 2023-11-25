public class Date {
    private int minute;
    private int hour;
    private int day;
    private int month;
    private int year;
    public Date(int minute, int hour, int day, int month, int year) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public int getMinute() {
        return minute;
    }
    public int getHour() {
        return hour;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String toString() {
        return "Date [minute=" + minute + ", hour=" + hour + ", day=" + day + ", month=" + month + ", year=" + year
                + "]";
    }
    
    
}
