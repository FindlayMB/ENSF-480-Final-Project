public class DisplayEconomyClass implements DisplayStrategy {
    private String news;
    public void update(String news) {
        this.news = news;
        display();
    }
    public void display() {
        System.out.println("Economy Class: " + news);
        //TODO
    }
        
    
}
