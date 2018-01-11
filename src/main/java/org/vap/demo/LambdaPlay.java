package org.vap.demo;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class LambdaPlay implements RequestHandler<List<Person>, String> {

    private static final Person DEFAULT = new Person();
    static {
        DEFAULT.setName("Default Person");
        DEFAULT.setAge(0);
    }

    public String handleRequest(List<Person> input, Context context) {

        Person oldest = input.stream().max(Comparator.comparingInt(Person::getAge)).orElse(DEFAULT);
        Person youngest = input.stream().min(Comparator.comparingInt(Person::getAge)).orElse(DEFAULT);

        StringJoiner response = new StringJoiner(", ");
        response.add("Youngest: " + youngest.getName() + " (" + youngest.getAge() + ")");
        response.add("Oldest: " + oldest.getName() + " (" + oldest.getAge() + ")");
        return response.toString();
    }
}
