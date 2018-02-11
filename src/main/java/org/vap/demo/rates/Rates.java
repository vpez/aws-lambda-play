package org.vap.demo.rates;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Vahe Pezeshkian
 *         February 11, 2018
 */
public class Rates implements RequestHandler<RatesRequest, String> {

    private static final Function<Context, Consumer<String>> LambdaLogger = context -> s -> context.getLogger().log(s);

    @Override
    public String handleRequest(RatesRequest request, Context context) {

        // Request parameters
        String currency = request.getCurrency();
        String type = request.getType();

        // Invoke service
        Double value = new RatesService(LambdaLogger.apply(context)).getRate(currency, type);

        // Build output
        return getResponse(request, value);
    }

    private String getResponse(RatesRequest request, Double value) {
        return
            LocalDate.now() + " " +
                    request.getCurrency() + " " +
                    request.getType() + " " +
                    value;
    }
}
