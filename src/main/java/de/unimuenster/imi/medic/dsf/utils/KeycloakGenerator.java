package utils;

import shared.DsfOrganizationDTO;
import shared.DsfProjectDTO;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.UUID;

public class KeycloakGenerator {

    public static boolean generateKeycloakImport(DsfProjectDTO dsfProjectDTO) {
        try {
            File keycloakJson = new File("./src/main/resources/keycloak/keycloak.json");
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                String keycloakStr = Files.readString(keycloakJson.toPath());

                String processName = dsfProjectDTO.getProjectName().replace("_", "-");
                String organizationName = organization.getNamespace().replace("_", "-");
                String uuidOne = String.valueOf(UUID.randomUUID());
                String uuidRealmOne = String.valueOf(UUID.randomUUID());
                String uuidRealmTwo = String.valueOf(UUID.randomUUID());
                String uuidRealmThree = String.valueOf(UUID.randomUUID());
                String uuidRealmFour = String.valueOf(UUID.randomUUID());
                String uuidRealmFive = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtOne = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtTwo = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtThree = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtFour = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtFive = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtSix = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtSeven = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtEight = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtNine = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtTen = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtEleven = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtTwelve = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtThirteen = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtFourteen = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtFifteen = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtSixteen = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtSeventeen = String.valueOf(UUID.randomUUID());
                String uuidRealmMgmtEighteen = String.valueOf(UUID.randomUUID());
                String uuidContainerId = String.valueOf(UUID.randomUUID());
                String uuidBroker = String.valueOf(UUID.randomUUID());
                String uuidAccountOne = String.valueOf(UUID.randomUUID());
                String uuidAccountTwo = String.valueOf(UUID.randomUUID());
                String uuidAccountThree = String.valueOf(UUID.randomUUID());
                String uuidAccountFour = String.valueOf(UUID.randomUUID());
                String uuidAccountFive = String.valueOf(UUID.randomUUID());
                String uuidAccountSix = String.valueOf(UUID.randomUUID());
                String uuidAccountSeven = String.valueOf(UUID.randomUUID());
                String uuidAccountEight = String.valueOf(UUID.randomUUID());
                String uuidBrokerContainerId = String.valueOf(UUID.randomUUID());
                String uuidAccountContainerId = String.valueOf(UUID.randomUUID());
                String uuidUserOne = String.valueOf(UUID.randomUUID());
                String uuidUserTwo = String.valueOf(UUID.randomUUID());
                String uuidUserThree = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperOne = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwo = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThree = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFour = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFive = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperSix = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperSeven = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperEight = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperNine = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperEleven = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwelve = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirteen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFourteen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFifteen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperSixteen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperSeventeen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperEighteen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperNineteen = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwenty = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentyone = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentytwo = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentythree = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentyfour = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentyfive = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentysix = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentyseven = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentyeight = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperTwentynine = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirty = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtyone = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtytwo = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtythree = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtyfour = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtyfive = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtysix = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtyseven = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtyeight = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperThirtynine = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperForty = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFortyone = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFortytwo = String.valueOf(UUID.randomUUID());
                String uuidProtocolMapperFortythree = String.valueOf(UUID.randomUUID());
                String uuidComponentsOne = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwo = String.valueOf(UUID.randomUUID());
                String uuidComponentsThree = String.valueOf(UUID.randomUUID());
                String uuidComponentsFour = String.valueOf(UUID.randomUUID());
                String uuidComponentsFive = String.valueOf(UUID.randomUUID());
                String uuidComponentsSix = String.valueOf(UUID.randomUUID());
                String uuidComponentsSeven = String.valueOf(UUID.randomUUID());
                String uuidComponentsEight = String.valueOf(UUID.randomUUID());
                String uuidComponentsNine = String.valueOf(UUID.randomUUID());
                String uuidComponentsTen = String.valueOf(UUID.randomUUID());
                String uuidComponentsEleven = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwelve = String.valueOf(UUID.randomUUID());
                String uuidComponentsThirteen = String.valueOf(UUID.randomUUID());
                String uuidComponentsFourteen = String.valueOf(UUID.randomUUID());
                String uuidComponentsFifteen = String.valueOf(UUID.randomUUID());
                String uuidComponentsSixteen = String.valueOf(UUID.randomUUID());
                String uuidComponentsSeventeen = String.valueOf(UUID.randomUUID());
                String uuidComponentsEighteen = String.valueOf(UUID.randomUUID());
                String uuidComponentsNineteen = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwenty = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentyone = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentytwo = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentythree = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentyfour = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentyfive = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentysix = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentyseven = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentyeight = String.valueOf(UUID.randomUUID());
                String uuidComponentsTwentynine = String.valueOf(UUID.randomUUID());
                String uuidComponentsThirty = String.valueOf(UUID.randomUUID());
                String uuidComponentsThirtyone = String.valueOf(UUID.randomUUID());
                String uuidComponentsThirtytwo = String.valueOf(UUID.randomUUID());
                String uuidComponentsThirtythree = String.valueOf(UUID.randomUUID());
                String uuidComponentsThirtyfour = String.valueOf(UUID.randomUUID());
                String uuidConfigOne = String.valueOf(UUID.randomUUID());
                String uuidConfigTwo = String.valueOf(UUID.randomUUID());

                String keycloakOrganization = keycloakStr
                        .replace("\\{ processName }", processName)
                        .replace("\\{ organizationName }", organizationName)
                        .replace("\\{ uuidOne }", uuidOne)
                        .replace("\\{ uuidRealmOne }", uuidRealmOne)
                        .replace("\\{ uuidRealmTwo }", uuidRealmTwo)
                        .replace("\\{ uuidRealmThree }", uuidRealmThree)
                        .replace("\\{ uuidRealmFour }", uuidRealmFour)
                        .replace("\\{ uuidRealmFive }", uuidRealmFive)
                        .replace("\\{ uuidRealmMgmtOne }", uuidRealmMgmtOne)
                        .replace("\\{ uuidRealmMgmtTwo }", uuidRealmMgmtTwo)
                        .replace("\\{ uuidRealmMgmtThree }", uuidRealmMgmtThree)
                        .replace("\\{ uuidRealmMgmtFour }", uuidRealmMgmtFour)
                        .replace("\\{ uuidRealmMgmtFive }", uuidRealmMgmtFive)
                        .replace("\\{ uuidRealmMgmtSix }", uuidRealmMgmtSix)
                        .replace("\\{ uuidRealmMgmtSeven }", uuidRealmMgmtSeven)
                        .replace("\\{ uuidRealmMgmtEight }", uuidRealmMgmtEight)
                        .replace("\\{ uuidRealmMgmtNine }", uuidRealmMgmtNine)
                        .replace("\\{ uuidRealmMgmtTen }", uuidRealmMgmtTen)
                        .replace("\\{ uuidRealmMgmtEleven }", uuidRealmMgmtEleven)
                        .replace("\\{ uuidRealmMgmtTwelve }", uuidRealmMgmtTwelve)
                        .replace("\\{ uuidRealmMgmtThirteen }", uuidRealmMgmtThirteen)
                        .replace("\\{ uuidRealmMgmtFourteen }", uuidRealmMgmtFourteen)
                        .replace("\\{ uuidRealmMgmtFifteen }", uuidRealmMgmtFifteen)
                        .replace("\\{ uuidRealmMgmtSixteen }", uuidRealmMgmtSixteen)
                        .replace("\\{ uuidRealmMgmtSeventeen }", uuidRealmMgmtSeventeen)
                        .replace("\\{ uuidRealmMgmtEighteen }", uuidRealmMgmtEighteen)
                        .replace("\\{ uuidContainerId }", uuidContainerId)
                        .replace("\\{ uuidBroker }", uuidBroker)
                        .replace("\\{ uuidAccountOne }", uuidAccountOne)
                        .replace("\\{ uuidAccountTwo }", uuidAccountTwo)
                        .replace("\\{ uuidAccountThree }", uuidAccountThree)
                        .replace("\\{ uuidAccountFour }", uuidAccountFour)
                        .replace("\\{ uuidAccountFive }", uuidAccountFive)
                        .replace("\\{ uuidAccountSix }", uuidAccountSix)
                        .replace("\\{ uuidAccountSeven }", uuidAccountSeven)
                        .replace("\\{ uuidAccountEight }", uuidAccountEight)
                        .replace("\\{ uuidBrokerContainerId }", uuidBrokerContainerId)
                        .replace("\\{ uuidAccountContainerId }", uuidAccountContainerId)
                        .replace("\\{ uuidUserOne }", uuidUserOne)
                        .replace("\\{ uuidUserTwo }", uuidUserTwo)
                        .replace("\\{ uuidUserThree }", uuidUserThree)
                        .replace("\\{ uuidProtocolMapperOne }", uuidProtocolMapperOne)
                        .replace("\\{ uuidProtocolMapperTwo }", uuidProtocolMapperTwo)
                        .replace("\\{ uuidProtocolMapperThree }", uuidProtocolMapperThree)
                        .replace("\\{ uuidProtocolMapperFour }", uuidProtocolMapperFour)
                        .replace("\\{ uuidProtocolMapperFive }", uuidProtocolMapperFive)
                        .replace("\\{ uuidProtocolMapperSix }", uuidProtocolMapperSix)
                        .replace("\\{ uuidProtocolMapperSeven }", uuidProtocolMapperSeven)
                        .replace("\\{ uuidProtocolMapperEight }", uuidProtocolMapperEight)
                        .replace("\\{ uuidProtocolMapperNine }", uuidProtocolMapperNine)
                        .replace("\\{ uuidProtocolMapperTen }", uuidProtocolMapperTen)
                        .replace("\\{ uuidProtocolMapperEleven }", uuidProtocolMapperEleven)
                        .replace("\\{ uuidProtocolMapperTwelve }", uuidProtocolMapperTwelve)
                        .replace("\\{ uuidProtocolMapperThirteen }", uuidProtocolMapperThirteen)
                        .replace("\\{ uuidProtocolMapperFourteen }", uuidProtocolMapperFourteen)
                        .replace("\\{ uuidProtocolMapperFifteen }", uuidProtocolMapperFifteen)
                        .replace("\\{ uuidProtocolMapperSixteen }", uuidProtocolMapperSixteen)
                        .replace("\\{ uuidProtocolMapperSeventeen }", uuidProtocolMapperSeventeen)
                        .replace("\\{ uuidProtocolMapperEighteen }", uuidProtocolMapperEighteen)
                        .replace("\\{ uuidProtocolMapperNineteen }", uuidProtocolMapperNineteen)
                        .replace("\\{ uuidProtocolMapperTwenty }", uuidProtocolMapperTwenty)
                        .replace("\\{ uuidProtocolMapperTwentyone }", uuidProtocolMapperTwentyone)
                        .replace("\\{ uuidProtocolMapperTwentytwo }", uuidProtocolMapperTwentytwo)
                        .replace("\\{ uuidProtocolMapperTwentythree }", uuidProtocolMapperTwentythree)
                        .replace("\\{ uuidProtocolMapperTwentyfour }", uuidProtocolMapperTwentyfour)
                        .replace("\\{ uuidProtocolMapperTwentyfive }", uuidProtocolMapperTwentyfive)
                        .replace("\\{ uuidProtocolMapperTwentysix }", uuidProtocolMapperTwentysix)
                        .replace("\\{ uuidProtocolMapperTwentyseven }", uuidProtocolMapperTwentyseven)
                        .replace("\\{ uuidProtocolMapperTwentyeight }", uuidProtocolMapperTwentyeight)
                        .replace("\\{ uuidProtocolMapperTwentynine }", uuidProtocolMapperTwentynine)
                        .replace("\\{ uuidProtocolMapperThirty }", uuidProtocolMapperThirty)
                        .replace("\\{ uuidProtocolMapperThirtyone }", uuidProtocolMapperThirtyone)
                        .replace("\\{ uuidProtocolMapperThirtytwo }", uuidProtocolMapperThirtytwo)
                        .replace("\\{ uuidProtocolMapperThirtythree }", uuidProtocolMapperThirtythree)
                        .replace("\\{ uuidProtocolMapperThirtyfour }", uuidProtocolMapperThirtyfour)
                        .replace("\\{ uuidProtocolMapperThirtyfive }", uuidProtocolMapperThirtyfive)
                        .replace("\\{ uuidProtocolMapperThirtysix }", uuidProtocolMapperThirtysix)
                        .replace("\\{ uuidProtocolMapperThirtyseven }", uuidProtocolMapperThirtyseven)
                        .replace("\\{ uuidProtocolMapperThirtyeight }", uuidProtocolMapperThirtyeight)
                        .replace("\\{ uuidProtocolMapperThirtynine }", uuidProtocolMapperThirtynine)
                        .replace("\\{ uuidProtocolMapperForty }", uuidProtocolMapperForty)
                        .replace("\\{ uuidProtocolMapperFortyone }", uuidProtocolMapperFortyone)
                        .replace("\\{ uuidProtocolMapperFortytwo }", uuidProtocolMapperFortytwo)
                        .replace("\\{ uuidProtocolMapperFortythree }", uuidProtocolMapperFortythree)
                        .replace("\\{ uuidComponentsOne }", uuidComponentsOne)
                        .replace("\\{ uuidComponentsTwo }", uuidComponentsTwo)
                        .replace("\\{ uuidComponentsThree }", uuidComponentsThree)
                        .replace("\\{ uuidComponentsFour }", uuidComponentsFour)
                        .replace("\\{ uuidComponentsFive }", uuidComponentsFive)
                        .replace("\\{ uuidComponentsSix }", uuidComponentsSix)
                        .replace("\\{ uuidComponentsSeven }", uuidComponentsSeven)
                        .replace("\\{ uuidComponentsEight }", uuidComponentsEight)
                        .replace("\\{ uuidComponentsNine }", uuidComponentsNine)
                        .replace("\\{ uuidComponentsTen }", uuidComponentsTen)
                        .replace("\\{ uuidComponentsEleven }", uuidComponentsEleven)
                        .replace("\\{ uuidComponentsTwelve }", uuidComponentsTwelve)
                        .replace("\\{ uuidComponentsThirteen }", uuidComponentsThirteen)
                        .replace("\\{ uuidComponentsFourteen }", uuidComponentsFourteen)
                        .replace("\\{ uuidComponentsFifteen }", uuidComponentsFifteen)
                        .replace("\\{ uuidComponentsSixteen }", uuidComponentsSixteen)
                        .replace("\\{ uuidComponentsSeventeen }", uuidComponentsSeventeen)
                        .replace("\\{ uuidComponentsEighteen }", uuidComponentsEighteen)
                        .replace("\\{ uuidComponentsNineteen }", uuidComponentsNineteen)
                        .replace("\\{ uuidComponentsTwenty }", uuidComponentsTwenty)
                        .replace("\\{ uuidComponentsTwentyone }", uuidComponentsTwentyone)
                        .replace("\\{ uuidComponentsTwentytwo }", uuidComponentsTwentytwo)
                        .replace("\\{ uuidComponentsTwentythree }", uuidComponentsTwentythree)
                        .replace("\\{ uuidComponentsTwentyfour }", uuidComponentsTwentyfour)
                        .replace("\\{ uuidComponentsTwentyfive }", uuidComponentsTwentyfive)
                        .replace("\\{ uuidComponentsTwentysix }", uuidComponentsTwentysix)
                        .replace("\\{ uuidComponentsTwentyseven }", uuidComponentsTwentyseven)
                        .replace("\\{ uuidComponentsTwentyeight }", uuidComponentsTwentyeight)
                        .replace("\\{ uuidComponentsTwentynine }", uuidComponentsTwentynine)
                        .replace("\\{ uuidComponentsThirty }", uuidComponentsThirty)
                        .replace("\\{ uuidComponentsThirtyone }", uuidComponentsThirtyone)
                        .replace("\\{ uuidComponentsThirtytwo }", uuidComponentsThirtytwo)
                        .replace("\\{ uuidComponentsThirtythree }", uuidComponentsThirtythree)
                        .replace("\\{ uuidComponentsThirtyfour }", uuidComponentsThirtyfour)
                        .replace("\\{ uuidConfigOne }", uuidConfigOne)
                        .replace("\\{ uuidConfigTwo }", uuidConfigTwo);

                File keycloakOrganizationFile = new File(dsfProjectDTO.getOutputPath() + File.separator +
                        "dev-setup/keycloak/import/"  + organizationName + ".json");
                Files.write(keycloakOrganizationFile.toPath(),
                        Collections.singleton(keycloakOrganization),
                        StandardCharsets.UTF_8);
            }


        } catch (Exception e) {
            return false;
        }



        return true;
    }
}
