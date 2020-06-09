package ua.training.controller.constants;

import java.util.regex.Pattern;

public interface RegexPatternConstants {
    Pattern nameSurnamePattern = Pattern.compile("[A-Z][a-z]{2,20}");
    Pattern userNamePattern = Pattern.compile("[A-Za-z_0-9.]{2,20}");
    Pattern nameSurnameUkrPattern = Pattern.compile("[А-ЯІЩЄҐЇ][а-ящєґ'ії]{2,20}");
    Pattern userCardNumberPattern = Pattern.compile("[0-9]{8}");
}
