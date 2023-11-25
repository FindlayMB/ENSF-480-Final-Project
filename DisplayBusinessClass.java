public class DisplayBusinessClass implements DisplayStrategy {
    private String news;
    public void update(String news) {
        this.news = news;
        display();
    }
    public void display() {
        System.out.println("Business Class: " + news);
        //TODO
    }
    
}
