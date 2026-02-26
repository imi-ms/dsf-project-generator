package de.unimuenster.imi.medic.dsf.utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import de.unimuenster.imi.medic.dsf.shared.DsfOrganizationDTO;
import de.unimuenster.imi.medic.dsf.shared.DsfProjectDTO;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProcessGenerator {

    public static boolean generateProcess(DsfProjectDTO dsfProjectDTO) {
        try {
            String processBaseFolder = dsfProjectDTO.getOutputPath() + File.separator +
                    dsfProjectDTO.getProcessFolderName();
            String processBaseFolderProcess = processBaseFolder + File.separator + "src/main";
            String processBaseFolderResources = processBaseFolderProcess + File.separator + "resources";

            String processFolderName = processBaseFolderProcess + File.separator + "java" + File.separator +
                    dsfProjectDTO.getDomainName() + File.separator + dsfProjectDTO.getDomain() + File.separator +
                    "process" + File.separator + dsfProjectDTO.getProjectName().replace("-process", "");

            File metaInf = new File("src/main/resources/process/main/META-INF/services/dev.dsf.bpe.v2.ProcessPluginDefinition");
            String metaInfStr = Files.readString(metaInf.toPath());
            String processPackage = dsfProjectDTO.getDomainName() + "." + dsfProjectDTO.getDomain() + "." +
                    "process" + "." + dsfProjectDTO.getProjectName().replace("-process", "");

            metaInfStr = metaInfStr.replace("{ processPackage }", processPackage);
            File metaInfProcess = new File(processBaseFolderResources + File.separator +
                    "META-INF/services/dev.dsf.bpe.v2.ProcessPluginDefinition");
            Files.write(metaInfProcess.toPath(),
                    metaInfStr.getBytes());

            File pluginProperties = new File("src/main/resources/process/main/plugin.properties");
            File pluginPropertiesProcess = new File(processBaseFolderResources + File.separator +
                    "plugin.properties");
            Files.copy(pluginProperties.toPath(), pluginPropertiesProcess.toPath(), REPLACE_EXISTING);

            String organizationName = dsfProjectDTO.getDomain() + "." + dsfProjectDTO.getDomainName();
            String nameOrganization = dsfProjectDTO.getDomainName() + "." + dsfProjectDTO.getDomain();
            String organizationNameConcat = dsfProjectDTO.getDomain() + dsfProjectDTO.getDomainName();

            File helloUser = new File("src/main/resources/process/main/bpe/hello-user.bpmn");
            File helloUserProcess = new File(processBaseFolderResources + File.separator +
                    "/bpe/hello-user.bpmn");
            Files.write(helloUserProcess.toPath(),
                    Files.readString(helloUser.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            File helloWorld = new File("src/main/resources/process/main/bpe/hello-world.bpmn");
            File helloWorldProcess = new File(processBaseFolderResources + File.separator +
                    "/bpe/hello-world.bpmn");
            Files.write(helloWorldProcess.toPath(),
                    Files.readString(helloWorld.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            File helloUserActivityDefinition = new File("src/main/resources/process/main/fhir/ActivityDefinition/dsf-hello-user.xml");
            File helloUserActivityDefinitionProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/ActivityDefinition/dsf-hello-user.xml");
            Files.write(helloUserActivityDefinitionProcess.toPath(),
                    Files.readString(helloUserActivityDefinition.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloWorldActivityDefinition = new File("src/main/resources/process/main/fhir/ActivityDefinition/dsf-hello-world.xml");
            File helloWorldActivityDefinitionProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/ActivityDefinition/dsf-hello-world.xml");
            Files.write(helloWorldActivityDefinitionProcess.toPath(),
                    Files.readString(helloWorldActivityDefinition.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloWorldCodeSystem = new File("src/main/resources/process/main/fhir/CodeSystem/dsf-hello-world.xml");
            File helloWorldCodeSystemProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/CodeSystem/dsf-hello-world.xml");
            Files.write(helloWorldCodeSystemProcess.toPath(),
                    Files.readString(helloWorldCodeSystem.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloUserQuestionnaire = new File("src/main/resources/process/main/fhir/Questionnaire/dsf-hello-user.xml");
            File helloUserQuestionnaireProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/Questionnaire/dsf-hello-user.xml");
            Files.write(helloUserQuestionnaireProcess.toPath(),
                    Files.readString(helloUserQuestionnaire.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloUserStructureDefinition = new File("src/main/resources/process/main/fhir/StructureDefinition/dsf-task-hello-user.xml");
            File helloUserStructureDefinitionProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/StructureDefinition/dsf-task-hello-user.xml");
            Files.write(helloUserStructureDefinitionProcess.toPath(),
                    Files.readString(helloUserStructureDefinition.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloWorldStructureDefinition = new File("src/main/resources/process/main/fhir/StructureDefinition/dsf-task-hello-world.xml");
            File helloWorldStructureDefinitionProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/StructureDefinition/dsf-task-hello-world.xml");
            Files.write(helloWorldStructureDefinitionProcess.toPath(),
                    Files.readString(helloWorldStructureDefinition.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloUserTask = new File("src/main/resources/process/main/fhir/Task/dsf-task-hello-user.xml");
            File helloUserTaskProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/Task/dsf-task-hello-user.xml");
            Files.write(helloUserTaskProcess.toPath(),
                    Files.readString(helloUserTask.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloWorldTask = new File("src/main/resources/process/main/fhir/Task/dsf-task-hello-world.xml");
            File helloWorldTaskProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/Task/dsf-task-hello-world.xml");
            Files.write(helloWorldTaskProcess.toPath(),
                    Files.readString(helloWorldTask.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File helloWorldValueSet = new File("src/main/resources/process/main/fhir/ValueSet/dsf-hello-world.xml");
            File helloWorldValueSetProcess = new File(processBaseFolderResources + File.separator +
                    "/fhir/ValueSet/dsf-hello-world.xml");
            Files.write(helloWorldValueSetProcess.toPath(),
                    Files.readString(helloWorldValueSet.toPath())
                            .replace("{ organizationName }", organizationName)
                            .replace("{ nameOrganization }", nameOrganization)
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .getBytes());

            File listenerJava = new File("src/main/resources/process/main/java/listener/ProcessPluginDeploymentListenerImpl.java");
            File listenerJavaProcess = new File(processFolderName + File.separator +
                    "listener/ProcessPluginDeploymentListenerImpl.java");
            Files.write(listenerJavaProcess.toPath(),
                    Files.readString(listenerJava.toPath())
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            File serviceOneJava = new File("src/main/resources/process/main/java/service/HelloWorld.java");
            File serviceOneJavaProcess = new File(processFolderName + File.separator +
                    "service/HelloWorld.java");
            Files.write(serviceOneJavaProcess.toPath(),
                    Files.readString(serviceOneJava.toPath())
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            File serviceTwoJava = new File("src/main/resources/process/main/java/service/LogUserTaskResponse.java");
            File serviceTwoJavaProcess = new File(processFolderName + File.separator +
                    "service/LogUserTaskResponse.java");
            Files.write(serviceTwoJavaProcess.toPath(),
                    Files.readString(serviceTwoJava.toPath())
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            File springConfigJava = new File("src/main/resources/process/main/java/spring/config/HelloWorldConfig.java");
            File springConfigJavaProcess = new File(processFolderName + File.separator +
                    "spring/config/HelloWorldConfig.java");
            Files.write(springConfigJavaProcess.toPath(),
                    Files.readString(springConfigJava.toPath())
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            File processPluginDefinitionJava = new File("src/main/resources/process/main/java/HelloWorldProcessPluginDefinition.java");
            File processPluginDefinitionProcess = new File(processFolderName + File.separator +
                    "HelloWorldProcessPluginDefinition.java");
            Files.write(processPluginDefinitionProcess.toPath(),
                    Files.readString(processPluginDefinitionJava.toPath())
                            .replace("{ organizationNameConcat }", organizationNameConcat)
                            .replace("{ packageName }", processPackage)
                            .getBytes());

            String testFolderName = processBaseFolder + File.separator + "test";
            String testPackageFolder = testFolderName + File.separator + "java" + File.separator +
                    dsfProjectDTO.getDomainName() + File.separator + dsfProjectDTO.getDomain() + File.separator +
                    "process" + File.separator + dsfProjectDTO.getProjectName()
                    .replace("-process", "");

            File testPluginDefinition = new File("src/main/resources/process/test/bpe/HelloProcessPluginDefinitionTest.java");
            File testPluginDefinitionBpe = new File(testPackageFolder + File.separator +
                    "bpe/HelloProcessPluginDefinitionTest.java");
            Files.copy(testPluginDefinition.toPath(), testPluginDefinitionBpe.toPath(), REPLACE_EXISTING);

            File testActivityDefinition = new File("src/main/resources/process/test/fhir/ActivityDefinitionProfileTest.java");
            File testActivityDefinitionFhir = new File(testPackageFolder + File.separator +
                    "fhir/profile/ActivityDefinitionProfileTest.java");
            Files.copy(testActivityDefinition.toPath(), testActivityDefinitionFhir.toPath(), REPLACE_EXISTING);

            File testTaskProfile = new File("src/main/resources/process/test/fhir/TaskProfileTest.java");
            File testTaskProfileFhir = new File(testPackageFolder + File.separator +
                    "fhir/profile/TaskProfileTest.java");
            Files.copy(testTaskProfile.toPath(), testTaskProfileFhir.toPath(), REPLACE_EXISTING);

            String testResourceFolder = testFolderName + File.separator + "resources";
            File testLogger = new File("src/main/resources/process/test/resources/log4j2.xml");
            File testLoggerResources = new File(testResourceFolder + File.separator + "log4j2.xml");
            Files.copy(testLogger.toPath(), testLoggerResources.toPath(), REPLACE_EXISTING);

            HashMap<String, Object> config = new HashMap<>();
            config.put("project", dsfProjectDTO);
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("process/main/templates/dev-setup.env.mustache");
            StringWriter writer = new StringWriter();
            mustache.execute(writer, config).flush();
            File devEnvFile = new File(processBaseFolderResources + File.separator + "templates" +
                    File.separator + "dev-setup.env");
            Files.write(devEnvFile.toPath(),
                    writer.toString().getBytes());

            mustache = mf.compile("process/main/templates/bundle.xml.mustache");
            writer = new StringWriter();
            mustache.execute(writer, config).flush();
            String allowList = writer.toString();
            String parentOrganizationId = UUID.randomUUID().toString();
            allowList = allowList.replace("{ " + dsfProjectDTO.getProjectOrganization() + "_id }", parentOrganizationId);
            for (DsfOrganizationDTO organization : dsfProjectDTO.getOrganizations()) {
                allowList = allowList.replace("{ " + organization.getName() + "_id_1 }", UUID.randomUUID().toString())
                        .replace("{ " + organization.getName() + "_id_2 }", UUID.randomUUID().toString())
                        .replace("{ " + organization.getName() + "_id_3 }", UUID.randomUUID().toString());
            }
            File bundleFile = new File(processBaseFolderResources + File.separator + "templates" +
                    File.separator + "bundle.xml");
            Files.write(bundleFile.toPath(),
                    allowList.getBytes());

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
