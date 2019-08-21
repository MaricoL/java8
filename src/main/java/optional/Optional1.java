package optional;

import domain.Car;
import domain.Insurance;
import domain.Person;

import java.util.Optional;

public class Optional1 {


    public static void main(String[] args) {
        Optional.ofNullable(getInsuranceByName(null)).ifPresent(System.out::println);

    }

    private static String getInsuranceByName(Person person) {
        String insuranceName = Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        return insuranceName;
    }


}
