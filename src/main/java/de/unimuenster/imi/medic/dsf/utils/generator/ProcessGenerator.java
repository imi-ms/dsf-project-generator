package de.unimuenster.imi.medic.dsf.utils.generator;

import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import de.unimuenster.imi.medic.dsf.utils.generator.base.AbstractGenerator;
import java.util.*;

public class ProcessGenerator extends AbstractGenerator {

    private final String mainResourcesFolder;
    private final String metaInfFile;
    private final List<String> processBPMNFiles;
    private final List<String> processFHIRFiles;
    private final List<String> processJavaFiles;
    private final String processTestFolder;
    private final List<String> processTestJavaFiles;
    private final String processTestLogger;

    public ProcessGenerator() {
        super();
        this.mainResourcesFolder = "src/main/resources/process/main";
        this.metaInfFile = "META-INF/services/dev.dsf.bpe.v2.ProcessPluginDefinition.mustache";
        this.processBPMNFiles = List.of("resources/bpe/hello-user.bpmn.mustache",
                "resources/bpe/hello-world.bpmn.mustache");
        this.processFHIRFiles = List.of("resources/fhir/ActivityDefinition/dsf-hello-user.xml.mustache",
                "resources/fhir/ActivityDefinition/dsf-hello-world.xml.mustache",
                "resources/fhir/CodeSystem/dsf-hello-world.xml.mustache",
                "resources/fhir/Questionnaire/dsf-hello-user.xml.mustache",
                "resources/fhir/StructureDefinition/dsf-task-hello-user.xml.mustache",
                "resources/fhir/StructureDefinition/dsf-task-hello-world.xml.mustache",
                "resources/fhir/Task/dsf-task-hello-user.xml.mustache",
                "resources/fhir/Task/dsf-task-hello-world.xml.mustache",
                "resources/fhir/ValueSet/dsf-hello-world.xml.mustache");
        this.processJavaFiles = List.of("listener/ProcessPluginDeploymentListenerImpl.java.mustache",
                "service/HelloWorld.java.mustache",
                "service/LogUserTaskResponse.java.mustache",
                "spring/config/HelloWorldConfig.java.mustache",
                "HelloWorldProcessPluginDefinition.java.mustache");
        this.processTestFolder = "src/main/resources/process/test";
        this.processTestJavaFiles = List.of("bpe/HelloProcessPluginDefinitionTest.java.mustache",
                "fhir/profile/ActivityDefinitionProfileTest.java.mustache",
                "fhir/profile/TaskProfileTest.java.mustache");
        this.processTestLogger = "log4j2.xml";
    }

    @Override
    public boolean generate(DsfProjectDTO dsfProjectDTO) {
        try {
            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            this.generateTemplate(config,
                    this.mainResourcesFolder + "/resources",
                    this.metaInfFile,
                    dsfProjectDTO.getProcessBaseFolderMainResourcesName());

            for (String bpmn : this.processBPMNFiles) {
                this.generateTemplate(config,
                        this.mainResourcesFolder,
                        bpmn,
                        dsfProjectDTO.getProcessBaseFolderMainName());
            }

            for (String fhir : this.processFHIRFiles) {
                this.generateTemplate(config,
                        this.mainResourcesFolder,
                        fhir,
                        dsfProjectDTO.getProcessBaseFolderMainName());
            }

            for (String java : this.processJavaFiles) {
                this.generateTemplate(config,
                        this.mainResourcesFolder + "/java",
                        java,
                        dsfProjectDTO.getProcessBaseFolderMainName() + "/java/"
                                + dsfProjectDTO.getProcessPackageNameFolder());
            }

            for (String testJava : this.processTestJavaFiles) {
                this.generateTemplate(config,
                        this.processTestFolder + "/java",
                        testJava,
                        dsfProjectDTO.getProcessBaseFolderTestName() + "/java/"
                                + dsfProjectDTO.getProcessPackageNameFolder());
            }

            this.copyFile(this.processTestFolder + "/resources" ,
                    this.processTestLogger,
                    dsfProjectDTO.getProcessBaseFolderTestResourcesName() + "/" + this.processTestLogger);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
