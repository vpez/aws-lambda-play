package org.vap.demo.rates;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Vahe Pezeshkian
 *         February 11, 2018
 */
public class RatesService {

    private static final String URL = "http://mobileapi.fcc.am/FCBank.Mobile.Api/api/PublicInfo/getrates";

    private Consumer<String> logger;

    public RatesService() {
        this(s -> {});
    }

    public RatesService(Consumer<String> logger) {
        this.logger = logger;
    }

    /**
     * Gets the value of the provided currency
     *
     * @param currency USD, EUR, GBP, RUB, GEL
     * @param type Sale or Buy
     * @return Currency value in Armenian Drams
     */
    public Double getRate(String currency, String type) {
        try {
            return filter(query(), currency, type);
        } catch (IOException e) {
            log("IOException occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, Object> query() throws IOException {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Gson gson = new Gson();
        HashMap data = gson.fromJson(new InputStreamReader(connection.getInputStream()), HashMap.class);
        log("Loaded data from URL " + URL);
        return data;
    }

    private Double filter(HashMap<String, Object> data, String currency, String type) {
        List<Map<String, Object>> rates = (List) data.get("Rates");
        return rates.stream()
                .filter(rate -> currency.equals(rate.get("Id")))
                .map(rate -> Double.parseDouble(String.valueOf(rate.get(type))))
                .findFirst()
                .orElse(Double.NaN);
    }

    private void log(String log) {
        logger.accept(log);
    }
}
