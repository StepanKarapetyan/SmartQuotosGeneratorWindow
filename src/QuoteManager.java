import java.util.Random;

public class QuoteManager implements QuoteManagerial {
    private final String[] QUOTES = new String[100];
    private int quotesCount = 0;
    private final Random RAND;

    private static final String[] INITIAL_QUOTES;

    static {
        INITIAL_QUOTES = new String[]{
                "Success is walking from failure to failure with no loss of enthusiasm.―\"Winston Churchill\"",
                "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe. ― \"Albert Einstein\"",
                "Success isn’t about the end result, it’s about what you learn along the way.―\"Vera Wang\"",
                "A room without books is like a body without a soul.―\"Marcus Tullius Cicero\"",
                "Success is falling nine times and getting up ten.―\"Jon Bon Jovi\"",
                "Be yourself; everyone else is already taken.―\"Oscar Wilde\"",
                "Success does not consist in never making mistakes but in never making the same one a second time.―\"George Bernard Shaw\"",
                "The only place success comes before work is in the dictionary.―\"Vince Lombardi\"",
        };
    }

    public QuoteManager() {
        RAND = new Random();

        for (String quote : INITIAL_QUOTES) {
            if (quotesCount < QUOTES.length) {
                QUOTES[quotesCount++] = quote;
            }
        }

    }

    @Override
    public void addQuote(String quote) {
        if (quotesCount < QUOTES.length) {
            QUOTES[quotesCount++] = quote;
        }
    }

    @Override
    public String showQuote() {
        if (quotesCount == 0) {
            return "No quotes to show yet";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quotesCount; i++) {
            sb.append((i+1)).append(". ").append(QUOTES[i]).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean removeQuote(int index) {
        if (index >= 0 && index < quotesCount) {
            for (int i = index; i < quotesCount - 1; i++) {
                QUOTES[i] = QUOTES[i+1];
            }
            quotesCount--;
            return true;
        }
        return false;
    }

    @Override
    public boolean editQuote(int index, String newQuote) {
        if (index >= 0 && index < quotesCount) {
            QUOTES[index] = newQuote;
            return true;
        }
        return false;
    }

    @Override
    public String randomQuote() {
        if (quotesCount == 0) {
           return "No quotes to show yet";
        }
        return QUOTES[RAND.nextInt(quotesCount)];
    }

    @Override
    public String searchQuote(String keyword) {
        if(quotesCount == 0) {
            return "No quotes to show yet";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quotesCount; i++) {
            if (QUOTES[i].toLowerCase().contains(keyword.toLowerCase())) {
                sb.append((i+1)).append(". ").append(QUOTES[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
