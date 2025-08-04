public interface QuoteManagerial {
    public void addQuote(String quote);

    public String showQuote();

    public boolean removeQuote(int index);

    public boolean editQuote(int index, String newQuote);

    public String randomQuote();

    public String searchQuote(String keyword);
}
