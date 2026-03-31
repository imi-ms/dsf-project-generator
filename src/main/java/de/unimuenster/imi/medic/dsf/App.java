package de.unimuenster.imi.medic.dsf;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationRole;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfVersion;
import de.unimuenster.imi.medic.dsf.utils.generator.*;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;
import de.unimuenster.imi.medic.dsf.utils.helper.InputCheckHelper;
import de.unimuenster.imi.medic.dsf.utils.helper.NetworkSelectionHelper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    private static Scene scene;
    private final Set<String> orgaIds = new HashSet<>();
    private final NetworkSelectionHelper networkSelectionHelper = new NetworkSelectionHelper();
    private final List<Character> charArray = IntStream.rangeClosed('a', 'z')
        .mapToObj(c -> (char) c)
        .collect(Collectors.toList());

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return App.stage;
    }

    public static Scene getMainScene() {
        return App.scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        drawStartDialog(stage);
    }

    private void drawStartDialog(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("DSF Initializr");
        stage.show();

        Button pathButton = (Button) scene.lookup("#pathButton");
        Label pathLabel = (Label) scene.lookup("#pathLabel");

        EventHandler<ActionEvent> fileSelect = event -> {
            try {
                File directory = new DirectoryChooser().showDialog(stage);
                pathLabel.setText(directory.getAbsolutePath());
            } catch (Exception ignore) {
            }
            event.consume();
        };
        pathButton.setOnAction(fileSelect);

        ChoiceBox<Integer> numberOfOrganizations = (ChoiceBox<Integer>) scene.lookup(
            "#numberOfOrganizations");
        ObservableList<Integer> availableNumbers = FXCollections.observableArrayList(2, 3, 4, 5);
        numberOfOrganizations.setItems(availableNumbers);
        numberOfOrganizations.setValue(2);
        setOrganizations();

        EventHandler<ActionEvent> adjustOrganizations = event -> {
            try {
                setOrganizations();
            } catch (Exception ignore) {
            }
            event.consume();
        };
        numberOfOrganizations.setOnAction(adjustOrganizations);

        EventHandler<ActionEvent> runEvent = event -> {
            try {
                runGeneration();
                setSuccessToStage();
            } catch (Exception ignore) {
                System.out.println("Fehler!");
            }
            event.consume();
        };
        Button generateButton = (Button) scene.lookup("#generateButton");
        generateButton.setOnAction(runEvent);

        EventHandler<ActionEvent> exit = event -> {
            try {
                stage.close();
            } catch (Exception ignore) {
            }
            event.consume();
        };
        ((Button) scene.lookup("#exitButton")).setOnAction(exit);
    }

    private void setOrganizations() {
        VBox orgaBox = (VBox) scene.lookup("#orgaBox");
        orgaBox.getChildren().clear();

        Integer number = getCurrentNumberOfOrganizations();

        for (int i = 0; i < number; i++) {
            HBox newOrgaBox = new HBox();

            Label label1 = new Label();
            label1.setText("Name:");

            TextField tf = new TextField();
            tf.setText("org" + charArray.get(i));
            tf.setPrefSize(250, 26);
            tf.setId("orgaName_" + i);

            Label label2 = new Label();
            label2.setText("Role:");

            ChoiceBox<DsfOrganizationRole> dsfRole = new ChoiceBox<>();

            ObservableList<DsfOrganizationRole> roles = FXCollections.observableArrayList(
                Arrays.asList(DsfOrganizationRole.values()));
            dsfRole.setItems(roles);
            dsfRole.setValue(DsfOrganizationRole.DIC);
            dsfRole.setId("orgaRole_" + i);

            Label label3 = new Label();
            label3.setText("DSF Version:");

            ChoiceBox<DsfVersion> dsfVersion = new ChoiceBox<>();

            ObservableList<DsfVersion> versions = FXCollections.observableArrayList(
                Arrays.asList(DsfVersion.values()));
            dsfVersion.setItems(versions);
            dsfVersion.setValue(DsfVersion.V_2_0_1);
            dsfVersion.setId("orgaVersion_" + i);

            ObservableList<Node> nobChildren = newOrgaBox.getChildren();
            nobChildren.add(label1);
            nobChildren.add(tf);
            nobChildren.add(label2);
            nobChildren.add(dsfRole);
            nobChildren.add(label3);
            nobChildren.add(dsfVersion);

            newOrgaBox.setSpacing(20);
            newOrgaBox.alignmentProperty().setValue(Pos.CENTER);
            newOrgaBox.setId("orga" + i);

            orgaBox.getChildren().add(newOrgaBox);
            orgaBox.setSpacing(20);
        }
    }

    private void runGeneration() {
        String title = ((TextField) scene.lookup("#projectTitle")).getText();
        String organizationName = ((TextField) scene.lookup("#orgaName")).getText();
        String targetDirectory = ((Label) scene.lookup("#pathLabel")).getText();

        Integer numberOfConfigurations = getCurrentNumberOfOrganizations();

        try {
            List<DsfOrganizationDTO> organizations = createOrganizationDTOsForNumberOfOrganizations(
                numberOfConfigurations);

            DsfProjectDTO dsfProjectDTO = new DsfProjectDTO(
                    InputCheckHelper.checkIfValidProjectName(title),
                    InputCheckHelper.checkIfDomainExists(organizationName),
                    DsfVersion.getLatestVersion(),
                    organizations,
                    targetDirectory
            );

            List<AbstractGenerator> generators = List.of(
                    new FolderGenerator(),
                    new SecretsGenerator(),
                    new DockerGenerator(),
                    new DbGenerator(),
                    new KeycloakGenerator(),
                    new ProxyGenerator(),
                    new PomGenerator(),
                    new ProcessGenerator(),
                    new DevEnvGenerator(),
                    new AllowListGenerator()
            );

            ProjectGenerator projectGenerator = new ProjectGenerator(generators);
            projectGenerator.generate(dsfProjectDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private List<DsfOrganizationDTO> createOrganizationDTOsForNumberOfOrganizations(
        Integer numberOfOrganizations) throws IOException {
        List<DsfOrganizationDTO> organizations = new ArrayList<>();
        for (int i = 0; i < numberOfOrganizations; i++) {
            String orgaName = ((TextField) scene.lookup("#orgaName_" + i)).getText();
            DsfOrganizationRole orgaRole = ((ChoiceBox<DsfOrganizationRole>) scene.lookup(
                "#orgaRole_" + i)).getValue();
            DsfVersion orgaVersion = ((ChoiceBox<DsfVersion>) scene.lookup(
                "#orgaVersion_" + i)).getValue();

            DsfOrganizationDTO dsfOrganizationDTO = new DsfOrganizationDTO.Builder()
                .name(orgaName)
                .role(orgaRole)
                .dsfVersion(orgaVersion)
                .ipConfig(networkSelectionHelper.getAndRemoveValidIp())
                .build();

            organizations.add(dsfOrganizationDTO);

        }
        return organizations;
    }

    private Integer getCurrentNumberOfOrganizations() {
        ChoiceBox<Integer> numberOfOrganizations = (ChoiceBox<Integer>) scene.lookup(
            "#numberOfOrganizations");
        return numberOfOrganizations.getValue();
    }

    private void setSuccessToStage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Success.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
    }


    private void setPrimaryStage(Stage stage) {
        App.stage = stage;
    }

    private void setPrimaryScene(Scene scene) {
        App.scene = scene;
    }


}