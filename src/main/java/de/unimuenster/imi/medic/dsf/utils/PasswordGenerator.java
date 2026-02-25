package de.unimuenster.imi.medic.dsf.utils;

import java.util.List;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.Rule;

/**
 * Generates random secrets to be used in the DSF setup
 */
public class PasswordGenerator {

    private static final Integer SECRET_LENGTH = 24;

    public PasswordGenerator() {}

    /**
     * Generates a random secret with at least two lowercase,
     * two uppercase and two number characters.
     * Secret length is static and defined by const SECRET_LENGTH
     * @return random Secret as String
     */
    public String generateSecret(Integer length) {
        org.passay.PasswordGenerator gen = new org.passay.PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        List<Rule> rules = List.of(lowerCaseRule, upperCaseRule, digitRule);

        return gen.generatePassword(SECRET_LENGTH, rules);
    }

    public String generateSecret() {
        return generateSecret(SECRET_LENGTH);
    }


}
